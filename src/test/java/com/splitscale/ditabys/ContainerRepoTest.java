package com.splitscale.ditabys;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.splitscale.ditabys.repositories.ContainerRepositoryInteractor;
import com.splitscale.fordastore.core.container.Container;
import com.splitscale.fordastore.core.repositories.ContainerRepository;

public class ContainerRepoTest {
  @Test
  public void shouldAddContainerSuccessfully() {
    ContainerRepository repo = new ContainerRepositoryInteractor();

    assertDoesNotThrow(() -> repo.add("title", "e7f60aa1-cc65-44bd-9150-2d9da00cef5b"));
  }

  @Test
  public void shouldShowContainerIDSuccessfully() {
    ContainerRepositoryInteractor repo = new ContainerRepositoryInteractor();

    try {
      Container container = repo.getByContainerID(4);

      System.out.println(container.getName());
      System.out.println(container.getContainerID());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void shouldShowUIDSuccessfully() {
    ContainerRepositoryInteractor repo = new ContainerRepositoryInteractor();

    try {
      Container container = repo.getByUid("e7f60aa1-cc65-44bd-9150-2d9da00cef5b");

      System.out.println(container.getName());
      System.out.println(container.getContainerID());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void shouldShowListofContainerTitle() throws IOException {
    ContainerRepositoryInteractor repo = new ContainerRepositoryInteractor();

    List<Container> containers = repo.getListByName("forda");
    assertNotNull(containers);
    assertEquals(3, containers.size());
    assertEquals("fordaStore", containers.get(0).getName());
    assertEquals("fordaWIN", containers.get(1).getName());
    assertEquals("fordaSheeesh", containers.get(2).getName());
  }

  @Test
  public void shouldUpdateContainerSuccessfully() throws IOException {
    ContainerRepository repo = new ContainerRepositoryInteractor();

    repo.add("title", "e7f60aa1-cc65-44bd-9150-2d9da00cef5b");
    assertDoesNotThrow(() -> repo.update("new title", 1L));

  }
}
