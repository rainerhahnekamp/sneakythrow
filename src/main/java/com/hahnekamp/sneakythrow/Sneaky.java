package com.hahnekamp.sneakythrow;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import pl.touk.throwing.ThrowingBiConsumer;
import pl.touk.throwing.ThrowingBiFunction;
import pl.touk.throwing.ThrowingBiPredicate;
import pl.touk.throwing.ThrowingBinaryOperator;
import pl.touk.throwing.ThrowingConsumer;
import pl.touk.throwing.ThrowingFunction;
import pl.touk.throwing.ThrowingPredicate;
import pl.touk.throwing.ThrowingRunnable;
import pl.touk.throwing.ThrowingSupplier;
import pl.touk.throwing.ThrowingUnaryOperator;

public class Sneaky {
  public static <T> T sneak(ThrowingSupplier<T, Exception> supplier) {
    return sneaked(supplier).get();
  }

  /**
   * Wraps all exceptions of a BiConsumer lambda to SneakyException.
   */
  public static <T, U> BiConsumer<T, U> sneaked(ThrowingBiConsumer<T, U, Exception> biConsumer) {
    return (t, u) -> {
      try {
        biConsumer.accept(t, u);
      } catch (Exception exception) {
        throw new SneakyException(exception);
      }
    };
  }

  /**
   * Wraps all exceptions of a BiFunction lambda to SneakyException.
   */
  public static <T, U, R> BiFunction<T, U, R> sneaked(
      ThrowingBiFunction<T, U, R, Exception> biFunction) {
    return (t, u) -> {
      try {
        return biFunction.apply(t, u);
      } catch (Exception exception) {
        throw new SneakyException(exception);
      }
    };
  }

  /**
   * Wraps all exceptions of a BinaryOperator lambda to SneakyException.
   */
  public static <T> BinaryOperator<T> sneaked(
      ThrowingBinaryOperator<T, Exception> binaryOperator) {
    return (t1, t2) -> {
      try {
        return binaryOperator.apply(t1, t2);
      } catch (Exception exception) {
        throw new SneakyException(exception);
      }
    };
  }

  /**
   * Wraps all exceptions of a BiPredicate lambda to SneakyException.
   */
  public static <T, U> BiPredicate<T, U> sneaked(
      ThrowingBiPredicate<T, U, Exception> biPredicate) {
    return (t, u) -> {
      try {
        return biPredicate.test(t, u);
      } catch (Exception exception) {
        throw new SneakyException(exception);
      }
    };
  }

  /**
   * Wraps all exceptions of a Consumer lambda to SneakyException.
   */
  public static <T> Consumer<T> sneaked(ThrowingConsumer<T, Exception> consumer) {
    return t -> {
      try {
        consumer.accept(t);
      } catch (Exception exception) {
        throw new SneakyException(exception);
      }
    };
  }

  /**
   * Wraps all exceptions of a Function lambda to SneakyException.
   */
  public static <T, R> Function<T, R> sneaked(ThrowingFunction<T, R, Exception> function) {
    return t -> {
      try {
        return function.apply(t);
      } catch (Exception exception) {
        throw new SneakyException(exception);
      }
    };
  }

  /**
   * Wraps all exceptions of a Predicate lambda to SneakyException.
   */
  public static <T> Predicate<T> sneaked(ThrowingPredicate<T, Exception> predicate) {
    return t -> {
      try {
        return predicate.test(t);
      } catch (Exception exception) {
        throw new SneakyException(exception);
      }
    };
  }

  /**
   * Wraps all exceptions of a Runnable lambda to SneakyException.
   */
  public static Runnable sneaked(ThrowingRunnable<Exception> runnable) {
    return () -> {
      try {
        runnable.run();
      } catch (Exception exception) {
        throw new SneakyException(exception);
      }
    };
  }

  /**
   * Wraps all exceptions of a Supplier lambda to SneakyException.
   */
  public static <T> Supplier<T> sneaked(ThrowingSupplier<T, Exception> supplier) {
    return () -> {
      try {
        return supplier.get();
      } catch (Exception exception) {
        throw new SneakyException(exception);
      }
    };
  }

  /**
   * Wraps all exceptions of a UnaryOperator lambda to SneakyException.
   */
  public static <T> UnaryOperator<T> sneaked(ThrowingUnaryOperator<T, Exception> unaryOperator) {
    return t -> {
      try {
        return unaryOperator.apply(t);
      } catch (Exception exception) {
        throw new SneakyException(exception);
      }
    };
  }
}
