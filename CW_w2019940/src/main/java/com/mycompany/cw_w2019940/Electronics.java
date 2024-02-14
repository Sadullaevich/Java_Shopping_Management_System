package com.mycompany.cw_w2019940;

/**
 *
 * @author w2019940
 */
public class Electronics extends Product{
    //private instance variables
    private String brand;
    private int warrantyPeriod;
    
    //constructors
    public Electronics(String productId, String productName, int avaItems,
            double price, String brand, int warrantyPeriod) {
        super(productId, productName, avaItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }
    
    // get/set methods
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
    
    @Override
    public String getProduct(){
        return "Electronics";
    }
    
    // override in electronics subclass
    @Override
    public String toString() {
        return " || Electronics: subclass of Product, " + "Product ID: " + super.toString() + "\n" +
                "Brand: " + brand + "\n" +
                "Warranty Period: " + warrantyPeriod + " years";
    }
}
