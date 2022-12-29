package com.splitscale.ditabys.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.splitscale.ditabys.driver.AuthDbDriver;
import com.splitscale.ditabys.driver.DatabaseDriver;
import com.splitscale.fordastore.core.auth.AuthPublicKey;
import com.splitscale.fordastore.core.repositories.AuthRepository;

public class AuthRepositoryInteractor implements AuthRepository {
  DatabaseDriver db;

  public AuthRepositoryInteractor() {
    this.db = new AuthDbDriver();
  }

  @Override
  public void deleteByUid(String uid) throws IOException {
    String query = "DELETE FROM public_key WHERE key_id = UUID_TO_BIN(?);";

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, uid);

      pstmt.executeUpdate();

      conn.close();

    } catch (SQLException e) {
      throw new IOException("Could not delete token due to server error: " + e.getMessage());
    }
  }

  @Override
  public AuthPublicKey getByUid(String uid) throws IOException {
    String query = "SELECT BIN_TO_UUID(key_id) as key_id, key_value FROM public_key WHERE key_id = UUID_TO_BIN(?);";

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, uid);

      ResultSet rs = pstmt.executeQuery();

      AuthPublicKey authPublicKey = new AuthPublicKey();

      if (rs.next()) {
        authPublicKey.setUid(rs.getString("key_id"));
        authPublicKey.setPublicKey(rs.getString("key_value"));
      }

      conn.close();
      return authPublicKey;
    } catch (SQLException e) {
      throw new IOException("Unable to find token by uid due to server error: " + e.getMessage());
    }
  }

  @Override
  public void insert(AuthPublicKey token) throws IOException {
    String query = "INSERT INTO public_key (key_id, key_value) VALUES (UUID_TO_BIN(?), ?);";
    Connection conn;

    try {
      conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, token.getUid());
      pstmt.setString(2, token.getPublicKey());
      pstmt.executeUpdate();

      conn.close();

    } catch (SQLException e) {
      throw new IOException("Could not add token due to server error: " + e.getMessage());
    }
  }

  @Override
  public void update(AuthPublicKey authPublicKey) throws IOException {
    String query = "UPDATE public_key SET key_value = ? WHERE key_id = UUID_TO_BIN(?);";

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, authPublicKey.getPublicKey());
      pstmt.setString(2, authPublicKey.getUid());

      pstmt.executeUpdate();

      conn.close();

    } catch (SQLException e) {
      throw new IOException("Could update token due to server error: " + e.getMessage());
    }
  }

}