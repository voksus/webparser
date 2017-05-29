package com.scalors.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

/**
 * Created by voksus on 26.05.2017.
 */
public class PropertyLoader {
    private static final Logger log = Logger.getLogger(PropertyLoader.class);

    private static String xmlFileName;
    private static String mainURL;
    private static String searchURL;
    private static boolean useSimpleName;
    private static boolean isLoaded;

    public PropertyLoader() {
        Properties prop = new Properties();
        GregorianCalendar gc = new GregorianCalendar();
        try {
            prop.load(new FileInputStream("src/main/resources/config.properties"));
            xmlFileName = prop.getProperty("xml.file.name");
            mainURL = prop.getProperty("main.url");
            searchURL = prop.getProperty("search.url");
            useSimpleName = prop.getProperty("use.simple.name").equals("true");
            isLoaded = true;
        } catch (IOException e) {
            isLoaded = false;
            log.error("Properties was not loaded.");
            log.error(e.getMessage());
        }
        xmlFileName += "["
                + gc.get(Calendar.YEAR) + "-"
                + gc.get(Calendar.MONTH) + "-"
                + gc.get(Calendar.DAY_OF_MONTH) + " "
                + gc.get(Calendar.HOUR_OF_DAY) + "-"
                + gc.get(Calendar.MINUTE) + "-"
                + gc.get(Calendar.SECOND) + "].xml";
    }

    public static String getXmlFileName() {
        return xmlFileName;
    }

    public static String getMainURL() {
        return mainURL;
    }

    public static String getSearchURL() {
        return searchURL;
    }

    public static boolean isUseSimpleName() {
        return useSimpleName;
    }

    public static boolean isLoaded() {
        return isLoaded;
    }

}