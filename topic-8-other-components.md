# Chapter 10 - Other components

## Core Components Overview
This summary covers advanced Swing components used for creating sophisticated GUI applications.

## Color Selection
```java
// Basic Color Chooser
JColorChooser chooser = new JColorChooser();

// Show Color Dialog
Color selectedColor = JColorChooser.showDialog(
    parentComponent,
    "Select Color",
    Color.BLUE  // Initial color
);
```

## List Components
```java
// Creating Lists
String[] items = {"Item 1", "Item 2", "Item 3"};
JList<String> list = new JList<>(items);

// Configuration
list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
list.setVisibleRowCount(5);

// Selection Handling
list.addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        String selected = list.getSelectedValue();
        int[] indices = list.getSelectedIndices();
    }
});
```

## Table Components
```java
// Creating Tables
Object[][] data = {
    {"John", 25, "New York"},
    {"Alice", 30, "London"}
};
String[] columns = {"Name", "Age", "City"};
JTable table = new JTable(data, columns);

// Configuration
table.setFillsViewportHeight(true);
table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

// Add to ScrollPane
JScrollPane scrollPane = new JScrollPane(table);
```

## Tree Structures
```java
// Creating Tree
DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
DefaultMutableTreeNode child = new DefaultMutableTreeNode("Child");
root.add(child);

DefaultTreeModel treeModel = new DefaultTreeModel(root);
JTree tree = new JTree(treeModel);

// Modifying Tree
treeModel.insertNodeInto(
    new DefaultMutableTreeNode("New Child"),
    root,
    0
);
```

## Split Panes
```java
// Creating Split Pane
JSplitPane splitPane = new JSplitPane(
    JSplitPane.HORIZONTAL_SPLIT,
    leftComponent,
    rightComponent
);

// Configuration
splitPane.setDividerLocation(200);
splitPane.setOneTouchExpandable(true);
```

## Tabbed Panes
```java
// Creating Tabbed Pane
JTabbedPane tabbedPane = new JTabbedPane();

// Adding Tabs
tabbedPane.add("Tab 1", component1);
tabbedPane.add("Tab 2", component2);

// Tab Management
tabbedPane.setSelectedIndex(0);
Component current = tabbedPane.getSelectedComponent();
```

## Toolbars
```java
// Creating Toolbar
JToolBar toolbar = new JToolBar();
toolbar.setFloatable(true);

// Adding Components
toolbar.add(new JButton("New"));
toolbar.add(new JButton("Open"));
toolbar.addSeparator();
```

## Complete Example
```java
public class AdvancedComponentsDemo extends JFrame {
    public AdvancedComponentsDemo() {
        setTitle("Advanced Components Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create components
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // List Panel
        JList<String> list = new JList<>(new String[]{"Item 1", "Item 2"});
        tabbedPane.add("List", new JScrollPane(list));
        
        // Table Panel
        JTable table = new JTable(new Object[][]{
            {"Data 1", 100},
            {"Data 2", 200}
        }, new String[]{"Name", "Value"});
        tabbedPane.add("Table", new JScrollPane(table));
        
        // Split Pane
        JTree tree = new JTree();
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(tree),
            tabbedPane
        );
        
        // Toolbar
        JToolBar toolbar = new JToolBar();
        toolbar.add(new JButton("New"));
        toolbar.add(new JButton("Open"));
        
        // Layout
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdvancedComponentsDemo().setVisible(true);
        });
    }
}
```

## Best Practices
1. **Component Organization**
   - Use appropriate layouts
   - Combine components logically
   - Consider user experience

2. **Performance**
   - Use JScrollPane for large data sets
   - Implement lazy loading where appropriate
   - Handle events efficiently

3. **Usability**
   - Provide consistent navigation
   - Use appropriate selection modes
   - Implement proper event handling

4. **Maintenance**
   - Organize components hierarchically
   - Use meaningful names
   - Document complex interactions

This summary covers the essential advanced Swing components needed for building sophisticated GUI applications in Java.