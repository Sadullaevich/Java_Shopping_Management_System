package com.mycompany.cw_w2019940;

/**
 *
 * @author w2019940
 */

interface ShoppingManager {
    public void addProduct(Product product);
    public void deleteProduct(String productId);
    public void printProducts();
    public void saveToFile(String fileName);
    public void loadFromFile(String fileName);
    public abstract boolean runMenu();
}

