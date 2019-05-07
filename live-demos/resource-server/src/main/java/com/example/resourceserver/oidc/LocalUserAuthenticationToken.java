package com.example.resourceserver.oidc;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LocalUserAuthenticationToken extends AbstractAuthenticationToken {

  private LocalUser localUser;

  /**
   * Creates a token with the supplied array of authorities.
   *
   * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal represented
   *     by this authentication object.
   */
  public LocalUserAuthenticationToken(
      LocalUser localUser, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.localUser = localUser;
  }

  @Override
  public Object getCredentials() {
    return "n/a";
  }

  @Override
  public Object getPrincipal() {
    return localUser;
  }
}
