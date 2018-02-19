package com.hahnekamp.sneakythrow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.Supplier;

import org.opentest4j.AssertionFailedError;

public class TestHelper {
  /**
   * This is a helper class, that executes a sneaked lambda and
   * checks, if it fails and if the exception is the right one.
   */
  public static void assertThrowsWithCause(Class<? extends Throwable> clazz, Runnable runnable) {
    try {
      runnable.run();
      throw new AssertionFailedError("no exception has been thrown");
    } catch (SneakyException sneakyException) {
      assertEquals(clazz, sneakyException.getCause().getClass());
    }
  }
}
