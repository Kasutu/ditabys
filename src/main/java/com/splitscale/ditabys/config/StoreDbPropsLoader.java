package com.splitscale.ditabys.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StoreDbPropsLoader implements PropsLoader {
  private String path = "src/main/resources/store-db.properties";

  public StoreDbPropsLoader() {
    // def
  }

  @Override
  public Properties loadProps() {
    Properties props = new Properties();
    InputStream fileStream;

    try {
      fileStream = new FileInputStream(path);
      props.load(fileStream);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return props;
  }

}
