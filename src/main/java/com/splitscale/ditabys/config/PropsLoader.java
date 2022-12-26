package com.splitscale.ditabys.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// "src/main/resources/store-db.properties"
// "src/main/resources/auth-db.properties"

public class PropsLoader {
  private PropsLoader() {
    // default
  }

  public static DBconfig loadProps(String path) {
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

    return new DBconfig(props);
  }

}
