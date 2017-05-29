package com.scalors.htmlFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by voksus on 25.05.2017.
 */
public class HTMLFactory {
    private static final Logger log = Logger.getLogger(HTMLFactory.class);

    private boolean isLoaded;
    private Document doc;

    public HTMLFactory(String url) {
        try {
            doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
                    .get();
//            log.info(doc.location());
            if (url.lastIndexOf("-") >= 0 ) save(doc.html(), url.substring(url.lastIndexOf("-")));
            if (url.lastIndexOf("term") >= 0 ) save(doc.html(), url.substring(url.lastIndexOf("term")));
            isLoaded = true;
        } catch (IOException e) {
            isLoaded = false;
            log.error("Unable to load web page: " + url);
            log.error(e.getMessage());
        }
    }

    private void save(String html, String name) {
        name += ".html";

        try {

            FileWriter writer = new FileWriter(new File(name), false);

            writer.write(html);
            writer.flush();

        } catch (IOException e) {
            log.error("Fail to save temporary file: " + name);
        }
    }

    public Document getDoc() {
        return doc;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

}