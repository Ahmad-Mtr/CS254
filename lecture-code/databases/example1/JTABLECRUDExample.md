```java
package additionalcomponents.databaseexamples;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.awt.event.*;
import javax.swing.table.AbstractTableModel;

public class JTableCRUDExample {

    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static String Drive;

    public static void main(String[] args) {
        // Initialize the UI on the Swing Event Dispatch Thread
        JTableCRUDExample t = new JTableCRUDExample();
        t.createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("JTable with CRUD Operations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the table
        JTable table = createTable();

        // Put the table in a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add buttons for Insert, Update, and Delete
        JPanel buttonPanel = new JPanel();
        JButton insertButton = new JButton("Insert");
        //JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(insertButton);
        //buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Create a listener object
        TableActionListener listener = new TableActionListener(table);

        // Add action listeners to buttons
        insertButton.addActionListener(listener);
        //updateButton.addActionListener(listener);
        deleteButton.addActionListener(listener);

        // Show the frame
        frame.setVisible(true);
    }

    private static JTable createTable() {
        try {

            Properties config = new Properties();
            try (FileInputStream configFile = new FileInputStream("config.properties")) {
                config.load(configFile);
                // Load the database connection properties from the config file
                URL = config.getProperty("db.url");
                USER = config.getProperty("db.username");
                PASSWORD = config.getProperty("db.password");
                Drive = config.getProperty("db.driver");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1); // Exit the program if config file is not found or there is an error
            }
            Class.forName(Drive);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            // Fetch the data using a scrollable and updatable ResultSet
            String query = "SELECT * FROM authors";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(query);

            // Create a table model from the ResultSet
            MyTableModel tableModel = new MyTableModel(resultSet, connection);

            // Create and return a JTable using the table model
            return new JTable(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
            return new JTable();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // ActionListener implementation
    public static class TableActionListener implements ActionListener {

        private JTable table;

        // Constructor to initialize with the table
        public TableActionListener(JTable table) {
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "Insert":
                    insertRow(table);
                    break;
                /*case "Update":
                    updateRow(table);
                    break;
                */case "Delete":
                    deleteRow(table);
                    break;
                default:
                    break;
            }
        }

        private void insertRow(JTable table) {
            try {
                // Get the TableModel
                // Get the TableModel
                MyTableModel model = (MyTableModel) table.getModel();
                ResultSet resultSet = model.getResultSet();
                resultSet.last();
                int id = resultSet.getInt(1);
                resultSet.moveToInsertRow(); // Move to the insert row
                resultSet.updateInt("authorID", id + 1);
                // Set the values for the new row
                resultSet.updateString("firstName", "");
                resultSet.updateString("lastName", "");

                // Insert the row into the ResultSet
                resultSet.insertRow();
                model.fireTableRowsInserted(model.getRowCount() - 1, model.getRowCount() - 1);

                // Focus on the newly inserted row and allow user input
                table.setRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);
                table.editCellAt(model.getRowCount() - 1, 1); // Focus on the 'firstname' column (column index 1)
                reloadData(table);
                model.fireTableDataChanged();
            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null,
                        sqlException.toString(),
                        "Database error", JOptionPane.ERROR_MESSAGE);

                System.exit(1);   // terminate application
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        e.toString(),
                        "Database error", JOptionPane.ERROR_MESSAGE);

            }

        }

        private void deleteRow(JTable table) {
            try {
                // Get the TableModel
                MyTableModel model = (MyTableModel) table.getModel();
                ResultSet resultSet = model.getResultSet();

                // Get the selected row
                int row = table.getSelectedRow();
                if (row != -1) {
                    resultSet.absolute(row + 1);  // ResultSet is 1-based
                    resultSet.deleteRow();
                }
                reloadData(table);

                // Notify the model of the data change
                model.fireTableDataChanged();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void reloadData(JTable table) {
            try {
                MyTableModel model = (MyTableModel) table.getModel();
                Connection connection = model.getConnection();

                // Fetch fresh data from the database
                String query = "SELECT * FROM authors";
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet result = statement.executeQuery(query);

                // Update the model with the new ResultSet
                model.setResultSet(result);

                // Notify the model of the data change
                model.fireTableDataChanged();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // Custom TableModel that handles a scrollable and updatable ResultSet
    public static class MyTableModel extends AbstractTableModel {

        private ResultSet resultSet;
        private ResultSetMetaData metaData;
        private int columnCount;
        private Connection connection;

        public MyTableModel(ResultSet resultSet, Connection con) {
            this.resultSet = resultSet;
            this.connection = con;
            try {
                metaData = resultSet.getMetaData();
                columnCount = metaData.getColumnCount();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getRowCount() {
            try {
                resultSet.last();
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();  // Reset the cursor position
                return rowCount;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override
        public int getColumnCount() {
            return columnCount;
        }

        @Override
        public String getColumnName(int columnIndex) {
            try {
                return metaData.getColumnName(columnIndex + 1);
            } catch (SQLException e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            try {
                resultSet.absolute(rowIndex + 1);  // ResultSet is 1-based
                return resultSet.getObject(columnIndex + 1);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;  // Allow editing
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            try {
                resultSet.absolute(rowIndex + 1);  // ResultSet is 1-based
                resultSet.updateObject(columnIndex + 1, aValue);
                resultSet.updateRow();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public ResultSet getResultSet() {
            return resultSet;
        }

        public Connection getConnection() {
            return connection;
        }

        public void setResultSet(ResultSet resultSet) {
            this.resultSet = resultSet;
            try {
                metaData = resultSet.getMetaData();
                columnCount = metaData.getColumnCount();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            fireTableDataChanged();
        }
    }
}

```