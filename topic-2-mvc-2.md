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

> [!NOTE] 
> Usually, a Model will override a `toString()` method that converts the Model (with its data) to a String, this is often quite helpful in cases such as: printing a receipt with all Order details. 
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

---
## `initController()` Method

### Explanation
- The `initController()` method is often used in Java Swing applications to centralize the initialization of event listeners and other controller logic.
- It helps separate UI setup (e.g., adding components) from interaction logic, improving code readability and maintainability.

### Example: Using `initController()`
```java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitControllerExample extends JFrame {
    private JButton button;
    private JTextField textField;

    public InitControllerExample() {
        // UI Setup
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        button = new JButton("Submit");
        textField = new JTextField(20);
        
        this.add(textField);
        this.add(button);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // Initialize Controller Logic
        initController();
    }

    private void initController() {
        // Add Action Listener to Button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                JOptionPane.showMessageDialog(null, "You entered: " + input);
            }
        });
    }

    public static void main(String[] args) {
        new InitControllerExample();
    }
}
```

### Key Benefits
1. **Separation of Concerns**:
   - UI construction and interaction logic are handled in distinct methods, making the code modular.
2. **Readability**:
   - A dedicated method clearly indicates where the interaction logic is defined.
3. **Maintainability**:
   - Easier to add or modify event handling code in one place without disrupting UI setup.

## Best Practices
1. Keep Model independent of View/Controller
2. Initialize GUI components in constructor
3. Use meaningful variable names for components
4. Group related components in separate panels
5. Always add action listeners through Controller
6. Use appropriate layout managers for component organization