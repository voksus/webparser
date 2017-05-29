package com.scalors.parser;

import com.scalors.entity.Offer;
import com.scalors.entity.Offers;
import com.scalors.htmlFactory.HTMLFactory;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by voksus on 26.05.2017.
 */
public class PageParser {
    private static final Logger log = Logger.getLogger(PageParser.class);

    private static String url;
    private static List<Offer> offerList = new ArrayList<>();
    private HTMLFactory searchPage;
    private Document doc;
    Offers offers = new Offers();

    public List<Offer> start(String url) {
        offers.setOffers(offerList);
        setUrl(url);
        parsePage();
        return offerList;
    }

    private void parsePage() {

        searchPage = new HTMLFactory(url);
        doc = searchPage.getDoc();

        if (searchPage.isLoaded()) {
            Elements offersList = doc.getElementsByClass("list-wrapper").first()
                    .getElementsByAttributeValue("itemprop", "itemListElement");
                for (Element offerElm : offersList) {
                    Offer offer = new OfferParser(offerElm).getOffer();
                    offerList.add(offer);
                }
        }
    }

    public Offers getOffers() {
        return offers;
    }

    private static void setUrl(String url) {
        PageParser.url = url;
    }

}