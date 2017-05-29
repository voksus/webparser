package com.scalors.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by voksus on 25.05.2017.
 */
@XmlSeeAlso({Offers.class})
@XmlType(propOrder = {
        "name",
        "brand",
        "color",
        "allColors",
        "price",
        "initialPrice",
        "size",
        "allSizes",
        "description",
        "articleId",
        "shippingCost"})
public class Offer {

    private String name;
    private String brand;
    private String color;
    private List<String> allColors;
    private String price;
    private String initialPrice;
    private String size;
    private List<String> allSizes;
    private String description;
    private String articleId;
    private String shippingCost;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    @XmlElement
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    @XmlElement
    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getAllColors() {
        return allColors;
    }

    @XmlElement
    public void setAllColors(List<String> allColors) {
        this.allColors = allColors;
    }

    public String getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    @XmlElement
    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getAllSizes() {
        return allSizes;
    }

    @XmlElement
    public void setAllSizes(List<String> allSizes) {
        this.allSizes = allSizes;
    }

    @XmlElement
    public void setInitialPrice(String initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getInitialPrice() {
        return initialPrice;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }

    @XmlElement
    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    @Override
    public String toString() {
        return "Offer{" +
                '\'' + name + '\'' +
                ", brand='" + brand + "'" +
                ", art.ID='" + articleId + "'" +
                ", col='" + color + "'" +
                ", sz=" + size +
                ". Pr=" + price +
                ", init.pr=" + initialPrice +
                ", shp.cost=" + shippingCost +
                "; \"" + description + "\"" +
                '}';
    }

}