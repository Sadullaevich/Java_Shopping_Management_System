package com.mycompany.cw_w2019940;

/**
 *
 * @author w2019940
 */
    public class Clothing extends Product{
    // private instance variables for product subclass
    private String size;
    private String color;

    //constructors
    public Clothing(String productId, String productName, int avaItems, 
            double price, String size, String color) {
        super(productId, productName, avaItems, price);
        this.size = size;
        this.color = color;
    }

    // get/set methods
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getProduct(){
        return "Clothing";
    }
    
    //override in clothing subclass
    @Override
    public String toString() {
        return " || Clothing: subclass of Product, " + "Product ID: " + super.toString() + "\n" +
                "Size: " + size + "\n" +
                "Color: " + color;
    }
}
