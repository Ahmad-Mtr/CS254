# JDBC (Java Database Connectivity) Cheatsheet
> [!NOTE] 
> This cheat sheet was written using an LLM, some parts might be different to what you've learned in course. You can check the `lecture-code/databases` for Lecture examples.
## Introduction
JDBC (Java Database Connectivity) is a Java API that enables seamless interaction between Java applications and databases. It acts as a standardized interface, allowing Java code to:
- Connect to any database system (MySQL, Oracle, PostgreSQL, etc.)
- Execute SQL queries and updates
- Process results efficiently
- Manage database transactions

The key benefit is database independence - write your Java code once and connect to any database by simply changing the driver and connection details.

## Explanation

### Core Components

#### 1. Driver and DriverManager
The foundation of JDBC connectivity:
```java
// Load JDBC driver
Class.forName("com.mysql.jdbc.Driver");  // MySQL
Class.forName("oracle.jdbc.driver.OracleDriver");  // Oracle
```

Different database URL patterns:
```java
// MySQL
"jdbc:mysql://hostname:3306/database_name"
// Oracle
"jdbc:oracle:thin:@hostname:1521:database_name"
// PostgreSQL
"jdbc:postgresql://hostname:5432/database_name"
// SQLite
"jdbc:sqlite:database_file.db"
```

#### 2. Connection Management
Best practice is to centralize connection handling:
```java
public class DatabaseConnection {
    private static Connection connection = null;
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
```

#### 3. Statement Types
Three ways to execute SQL:
```java
// 1. Statement - Basic queries
Statement stmt = conn.createStatement();

// 2. PreparedStatement - Parameterized queries (preferred)
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");

// 3. CallableStatement - Stored procedures
CallableStatement cstmt = conn.prepareCall("{call procedure_name(?)}");
```

#### 4. ResultSet Operations
```java
// Execute query and process results
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
while(rs.next()) {
    // By column index (starts from 1)
    int id = rs.getInt(1);
    
    // By column name (more readable)
    String name = rs.getString("name");
    
    // Different data types
    Date date = rs.getDate("birth_date");
    double salary = rs.getDouble("salary");
    boolean active = rs.getBoolean("is_active");
}
```

### Advanced Features

#### 1. Transaction Management
```java
try {
    conn.setAutoCommit(false);  // Start transaction
    
    // Multiple operations
    stmt.executeUpdate("UPDATE accounts SET balance = balance - 100 WHERE id = 1");
    stmt.executeUpdate("UPDATE accounts SET balance = balance + 100 WHERE id = 2");
    
    conn.commit();  // Commit if successful
} catch (SQLException e) {
    conn.rollback();  // Rollback on error
} finally {
    conn.setAutoCommit(true);  // Reset auto-commit
}
```

#### 2. Batch Processing
```java
// Add multiple operations to batch
stmt.addBatch("INSERT INTO users(name) VALUES('John')");
stmt.addBatch("INSERT INTO users(name) VALUES('Jane')");

// Execute batch
int[] results = stmt.executeBatch();
```

#### 3. Metadata Handling
```java
ResultSetMetaData metaData = rs.getMetaData();
int columnCount = metaData.getColumnCount();

for(int i = 1; i <= columnCount; i++) {
    String columnName = metaData.getColumnName(i);
    String columnType = metaData.getColumnTypeName(i);
}
```

## Complete Example
Here's a production-ready JDBC implementation:

### DatabaseConfig.java
```java
public class DatabaseConfig {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/mydb";
    public static final String USER = "username";
    public static final String PASSWORD = "password";
}
```

### DatabaseConnection.java
```java
import java.sql.*;

public class DatabaseConnection {
    private static Connection connection = null;
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(DatabaseConfig.DRIVER);
                connection = DriverManager.getConnection(
                    DatabaseConfig.URL,
                    DatabaseConfig.USER,
                    DatabaseConfig.PASSWORD
                );
            } catch (ClassNotFoundException e) {
                throw new SQLException("Database driver not found", e);
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### DatabaseOperations.java
```java
import java.sql.*;
import java.util.List;

public class DatabaseOperations {
    // Select with PreparedStatement
    public static void selectUsers(String pattern) {
        String sql = "SELECT * FROM users WHERE name LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + pattern + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Email: %s%n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Insert with transaction
    public static boolean insertUser(String name, String email) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.executeUpdate();
                conn.commit();
                return true;
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        }
    }
    
    // Batch operations
    public static void batchInsert(List<User> users) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            conn.setAutoCommit(false);
            
            for (User user : users) {
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getEmail());
                pstmt.addBatch();
            }
            
            pstmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```
