package com.splitscale.ditabys;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.splitscale.ditabys.repositories.UserRepositoryInteractor;
import com.splitscale.fordastore.core.repositories.UserRepository;
import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserBuilder;
import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.fordastore.core.user.register.UserClaims;
import com.splitscale.loggerist.Loggerist;

public class UserRepoTest {

  private UserRepository conn;

  private User user;

  private UserRequest userRequest;

  @Before
  public void setup() {
    conn = new UserRepositoryInteractor();
    user = new UserBuilder();
    userRequest = new UserRequest("username", "pwd");

    user.setUid("some-id-here");
    user.setUsername("gem");
    user.setPassword("pwd");
  }

  @Test
  public void shouldAddUserSuccessfully() {

    UserRepository repo = new UserRepositoryInteractor();

    try {
      UserClaims claims = repo.add(userRequest);

      Loggerist.info(claims.getUsername());
      Loggerist.info(claims.getUid());
      Loggerist.info(claims.getId().toString());

      assertNotNull(claims);

    } catch (Exception e) {
      Loggerist.error(e.getMessage());
    }

  }

  @Test
  public void findUserByUid() {

    try {
      User foundUser = conn.findByUID("some-id-here");

      Loggerist.info(foundUser.toString());

      assertNotNull(foundUser);

    } catch (Exception e) {
      Loggerist.error(e.getMessage());
    }

  }

  @Test
  public void findUserByUsername() {

    try {
      User foundUser = conn.findByUsername("gem");

      Loggerist.info(foundUser.toString());

      assertNotNull(foundUser);

    } catch (Exception e) {
      Loggerist.error(e.getMessage());
    }

  }
}
