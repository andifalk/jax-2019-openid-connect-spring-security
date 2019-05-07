package com.example.resourceserver.oidc;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LocalUserJwtConverter implements Converter<Jwt, LocalUserAuthenticationToken> {

  private final UserDetailsService userDetailsService;

  public LocalUserJwtConverter(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  public LocalUserAuthenticationToken convert(Jwt jwt) {
    UserDetails user = userDetailsService.loadUserByUsername(
            jwt.getClaimAsString("email"));
    if (user != null) {
      String authoritiesString =
          Arrays.stream(jwt.getClaimAsString("scope").split(" "))
              .map(sc -> "SCOPE_" + sc)
              .collect(Collectors.joining(","));
      LocalUserAuthenticationToken localUserAuthenticationToken =
          new LocalUserAuthenticationToken(
              (LocalUser) user,
              AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesString));
      localUserAuthenticationToken.setAuthenticated(true);
      return localUserAuthenticationToken;
    } else {
      throw new UsernameNotFoundException("No user found for "
              + jwt.getClaimAsString("email"));
    }
  }
}
