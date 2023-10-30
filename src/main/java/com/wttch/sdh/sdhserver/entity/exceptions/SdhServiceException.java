package com.wttch.sdh.sdhserver.entity.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class SdhServiceException extends RuntimeException {
  private final int code;
  private final String message;
}
