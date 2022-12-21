package com.splitscale.ditabys;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.splitscale.ditabys.repositories.UserRepositoryInteractor;
import com.splitscale.fordastore.core.repositories.UserRepository;
import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserRequest;

public class UserRepoTest {

  private UserRepository conn;

  private User user;

  private UserRequest userRequest;

  @Before
  public void setup() {
    conn = new UserRepositoryInteractor();
    user = new User();
    userRequest = new UserRequest("username", "pwd");

    user.setUid("some-id-here");
    user.setUsername("gem");
    user.setPassword("pwd");
  }

  @Test
  public void shouldAddUserSuccessfully() {

    UserRepository repo = new UserRepositoryInteractor();

    try {
      User claims = repo.add(userRequest);

      System.out.println(claims.getUsername());
      System.out.println(claims.getUid());
      System.out.println(claims.getId().toString());

      assertNotNull(claims);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  @Test
  public void findUserByUid() {

    try {
      User foundUser = conn.findByUID("some-id-here");

      System.out.println(foundUser.toString());

      assertNotNull(foundUser);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  @Test
  public void findUserByUsername() {

    try {
      User foundUser = conn.findByUsername("gem");

      System.out.println(foundUser.toString());

      assertNotNull(foundUser);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
