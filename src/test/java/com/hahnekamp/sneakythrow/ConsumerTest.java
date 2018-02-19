package com.hahnekamp.sneakythrow;

import static com.hahnekamp.sneakythrow.Sneaky.sneaked;
import static com.hahnekamp.sneakythrow.TestHelper.assertThrowsWithCause;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

public class ConsumerTest {
  @Test
  public void withoutException() {
    Consumer<List<Integer>> consumer = sneaked(
        (List<Integer> list) -> {
          list.add(5);
        }
    );

    List<Integer> list = new ArrayList<>();
    consumer.accept(list);

    assertEquals(1, list.size());
  }

  @Test
  public void withException() {
    Consumer<List<Integer>> consumer = sneaked(
        (List<Integer> list) -> {
          list.add(5);
        }
    );

    assertThrowsWithCause(
        UnsupportedOperationException.class,
        () -> consumer.accept(Collections.emptyList())
    );
  }
}

