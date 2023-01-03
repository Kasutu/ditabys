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
    UrlRepository repo = new URLRepositoryInteractor();

    assertDoesNotThrow(() -> repo.add("twitter.com/home", containerId));
  }

  @Test
  public void shouldUpdateURLSuccessfully() throws IOException {
    UrlRepository repo = new URLRepositoryInteractor();

    repo.add("twitter.com/home", containerId);

    assertDoesNotThrow(() -> repo.update("twitter.com/home", 2L));
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

    assertDoesNotThrow(() -> repo.deleteByUrlId(2L));
  }
}