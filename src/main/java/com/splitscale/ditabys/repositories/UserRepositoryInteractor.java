package com.splitscale.ditabys.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.splitscale.ditabys.driver.DatabaseDriver;
import com.splitscale.ditabys.driver.StoreDbDriver;
import com.splitscale.fordastore.core.repositories.UserRepository;
import com.splitscale.fordastore.core.user.User;

public class UserRepositoryInteractor implements UserRepository {

  DatabaseDriver db;

  public UserRepositoryInteractor() {
    this.db = new StoreDbDriver();
  }

  @Override
  public User add(String username, String password) throws IOException {
    String query = "INSERT INTO user (user_id, username, password) VALUES (UUID_TO_BIN(?), ?, ?);";

    String uid = UUID.randomUUID().toString();

    User user = new User(uid, username, password);

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, uid);
      pstmt.setString(2, username);
      pstmt.setString(3, password);

      pstmt.executeUpdate();

      conn.close();

      return user;
    } catch (SQLException e) {
      throw new IOException("Could not add user to database due to server error: " + e.getMessage());
    }
  }

  @Override
  public User findByUID(String uid) throws IOException {
    String query = "SELECT BIN_TO_UUID(user_id) as uid, username, password FROM user WHERE uid = BIN_TO_UUID(?);";

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, uid);
      ResultSet rs = pstmt.executeQuery();

      User user = new User();

      if (rs.next()) {
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setUid(rs.getString("uid"));
      }

      conn.close();

      return user;
    } catch (SQLException e) {
      throw new IOException("Could not find user by uid");
    }
  }

  @Override
  public User findByUsername(String username) throws IOException {
    String query = "SELECT BIN_TO_UUID(user_id) as uid, username, password FROM user WHERE username = ?;";

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, username);
      ResultSet rs = pstmt.executeQuery();

      User user = new User();

      if (rs.next()) {
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setUid(rs.getString("uid"));
      }

      conn.close();

      return user;
    } catch (SQLException e) {
      throw new IOException("Could not find user by username: " + e.getMessage());
    }
  }

  @Override
  public User update(String uid, String username, String password) throws IOException {
    final String query = "UPDATE user SET username = ?, password = ? WHERE user_id = UUID_TO_BIN(?);";

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, username);
      pstmt.setString(2, password);
      pstmt.setString(3, uid);

      pstmt.executeUpdate();

      conn.close();

      return new User(uid, username, password);
    } catch (SQLException e) {
      throw new IOException("Failed to update user");
    }
  }

}
