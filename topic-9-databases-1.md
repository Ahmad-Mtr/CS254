# JDBC (Java Database Connectivity) 

## Table of Contents
- [Introduction](#introduction)
- [Core Components](#core-components)
- [Best Practices](#best-practices)
- [Integration with Swing](#integration-with-swing)
- [Advanced Features](#advanced-features)
- [Complete Examples](#complete-examples)

## Introduction
> [!NOTE] 
> This reference combines course material and practical implementations. For specific lecture examples, refer to `lecture-code/databases`.

JDBC provides a standardized API for Java applications to interact with databases:
- Database-independent interface
- SQL query execution
- Result processing
- Transaction management
- Resource management

## Core Components
### 1. Driver and DriverManager
```java
// Loading Drivers
Class.forName("com.mysql.jdbc.Driver");        // MySQL
Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle
Class.forName("org.postgresql.Driver");        // PostgreSQL

// Connection URLs
String mysqlUrl = "jdbc:mysql://hostname:3306/database";
String oracleUrl = "jdbc:oracle:thin:@hostname:1521:database";
String postgresUrl = "jdbc:postgresql://hostname:5432/database";
```

### 2. Connection Management
```java
// Basic connection
Connection conn = DriverManager.getConnection(url, username, password);

// Better practice with try-with-resources
try (Connection conn = DriverManager.getConnection(url, username, password)) {
    // Database operations
} catch (SQLException e) {
    e.printStackTrace();
}
```

### 3. Statement Types
```java
// 1. Basic Statement
Statement stmt = conn.createStatement();

// 2. PreparedStatement (preferred)
PreparedStatement pstmt = conn.prepareStatement(
    "SELECT * FROM users WHERE id = ?"
);

// 3. CallableStatement
CallableStatement cstmt = conn.prepareCall(
    "{call stored_procedure(?)}"
);
```

### 4. ResultSet Operations
```java
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
while(rs.next()) {
    // Column access methods
    int id = rs.getInt(1);                // by index
    String name = rs.getString("name");    // by name
    Date date = rs.getDate("created_at");
    Blob data = rs.getBlob("binary_data");
}
```

### 5. Metadata Handling
```java
ResultSetMetaData md = rs.getMetaData();
int columnCount = md.getColumnCount();

for(int i = 1; i <= columnCount; i++) {
    String columnName = md.getColumnName(i);
    String columnType = md.getColumnTypeName(i);
    int columnSize = md.getColumnDisplaySize(i);
}
```

## Best Practices
### 1. Resource Management
```java
try (
    Connection conn = getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(query)
) {
    // Process results
} catch (SQLException e) {
    handleException(e);
} finally {
    // Resources automatically closed
}
```

### 2. Connection Pooling
```java
public class ConnectionPool {
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();
    
    static {
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl(url);
        cpds.setUser(username);
        cpds.setPassword(password);
        
        cpds.setMinPoolSize(5);
        cpds.setMaxPoolSize(50);
    }
    
    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
}
```

## Integration with Swing
### 1. Basic Pattern
```java
public class DatabaseViewer extends JFrame {
    private JTable dataTable;
    private Connection conn;
    
    private void loadData() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                    // Process results
                    updateTableModel(rs);
                }
                return null;
            }
        };
        worker.execute();
    }
}
```

### 2. Result Display
```java
private void updateTableModel(ResultSet rs) throws SQLException {
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    
    // Create column names
    Vector<String> columnNames = new Vector<>();
    for (int i = 1; i <= columns; i++) {
        columnNames.add(md.getColumnName(i));
    }
    
    // Create data vectors
    Vector<Vector<Object>> data = new Vector<>();
    while (rs.next()) {
        Vector<Object> row = new Vector<>();
        for (int i = 1; i <= columns; i++) {
            row.add(rs.getObject(i));
        }
        data.add(row);
    }
    
    // Update on EDT
    SwingUtilities.invokeLater(() -> {
        dataTable.setModel(new DefaultTableModel(data, columnNames));
    });
}
```

## Advanced Features
### 1. Transaction Management
```java
conn.setAutoCommit(false);
try {
    // Multiple operations
    stmt.executeUpdate(query1);
    stmt.executeUpdate(query2);
    
    conn.commit();
} catch (SQLException e) {
    conn.rollback();
} finally {
    conn.setAutoCommit(true);
}
```

### 2. Batch Processing
```java
stmt.addBatch("INSERT INTO users(name) VALUES('John')");
stmt.addBatch("INSERT INTO users(name) VALUES('Jane')");
int[] results = stmt.executeBatch();
```

---

## Complete Examples
### 1. Database Utility Class
```java
public class DatabaseUtil {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASS = "password";
    
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
```

### 2. Complete Swing Database Application
```java
public class DatabaseApplication extends JFrame {
    private JTable table;
    private JTextField queryField;
    private JButton executeButton;
    private Connection connection;
    
    public DatabaseApplication() {
        initializeComponents();
        setupLayout();
        connectToDatabase();
    }
    
    private void initializeComponents() {
        table = new JTable();
        queryField = new JTextField(30);
        executeButton = new JButton("Execute Query");
        executeButton.addActionListener(e -> executeQuery());
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("SQL Query:"));
        topPanel.add(queryField);
        topPanel.add(executeButton);
        
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
    }
    
    private void connectToDatabase() {
        try {
            connection = DatabaseUtil.getConnection();
        } catch (SQLException e) {
            showError("Database Connection Failed", e);
        }
    }
    
    private void executeQuery() {
        String query = queryField.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a query", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                    updateTableFromResultSet(rs);
                }
                return null;
            }
            
            @Override
            protected void done() {
                try {
                    get(); // Check for exceptions
                } catch (Exception e) {
                    showError("Query Execution Failed", e);
                }
            }
        };
        
        worker.execute();
    }
    
    private void updateTableFromResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        // Prepare column names
        Vector<String> columnNames = new Vector<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
        }
        
        // Prepare data
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }
        
        // Update table on EDT
        SwingUtilities.invokeLater(() -> 
            table.setModel(new DefaultTableModel(data, columnNames))
        );
    }
    
    private void showError(String message, Exception e) {
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(this,
                message + "\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE)
        );
    }
    
    @Override
    public void dispose() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.dispose();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DatabaseApplication().setVisible(true);
        });
    }
}
```


## Common Issues and Solutions
1. Connection Pooling
2. Statement vs PreparedStatement
3. Resource Management
4. Transaction Handling
5. Exception Handling
6. Thread Safety in Swing

#jdbc #java #database #swing #programming