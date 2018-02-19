package com.hahnekamp.sneakythrow;

import static com.hahnekamp.sneakythrow.Sneaky.sneak;
import static com.hahnekamp.sneakythrow.Sneaky.sneaked;
import static com.hahnekamp.sneakythrow.TestHelper.assertThrowsWithCause;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class SneakyTest {
  @Test
  public void withoutException() {
    assertEquals(String.class,
        sneak(() -> Class.forName("java.lang.String"))
    );

    List<URL> urls = Stream
        .of("www.hahnekamp.com", "www.orf.at")
        .map(sneaked(this::createURL))
        .collect(Collectors.toList());

  }

  private URL createURL(String url) throws MalformedURLException {
    return new URL("https://www.hahnekamp.com");
  }


  @Test
  public void withException() {
    assertThrowsWithCause(
        ClassNotFoundException.class,
        () -> sneak(() -> Class.forName("java.string")));
  }
}
