package com.splitscale.ditabys;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import com.splitscale.ditabys.repositories.UserRepositoryInteractor;
import com.splitscale.fordastore.core.repositories.UserRepository;
import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserRequest;

public class UserRepoTest {
  @Test
  public void shouldAddUserSuccessfully() {

    UserRequest userRequest = new UserRequest("username", "pwd");
    UserRepository repo = new UserRepositoryInteractor();

    try {
      User user = repo.add(userRequest);

      System.out.println(user.getUsername());
      System.out.println(user.getUid());

      assertNotNull(user);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

}
