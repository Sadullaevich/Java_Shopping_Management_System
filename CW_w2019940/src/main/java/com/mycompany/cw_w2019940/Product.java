package com.mycompany.cw_w2019940;

/**
 *
 * @author w2019940
 */
public abstract class Product implements Comparable<Product> {
//    private instance variables
    private String productId;
    private String productName;
    private int avaItems; //available items 
    private double price;
    
//    constructors setting
    public Product(String productId, String productName, int avaItems, double price) {
        this.productId = productId;
        this.productName = productName;
        this.avaItems = avaItems;
        this.price = price;
        }

// get/set methods
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableItems() {
        return avaItems;
    }

    public void setAvailableItems(int avaItems) {
        this.avaItems = avaItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String toString() {
        return "Product ID: " + productId + "\n" +
                "Product Name: " + productName + "\n" +
                "Available Items: " + avaItems + "\n" +
                "Price: " + price;
}
    
    public abstract String getProduct();
    
    @Override
    public int compareTo(Product otherProduct) {
        return this.productId.compareTo(otherProduct.productId);
    }
}

