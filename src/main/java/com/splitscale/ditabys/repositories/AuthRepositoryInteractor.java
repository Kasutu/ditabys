package com.splitscale.ditabys.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.splitscale.ditabys.driver.AuthDbDriver;
import com.splitscale.ditabys.driver.DatabaseDriver;
import com.splitscale.fordastore.core.auth.PublicKey;
import com.splitscale.fordastore.core.repositories.AuthRepository;

public class AuthRepositoryInteractor implements AuthRepository {
  DatabaseDriver db;

  public AuthRepositoryInteractor() {
    this.db = new AuthDbDriver();
  }

  @Override
  public boolean deleteByID(String arg0) {
    return false;
  }

  @Override
  public PublicKey getByID(String arg0) {
    return null;
  }

  @Override
  public boolean insert(PublicKey token) throws IOException {
    String query = "INSERT INTO public_key (key_id, key_value) VALUES (UUID_TO_BIN(?), ?);";
    Connection conn;

    try {
      conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, token.getUid());
      pstmt.setString(2, token.getPublicKey());
      pstmt.executeUpdate();

      conn.close();

      return true;
    } catch (SQLException e) {
      throw new IOException("Could not add user to database due to server error: " + e.getMessage());
    }
  }

  @Override
  public boolean update(PublicKey arg0) throws IOException {
    return false;
  }

}