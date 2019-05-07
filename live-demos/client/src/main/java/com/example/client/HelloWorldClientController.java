package com.example.client;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class HelloWorldClientController {

  private final WebClient webClient;

  public HelloWorldClientController(WebClient webClient) {
    this.webClient = webClient;
  }

  @GetMapping("/")
  String index(
      @RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient) {
    return "index";
  }

  @GetMapping("/helloworld-user")
  Mono<String> hellowWorldRequestUser(Model model) {
    return webClient
        .get()
        .uri("http://localhost:9091/hello-server/hello-user")
        .retrieve()
        .onStatus(
            s -> s.equals(HttpStatus.FORBIDDEN),
            c -> Mono.just(new AccessDeniedException("You are not authorized!!")))
        .onStatus(
            HttpStatus::is5xxServerError,
            c -> Mono.just(new IllegalStateException(c.statusCode().getReasonPhrase())))
        .bodyToMono(String.class)
        .map(
            s -> {
              model.addAttribute("response", s);
              return "response";
            });
  }

  @GetMapping("/helloworld-admin")
  Mono<String> hellowWorldRequestAdmin(Model model) {
    return webClient
        .get()
        .uri("http://localhost:9091/hello-server/hello-admin")
        .retrieve()
        .onStatus(
            s -> s.equals(HttpStatus.FORBIDDEN),
            c -> Mono.just(new AccessDeniedException("You are not authorized!!")))
        .onStatus(
            HttpStatus::is5xxServerError,
            c -> Mono.just(new IllegalStateException(c.statusCode().getReasonPhrase())))
        .bodyToMono(String.class)
        .map(
            s -> {
              model.addAttribute("response", s);
              return "response";
            });
  }
}
