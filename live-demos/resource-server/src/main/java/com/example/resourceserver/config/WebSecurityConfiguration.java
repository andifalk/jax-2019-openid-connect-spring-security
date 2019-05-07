package com.example.resourceserver.config;

import com.example.resourceserver.oidc.AudienceValidator;
import com.example.resourceserver.oidc.LocalUser;
import com.example.resourceserver.oidc.LocalUserDetailsService;
import com.example.resourceserver.oidc.LocalUserJwtConverter;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoderJwkSupport;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final OAuth2ResourceServerProperties oAuth2ResourceServerProperties;

  public WebSecurityConfiguration(OAuth2ResourceServerProperties oAuth2ResourceServerProperties) {
    this.oAuth2ResourceServerProperties = oAuth2ResourceServerProperties;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.oauth2ResourceServer().jwt();

    http.authorizeRequests().anyRequest().fullyAuthenticated();
  }

  @Bean
  JwtDecoder jwtDecoder() {
    NimbusJwtDecoderJwkSupport jwtDecoder =
        (NimbusJwtDecoderJwkSupport)
            JwtDecoders.fromOidcIssuerLocation(
                oAuth2ResourceServerProperties.getJwt().getIssuerUri());

    OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator();
    OAuth2TokenValidator<Jwt> withIssuer =
        JwtValidators.createDefaultWithIssuer(
            oAuth2ResourceServerProperties.getJwt().getIssuerUri());
    OAuth2TokenValidator<Jwt> withAudience =
        new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

    jwtDecoder.setJwtValidator(withAudience);

    return jwtDecoder;
  }

  @Bean
  LocalUserJwtConverter localUserJwtConverter() {
    return new LocalUserJwtConverter(userDetailsService());
  }

  @Bean
  protected UserDetailsService userDetailsService() {
    return new LocalUserDetailsService(
        new LocalUser("Bruce", "Wayne", "bruce.wayne@example.com"),
        new LocalUser("Clark", "Kent", "clark.kent@example.com"));
  }
}
