package com.splitscale.ditabys.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.splitscale.ditabys.config.DBconfig;
import com.splitscale.ditabys.config.MysqlConfig;

public class DatabaseDriver {

  private DBconfig config;

  public DatabaseDriver() {
    this.config = new MysqlConfig();
  }

  public Connection getConnection() throws SQLException {
    // assign db parameters
    String url = config.getUrl();
    String user = config.getUsername();
    String password = config.getPassword();

    // create a connection to the database
    return DriverManager.getConnection(url, user, password);
  }

}
