package com.example.resourceserver.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

  @PreAuthorize("hasAnyAuthority('SCOPE_user', 'SCOPE_admin')")
  @GetMapping("/hello-user")
  // String helloUser(@AuthenticationPrincipal(errorOnInvalidType = true) LocalUser principal) {
  String helloUser(
      @AuthenticationPrincipal(errorOnInvalidType = true) JwtAuthenticationToken principal) {
    return "Hello Mr. '" + principal.getTokenAttributes().get("given_name") + "'";
    // return "Hello Mr. '" + principal.getLastName() + "'";
  }

  @PreAuthorize("hasAuthority('SCOPE_admin')")
  @GetMapping("/hello-admin")
  // String helloAdmin(@AuthenticationPrincipal(errorOnInvalidType = true) LocalUser principal) {
  String helloAdmin(
      @AuthenticationPrincipal(errorOnInvalidType = true) JwtAuthenticationToken principal) {
    return "Hello Mr. '" + principal.getTokenAttributes().get("given_name")
            + "' as Administrator";
    //  return "Hello Mr. '" + principal.getLastName() + "' as Administrator";
  }
}
