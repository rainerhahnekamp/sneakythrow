package com.hahnekamp.sneakythrow;

public class SneakyException extends RuntimeException {
  public SneakyException(Exception exception) {
   super(exception);
  }
}
