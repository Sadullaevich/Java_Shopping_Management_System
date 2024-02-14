/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cw_w2019940;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author w2019940
 */
public class ShoppingCart {
    private ArrayList<Product> prodList;

    public ShoppingCart() {
        this.prodList = new ArrayList<>();
    }

    public void addToCart(Product product) {
        prodList.add(product);
    }

    public void removeFromCart(Product product) {
        prodList.remove(product);
    }

     public double calculateTotalCost() {
        double totalCost = 0;
        Map<String, Integer> categoryCount = new HashMap<>();

        // Calculate total cost of all items in the cart and count product categories
        for (Product item : prodList) {
            totalCost += item.getPrice();
            String category = getCategory(item);
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        }

        // Apply discount if eligible
        totalCost = applyDiscount(totalCost, categoryCount);
        return totalCost;
    }

    private double applyDiscount(double totalCost, Map<String, Integer> categoryCount) {
        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            if (entry.getValue() >= 3) {
                // Apply 20% discount for at least three products of the same category
                double categoryCost = getCategoryTotalCost(entry.getKey());
                totalCost -= categoryCost * 0.2; // Apply discount for the category
            }
        }
        return totalCost;
    }

    private String getCategory(Product product) {
        // Implement logic to determine the category of the product
        if (product instanceof Electronics) {
            return "Electronics";
        } else if (product instanceof Clothing) {
            return "Clothing";
        }
        return "Other";
    }

    private double getCategoryTotalCost(String category) {
        double categoryTotalCost = 0;
        for (Product item : prodList) {
            if (getCategory(item).equals(category)) {
                categoryTotalCost += item.getPrice();
            }
        }
        return categoryTotalCost;
    }
    
     public ArrayList<Product> getCartItems() {
        return prodList;
    }

    public void setCartItems(ArrayList<Product> prodList) {
        this.prodList = prodList;
    }
}
