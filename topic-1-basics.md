# Java Swing Cheatsheet

## Basics
- **AWT**: Heavy-weight, platform-dependent GUI framework.
- **Swing**: Lightweight, platform-independent GUI framework.

## Key Terminology
- **Window**: Top-level container (e.g., `JFrame`, `JDialog`).
- **Component**: GUI widget (e.g., `JButton`, `JLabel`, `JTextField`).
- **Container**: Groups components logically (e.g., `JPanel`).

---

## JFrame
- **Constructor**: 
  ```java
  JFrame() 
  JFrame(String title)
  ```
- **Common Methods**:
  ```java
  setVisible(true); // Display frame
  setTitle(String title); // Set title
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app on close
  setSize(int width, int height); // Set size in pixels
  setLocation(int x, int y); // Set position
  add(Component comp); // Add a component
  pack(); // Fit around components
  ```
- **Example**:
  ```java
  JFrame frame = new JFrame("Title");
  frame.setSize(400, 300);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);
  ```

---

## JPanel
- **Constructor**: 
  ```java
  JPanel() 
  JPanel(LayoutManager mgr)
  ```
- **Common Methods**:
  ```java
  setBackground(Color c); // Set background color
  setPreferredSize(Dimension d); // Set size
  ```
- **Example**:
  ```java
  JPanel panel = new JPanel();
  panel.setBackground(Color.RED);
  frame.add(panel);
  ```

---

## JButton
- **Constructor**:
  ```java
  JButton(String text)
  ```
- **Methods**:
  ```java
  setText(String text); // Set button text
  getText(); // Get button text
  addActionListener(ActionListener al); // Add event listener
  ```
- **Example**:
  ```java
  JButton button = new JButton("Click Me");
  button.addActionListener(e -> System.out.println("Clicked!"));
  ```

---

## JLabel
- **Constructor**:
  ```java
  JLabel(String text)
  JLabel(ImageIcon icon)
  ```
- **Methods**:
  ```java
  setText(String text); // Set label text
  setForeground(Color c); // Set text color
  setBackground(Color c); // Set background color
  setOpaque(boolean b); // Set transparency
  ```
- **Example**:
  ```java
  JLabel label = new JLabel("Hello, Swing!");
  label.setForeground(Color.BLUE);
  ```


### Key Points
1. **Text Colors**:
   - Use `setForeground(Color c)` to set the color of the text in a `JLabel`.
     ```java
     JLabel label = new JLabel("Hello");
     label.setForeground(Color.BLUE); // Set text color to blue
     ```

2. **Background Colors**:
   - Use `setBackground(Color c)` to set the background color of a `JLabel`.

3. **Transparency with `setOpaque()`**:
   - By default, `JLabel` components are transparent.
   - To make the background color visible, call `setOpaque(true)`.
     ```java
     JLabel label = new JLabel("Opaque Label");
     label.setBackground(Color.YELLOW); // Set background color
     label.setOpaque(true);             // Make the background visible
     ```

### Example
```java
import javax.swing.*;
import java.awt.*;

public class JLabelExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JLabel Colors & Opaque Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel transparentLabel = new JLabel("Transparent Label");
        transparentLabel.setForeground(Color.RED); // Text color only
        frame.add(transparentLabel);

        JLabel opaqueLabel = new JLabel("Opaque Label");
        opaqueLabel.setForeground(Color.BLACK);
        opaqueLabel.setBackground(Color.YELLOW);
        opaqueLabel.setOpaque(true); // Make background visible
        frame.add(opaqueLabel);

        frame.setSize(300, 150);
        frame.setVisible(true);
    }
}
```

### Summary
- **Text Colors**: Use `setForeground(Color c)` to customize the text color.
- **Background Colors**: Use `setBackground(Color c)` for background but enable visibility with `setOpaque(true)`.
- **Visual Impact**: Proper use of colors and `setOpaque()` enhances the readability and visual design of `JLabel` components.

---
## JTextField and JPasswordField
- **Constructor**:
  ```java
  JTextField(int columns)
  JPasswordField(String text)
  ```
- **Methods**:
  ```java
  setText(String text); // Set text
  getText(); // Get text
  setEditable(boolean editable); // Enable/disable editing
  ```
- **Example**:
  ```java
  JTextField textField = new JTextField(20);
  textField.addActionListener(e -> System.out.println("Entered: " + textField.getText()));
  ```

---
## Layout Managers

