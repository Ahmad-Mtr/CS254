```java
package additionalcomponents.databaseexamples;

// Java core packages
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

// Java extension packages
import javax.swing.table.*;

// ResultSet rows and columns are counted from 1 and JTable 
// rows and columns are counted from 0. When processing 
// ResultSet rows or columns for use in a JTable, it is 
// necessary to add 1 to the row or column number to manipulate
// the appropriate ResultSet column (i.e., JTable column 0 is 
// ResultSet column 1 and JTable row 0 is ResultSet row 1).
public class ResultSetTableModel extends AbstractTableModel {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private int resultSetRowCount;
    private ResultSetMetaData metaData;
    private List<Object[]> dataList = new ArrayList<>();
    private int numberOfRows;

    // initialize resultSet and obtain its meta data object;
    // determine number of rows
    public ResultSetTableModel(String driver, String url, String login, String passwd, String query) {
        try {
            // load database driver class
            Class.forName(driver);

            // connect to database
            connection = DriverManager.getConnection(url, login, passwd);

            statement = connection.createStatement();

            // set query and execute it
            setQuery(query);
        } catch (ClassNotFoundException classNotFound) {
            JOptionPane.showMessageDialog(null,
                    "driver not found", "Driver not found",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null,
                    sqlException.toString(),
                    "Database error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }
    }

    // get class that represents column type
    public Class getColumnClass(int column) {
        try {
            String className = metaData.getColumnClassName(column + 1);

            return Class.forName(className);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // if problems occur above, assume type Object 
        return Object.class;
    }

    // get number of columns in ResultSet
    public int getColumnCount() {
        try {
            return metaData.getColumnCount();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    public String getColumnName(int column) {
        try {
            return metaData.getColumnName(column + 1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return "";
    }

    public int getRowCount() {
        return numberOfRows;
    }

    public Object getValueAt(int row, int column) {
        try {
            return dataList.get(row)[column];
        } // catch SQLExceptions and print error message
        catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

        return "";
    }

    // close Statement and Connection
    protected void finalize() {
        // close Statement and Connection
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    // set new database query string
    public void setQuery(String query) throws SQLException {
        resultSet = statement.executeQuery(query);
        metaData = getResultSet().getMetaData();

        dataList.clear();
        int numCol = metaData.getColumnCount();

        while (getResultSet().next()) {
            Object[] rowData = new Object[numCol];
            for (int i = 0; i < numCol; i++) {
                rowData[i] = getResultSet().getObject(i + 1);
            }
            dataList.add(rowData);
        }

        numberOfRows = dataList.size();
        resultSetRowCount = numberOfRows;
        // notify JTable that model has changed
        fireTableStructureChanged();
        fireTableDataChanged();
    }

    // Add a row to the table model
    public void addRow(Object[] row) {
        dataList.add(row);
        numberOfRows++;

        fireTableRowsInserted(dataList.size() - 1, dataList.size() - 1); // Notify table to refresh
    }

    public boolean isCellEditable(int row, int column) {
        // In this example, we allow all cells to be editable
        return column != 0;
    }

    public void setValueAt(Object aValue, int row, int column) {
        // Update the value in the dataList (the model)
        dataList.get(row)[column] = aValue;

    }
    // set new database query string

    public void insertRowIntoDatabase(int row) {
        try {
            // Insert the row into the database (assuming `authors` table)
            Object[] rowData = dataList.get(row);
            String insertQuery = "INSERT INTO authors (authorID, firstName, lastName) VALUES (" + rowData[0] + ", '" + rowData[1] + "', '" + rowData[2] + "');";

            Statement pstmt = connection.createStatement();

            pstmt.executeUpdate(insertQuery);
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null,
                    sqlException.toString(),
                    "Database error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);   // terminate application
        }
    }

    public void updateRowDatabase(int row) {
        try {
            // Insert the row into the database (assuming `authors` table)
            Object[] rowData = dataList.get(row);
            String updateQuery = "Update authors set firstName = ?, lastName = ? where authorID = ?";

            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setString(1, rowData[1] + "");
            pstmt.setString(2, rowData[2] + "");
            pstmt.setInt(3, (int) rowData[0]);
            pstmt.executeUpdate();
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null,
                    sqlException.toString(),
                    "Database error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);   // terminate application
        }
    }

    public void deleteRowDatabase(int row) {
        try {
            // Insert the row into the database (assuming `authors` table)
            Object[] rowData = dataList.get(row);
            String deleteQuery = "delete from authors where authorID = ?";

            PreparedStatement pstmt = connection.prepareStatement(deleteQuery);
            pstmt.setInt(1, (int) rowData[0]);
            pstmt.executeUpdate();
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null,
                    sqlException.toString(),
                    "Database error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);   // terminate application
        }
    }

    // Delete the selected row from the database and table
    public void deleteSelectedRow(int row) {

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;  // No row selected
        }

        // Get the employee ID of the selected row
        int authorID = (int) getValueAt(row, 0);

        // Confirm the deletion with the user
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete author with ID: " + authorID + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            deleteRowDatabase(row);
        } else {
            JOptionPane.showMessageDialog(null, "Error deleting author. Please try again.");
        }

    }

    // Check if all cells in the row are filled
    public boolean isRowFilled(int row) {
        Object[] rowData = dataList.get(row);
        for (Object value : rowData) {
            if (value == null || value.toString().trim().isEmpty()) {
                return false;  // Row is not fully filled
            }
        }
        return true;  // Row is fully filled
    }

    /**
     * @return the resultSet
     */
    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * @return the resultSetRowCount
     */
    public int getResultSetRowCount() {
        return resultSetRowCount;
    }
}  // end class ResultSetTableModel

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