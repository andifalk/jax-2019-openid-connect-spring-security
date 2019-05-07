package com.example.resourceserver.oidc;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

  private OAuth2Error error =
      new OAuth2Error("invalid_token", "The required audience is missing", null);

  public OAuth2TokenValidatorResult validate(Jwt jwt) {
    if (jwt.getAudience().contains("hello-world")) {
      return OAuth2TokenValidatorResult.success();
    } else {
      return OAuth2TokenValidatorResult.failure(error);
    }
  }
}
