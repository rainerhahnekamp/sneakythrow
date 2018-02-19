package com.hahnekamp.sneakythrow;

import static com.hahnekamp.sneakythrow.Sneaky.sneaked;
import static com.hahnekamp.sneakythrow.TestHelper.assertThrowsWithCause;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RunnableTest {
  @Test
  public void withoutException() {
    List<Integer> list = new ArrayList<Integer>();
    Runnable runnable = sneaked(() -> {
      list.add(5);
    });
    runnable.run();

    assertEquals(1, list.size());
  }

  @Test
  public void withException() {
    List<Integer> list = Collections.emptyList();
    Runnable runnable = sneaked(() -> {
      list.add(5);
    });

    assertThrowsWithCause(UnsupportedOperationException.class, runnable);
  }
}
