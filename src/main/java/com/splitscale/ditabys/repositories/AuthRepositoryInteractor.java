package com.splitscale.ditabys.repositories;

import java.io.IOException;

import com.splitscale.ditabys.driver.DatabaseDriver;
import com.splitscale.fordastore.core.auth.PublicKey;
import com.splitscale.fordastore.core.repositories.AuthRepository;

public class AuthRepositoryInteractor implements AuthRepository {
  DatabaseDriver db;

  public AuthRepositoryInteractor() {
    this.db = new DatabaseDriver();
  }

  @Override
  public boolean deleteByID(String arg0) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public PublicKey getByID(String uid) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean insert(PublicKey publicKey) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean update(PublicKey publicKey) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

}