### FlowLayout
- Arranges components in a left-to-right, top-to-bottom flow, similar to text in a paragraph.
- **Constructors**:
  ```java
  FlowLayout() // Default alignment and spacing
  FlowLayout(int align) // Alignment: FlowLayout.LEFT, FlowLayout.CENTER, FlowLayout.RIGHT
  FlowLayout(int align, int hgap, int vgap) // Alignment with horizontal and vertical gaps
  ```
- **Example**:
  ```java
  frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
  ```

### BorderLayout
- Divides container into 5 regions: `NORTH`, `SOUTH`, `EAST`, `WEST`, `CENTER`.
- **Behavior**:
  - `NORTH` and `SOUTH`: Expand horizontally, use preferred size vertically.
  - `EAST` and `WEST`: Expand vertically, use preferred size horizontally.
  - `CENTER`: Takes remaining space.
- **Constructors**:
  ```java
  BorderLayout() // Default with no gaps
  BorderLayout(int hgap, int vgap) // Specify horizontal and vertical gaps
  ```
- **Example**:
  ```java
  frame.setLayout(new BorderLayout(10, 5));
  frame.add(panel1, BorderLayout.NORTH);
  frame.add(panel2, BorderLayout.CENTER);
  ```

### GridLayout
- Treats container as a grid of equally-sized rows and columns.
- **Constructors**:
  ```java
  GridLayout() // Single row, infinite columns
  GridLayout(int rows, int cols) // Fixed rows and columns
  GridLayout(int rows, int cols, int hgap, int vgap) // Specify gaps
  ```
- **Behavior**:
  - Use `0` for rows or columns to allow dynamic expansion.
- **Example**:
  ```java
  frame.setLayout(new GridLayout(3, 2, 5, 5));
  ```

---

## Event Handling
- **Common Events**: Button clicks, text input, menu selection, etc.
- **ActionListener Interface**:
  ```java
  public class MyListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          System.out.println("Event triggered: " + e.getActionCommand());
      }
  }
  ```
- **Attaching Listeners**:
  ```java
  button.addActionListener(new MyListener());
  ```

---

## JOptionPane

- **Message Dialog**: Displays a simple message.
  ```java
  JOptionPane.showMessageDialog(parent, "Message");
  JOptionPane.showMessageDialog(parent, "Message", "Title", JOptionPane.INFORMATION_MESSAGE);
  ```

- **Input Dialog**: Displays a prompt and accepts user input.
  ```java
  String input = JOptionPane.showInputDialog(parent, "Enter text:");
  String input = JOptionPane.showInputDialog(parent, "Enter text:", "Title", JOptionPane.QUESTION_MESSAGE);
  ```

- **Confirmation Dialog**: Displays a prompt with options like Yes, No, or Cancel.
  ```java
  int choice = JOptionPane.showConfirmDialog(parent, "Are you sure?");
  int choice = JOptionPane.showConfirmDialog(parent, "Are you sure?", "Title", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
  ```

- **Option Dialog**: Customizes buttons and actions.
  ```java
  int choice = JOptionPane.showOptionDialog(
      parent,
      "Message",
      "Title",
      JOptionPane.YES_NO_OPTION,
      JOptionPane.PLAIN_MESSAGE,
      null, // Custom icon, use null for default
      new Object[] {"Option 1", "Option 2", "Option 3"}, // Button options
      "Option 1" // Default button
  );
  ```

---

## More `JButton` & `JTextField` Examples

### `JButton` Examples
- **Basic Button**:
  ```java
  JButton button = new JButton("Click Me");
  button.setEnabled(true); // Enable or disable the button
  button.setToolTipText("Click to perform an action"); // Set tooltip
  panel.add(button); // Add to a container
  ```
- **Button with Icons**:
  ```java
  Icon icon = new ImageIcon("icon.png");
  JButton button = new JButton("Button with Icon", icon);
  button.setRolloverIcon(new ImageIcon("hover_icon.png")); // Change icon on hover
  panel.add(button);
  ```
- **Action Listener**:
  ```java
  button.addActionListener(e -> System.out.println("Button clicked!"));
  ```

---

### `JTextField` Examples
- **Basic Text Field**:
  ```java
  JTextField textField = new JTextField(20); // 20 columns
  textField.setText("Default Text"); // Set initial text
  textField.setEditable(true); // Enable or disable editing
  panel.add(textField); // Add to a container
  ```
