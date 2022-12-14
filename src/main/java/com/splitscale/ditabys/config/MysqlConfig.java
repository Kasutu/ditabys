package com.splitscale.ditabys.config;

import java.util.Properties;

public class MysqlConfig implements DBconfig {
  Properties props;

  public MysqlConfig() {
    PropsLoader loader = new MysqlPropsLoader();
    props = loader.loadProps();
  }

  public String getUrl() {
    return props.getProperty("mysql.url");
  }

  public String getUsername() {
    return props.getProperty("mysql.username");
  }

  public String getPassword() {
    return props.getProperty("mysql.password");
  }
}
