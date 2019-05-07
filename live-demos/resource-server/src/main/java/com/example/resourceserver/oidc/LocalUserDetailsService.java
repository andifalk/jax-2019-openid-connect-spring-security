package com.example.resourceserver.oidc;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LocalUserDetailsService implements UserDetailsService {

  private final Map<String, UserDetails> users = new HashMap<>();

  public LocalUserDetailsService(LocalUser... localusers) {
    Arrays.stream(localusers).forEach(u -> users.put(u.getEmail(), u));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return users.get(username);
  }
}
