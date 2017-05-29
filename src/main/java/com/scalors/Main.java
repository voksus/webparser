package com.scalors;

import com.scalors.entity.Offer;
import com.scalors.parser.JaxbParser;
import com.scalors.parser.PageParser;
import com.scalors.util.PropertyLoader;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

/**
 * Created by voksus on 25.05.2017.
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    private static String xmlFileName;
    private static String url;

    public static void main(String[] keyword) {

        if (keyword.length == 0) {
            log.error("There is no keyword(s) to start.");
            log.warn("Application terminated.");
            log.info("....");
            log.info(".");
            System.exit(1);
        }

        loadProperties(keyword[0]);

        log.info("********************************");
        log.info("*    Application is started    *");
        log.info("********************************");
        log.info("Search url: " + url);

        long timeStart = System.currentTimeMillis();
        PageParser pp = new PageParser();

        List<Offer> offers = pp.start(url);

        if (offers != null) {
            log.info("");
            log.info("Time used to parse " + offers.size() + " offer(s):");
            log.info((double) ((System.currentTimeMillis() - timeStart) / 10) / 100 + " seconds");
            log.info("");
            log.info("Saving data to file: " + xmlFileName);

            //TODO: I/O exception about file
            File xmlFile = new File(xmlFileName);
            if (xmlFile.exists()) {
                xmlFile.delete();
            }

            try {
                JaxbParser xmlParser = new JaxbParser();
                xmlParser.saveObject(pp.getOffers(), xmlFile);
            } catch (JAXBException e) {
                e.printStackTrace();
                log.error("JAXBException!");
            }
        } else {
            log.info("No offers was found.");
        }
        log.info("");
        log.info("              Done");
        log.info("********************************");
        log.info("");
        log.info("");

    }

    private static void loadProperties(String keyword) {
        PropertyLoader pl = new PropertyLoader();
        if (pl.isLoaded()) {
            url = pl.getSearchURL() + keyword;
        } else {
            log.info("The following url will be used:");
            url = "https://www.aboutyou.de/suche?term=" + keyword;
            log.info(url);
        }
        if (pl.isUseSimpleName()) {
            xmlFileName = "aboutyou.de.xml";
            return;
        }
        xmlFileName = pl.isLoaded() ? "" : "aboutyou.de";
        xmlFileName += pl.getXmlFileName();
    }

}