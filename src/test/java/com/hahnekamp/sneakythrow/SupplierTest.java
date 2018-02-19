package com.hahnekamp.sneakythrow;

import static com.hahnekamp.sneakythrow.Sneaky.sneaked;
import static com.hahnekamp.sneakythrow.TestHelper.assertThrowsWithCause;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

public class SupplierTest {
  @Test
  public void withoutException() {
    Supplier<Integer> supplier = sneaked(() -> 5);
    assertEquals(5, (int)supplier.get());
  }

  @Test
  public void withException() {
    Supplier<Integer> supplier = sneaked(() -> 5 / 0);
    assertThrowsWithCause(
        ArithmeticException.class,
        () -> supplier.get()
    );
  }
}
