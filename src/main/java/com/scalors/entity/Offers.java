package com.scalors.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by voksus on 25.05.2017.
 */
@XmlRootElement
public class Offers {

    @XmlElement(name = "offer")
    public List<Offer> offers;

    public void setOffers(List<Offer> offerList) {
        offers = offerList;
    }

    public void addOffers(List<Offer> offerList) {
        offers.addAll(offerList);
    }

    public int getSize() {
        if (offers == null) {
            return 0;
        }
        return offers.size();
    }

}