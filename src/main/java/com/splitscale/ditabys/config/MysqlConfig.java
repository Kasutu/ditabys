package com.splitscale.ditabys.config;

import java.util.Properties;

public class MysqlConfig implements DBconfig {
  private String url;
  private String username;
  private String password;

  public MysqlConfig() {
    PropsLoader loader = new MysqlPropsLoader();
    Properties props = loader.loadProps();

    url = props.getProperty("mysql.url");
    username = props.getProperty("mysql.username");
    password = props.getProperty("mysql.password");
  }

  public String getUrl() {
    return url;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}