- **Action Listener**:
  ```java
  textField.addActionListener(e -> System.out.println("Text entered: " + textField.getText()));
  ```
- **Password Field** (Subclass of `JTextField`):
  ```java
  JPasswordField passwordField = new JPasswordField(20); // 20 columns
  passwordField.setEchoChar('*'); // Mask input with '*'
  panel.add(passwordField);
  ```

---
## Full Example
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Swing Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new FlowLayout());
        JButton button = new JButton("Click Me");
        JTextField textField = new JTextField(15);
        
        button.addActionListener(e -> textField.setText("Button clicked!"));
        panel.add(button);
        panel.add(textField);
        
        frame.add(panel);
        frame.setVisible(true);
    }
}
```

---
## Utils and Selection in Swing Components

### Selection in Components
Swing provides various components like `JRadioButton`, `JCheckBox`, and others for user selection. These components allow users to make single or multiple selections. Here’s how to manage selection states effectively.

---

### `JRadioButton` (Single Selection)
- **Concept**: `JRadioButton` components are grouped together to allow only one selection at a time.
- **Usage**:
  ```java
  JRadioButton radio1 = new JRadioButton("Option 1");
  JRadioButton radio2 = new JRadioButton("Option 2");

  ButtonGroup group = new ButtonGroup(); // Group radio buttons
  group.add(radio1);
  group.add(radio2);

  boolean isSelected = radio1.isSelected(); // Check if selected
  ```
- **Example**:
  ```java
  JPanel panel = new JPanel();
  panel.add(radio1);
  panel.add(radio2);
  ```

---

### `JCheckBox` (Multiple Selection)
- **Concept**: `JCheckBox` allows multiple selections independently.
- **Usage**:
  ```java
  JCheckBox checkbox1 = new JCheckBox("Check 1");
  JCheckBox checkbox2 = new JCheckBox("Check 2");

  boolean isChecked = checkbox1.isSelected(); // Check if selected
  ```
- **Example**:
  ```java
  JPanel panel = new JPanel();
  panel.add(checkbox1);
  panel.add(checkbox2);
  ```

---

### `JComboBox` (Dropdown Selection)
- **Concept**: Allows users to select one option from a dropdown list.
- **Usage**:
  ```java
  JComboBox<String> comboBox = new JComboBox<>(new String[] {"Option 1", "Option 2", "Option 3"});

  String selectedItem = (String) comboBox.getSelectedItem(); // Get selected item
  int selectedIndex = comboBox.getSelectedIndex(); // Get selected index
  ```
- **Example**:
  ```java
  panel.add(comboBox);
  ```

---

### `JList` (List Selection)
- **Concept**: Allows single or multiple selections in a scrollable list.
- **Usage**:
  ```java
  JList<String> list = new JList<>(new String[] {"Item 1", "Item 2", "Item 3"});
  list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Single selection

  String selectedValue = list.getSelectedValue(); // Get selected value
  int[] selectedIndices = list.getSelectedIndices(); // Get selected indices (multi-selection)
  ```
- **Example**:
  ```java
  JScrollPane scrollPane = new JScrollPane(list);
  panel.add(scrollPane);
  ```

---

### Utils for Component Selection
#### Iterating Through Components in a View
- To process or apply actions on all components in a container (e.g., `JPanel`):
  ```java
  for (Component comp : panel.getComponents()) {
      if (comp instanceof JCheckBox) {
          JCheckBox checkbox = (JCheckBox) comp;
          if (checkbox.isSelected()) {
              System.out.println(checkbox.getText() + " is selected");
          }
      } else if (comp instanceof JRadioButton) {
          JRadioButton radioButton = (JRadioButton) comp;
          System.out.println(radioButton.getText() + " is selected: " + radioButton.isSelected());
      }
  }
  ```

#### Resetting Selection
- Clear all selections in a view:
  ```java
  for (Component comp : panel.getComponents()) {
      if (comp instanceof AbstractButton) { // Works for JRadioButton and JCheckBox
          AbstractButton button = (AbstractButton) comp;
          button.setSelected(false);
      }
  }
  ```

---

### Summary
- **Common Selection Components**:
  - `JRadioButton` for single selection (grouped with `ButtonGroup`).
  - `JCheckBox` for independent multiple selections.
  - `JComboBox` for dropdown lists.
  - `JList` for scrollable list selection.
- **Utils**:
  - Iterate through components with `getComponents()` for bulk actions like checking selection states or resetting selections.
