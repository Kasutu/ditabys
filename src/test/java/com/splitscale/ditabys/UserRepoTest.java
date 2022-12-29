package com.splitscale.ditabys;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;

import com.splitscale.ditabys.repositories.UserRepositoryInteractor;
import com.splitscale.fordastore.core.repositories.UserRepository;
import com.splitscale.fordastore.core.user.UserRequest;

public class UserRepoTest {
  @Test
  public void shouldAddUserSuccessfully() {

    UserRequest userRequest = new UserRequest("fumi", "pwd");
    UserRepository repo = new UserRepositoryInteractor();

    assertDoesNotThrow(() -> repo.add(userRequest));
  }

}
