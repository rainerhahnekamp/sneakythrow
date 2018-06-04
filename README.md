# SneakyThrow

[![Build Status](https://travis-ci.org/rainerhahnekamp/sneakythrow.svg?branch=master)](https://travis-ci.org/rainerhahnekamp/sneakythrow.svg?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/rainerhahnekamp/sneakythrow/badge.svg?branch=master)](https://coveralls.io/github/rainerhahnekamp/sneakythrow?branch=master)
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.rainerhahnekamp/sneakythrow/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.rainerhahnekamp/sneakythrow)
[![Javadocs](https://www.javadoc.io/badge/com.rainerhahnekamp/sneakythrow.svg)](https://www.javadoc.io/doc/com.rainerhahnekamp/sneakythrow)

SneakyThrow is a Java library to ignore checked exceptions. You can integrate it using maven:

```xml
<dependency>
  <groupId>com.rainerhahnekamp</groupId>
  <artifactId>sneakythrow</artifactId>
  <version>1.1.0</version>
</dependency>
```

![SneakyThrow Usage GIF](https://github.com/rainerhahnekamp/sneakythrow/blob/master/ezgif-3-db5218262c.gif)


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

**Please note the difference between `sneak` and `sneaked`.**
## How it works

This project is heavily influenced by [ThrowingFunction](https://github.com/pivovarit/ThrowingFunction). 

In SneakyThrow, each functional interface, defined in `java.util.function`, has an equivalent one with the same signature. The only difference is, that these "Sneaky Functional Interfaces" throw exceptions. This gives us the possibility to write lambdas or similar code that also throws exceptions.

Both `sneak` and `sneaked` wrap the passed "Sneaky Functional Interfaces" into a try/catch clause and return the equivalent `java.util.function` interface. In the case of `sneak`, execution and the return of the result is done immediately. 


