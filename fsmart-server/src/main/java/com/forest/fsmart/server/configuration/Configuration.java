package com.forest.fsmart.server.configuration;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {


  private static final Log LOG = LogFactory.getLog(Configuration.class);

  private static  Properties properties;

  public Configuration(String configFile) {
      readConfigFile(configFile);
  }

  private  Properties readConfigFile(String configFile) {
    properties = new Properties();

    //Get property file stream from classpath
    InputStream inputStream = Configuration.class.getResourceAsStream(configFile);

    if (inputStream == null) {
      throw new IllegalArgumentException(configFile + " not found in classpath");
    }

    // load the properties
    try {
      properties.load(inputStream);
      inputStream.close();
    } catch (FileNotFoundException fnf) {
      LOG.info("No configuration file " + configFile + " found in classpath.", fnf);
    } catch (IOException ie) {
      throw new IllegalArgumentException("Can't read configuration file " +
          configFile, ie);
    }
    return properties;
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public String getProperty(String key, String defaultValue) {
    return properties.getProperty(key, defaultValue);
  }

  public Map<String, String> getPropertiesMap(String configFile) {

    Properties properties = readConfigFile(configFile);
    Map<String, String> propertiesMap = new HashMap<String, String>();

    for(String key : properties.stringPropertyNames()) {
      propertiesMap.put(key, properties.getProperty(key));
    }
    return propertiesMap;
  }

}
