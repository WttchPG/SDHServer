package com.wttch.sdh.sdhserver.config;

import com.wttch.sdh.sdhserver.entity.vo.APIResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

  public ExceptionHandlers() {
    System.out.println("");
  }

  @ExceptionHandler(AccountExpiredException.class)
  public APIResponse<String> handleAuthenticationException(AccountExpiredException e) {
    return APIResponse.error(401, e.getLocalizedMessage());
  }
}
