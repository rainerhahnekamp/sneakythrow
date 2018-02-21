package com.rainerhahnekamp.sneakythrow;

import static com.rainerhahnekamp.sneakythrow.Sneaky.sneak;
import static com.rainerhahnekamp.sneakythrow.Sneaky.sneaked;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class TutorialTest {
  @Test
  public void defaultUsage() {
    URL url = sneak(() -> new URL("https://www.hahnekamp.com"));
    assertEquals("https", url.getProtocol());
  }

  @Test
  public void streamUsage() {
    List<URL> urls = Stream
        .of("https://www.hahnekamp.com", "https://www.austria.info")
        .map(sneaked(this::createUrl))
        .collect(Collectors.toList());

    assertEquals("www.hahnekamp.com", urls.get(0).getHost());
    assertEquals("www.austria.info", urls.get(1).getHost());
  }

  private URL createUrl(String url) throws MalformedURLException {
    return new URL(url);
  }

}
