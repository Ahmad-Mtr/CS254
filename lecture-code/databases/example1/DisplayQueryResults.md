```java
package additionalcomponents.databaseexamples;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class DisplayQueryResults extends JFrame {

    private ResultSetTableModel tableModel;
    private JTextArea queryArea;

    // create ResultSetTableModel and GUI
    public DisplayQueryResults() {
        super("Displaying Query Results");

        String driver = "com.mysql.cj.jdbc.Driver";

        // URL to connect to books database
        String url = "jdbc:mysql://localhost:3306/cs254";
        String login = "swingUser";
        String passwd = "swingUser123";

        // query to select entire authors table
        String query = "SELECT * FROM authors";

        // SELECT * FROM authors
        tableModel = new ResultSetTableModel(driver, url, login, passwd, query);

        // set up JTextArea in which user types queries
        queryArea = new JTextArea(query, 3, 100);
        queryArea.setWrapStyleWord(true);
        queryArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(queryArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // set up JButton for submitting queries
        JButton submitButton = new JButton("Submit Query");

        // create Box to manage placement of queryArea and 
        // submitButton in GUI
        Box box = Box.createHorizontalBox();
        box.add(scrollPane);
        box.add(submitButton);

        // create JTable delegate for tableModel 
        JTable resultTable = new JTable(tableModel);

        // place GUI components on content pane
        Container c = getContentPane();
        c.add(box, BorderLayout.NORTH);
        c.add(new JScrollPane(resultTable),
                BorderLayout.CENTER);

        // create event listener for submitButton
        submitButton.addActionListener(
                new ActionListener() {

            // pass query to table model
            public void actionPerformed(ActionEvent e) {
                // perform a new query
                try {
                    tableModel.setQuery(queryArea.getText());
                } // catch SQLExceptions that occur when 
                // performing a new query
                catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null,
                            sqlException.toString(),
                            "Database error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }  // end actionPerformed

        } // end ActionListener inner class          

        ); // end call to addActionListener

        // set window size and display window
        setSize(500, 250);
        setVisible(true);

    }  // end DisplayQueryResults constructor

    // execute application
    public static void main(String args[]) {
        DisplayQueryResults app = new DisplayQueryResults();

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