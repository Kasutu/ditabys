package com.splitscale.ditabys;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;

import org.junit.Test;

import com.splitscale.ditabys.repositories.URLRepositoryInteractor;
import com.splitscale.fordastore.core.repositories.UrlRepository;
import com.splitscale.fordastore.core.url.Url;
import com.splitscale.fordastore.core.url.UrlRequest;

public class URLRepoTest {
  private long containerId = 1;

  @Test
  public void shouldAddURLSuccessfully() throws IOException {
    UrlRequest urlRequest = new UrlRequest(containerId, "twitter.com/home");
    UrlRepository repo = new URLRepositoryInteractor();

    assertDoesNotThrow(() -> repo.add(urlRequest));
  }

  @Test
  public void shouldUpdateURLSuccessfully() throws IOException {
    UrlRequest urlRequest = new UrlRequest(containerId, "twitter.com/home");
    UrlRepository repo = new URLRepositoryInteractor();

    Url url = new Url();
    url.setInnerUrl("www.splitscale.com");
    url.setUrlID(2L);

    repo.add(urlRequest);

    assertDoesNotThrow(() -> repo.update(url));
  }

  @Test
  public void shouldReturnListSuccessfully() throws IOException {
    UrlRepository repo = new URLRepositoryInteractor();

    assertDoesNotThrow(() -> repo.getALLByContainerID(containerId));
  }

  @Test
  public void shouldReturnURLSuccessfully() throws IOException {
    UrlRepository repo = new URLRepositoryInteractor();

    assertDoesNotThrow(() -> repo.getByUrlID(2));
  }

  @Test
  public void shouldDeleteURLSuccessfully() throws IOException {
    UrlRepository repo = new URLRepositoryInteractor();

    assertDoesNotThrow(() -> repo.delete(2L));
  }
}