package com.splitscale.ditabys.repositories;

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
  public User add(UserRequest userRequest) {
    String query = "INSERT INTO user (username, password, uid) VALUES (?, ?, ?)";

    final String username = userRequest.getUsername();
    final String password = userRequest.getPassword();
    String uid = UUID.randomUUID().toString();

    User claims = new User();
    claims.setUid(uid);
    claims.setUsername(username);

    Connection conn;

    try {
      conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, username);
      pstmt.setString(2, password);
      pstmt.setString(3, uid);

      int rowAffected = pstmt.executeUpdate();

      if (rowAffected == 1) {

        ResultSet rs = pstmt.getGeneratedKeys();

        if (rs.next()) {
          long id = rs.getLong(1);
          claims.setId(id);
        }

      }

      conn.close();

      return claims;
    } catch (SQLException e) {
      return null;
    }
  }

  @Override
  public User findByUID(String uid) {
    String query = "SELECT * FROM user WHERE uid = " + "?" + ";";

    try {
      return getUserFromDb(query, uid);
    } catch (SQLException e) {
      return null;
    }
  }

  @Override
  public User findByUsername(String username) {
    String query = "SELECT * FROM user WHERE username = ?;";

    try {
      return getUserFromDb(query, username);
    } catch (SQLException e) {
      return null;
    }
  }

  @Override
  public boolean update(Long id, UserRequest userRequest) throws Exception {
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
      return false;
    }
  }

}
