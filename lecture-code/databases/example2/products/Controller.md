```java
package Products;

import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Controller extends WindowAdapter implements ActionListener {

    Database db;
    View view;

    public Controller(View v) {
        db = new Database();
        view = v;

        view.addListeners(this);
        view.addWindowHandler(this);
        view.fillProducts(loadProducts());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        db.disconnect();
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        try {
            db.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Search")) {
            String productID = view.getProductIDToSearchFor();
            int id = -1;
            try {
                id = Integer.parseInt(productID);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please Select A Product ID");
            }
            view.setProductInfo(db.getProductInfo(id));
        } else if (e.getActionCommand().equals("New")) {
            view.clear();
        } else if (e.getActionCommand().equals("Add")) {
            db.addProduct(view.getProductInfo());
            view.clear();
            view.fillProducts(loadProducts());
        } else if (e.getActionCommand().equals("Update")) {
            String productID = view.getProductIDToSearchFor();
            int id = -1;
            try {
                id = Integer.parseInt(productID);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please Select A Product ID");
            }

            db.updateProductInfo(view.getProductInfo(), id);
            view.clear();
            view.fillProducts(loadProducts());
        } else if (e.getActionCommand().equals("Delete")) {
            String productID = view.getProductIDToSearchFor();
            int id = -1;
            try {
                id = Integer.parseInt(productID);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please Select A Product ID");
            }
            db.deleteProduct(id);
            view.clear();
            view.fillProducts(loadProducts());
        }
    }

    public String[] loadProducts() {
        ArrayList<String> products = db.LoadProductno();
        String productIds[] = new String[products.size() + 1];
        productIds[0] = "Select Product ID";
        for (int i = 1; i <= products.size(); i++) {
            productIds[i] = products.get(i - 1);
        }

        return productIds;
    }
}

```