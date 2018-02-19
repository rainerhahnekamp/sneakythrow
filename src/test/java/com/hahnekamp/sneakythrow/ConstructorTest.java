package com.hahnekamp.sneakythrow;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ConstructorTest {
  @Test
  public void newInstance() {
    assertTrue(new Sneaky() instanceof Sneaky);
  }
}
