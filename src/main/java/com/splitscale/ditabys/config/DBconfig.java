package com.splitscale.ditabys.config;

import java.util.Properties;

public class DBconfig {
  Properties properties;

  public DBconfig(Properties properties) {
    this.properties = properties;
  }

  public String getUrl() {
    return properties.getProperty("mysql.url");
  }

  public String getUsername() {
    return properties.getProperty("mysql.username");
  }

  public String getPassword() {
    return properties.getProperty("mysql.password");
  }
}
