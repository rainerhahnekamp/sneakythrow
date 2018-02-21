package com.rainerhahnekamp.sneakythrow;

import static com.rainerhahnekamp.sneakythrow.Sneaky.sneaked;
import static com.rainerhahnekamp.sneakythrow.TestHelper.assertThrowsWithCause;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

public class BiFunctionTest {
  @Test
  public void withoutException() {
    assertEquals(500, execute(
        sneaked((Integer a, String b) -> parseInt(b) * a),
        5, "100"
    ));
  }

  @Test
  public void withException() {
    assertThrowsWithCause(
        NumberFormatException.class,
        () -> execute(
            sneaked((Integer a, String b) -> parseInt(b) * a),
            5, "foo"
        )
    );
  }

  private int execute(
      BiFunction<Integer, String, Integer> biFunction,
      Integer a, String b) {
    return biFunction.apply(a, b);
  }
}
