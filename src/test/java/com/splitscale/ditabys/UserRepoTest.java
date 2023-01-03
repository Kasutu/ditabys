package com.splitscale.ditabys;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;

import com.splitscale.ditabys.repositories.UserRepositoryInteractor;
import com.splitscale.fordastore.core.repositories.UserRepository;

public class UserRepoTest {
  @Test
  public void shouldAddUserSuccessfully() {

    UserRepository repo = new UserRepositoryInteractor();

    assertDoesNotThrow(() -> repo.add("fumi", "pwd"));
  }

}
