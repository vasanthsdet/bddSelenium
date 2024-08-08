package com.bddselenium.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());
    private static Config instance;
    private Properties properties;

    private Config() {
        properties = new Properties();
        String configFilePath = System.getProperty("config.file", "src/main/resources/config.properties");
        try (FileInputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load config properties", e);
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
