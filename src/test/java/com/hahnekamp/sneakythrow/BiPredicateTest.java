package com.hahnekamp.sneakythrow;

import static com.hahnekamp.sneakythrow.Sneaky.sneaked;
import static com.hahnekamp.sneakythrow.TestHelper.assertThrowsWithCause;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BiPredicate;

import org.junit.jupiter.api.Test;

public class BiPredicateTest {
  @Test
  public void withoutException() {
    assertTrue(execute(
        sneaked((Integer a, String b) -> a == parseInt(b))
    ));
  }

  @Test
  public void withException() {
    assertThrowsWithCause(
        ArithmeticException.class,
        () -> execute(
            sneaked((Integer a, String b) -> (a / 0) == parseInt(b))
        )
    );
  }

  private boolean execute(BiPredicate<Integer, String> biPredicate) {
    return biPredicate.test(2, "2");
  }
}
