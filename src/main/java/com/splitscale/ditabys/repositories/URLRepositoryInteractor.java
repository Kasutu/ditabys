package com.splitscale.ditabys.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.splitscale.ditabys.driver.DatabaseDriver;
import com.splitscale.ditabys.driver.StoreDbDriver;
import com.splitscale.fordastore.core.repositories.UrlRepository;
import com.splitscale.fordastore.core.url.Url;
import com.splitscale.fordastore.core.url.UrlRequest;

public class URLRepositoryInteractor implements UrlRepository {
  DatabaseDriver db;

  public URLRepositoryInteractor() {
    this.db = new StoreDbDriver();
  }

  @Override
  public void add(UrlRequest urlRequest) throws IOException {
    String query = "INSERT INTO url (container_id, url) VALUES (?,?);";

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setLong(1, urlRequest.getContainerID());
      pstmt.setString(2, urlRequest.getInnerUrl());

      pstmt.executeUpdate();

      conn.close();

    } catch (SQLException e) {
      throw new IOException("Could not add url: " + e.getMessage());
    }
  }

  @Override
  public void delete(long urlID) throws IOException {
    String query = "DELETE FROM url WHERE url_id = ?";

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setLong(1, urlID);

      pstmt.executeUpdate();

      conn.close();

    } catch (SQLException e) {
      throw new IOException("Could not delete url" + e.getMessage());
    }
  }

  @Override
  public void update(Url url) throws IOException {
    String query = "UPDATE url SET url = ? WHERE url_id = ?";

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, url.getInnerUrl());
      pstmt.setLong(2, url.getUrlID());

      pstmt.executeUpdate();

      conn.close();

    } catch (SQLException e) {
      throw new IOException("Unable to update url" + e.getMessage());
    }
  }

  @Override
  public List<Url> getALLByContainerID(long containerID) throws IOException {
    String query = "SELECT * FROM url WHERE container_id = ?";

    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setLong(1, containerID);

      ResultSet rs = pstmt.executeQuery();

      List<Url> urls = new ArrayList<Url>();

      while (rs.next()) {
        Url url = new Url();
        url.setUrlID(rs.getInt("url_id"));
        url.setInnerUrl(rs.getString("url"));
        urls.add(url);
      }

      conn.close();

      return urls;

    } catch (SQLException e) {
      throw new IOException("unable to get list of urls from given container" + e.getMessage());
    }
  }

  @Override
  public Url getByUrlID(long urlID) throws IOException {
    String query = "SELECT * FROM url WHERE url_id = ?";
    try {
      Connection conn = db.getConnection();

      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setLong(1, urlID);

      ResultSet rs = pstmt.executeQuery();

      Url url = new Url();

      if (rs.next()) {
        url.setUrlID(rs.getInt("url_id"));
        url.setInnerUrl(rs.getString("url"));
      }

      conn.close();
      return url;
    } catch (SQLException e) {
      throw new IOException("Unable to find url from urlID: " + e.getMessage());
    }
  }
}