package com.splitscale.ditabys.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.splitscale.ditabys.driver.DatabaseDriver;
import com.splitscale.fordastore.core.repositories.UserRepository;
import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserRequest;

public class UserRepositoryInteractor implements UserRepository {

  DatabaseDriver db;

  public UserRepositoryInteractor() {
    this.db = new DatabaseDriver();
  }

  private User getUserFromDb(String query, String whereVal) throws SQLException {
    Connection conn = db.getConnection();

    PreparedStatement pstmt = conn.prepareStatement(query);
    pstmt.setString(1, whereVal);
    ResultSet rs = pstmt.executeQuery();

    User user = new User();

    if (rs.next() && rs.getString("uid").equals(whereVal)) {
      user.setUsername(rs.getString("username"));
      user.setPassword(rs.getString("userPassword"));
      user.setUid(rs.getString("uid"));
    }

    conn.close();

    return user;
  }

  @Override
  public User add(UserRequest userRequest) throws IOException {
    String query = "INSERT INTO user (username, password, uid) VALUES (?, ?, ?)";

    final String username = userRequest.getUsername();
    final String password = userRequest.getPassword();
    String uid = UUID.randomUUID().toString();

    User user = new User();
    user.setUid(uid);
    user.setUsername(username);
    user.setPassword(password);

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, username);
      pstmt.setString(2, password);
      pstmt.setString(3, uid);

      pstmt.executeUpdate();

      conn.close();

      return user;
    } catch (SQLException e) {
      throw new IOException("Could not add user to database");
    }
  }

  @Override
  public User findByUID(String uid) throws IOException {
    String query = "SELECT * FROM user WHERE uid = " + "?" + ";";

    try {
      return getUserFromDb(query, uid);
    } catch (SQLException e) {
      throw new IOException("Could not find user by uid");
    }
  }

  @Override
  public User findByUsername(String username) throws IOException {
    String query = "SELECT * FROM user WHERE username = ?;";

    try {
      return getUserFromDb(query, username);
    } catch (SQLException e) {
      throw new IOException("Could not find user by username");
    }
  }

  @Override
  public boolean update(Long id, UserRequest userRequest) throws IOException {
    final String query = "UPDATE user SET username = ?, password = ? WHERE user_id = ?;";

    final String username = userRequest.getUsername();
    final String password = userRequest.getPassword();

    Connection conn;

    try {
      conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, username);
      pstmt.setString(2, password);
      pstmt.setLong(3, id);

      pstmt.executeUpdate();

      conn.close();

      return true;
    } catch (SQLException e) {
      throw new IOException("Failed to update user");
    }
  }

}
