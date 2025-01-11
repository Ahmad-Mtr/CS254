```java
package additionalcomponents.databaseexamples;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

// Java extension packages
import javax.swing.*;

public class AuthorsTable extends JFrame {

    private ResultSetTableModel tableModel;
    private JTextArea queryArea;

    // create ResultSetTableModel and GUI
    public AuthorsTable() {
        super("Displaying Query Results");
        // Create a Properties object to hold the configuration
        Properties props = new Properties();

        // Specify the path to the properties file
        String fileName = "config.properties"; // Adjust the path accordingly
        String url = "", login = "", passwd = "", driver = "";
        try (FileInputStream in = new FileInputStream(fileName)) {
            // Load the properties from the file
            props.load(in);
            in.close();
            // Access properties using getProperty()
            url = props.getProperty("db.url");
            login = props.getProperty("db.username");
            passwd = props.getProperty("db.password");
            driver = props.getProperty("db.driver");

        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions like file not found or I/O errors
        }

        // query to select entire authors table
        String query = "SELECT * FROM authors";

        // create TableModel for results of query
        // SELECT * FROM authors
        tableModel = new ResultSetTableModel(driver, url, login, passwd, query);
        // Create "Add Row" button
        JButton addRowButton = new JButton("Add Row");
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addRowToTable(tableModel);
            }
        });

        JTable resultTable = new JTable(tableModel);
        JButton deleteRowButton = new JButton("Delete Row");
        deleteRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = resultTable.getSelectedRow();
                if (row != -1) {
                    tableModel.deleteSelectedRow(row);
                }
            }
        });
        // Create "Add Row" button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tableModel.getResultSetRowCount() < tableModel.getRowCount()) {
                    int lastRow = tableModel.getRowCount() - 1;

                    // Check if all cells in the last row are filled
                    if (tableModel.isRowFilled(lastRow)) {
                        // Insert the row into the database
                        tableModel.insertRowIntoDatabase(lastRow);
                        JOptionPane.showMessageDialog(null, "Row saved to database.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill all cells in the row before saving.");
                    }
                } else if (tableModel.getResultSetRowCount() == tableModel.getRowCount()) {
                    int row = resultTable.getSelectedRow();
                    if (row != -1) {
                        tableModel.updateRowDatabase(row);
                    }
                }
            }
        });
        // place GUI components on content pane
        Container c = getContentPane();
        JPanel p = new JPanel();
        BoxLayout b = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(b);
        p.add(addRowButton);
        p.add(deleteRowButton);
        p.add(saveButton);
        c.add(p, BorderLayout.NORTH);
        c.add(
                new JScrollPane(resultTable),
                BorderLayout.CENTER);
        // set window size and display window
        setSize(
                500, 250);
        setVisible(
                true);

    } // end DisplayQueryResults constructor

    // Adding a row to the model
    private void addRowToTable(ResultSetTableModel model) {
        Object[] newRow = new Object[3]; // Default values for a new employee
        newRow[0] = model.getRowCount() + 1;
        model.addRow(newRow); // Add the employee to the model
    }

    // execute application
    public static void main(String args[]) {
        AuthorsTable app = new AuthorsTable();

        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}  // end class DisplayQueryResults

/**
 * ************************************************************************
 * (C) Copyright 2001 by Deitel & Associates, Inc. and Prentice Hall. * All
 * Rights Reserved. * * DISCLAIMER: The authors and publisher of this book have
 * used their * best efforts in preparing the book. These efforts include the *
 * development, research, and testing of the theories and programs * to
 * determine their effectiveness. The authors and publisher make * no warranty
 * of any kind, expressed or implied, with regard to these * programs or to the
 * documentation contained in these books. The authors * and publisher shall not
 * be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 * ***********************************************************************
 */

```