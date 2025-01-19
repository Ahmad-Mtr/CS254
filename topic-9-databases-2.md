# JDBC Advanced Features - Comprehensive Summary

## Introduction to Advanced JDBC
JDBC provides powerful features beyond basic database connectivity, enabling developers to create robust and efficient database applications. This summary covers the key advanced features: Metadata handling, Prepared Statements, Scrollable ResultSets, and Callable Statements.

## Database Metadata
Metadata in JDBC provides information about the database itself and its capabilities.

### Basic Metadata Operations
```java
// Obtaining Database Metadata
DatabaseMetaData dbMetaData = connection.getMetaData();

// Common Information Retrieval
String dbName = dbMetaData.getDatabaseProductName();
String dbVersion = dbMetaData.getDatabaseProductVersion();
String driverName = dbMetaData.getDriverName();
```

### Advanced Metadata Queries
```java
// Table Information
ResultSet tables = dbMetaData.getTables(
    null,           // catalog
    null,           // schema
    "table_name%",  // table name pattern
    new String[]{"TABLE"} // table types
);

// Primary Key Information
ResultSet pKeys = dbMetaData.getPrimaryKeys(null, null, "tableName");

// Schema Information
ResultSet schemas = dbMetaData.getSchemas();
```

## Prepared Statements
PreparedStatements offer precompiled SQL queries with placeholder parameters, providing better performance and security.

### Basic Usage
```java
// Creating a PreparedStatement
String sql = "SELECT * FROM employees WHERE salary > ? AND department = ?";
PreparedStatement pstmt = connection.prepareStatement(sql);

// Setting Parameters
pstmt.setDouble(1, 50000.00);
pstmt.setString(2, "IT");

// Executing Query
ResultSet rs = pstmt.executeQuery();
```

### Update Operations
```java
String updateSQL = "UPDATE products SET price = ? WHERE id = ?";
PreparedStatement ps = connection.prepareStatement(updateSQL);

ps.setDouble(1, 99.99);
ps.setInt(2, 1001);
int rowsAffected = ps.executeUpdate();
```

## Scrollable ResultSets
Enables bi-directional navigation through query results.

### Creating Scrollable ResultSet
```java
// Creating Statement with Scrollable ResultSet
Statement stmt = connection.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY
);

// Navigation Methods
ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
rs.first();          // First row
rs.last();           // Last row
rs.absolute(5);      // Go to row 5
rs.relative(-2);     // Go back 2 rows
rs.previous();       // Previous row
```

## Callable Statements and Stored Procedures
Enables execution of stored procedures and functions in the database.

### Creating Stored Procedures
```sql
-- Simple Stored Procedure
CREATE PROCEDURE GetEmployeeCount()
BEGIN
    SELECT COUNT(*) FROM employees;
END;

-- Procedure with Parameters
CREATE PROCEDURE UpdateSalary(
    IN empId INT,
    IN newSalary DECIMAL(10,2)
)
BEGIN
    UPDATE employees 
    SET salary = newSalary 
    WHERE id = empId;
END;
```

### Using Callable Statements
```java
// Simple Procedure Call
CallableStatement cs = connection.prepareCall("{call GetEmployeeCount()}");
ResultSet rs = cs.executeQuery();

// Procedure with Parameters
CallableStatement cs = connection.prepareCall("{call UpdateSalary(?, ?)}");
cs.setInt(1, 1001);         // Employee ID
cs.setDouble(2, 60000.00);  // New Salary
cs.execute();

// Function with Return Value
CallableStatement cs = connection.prepareCall("{? = call CalculateBonus(?)}");
cs.registerOutParameter(1, Types.DECIMAL);
cs.setInt(2, employeeId);
cs.execute();
double bonus = cs.getDouble(1);
```

## Complete Working Example
```java
import java.sql.*;

public class AdvancedJDBCDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/company";
        String username = "user";
        String password = "pass";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Metadata Example
            DatabaseMetaData dbmd = conn.getMetaData();
            System.out.println("Database: " + dbmd.getDatabaseProductName());

            // Prepared Statement Example
            String sql = "SELECT * FROM employees WHERE department = ? AND salary > ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                
                pstmt.setString(1, "IT");
                pstmt.setDouble(2, 50000.00);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    // Scrollable ResultSet Navigation
                    while (rs.next()) {
                        System.out.printf("Employee: %s, Salary: %.2f%n",
                            rs.getString("name"),
                            rs.getDouble("salary"));
                    }
                }
            }

            // Callable Statement Example
            try (CallableStatement cs = conn.prepareCall("{call UpdateSalary(?, ?)}")) {
                cs.setInt(1, 1001);
                cs.setDouble(2, 65000.00);
                cs.execute();
                System.out.println("Salary updated successfully");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

## Best Practices and Tips
1. **Resource Management**
   - Always use try-with-resources for automatic resource closing
   - Close ResultSets, Statements, and Connections in reverse order of creation

2. **Security**
   - Use PreparedStatements to prevent SQL injection
   - Never concatenate user input directly into SQL strings
   - Implement proper authentication and authorization

3. **Performance**
   - Reuse PreparedStatements for repeated executions
   - Use batch updates for multiple similar operations
   - Choose appropriate ResultSet type based on needs

4. **Error Handling**
   - Implement proper exception handling
   - Log SQL exceptions with meaningful context
   - Consider implementing retry mechanisms for transient failures

This summary covers the essential advanced features of JDBC, providing a solid foundation for building robust database applications in Java.