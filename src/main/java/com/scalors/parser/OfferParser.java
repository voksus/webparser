package com.scalors.parser;

import com.scalors.entity.Offer;
import com.scalors.htmlFactory.HTMLFactory;
import com.scalors.util.PropertyLoader;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by voksus on 26.05.2017.
 */
public class OfferParser {
    private static final Logger log = Logger.getLogger(OfferParser.class);

    private Offer offer = new Offer();
    private String offerURL;
    private boolean loadFromDirectOffer = false;
    HTMLFactory offerPage;

    public OfferParser(Element offerElm) {

        Elements nameElm = offerElm.getElementsByClass("product-name-link");
        if (nameElm == null) {
            log.error("Offer name not found.");
            return;
        }
        offerURL = PropertyLoader.getMainURL() + nameElm.attr("href");
        offer.setName(nameElm.text());

        setArticleID(offerElm);

        setBrand(offerElm);

        setPrices(offerElm);

        setAllSizes();

        log.info(offer);
    }

    private void setPrices(Element offerElm) {
        Elements pricesElm = offerElm.getElementsByClass("product-price");
        Element priceElm;
        if (pricesElm != null && (priceElm = pricesElm.first()) != null) {

            // Initial Price
            Elements initialPriceElm = priceElm.getElementsByClass("isStriked");
            if (initialPriceElm != null && initialPriceElm.first() != null) {
                String initPrice = initialPriceElm.first().getElementsByTag("span").first()
                        .attr("data-original");
                offer.setInitialPrice(initPrice);
            }

            // Current price
            Elements actualPriceElm = priceElm.getElementsByClass("actual-price");
            if (actualPriceElm != null && actualPriceElm.first() != null) {
                String actualPrice = actualPriceElm.first().getElementsByTag("span").first()
                        .attr("data-original");
                offer.setPrice(actualPrice);
            }
        }
    }

    private void setBrand(Element offerElm) {
        Elements brandElm = offerElm.getElementsByClass("js-product-item-brand");
        if (brandElm != null) {
            offer.setBrand(brandElm.text());
        }
    }

    private void setArticleID(Element offerElm) {
        offer.setArticleId(offerElm.select("article").attr("data-product-id"));
    }

    private void setAllSizes() {

        loadOffer();

        if (offerPage != null && offerPage.isLoaded()) {
            Document offerDoc = offerPage.getDoc();

            Elements availableSizesElm = offerDoc.getElementsByClass("sizeDropdown__row");
            if (availableSizesElm != null) {
                List<String> allSizes = new ArrayList<>();

                for (Element sizeElm : availableSizesElm) {
                    if (!sizeElm.hasAttr("disabled")) {
                        allSizes.add(sizeElm.getElementsByClass("sizeDropdown__item--paddingLeft").text());
                    }
                }
                offer.setAllSizes(allSizes);
            }

            String size = offerDoc.getElementsByClass("col-xs-10 adp-selector sizeDropdown").select("sizeDropdown__placeholder").text();
//            if (!size.trim().equals("")) {
                offer.setSize(size);
//            }

        } else if (loadFromDirectOffer) {
            log.error("Page could not be loaded: " + offerURL);
        }
    }

    private void loadOffer() {
        if (offerPage == null && loadFromDirectOffer) {
            offerPage = new HTMLFactory(offerURL);
        }
    }

    public Offer getOffer() {
        return offer;
    }

}