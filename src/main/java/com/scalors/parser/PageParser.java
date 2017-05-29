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

    private String url;
    private String nextPage;
    private List<Offer> offerList = new ArrayList<>();
    private HTMLFactory searchPage;
    private Document doc;
    private Offers offers = new Offers();

    public List<Offer> start(String startUrl) {
        offers.setOffers(offerList);
        setUrl(startUrl);
        do {
            parsePage();
            setUrl(nextPage);
            if (url == null) break;
            int nextPageNumber = url.indexOf("page=");
            if (nextPageNumber >= 0) {
                log.info("-----------------------");
                log.info("Loading page: " + url.substring(nextPageNumber + 5));
                log.info("------");
            }
        } while (url != null);
        return offerList;
    }

    private void parsePage() {

        searchPage = new HTMLFactory(url);
        doc = searchPage.getDoc();

        if (searchPage.isLoaded()) {
            Element checkElm = doc.getElementsByClass("list-wrapper").first();
            if (checkElm != null) {
                Elements offersList = checkElm.getElementsByAttributeValue("itemprop", "itemListElement");
                for (Element offerElm : offersList) {
                    Offer offer = new OfferParser(offerElm).getOffer();
                    offerList.add(offer);
                }
                Element nextPageElm = checkElm.getElementsByClass("col-xs-4 productlist-item-border").first();
                if (nextPageElm != null) {
                    nextPage = doc.baseUri() + nextPageElm.getElementsByTag("a").attr("href");
                } else {
                    nextPage = null;
                }
            } else {
                log.info("No offers was found.");
            }
        }
    }

    public Offers getOffers() {
        return offers;
    }

    private void setUrl(String url) {
        this.url = url;
    }

}