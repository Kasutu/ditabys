package com.splitscale.ditabys.driver;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBdriverConnector {
  public Connection getConnection() throws SQLException;
}
