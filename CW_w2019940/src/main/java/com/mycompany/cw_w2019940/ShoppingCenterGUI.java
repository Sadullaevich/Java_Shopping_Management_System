package com.mycompany.cw_w2019940;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ShoppingCenterGUI extends JFrame {
// GUI components declaration
    private ArrayList<Product> prodList;
    private JComboBox<String> categoryDropdown;
    private JTable productTable;
    private JTable shoppingCartTable;
    private JTextArea productDetailsTextArea;
    private JButton addToCartButton;
    private JButton viewCartButton;
    private DefaultTableModel productTableModel;
    private DefaultTableModel shoppingCartTableModel;
    private JLabel cartTotalLabel; // Assuming this JLabel represents the total in your GUI


    public ShoppingCenterGUI() {
        // Initialize components
        super("Westminster Shopping Center");
        categoryDropdown = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        productTableModel = new DefaultTableModel(new Object[]{"Product ID", "Name", "Category", "Price(€)", "Info"}, 0);
        shoppingCartTableModel = new DefaultTableModel(new Object[]{"Product", "Quantity", "Price"}, 0);
        productTable = new JTable(productTableModel);
        shoppingCartTable = new JTable(shoppingCartTableModel);
        productDetailsTextArea = new JTextArea(5, 20);
        addToCartButton = new JButton("Add to Shopping Cart");
        viewCartButton = new JButton("Shopping Cart");

        // Layout configuration
        setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        northPanel.add(categoryDropdown);
        northPanel.add(viewCartButton);
        add(northPanel, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(productTable), new JScrollPane(shoppingCartTable));
        add(splitPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(new JScrollPane(productDetailsTextArea), BorderLayout.CENTER);
        southPanel.add(addToCartButton, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);

// Method to initialize the listeners
  
    // Listener for the category dropdown
    categoryDropdown.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement logic to filter the product table based on the selected category
            String selectedCategory = (String) categoryDropdown.getSelectedItem();
            filterProductTableByCategory(selectedCategory);
        }
    });

    // Listener for the add to cart button
    addToCartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement logic to add the selected product to the shopping cart
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                addToCart(selectedRow);
            }
        }
    });

    // Listener for selecting a product in the table
    productTable.getSelectionModel().addListSelectionListener(event -> {
        if (!event.getValueIsAdjusting()) {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                displayProductDetails(selectedRow);
            }
        }
    });

        
        // Finalize and display the GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

// Method to filter the product table by category
private void filterProductTableByCategory(String product) {
    DefaultTableModel model = (DefaultTableModel) productTable.getModel();
    model.setRowCount(0); // Clear the table

    // Assuming productList contains the products data
    for (Product item : prodList) {
        if (item.getProduct().equals(item)) {
            model.addRow(new Object[]{item.getProductId(), item.getProductName(), item.getPrice(), item.getAvailableItems()});
        }
    }
}


// Method to add a product to the shopping cart
private void addToCart(int selectedRow) {
    DefaultTableModel cartModel = (DefaultTableModel) productTable.getModel();
    Object[] productData = getProductDataFromTableModel(selectedRow);

    // Add the selected product to the cart table model
    cartModel.addRow(productData);

    // Update the cart total
    updateShoppingCartTotal();
}

// Method to display details of the selected product
private void displayProductDetails(int selectedRow) {
    Object[] productData = getProductDataFromTableModel(selectedRow);

    // Display details in the text area
    productDetailsTextArea.setText(formatProductDetails(productData));
}

// Method to update the shopping cart total with discounts
   private void updateShoppingCartTotal() {
    double total = calculateTotalWithDiscounts();
    if (cartTotalLabel != null) {
        cartTotalLabel.setText("Total: $" + total);
    } else {
        System.err.println("cartTotalLabel is not initialized or set.");
    }
}


// Helper method to get product data from the table model
private Object[] getProductDataFromTableModel(int selectedRow) {
    DefaultTableModel model = (DefaultTableModel) productTable.getModel();
    Object[] productData = new Object[model.getColumnCount()];
    for (int i = 0; i < model.getColumnCount(); i++) {
        productData[i] = model.getValueAt(selectedRow, i);
    }
    return productData;
}

// Helper method to format product details
private String formatProductDetails(Object[] productData) {
    // Format and return the product details as a string
    return "Product Details: " + productData[0] + " - " + productData[1] + " - $" + productData[2];
}

// Helper method to calculate total with discounts
private double calculateTotalWithDiscounts() {
    // Calculate the total with applied discounts
    double total = 0.0;

    // Assuming cartTableModel contains the cart items with prices
    for (int i = 0; i < productTable.getRowCount(); i++) {
        double price = (double) productTable.getValueAt(i, 2); // Assuming price is in the third column
        total += price;
    }

    // Apply discounts or additional logic here if needed

    return total;
}

    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShoppingCenterGUI());
    }
}