package com.mycompany.cw_w2019940;

/**
 *
 * @author w2019940
 */
public class CW_w2019940 {

    public static void main(String[] args) {
        ShoppingManager sys = new WestminsterShoppingManager(50) {};      
        boolean exit = false;
        sys.loadFromFile("prod.csv");
        while (!exit){
            exit = sys.runMenu();
        }
    }
}
