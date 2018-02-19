# SneakyThrow

[![Build Status](https://travis-ci.org/rainerhahnekamp/sneakythrow.svg?branch=master)](https://travis-ci.org/rainerhahnekamp/sneakythrow.svg?branch=master)
[![codecov](https://codecov.io/gh/rainerhahnekamp/sneakythrow/branch/master/graph/badge.svg)](https://codecov.io/gh/rainerhahnekamp/sneakythrow)

SneakyThrow is a Java library to ignore checked exceptions. It does that by wrapping an `Exception` into a `RuntimeException`.

## Usage

Without SneakyThrow:
```java
URL url;
try {
  url = new URL("https://www.hahnekamp.com");
} catch (MalformedURLException mue) {
  throw new RuntimeException(mue);
}
```
With SneakyThrow:
```java
URL url = sneak(() -> new URL("https://www.hahnekamp.com"));
```
## Usage with Java 8 Streams
```java
private URL createURL(String url) throws MalformedURLException {
  return new URL(url);
}
```

The function above used within a Stream without SneakyThrow:
```java
Stream
  .of("https://www.hahnekamp.com", "https://www.austria.info")
  .map(url -> {
    try {
      return this.createURL(url);
    } catch (MalformedURLException mue) {
      throw new RuntimeException(mue);
    }
  })  
  .collect(Collectors.toList());
```
Again with SneakyThrow:

```java
Stream
  .of("https://www.hahnekamp.com", "https://www.austria.info")
  .map(sneaked(this::createURL))
  .collect(Collectors.toList());
```
The static method `sneaked` wraps each function, that has the same signature as a functional interface (java.util.functional).
## How it works

We make heavy usage of the [ThrowingFunction](https://github.com/pivovarit/ThrowingFunction) library. It provides functional interfaces equivalent to the official ones of `java.util.functional`, but has two major extensions:
1. Its functional interfaces extend the official ones. Therefore they can be used within Java 8 Streams.
2. They are throwing exceptions. This gives us the possibility to write lambdas throwing checked exceptions.

Both `sneak` and `sneaked` wrap the passed functional interface into a try/catch clause. By that, they can return a `java.util.functional` interface. In the case of `sneak`, execution and the return of the result is done immediately. 


