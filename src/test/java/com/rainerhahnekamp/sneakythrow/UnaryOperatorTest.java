package com.rainerhahnekamp.sneakythrow;

import static com.rainerhahnekamp.sneakythrow.Sneaky.sneaked;
import static com.rainerhahnekamp.sneakythrow.TestHelper.assertThrowsWithCause;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Test;

public class UnaryOperatorTest {
  @Test
  public void withoutException() {
    UnaryOperator<String> unaryOperator =
        sneaked((String name) -> "Hello " + name);
    assertEquals("Hello Sneaky", unaryOperator.apply("Sneaky"));
  }

  @Test
  public void withException() {
    UnaryOperator<String> unaryOperator = sneaked((String name) -> {
      if (name == null) {
        throw new NullPointerException();
      }

      return "Hello " + name;
    });

    assertThrowsWithCause(
        NullPointerException.class,
        () -> unaryOperator.apply(null)
    );
  }
}
