package com.splitscale.ditabys;

import com.splitscale.ditabys.config.DBconfig;
import com.splitscale.ditabys.config.MysqlConfig;

public class App {
  public static void main(String[] args) {
    DBconfig config = new MysqlConfig();

    System.out.println(config.getUrl());
    System.out.println(config.getUsername());
    System.out.println(config.getPassword());
  }
}
