package com.splitscale.ditabys.repositories;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.splitscale.ditabys.driver.DatabaseDriver;
import com.splitscale.shield.auth.Token;
import com.splitscale.shield.repository.AuthRepository;

public class AuthRepositoryInteractor implements AuthRepository {
  DatabaseDriver db;

  public AuthRepositoryInteractor() {
    this.db = new DatabaseDriver();
  }

  @Override
  public boolean deleteByID(Long arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public PublicKey getByID(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean insert(Token token) {
    String query = "INSERT INTO token (uid, public_key) VALUES (?, ?)";
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
      return false;
    }
  }

  @Override
  public boolean update(Token arg0) {
    // TODO Auto-generated method stub
    return false;
  }

}