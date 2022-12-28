package com.splitscale.ditabys.repositories;

import java.io.IOException;
import java.util.List;

import com.splitscale.ditabys.driver.DatabaseDriver;
import com.splitscale.ditabys.driver.StoreDbDriver;
import com.splitscale.fordastore.core.repositories.UrlRepository;
import com.splitscale.fordastore.core.url.Url;

public class URLRepositoryInteractor implements UrlRepository {
  DatabaseDriver db;

  public URLRepositoryInteractor() {
    this.db = new StoreDbDriver();
  }

  @Override
  public Url add(Url arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean delete(long arg0) throws IOException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<Url> getALLByOwnerID(long arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Url getByContentID(long arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Url update(Url arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

}