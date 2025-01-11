```java
package Products;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Database {

    private Connection con;

    public Database() {
    }

    public void connect() throws Exception {

        if (con != null) {
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver not found");
        }

        String url = "jdbc:mysql://localhost:3306/cs254";

        con = DriverManager.getConnection(url, "swingUser", "swingUser123");
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Can't close connection");
            }
        }
    }

    public ArrayList<String> LoadProductno() {
        ArrayList<String> productsList = new ArrayList<String>();
        try {
            PreparedStatement pst;
            pst = con.prepareStatement("SELECT id FROM products");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                productsList.add(rs.getInt(1) + "");
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productsList;
    }

    public ProductModel getProductInfo(int id) {
        ProductModel product = null;
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM products where id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next() == true) {
                product = new ProductModel();
                product.setProductID(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setProductPrice(rs.getDouble(3));
                product.setProductQuantity(rs.getInt(4));
            } else {
                JOptionPane.showMessageDialog(null, "Record Not Found");
            }
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;

    }

    public void updateProductInfo(ProductModel p, int id) {
        String pname = p.getProductName();
        Double price = p.getProductPrice();
        Integer qty = p.getProductQuantity();
        try {
            PreparedStatement pst;
            pst = con.prepareStatement("update products set name = ?, price = ?, quantity= ? where id = ?");
            pst.setString(1, pname);
            pst.setDouble(2, price);
            pst.setInt(3, qty);
            pst.setInt(4, id);
            int k = pst.executeUpdate();
            if (k == 1) {
                JOptionPane.showMessageDialog(null, "Record Updated!");
            } else {
                JOptionPane.showMessageDialog(null, "Record update Failed!");
            }
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        try {

            PreparedStatement pst;
            pst = con.prepareStatement("delete from products where id =?");
            pst.setInt(1, id);
            int k = pst.executeUpdate();

            if (k == 1) {
                JOptionPane.showMessageDialog(null, "Record Deleted!");
            } else {
                JOptionPane.showMessageDialog(null, "Record Deletion Failed");
            }
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addProduct(ProductModel p) {
        try {
            PreparedStatement pst;
            pst = con.prepareStatement("insert into products(name,price,quantity)values(?,?,?)");
            pst.setString(1, p.getProductName());
            pst.setDouble(2, p.getProductPrice());
            pst.setInt(3, p.getProductQuantity());
            int k = pst.executeUpdate();
            if (k == 1) {
                JOptionPane.showMessageDialog(null, "Record Added!");

            } else {
                JOptionPane.showMessageDialog(null, "Record Insertion Failed");
            }
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

```