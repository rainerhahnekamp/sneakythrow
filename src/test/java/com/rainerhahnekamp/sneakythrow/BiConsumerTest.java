package com.rainerhahnekamp.sneakythrow;

import static com.rainerhahnekamp.sneakythrow.Sneaky.sneaked;
import static com.rainerhahnekamp.sneakythrow.TestHelper.assertThrowsWithCause;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

import org.junit.jupiter.api.Test;

public class BiConsumerTest {
  @Test
  public void withoutException() {
    executeAndAssert(
        sneaked((List<Integer> l, Integer e) -> {
          l.add(e);
        })
    );
  }

  @Test
  public void withException() {
    List<Integer> list = Collections.emptyList();
    assertThrowsWithCause(
        ArithmeticException.class,
        () -> executeAndAssert(
            sneaked((List<Integer> l, Integer e) -> {
              list.add(e / 0);
            })
        )
    );
  }

  private void executeAndAssert(BiConsumer<List<Integer>, Integer> consumer) {
    List<Integer> list = new ArrayList<>();
    consumer.accept(list, 5);
    assertEquals(1, list.size());
  }
}
