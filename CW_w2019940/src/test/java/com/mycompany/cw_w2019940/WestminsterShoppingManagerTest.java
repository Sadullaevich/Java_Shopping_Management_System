package com.mycompany.cw_w2019940;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WestminsterShoppingManagerTest {

    private WestminsterShoppingManager shoppingManager;

    @BeforeEach
    public void setUp() {
        shoppingManager = new WestminsterShoppingManager(50); // Initialize the WestminsterShoppingManager object
    }

    @AfterEach
    public void tearDown() {
        shoppingManager = null; // Clean up resources after each test method
    }

    @Test
    public void testAddProduct() {
        Product product = new Electronics("E1", "Laptop", 5, 1500.0, "Dell", 2);
        shoppingManager.addProduct(product);
        assertEquals(1, shoppingManager.getProdList().size());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Clothing("C1", "T-Shirt", 10, 25.0, "M", "Red");
        shoppingManager.addProduct(product);
        assertEquals(1, shoppingManager.getProdList().size());

        shoppingManager.deleteProduct("C1");
        assertEquals(0, shoppingManager.getProdList().size());
    }

    @Test
    public void testPrintProducts() {
        
        PrintStream originalOut = System.out;
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        
        Product electronics = new Electronics("E1", "Laptop", 5, 1500.0, "Dell", 2);
        Product clothing = new Clothing("C1", "T-Shirt", 10, 25.0, "M", "Red");
        shoppingManager.addProduct(electronics);
        shoppingManager.addProduct(clothing);

        ArrayList<String> output = new ArrayList<>();
        
        
        
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                output.add(String.valueOf((char) b));
            }
        }));

        shoppingManager.printProducts();
        assertTrue(output.contains("Product = Electronics" + electronics.toString()));
        assertTrue(output.contains("Product = Clothing" + clothing.toString()));
        
        System.setOut(originalOut);
    }

    @Test
    public void testSaveAndLoadFromFile() {
        Product electronics = new Electronics("E1", "Laptop", 5, 1500.0, "Dell", 2);
        Product clothing = new Clothing("C1", "T-Shirt", 10, 25.0, "M", "Red");
        shoppingManager.addProduct(electronics);
        shoppingManager.addProduct(clothing);

        shoppingManager.saveToFile("testProducts.csv");

        WestminsterShoppingManager newManager = new WestminsterShoppingManager(50);
        newManager.loadFromFile("testProducts.csv");

        assertEquals(2, newManager.getProdList().size());
        assertEquals(electronics, newManager.getProdList().get(0));
        assertEquals(clothing, newManager.getProdList().get(1));

        File file = new File("testProducts.csv");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testRunMenu_AddProduct() {
        String input = "1\n10\nE1\nLaptop\n5\n1500.0\nDell\n2\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean exit = shoppingManager.runMenu();

        assertFalse(exit);
        assertEquals(1, shoppingManager.getProdList().size());
    }

    @Test
    public void testRunMenu_DeleteProduct() {
        Product product = new Electronics("E1", "Laptop", 5, 1500.0, "Dell", 2);
        shoppingManager.addProduct(product);

        String input = "2\nE1\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean exit = shoppingManager.runMenu();

        assertFalse(exit);
        assertEquals(0, shoppingManager.getProdList().size());
    }

    @Test
    public void testRunMenu_PrintProducts() {
        Product product = new Clothing("C1", "T-Shirt", 10, 25.0, "M", "Red");
        shoppingManager.addProduct(product);

        String input = "3\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean exit = shoppingManager.runMenu();

        assertFalse(exit);
        assertEquals(1, shoppingManager.getProdList().size());
    }

    @Test
    public void testRunMenu_Exit() {
        String input = "4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean exit = shoppingManager.runMenu();

        assertTrue(exit);
    }

    @Test
    public void testRunMenu_InvalidChoice() {
        String input = "6\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean exit = shoppingManager.runMenu();

        assertFalse(exit);
    }
}
