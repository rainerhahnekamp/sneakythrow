package com.hahnekamp.sneakythrow;

import static com.hahnekamp.sneakythrow.Sneaky.sneaked;
import static com.hahnekamp.sneakythrow.TestHelper.assertThrowsWithCause;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.BinaryOperator;

import org.junit.jupiter.api.Test;

public class BinaryOperatorTest {
  @Test
  public void withoutException() {
    assertEquals(5, execute(
        sneaked((Integer a, Integer b) -> a / b),
        25, 5
    ));
  }

  @Test
  public void withException() {
    assertThrowsWithCause(
        ArithmeticException.class,
        () -> execute(
            sneaked((Integer a, Integer b) -> a / b),
            25, 0
        )
    );
  }

  private int execute(
      BinaryOperator<Integer> binaryOperator,
      Integer a, Integer b) {
    return binaryOperator.apply(a, b);
  }
}
