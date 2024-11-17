# Java Swing MVC Cheatsheet (pt.2)

## MVC Pattern Basics
### Model
- Holds data and business logic
- No GUI elements
- Provides methods to access/modify data
```java
public class Model {
    private String data;
    
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
```

### View
- Contains GUI components
- No business logic
- Basic Component Creation:
```java
// Frame Setup
JFrame frame = new JFrame("Title");
frame.setSize(width, height);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// Panel Setup
JPanel panel = new JPanel();
panel.setLayout(new BorderLayout());  // or other layouts
```

### Controller
- Handles user interactions
- Links Model and View
- Typical Structure:
```java
public class Controller implements ActionListener {
    private Model model;
    private View view;
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Action")) {
            // Handle action
            model.updateData();
            view.updateDisplay();
        }
    }
}
```

## Common Swing Components

### Basic Input Components
```java
// Text Field
JTextField textField = new JTextField(20);  // 20 columns wide

// Text Area with ScrollPane
JTextArea textArea = new JTextArea(rows, columns);
JScrollPane scrollPane = new JScrollPane(textArea);

// Button
JButton button = new JButton("Click Me");
button.addActionListener(listener);

// Label
JLabel label = new JLabel("Text");
label.setOpaque(true);  // For background color
```

### Selection Components
```java
// Checkbox
JCheckBox checkbox = new JCheckBox("Option", isSelected);

// Radio Buttons
ButtonGroup group = new ButtonGroup();
JRadioButton radio1 = new JRadioButton("Option 1");
JRadioButton radio2 = new JRadioButton("Option 2");
group.add(radio1);
group.add(radio2);

// Combo Box
JComboBox<String> combo = new JComboBox<>();
combo.addItem("Item 1");
combo.setSelectedIndex(0);
```

### Lists and Models
```java
// List with Default Model
DefaultListModel<String> listModel = new DefaultListModel<>();
listModel.addElement("Item");
JList<String> list = new JList<>(listModel);

// Common List Operations
listModel.removeElement(object);
listModel.insertElementAt(object, index);
listModel.removeAllElements();
```

## Layout Managers

### GridLayout
```java
panel.setLayout(new GridLayout(rows, cols, hgap, vgap));
```

### BorderLayout
```java
panel.setLayout(new BorderLayout());
panel.add(component, BorderLayout.CENTER);
panel.add(component, BorderLayout.NORTH);
```

### Event Handling Quick Reference
```java
// Add Listeners
component.addActionListener(e -> {
    // Handle event
});

// Common Event Handling Pattern
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
        // Handle button1
    } else if (e.getSource() == button2) {
        // Handle button2
    }
}
```

## Best Practices
1. Keep Model independent of View/Controller
2. Initialize GUI components in constructor
3. Use meaningful variable names for components
4. Group related components in separate panels
5. Always add action listeners through Controller
6. Use appropriate layout managers for component organization