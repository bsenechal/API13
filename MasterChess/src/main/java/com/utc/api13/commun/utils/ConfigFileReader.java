package com.utc.api13.commun.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.utc.api13.commun.exceptions.TechnicalException;

public class ConfigFileReader {

    private String configFile;
    private InputStream inputStream;
    private static final Logger LOGGER = Logger.getLogger(ConfigFileReader.class);

    public ConfigFileReader(String file) {
        configFile = file;
    }

    public String getPropValue(String key) throws TechnicalException {
        String value = "";
        try {
            Properties prop = new Properties();

            inputStream = getClass().getClassLoader().getResourceAsStream(configFile);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new TechnicalException("property file '" + configFile + "' not found in the classpath");
            }
            value = prop.getProperty(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new TechnicalException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return value;
    }
}
