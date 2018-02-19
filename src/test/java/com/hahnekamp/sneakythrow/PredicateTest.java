package com.hahnekamp.sneakythrow;

import static com.hahnekamp.sneakythrow.Sneaky.sneaked;
import static com.hahnekamp.sneakythrow.TestHelper.assertThrowsWithCause;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class PredicateTest {
  @Test
  public void withoutException() {
    Predicate<String> predicate =
        sneaked((String a) -> 2 == parseInt(a));

    assertTrue(predicate.test("2"));
  }

  @Test
  public void withException() {
    Predicate<String> predicate =
        sneaked((String a) -> 2 == parseInt(a));

    assertThrowsWithCause(
        NumberFormatException.class,
        () -> predicate.test("foo")
    );
  }
}
