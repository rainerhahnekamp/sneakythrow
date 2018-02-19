package com.hahnekamp.sneakythrow;

import static com.hahnekamp.sneakythrow.Sneaky.sneak;
import static com.hahnekamp.sneakythrow.Sneaky.sneaked;
import static com.hahnekamp.sneakythrow.TestHelper.assertThrowsWithCause;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class FunctionTest {
  @Test
  public void withoutException() {
    Function<String, Integer> function =
        sneaked((String b) -> parseInt(b) * 2);
    assertEquals(10, (int)function.apply("5"));
  }

  @Test
  public void withException() {
    Function<String, Integer> function =
        sneaked((String b) -> parseInt(b) * 2);

    assertThrowsWithCause(
        NumberFormatException.class,
        () -> function.apply("foo")
    );
  }
}
