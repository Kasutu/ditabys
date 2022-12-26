package com.splitscale.ditabys.driver;

import com.splitscale.ditabys.config.PropsLoader;

public class AuthDbDriver extends DatabaseDriver {

  public AuthDbDriver() {
    super(PropsLoader.loadProps("src/main/resources/auth-db.properties"));
  }

}
