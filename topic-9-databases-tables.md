# Java Swing Tables - Comprehensive Summary

## Introduction
Java Swing Tables (`JTable`) are powerful components used to display and edit two-dimensional data in a tabular format. They are part of the Swing GUI toolkit and provide built-in functionality for sorting, filtering, and customizing cell rendering and editing.

## Basic Components

### Main Classes
- `JTable`: The main table component
- `TableModel`: Interface defining how to access tabular data
- `DefaultTableModel`: Standard implementation of TableModel
- `TableColumnModel`: Manages table columns
- `JScrollPane`: Usually used to contain the table

## Key Concepts

### 1. Creating Tables
There are several ways to create a JTable:
```java
// Method 1: Using a 2D array and column names
String[] columnNames = {"Name", "Age", "City"};
Object[][] data = {
    {"John", 25, "New York"},
    {"Alice", 30, "London"}
};
JTable table = new JTable(data, columnNames);

// Method 2: Using DefaultTableModel
DefaultTableModel model = new DefaultTableModel(data, columnNames);
JTable table = new JTable(model);
```

### 2. Table Models
TableModel is the interface that defines how table data is accessed and modified:

- `DefaultTableModel`: Most commonly used
- `AbstractTableModel`: Base class for custom table models
- Custom models: Implement TableModel interface

## Important Features

### 1. Column Operations
```java
// Resize columns
table.getColumnModel().getColumn(0).setPreferredWidth(100);

// Set column headers
table.getTableHeader().setReorderingAllowed(false); // Prevent column reordering
```

### 2. Selection Modes
```java
// Set selection mode
table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
table.setRowSelectionAllowed(true);
table.setColumnSelectionAllowed(false);
```

### 3. Cell Rendering and Editing
```java
// Custom cell renderer
table.getColumn("Age").setCellRenderer(new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setHorizontalAlignment(SwingConstants.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }
});
```

## Best Practices

1. Always place JTable inside JScrollPane
2. Implement proper data models for large datasets
3. Use appropriate selection modes
4. Handle table events properly
5. Implement proper validation for editable cells

## Comprehensive Example

```java
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class TableExample extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton addButton, deleteButton;
    
    public TableExample() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create column names and initial data
        String[] columnNames = {"ID", "Name", "Age", "Grade"};
        Object[][] data = {
            {1, "John Doe", 20, "A"},
            {2, "Jane Smith", 21, "B"},
            {3, "Bob Johnson", 19, "A-"}
        };
        
        // Create table model
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Make ID column non-editable
            }
            
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 2) return Integer.class; // Age column
                return super.getColumnClass(column);
            }
        };
        
        // Create and setup table
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        
        // Set column widths
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // ID
        columnModel.getColumn(1).setPreferredWidth(150); // Name
        columnModel.getColumn(2).setPreferredWidth(50);  // Age
        columnModel.getColumn(3).setPreferredWidth(70);  // Grade
        
        // Create buttons
        addButton = new JButton("Add Student");
        deleteButton = new JButton("Delete Student");
        
        // Add button listeners
        addButton.addActionListener(e -> addStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        
        // Layout setup
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        
        // Add components to frame
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Set frame properties
        setSize(500, 300);
        setLocationRelativeTo(null);
    }
    
    private void addStudent() {
        int nextId = model.getRowCount() + 1;
        model.addRow(new Object[]{nextId, "New Student", 18, "N/A"});
    }
    
    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this,
                "Please select a student to delete",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TableExample().setVisible(true);
        });
    }
}
```

This example demonstrates:
- Custom TableModel with type checking and cell editability
- Column width management
- Selection handling
- Add/Delete functionality
- Proper event handling
- Basic validation
- Layout management
- ScrollPane integration

The code creates a simple student management system with a table that displays student information and allows for basic CRUD operations.