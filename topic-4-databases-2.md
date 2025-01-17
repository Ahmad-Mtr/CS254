# JDBC Advanced Features Cheatsheet

> [!NOTE] 
> This cheat sheet was written using an LLM, some parts might be different to what you've learned in course. You can check the `lecture-code/databases` for Lecture examples.

## Introduction
JDBC (Java Database Connectivity) provides advanced features for robust database interactions beyond basic queries. These features enable metadata inspection, efficient query preparation, and stored procedure execution, making database operations more secure, efficient, and maintainable.

## Database Metadata
### Why Use Metadata?
Metadata provides crucial information about the database structure and capabilities, enabling dynamic database interactions and feature compatibility checking.

### How to Use Metadata
```java
// Getting Database Metadata
DatabaseMetaData dbmd = connection.getMetaData();
```

Common Metadata Operations:
```java
// Database Information
String productName = dbmd.getDatabaseProductName();
String version = dbmd.getDatabaseProductVersion();
String driverName = dbmd.getDriverName();

// Schema Information
ResultSet schemas = dbmd.getSchemas();

// Table Information
ResultSet tables = dbmd.getTables(catalog, schemaPattern, tableNamePattern, types);

// Primary Keys
ResultSet pKeys = dbmd.getPrimaryKeys(catalog, schema, table);
```

⚠️ **Warning**: Always check for null values when working with metadata results.

## Prepared Statements
### Why Use Prepared Statements?
1. Better performance through query precompilation
2. Protection against SQL injection
3. Easier handling of dynamic parameters

### How to Use Prepared Statements
```java
// Basic Prepared Statement
String sql = "SELECT * FROM Books WHERE publisher = ? AND price < ?";
PreparedStatement pstmt = connection.prepareStatement(sql);
pstmt.setString(1, publisherName);
pstmt.setDouble(2, maxPrice);
ResultSet rs = pstmt.executeQuery();

// Update Example
String updateSQL = "UPDATE authors SET lastname = ? WHERE Authid = ?";
PreparedStatement ps = connection.prepareStatement(updateSQL);
ps.setString(1, "Allamaraju");
ps.setInt(2, 212);
int rowsUpdated = ps.executeUpdate();
```

⚠️ **Best Practices**:
- Always use prepared statements for parameterized queries
- Close statements in finally block
- Use try-with-resources when possible

## Scrollable ResultSet
### Why Use Scrollable ResultSet?
Enables navigation through result sets in any direction, providing more flexibility in data processing.

### How to Use Scrollable ResultSet
```java
// Creating Scrollable ResultSet
Statement stmt = connection.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY
);

// Navigation Methods
rs.first();          // Move to first row
rs.last();           // Move to last row
rs.absolute(5);      // Move to 5th row
rs.relative(-2);     // Move 2 rows backward
rs.previous();       // Move to previous row
rs.next();           // Move to next row
```

## Callable Statements & Stored Procedures
### Why Use Callable Statements?
1. Execute stored procedures
2. Standardize database operations
3. Improve performance
4. Better transaction control

### How to Use Callable Statements
```java
// Basic Callable Statement
CallableStatement cs = connection.prepareCall("{call procProductsList}");
cs.execute();

// With Parameters
CallableStatement cs = connection.prepareCall(
    "{call procProductsUpdateItem(?, ?)}"
);
cs.setLong(1, productId);
cs.setString(2, newName);
cs.execute();

// With OUT Parameters
CallableStatement cs = connection.prepareCall("{? = call calculateTotal(?)}");
cs.registerOutParameter(1, Types.DECIMAL);
cs.setInt(2, orderId);
cs.execute();
BigDecimal total = cs.getBigDecimal(1);
```

## Complete Example
```java
import java.sql.*;

public class AdvancedJDBCExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bookstore";
        String user = "username";
        String password = "password";
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Metadata Example
            DatabaseMetaData dbmd = conn.getMetaData();
            System.out.println("Database: " + dbmd.getDatabaseProductName());
            
            // Prepared Statement Example
            String sql = "SELECT * FROM Books WHERE price < ?";
            PreparedStatement pstmt = conn.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
            pstmt.setDouble(1, 29.99);
            
            // Scrollable ResultSet
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Book: " + rs.getString("title"));
            }
            
            // Callable Statement Example
            CallableStatement cs = conn.prepareCall(
                "{call procProductsUpdateItem(?, ?)}"
            );
            cs.setLong(1, 1000);
            cs.setString(2, "New Product");
            cs.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### Required Imports
```java
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
```

⚠️ **Common Pitfalls**:
1. Not closing resources properly
2. Ignoring SQLExceptions
3. Using string concatenation instead of prepared statements
4. Not checking metadata for feature support
5. Not handling null values in result sets

This cheatsheet covers the essential advanced JDBC features. Remember to always handle exceptions appropriately and close resources when done.