package com.example.client;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(AccessDeniedException.class)
  public String handle(AccessDeniedException ex) {
    return "access_denied";
  }

  @ExceptionHandler(Exception.class)
  public String handle(Exception ex, Model model) {
    model.addAttribute("error", ex.getMessage());
    return "server_error";
  }
}
