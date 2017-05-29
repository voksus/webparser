package com.scalors.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by voksus on 26.05.2017.
 */
public class FileTool {
    private static final Logger log = Logger.getLogger(FileTool.class);

    private static final File TEMP_HTML_FILE = new File("temp_html_file.dat");
    private static final boolean CLEAN_TEMP_DATA = false;

    /**
     * This method saves loaded data to a local html-file
     * @param queryResult
     */
    public static void saveDataToFile(String queryResult) {
        try {

            FileWriter writer = new FileWriter(TEMP_HTML_FILE, false);

            writer.write(queryResult);
            writer.flush();

        } catch (IOException e) {
            log.error("Fail to save temporary file: " + TEMP_HTML_FILE.getName());
        }

        cleanTempData();

    }

    /**
     * This method removing all temporary (useless) local files
     */
    private static void cleanTempData() {

        if (!CLEAN_TEMP_DATA) {
            return;
        }

        if (TEMP_HTML_FILE.exists()) {
            if (!TEMP_HTML_FILE.delete()) {
                log.error("Fail to remove temporary file " + TEMP_HTML_FILE.getName());
            }
        }

    }

}