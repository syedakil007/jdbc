package com.akil.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    

    public static String getProperty(String propertyName) {
        Properties properties = new Properties();

        // Path to your properties file
        String configPath = "src/config.properties"; 
        String propertyValue ="";

        try (FileInputStream input = new FileInputStream(configPath)) {
            properties.load(input);
            propertyValue = properties.getProperty(propertyName);
            if (propertyValue != null) {
                propertyValue = propertyValue.trim();
                if (propertyValue.length() >= 2 && propertyValue.startsWith("\"") && propertyValue.endsWith("\"")) {
                    propertyValue = propertyValue.substring(1, propertyValue.length() - 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load properties file!");
            e.printStackTrace();
        }
        return propertyValue;
    }
}
