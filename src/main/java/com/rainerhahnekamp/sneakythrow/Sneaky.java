/*
 * MIT License
 *
 * Copyright (c) 2018-present, Rainer Hahnekamp
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.rainerhahnekamp.sneakythrow;

import com.rainerhahnekamp.sneakythrow.functional.SneakyBiConsumer;
import com.rainerhahnekamp.sneakythrow.functional.SneakyBiFunction;
import com.rainerhahnekamp.sneakythrow.functional.SneakyBiPredicate;
import com.rainerhahnekamp.sneakythrow.functional.SneakyBinaryOperator;
import com.rainerhahnekamp.sneakythrow.functional.SneakyConsumer;
import com.rainerhahnekamp.sneakythrow.functional.SneakyFunction;
import com.rainerhahnekamp.sneakythrow.functional.SneakyPredicate;
import com.rainerhahnekamp.sneakythrow.functional.SneakyRunnable;
import com.rainerhahnekamp.sneakythrow.functional.SneakySupplier;
import com.rainerhahnekamp.sneakythrow.functional.SneakyUnaryOperator;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Code that throws checked exceptions can be executed with the static methods of this
 * class without catching or throwing them.
 *
 * <p>A function or lambda that throws an exception can be executed via
 * {@link #sneak(SneakySupplier)} method. For example:<pre>
 *     URL austriaInfo = sneak(() -&gt; new URL("https://www.austria.info");}</pre>
 *
 * <p>If a function should only be wrapped, then the {@link #sneaked(SneakyRunnable)} &amp;
 * overloads can be used:<pre>
 *     public void createUrls() {
 *         Stream.of("https://www.austria.info")
 *             .map(sneaked(this::toURL))
 *             .collect(Collectors.toList());
 *     }
 *
 *     private URL toURL(String url) throws MalformedURLException {
 *         return new URL(url);
 *     }
 * </pre>
 *
 * @author Rainer Hahnekamp {@literal <rainer.hahnekamp@gmail.com>}
 */
public class Sneaky {
  public static <T> T sneak(SneakySupplier<T> supplier) {
    return sneaked(supplier).get();
  }

  /**
   * Sneaky throws a BiConsumer lambda.
   * @param biConsumer BiConsumer that can throw an exception
   * @param <T> type of first argument
   * @param <U> type of the second argument
   * @return a BiConsumer as defined in java.util.function
   */
  public static <T, U> BiConsumer<T, U> sneaked(SneakyBiConsumer<T, U> biConsumer) {
    return (t, u) -> {
      try {
        biConsumer.accept(t, u);
      } catch (Exception exception) {
        throwUnchecked(exception);
      }
    };
  }

  /**
   * Sneaky throws a BiFunction lambda.
   * @param biFunction BiFunction that can throw an exception
   * @param <T> type of first argument
   * @param <U> type of second argument
   * @param <R> return type of biFunction
   * @return a BiFunction as defined in java.util.function
   */
  public static <T, U, R> BiFunction<T, U, R> sneaked(SneakyBiFunction<T, U, R> biFunction) {
    return (t, u) -> {
      try {
        return biFunction.apply(t, u);
      } catch (Exception exception) {
        throwUnchecked(exception);
        return null;
      }
    };
  }

  /**
   * Sneaky throws a BinaryOperator lambda.
   * @param binaryOperator BinaryOperator that can throw an exception
   * @param <T> type of the two arguments and the return type of the binaryOperator
   * @return a BinaryOperator as defined in java.util.function
   */
  public static <T> BinaryOperator<T> sneaked(SneakyBinaryOperator<T> binaryOperator) {
    return (t1, t2) -> {
      try {
        return binaryOperator.apply(t1, t2);
      } catch (Exception exception) {
        throwUnchecked(exception);
        return null;
      }
    };
  }

  /**
   * Sneaky throws a BiPredicate lambda.
   * @param biPredicate BiPredicate that can throw an exception
   * @param <T> type of first argument
   * @param <U> type of second argument
   * @return a BiPredicate as defined in java.util.function
   */
  public static <T, U> BiPredicate<T, U> sneaked(
      SneakyBiPredicate<T, U> biPredicate) {
    return (t, u) -> {
      try {
        return biPredicate.test(t, u);
      } catch (Exception exception) {
        throwUnchecked(exception);
        return false;
      }
    };
  }

  /**
   * Sneaky throws a Consumer lambda.
   * @param consumer Consumer that can throw an exception
   * @param <T> type of first argument
   * @return a Consumer as defined in java.util.function
   */
  public static <T> Consumer<T> sneaked(SneakyConsumer<T> consumer) {
    return t -> {
      try {
        consumer.accept(t);
      } catch (Exception exception) {
        throwUnchecked(exception);
      }
    };
  }

  /**
   * Sneaky throws a Function lambda.
   * @param function Function that can throw an exception
   * @param <T> type of first argument
   * @param <R> type of the second argument
   * @return a Function as defined in java.util.function
   */
  public static <T, R> Function<T, R> sneaked(SneakyFunction<T, R> function) {
    return t -> {
      try {
        return function.apply(t);
      } catch (Exception exception) {
        throwUnchecked(exception);
        return null;
      }
    };
  }

  /**
   * Sneaky throws a Predicate lambda.
   * @param predicate Predicate that can throw an exception
   * @param <T> type of first argument
   * @return a Predicate as defined in java.util.function
   */
  public static <T> Predicate<T> sneaked(SneakyPredicate<T> predicate) {
    return t -> {
      try {
        return predicate.test(t);
      } catch (Exception exception) {
        throwUnchecked(exception);
        return false;
      }
    };
  }

  /**
   * Sneaky throws a Runnable lambda.
   * @param runnable Runnable that can throw an exception
   * @return a Runnable as defined in java.util.function
   */
  public static Runnable sneaked(SneakyRunnable runnable) {
    return () -> {
      try {
        runnable.run();
      } catch (Exception exception) {
        throwUnchecked(exception);
      }
    };
  }

  /**
   * Sneaky throws a Supplier lambda.
   * @param supplier Supplier that can throw an exception
   * @param <T> type of supplier's return value
   * @return a Supplier as defined in java.util.function
   */
  public static <T> Supplier<T> sneaked(SneakySupplier<T> supplier) {
    return () -> {
      try {
        return supplier.get();
      } catch (Exception exception) {
        throwUnchecked(exception);
        return null;
      }
    };
  }

  /**
   * Sneaky throws a UnaryOperator lambda.
   * @param unaryOperator UnaryOperator that can throw an exception
   * @param <T> type of unaryOperator's argument and returned value
   * @return a UnaryOperator as defined in java.util.function
   */
  public static <T> UnaryOperator<T> sneaked(SneakyUnaryOperator<T> unaryOperator) {
    return t -> {
      try {
        return unaryOperator.apply(t);
      } catch (Exception exception) {
        throwUnchecked(exception);
        return null;
      }
    };
  }

  /**
   * as described in https://stackoverflow.com/questions/31316581/a-peculiar-feature-of-exception-type-inference-in-java-8.
   */
  @SuppressWarnings("unchecked")
  private static <T extends Throwable> void throwUnchecked(Throwable t) throws T {
    throw (T) t;
  }
}
