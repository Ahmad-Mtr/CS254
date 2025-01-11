```java
package Products;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.*;

public class View extends JFrame {

    JComboBox productIdsComboBox;
    JTextField productNameTextField, productPriceTextField, productQuantityTextField;
    JButton searchButton, addButton, updateButton, deleteButton, newButton;

    public View() {
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));

        JLabel productIdLabel = new JLabel("Product ID");
        JLabel productNameLabel = new JLabel("Product Name");
        JLabel productPriceLabel = new JLabel("Product Price");
        JLabel productQuantityLabel = new JLabel("Product Quantity");

        productIdsComboBox = new JComboBox();
        productNameTextField = new JTextField();
        productPriceTextField = new JTextField();
        productQuantityTextField = new JTextField();

        searchButton = new JButton("Search");
        newButton = new JButton("New");
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        JPanel searchPanel = new JPanel();
        searchPanel.add(productIdLabel);
        searchPanel.add(productIdsComboBox);
        searchPanel.add(searchButton);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2));
        infoPanel.add(productNameLabel);
        infoPanel.add(productNameTextField);
        infoPanel.add(productPriceLabel);
        infoPanel.add(productPriceTextField);
        infoPanel.add(productQuantityLabel);
        infoPanel.add(productQuantityTextField);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(addButton);
        buttonsPanel.add(updateButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(newButton);

        mainPanel.add(searchPanel);
        mainPanel.add(infoPanel);
        mainPanel.add(buttonsPanel);

        this.add(mainPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    public void fillProducts(String productIDs[]) {
        productIdsComboBox.setModel(new DefaultComboBoxModel(productIDs));
    }

    public void addListeners(ActionListener al) {
        addButton.addActionListener(al);
        updateButton.addActionListener(al);
        deleteButton.addActionListener(al);
        newButton.addActionListener(al);
        searchButton.addActionListener(al);
    }

    public void addWindowHandler(WindowListener wl) {
        this.addWindowHandler(wl);
    }

    public void setProductInfo(ProductModel product) {
        productNameTextField.setText(product.getProductName());
        productPriceTextField.setText(product.getProductPrice() + "");
        productQuantityTextField.setText(product.getProductQuantity() + "");
    }

    public ProductModel getProductInfo() {
        ProductModel product = new ProductModel();
        product.setProductName(productNameTextField.getText());
        try {
            product.setProductPrice(Double.parseDouble(productPriceTextField.getText()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please Enter a Valid Price");
        }
        try {
            product.setProductQuantity(Integer.parseInt(productQuantityTextField.getText()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please Enter A Valid Quantity");
        }

        return product;
    }

    public String getProductIDToSearchFor() {
        return productIdsComboBox.getSelectedItem().toString();
    }

    public void clear() {
        productNameTextField.setText("");
        productPriceTextField.setText("");
        productQuantityTextField.setText("");
        productIdsComboBox.setSelectedIndex(0);
    }
}

```