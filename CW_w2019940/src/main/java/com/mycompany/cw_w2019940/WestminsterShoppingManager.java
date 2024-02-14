package com.mycompany.cw_w2019940;

/**
 *
 * @author w2019940
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;



public class WestminsterShoppingManager implements ShoppingManager{
    private ArrayList<Product> prodList;
    private int numProducts;
    
    //constructor
    public WestminsterShoppingManager(int numProducts) {
        this.prodList = new ArrayList<>();
        this.numProducts = numProducts;
    }

    //add products maximum of 50
    @Override
    public void addProduct(Product product) {
        if (prodList.size() < numProducts) {
            prodList.add(product);
            saveToFile("prod.csv");
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Maximum product limit reached. Cannot add more products.");
        }
    }

    @Override
    public void deleteProduct(String productId) {
        boolean found = false;
        for (int i = 0; i < prodList.size(); i++) {
            Product product = prodList.get(i);
            if (product.getProductId().equals(productId)) {
                prodList.remove(i);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Product with ID " + productId + " has been deleted.");
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
        System.out.println("Total products left: " + prodList.size());
    }
   
   @Override   
   public void printProducts(){
        Collections.sort(prodList); // Sort by productID
        for(int i=0; i < prodList.size(); i++){
            
            if(prodList.get(i).getProduct().equals("Electronics") ){
                System.out.println("Product = Electronics" + prodList.get(i));
            }
            
            if(prodList.get(i).getProduct().equals("Clothing") ){
                System.out.println("Product = Clothing" + prodList.get(i));
            }
        }
    }
 // save added products to csv file
    @Override
     public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : prodList) {
                if (product instanceof Electronics) {
                    writer.write("Electronics," + product.getProductId() + "," +
                            product.getProductName() + "," + product.getAvailableItems() + "," +
                            product.getPrice() + "," + ((Electronics) product).getBrand() + "," +
                            ((Electronics) product).getWarrantyPeriod() + "\n");
                } else if (product instanceof Clothing) {
                    writer.write("Clothing," + product.getProductId() + "," +
                            product.getProductName() + "," + product.getAvailableItems() + "," +
                            product.getPrice() + "," + ((Clothing) product).getSize() + "," +
                            ((Clothing) product).getColor() + "\n");
                }
            }
            System.out.println("Products saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
      
     //load added products from csv file
    @Override    
    public void loadFromFile(String fileName){
    try (BufferedReader reader = new BufferedReader(new FileReader("prod.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(",");
                
                String type = attributes[0];
                String productId = attributes[1];
                String productName = attributes[2];
                int availableItems = Integer.parseInt(attributes[3]);
                double price = Double.parseDouble(attributes[4]);

                // Check the type of product and create the respective object
                if (type.equals("Electronics")) {
                    String brand = attributes[5];
                    int warrantyPeriod = Integer.parseInt(attributes[6]);

                    Electronics electronics = new Electronics(productId, productName,
                            availableItems, price, brand, warrantyPeriod);
                    prodList.add(electronics);
                } else if (type.equals("Clothing")) {
                    String size = attributes[5];
                    String color = attributes[6];

                    Clothing clothing = new Clothing(productId, productName,
                            availableItems, price, size, color);
                    prodList.add(clothing);
                }
            }
            System.out.println("Products loaded from file: " + "prod.csv");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
      }
    
    public ArrayList<Product> getProdList() {
        return prodList;
    }
    


     
//     console menu application implementation 
    
    @Override    
     public boolean runMenu(){
        Scanner s = new Scanner (System.in);
        boolean exit = false;
        
        System.out.println("\nWestminster Shopping Manager Menu");              
        System.out.println("To Add a new product press 1");
        System.out.println("To delete a product from the list press 2");
        System.out.println("To print the list of the products press 3");
        System.out.println("To exit press 4");
        System.out.println("To open the GUI press 5");
        
        
        int choise = s.nextInt();
        s.nextLine(); // Consume newline character
        
            switch(choise){
                case 1:
                    System.out.println("Press 10 if you want to add an Electronic");
                    System.out.println("Press 20 if you want to add a Clothing");
                
                    
                    int choise2 = s.nextInt();
                    s.nextLine();
                    System.out.println("Enter the ID of the Product");
                    String productId = s.nextLine();
                
                    System.out.println("Enter the name of the Product");
                    String productName = s.nextLine();
                
                    System.out.println("Enter the number of available items");
                    int avaItems = s.nextInt();
                
                    System.out.println("Enter the price of the Product");
                    double price = s.nextDouble();
                
                    
                    switch(choise2){
                        case 10:
                            
                            s.nextLine();
               // it is an electronic
                            System.out.println("Enter the brand of Electronics");
                            String brand = s.nextLine();
                            System.out.println("Enter the warranty period of Electronics in years");
                            int warrantyPeriod = s.nextInt();
                    
                            Electronics e = new Electronics(productId, productName, 
                                avaItems, price, brand, warrantyPeriod) {};
                            this.addProduct(e);
                            break;

                        
                        case 20:
                    // it is a clothing
                            s.nextLine();
                            System.out.println("Insert the size");
                            String size = s.nextLine();
                       
                            System.out.println("Insert the colour");
                            String color = s.nextLine();
                        
                            Clothing c = new Clothing(productId, productName, 
                                avaItems, price, size, color) {};
                            this.addProduct(c);
                            break;
                                 }
                break;
                
                
                case 10:
                    this.printProducts();
                    break;
            
                case 20:
                    exit = true;
                    break;
                case 2:
                // Delete a product
                    System.out.print("Enter Product ID to delete: ");
                    String productIdToDelete = s.nextLine();
                    deleteProduct(productIdToDelete);
                    break;
                case 3:
                // Print products
                    printProducts();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                case 5:
                    ShoppingCenterGUI gui = new ShoppingCenterGUI();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
        }
        return exit;
        }
     }


