# Java Swing Cheatsheet

## Basics
- **AWT**: Heavy-weight, platform-dependent GUI framework (JDK 1.0 - 1.1).
- **Swing**: Lightweight, platform-independent GUI framework (JDK 1.2+).

## Key Terminology
- **Window**: Top-level container (e.g., JFrame, JDialog).
- **Component**: GUI widget (e.g., JButton, JLabel, JTextField).
- **Container**: Groups components logically (e.g., JPanel).

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
- Treats container as a left-to-right, top-to-bottom "paragraph".
- Constructor:
  ```java
  FlowLayout()
  FlowLayout(int align, int hgap, int vgap)
  ```
- **Example**:
  ```java
  frame.setLayout(new FlowLayout());
  ```

### BorderLayout
- Divides container into 5 regions: `NORTH`, `SOUTH`, `EAST`, `WEST`, `CENTER`.
- **Example**:
  ```java
  frame.setLayout(new BorderLayout());
  frame.add(panel, BorderLayout.NORTH);
  ```

### GridLayout
- Treats container as a grid of equally sized rows/columns.
- Constructor:
  ```java
  GridLayout(int rows, int cols)
  ```
- **Example**:
  ```java
  frame.setLayout(new GridLayout(2, 3));
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
- **Message Dialog**:
  ```java
  JOptionPane.showMessageDialog(null, "Message");
  ```
- **Input Dialog**:
  ```java
  String input = JOptionPane.showInputDialog(null, "Enter text:");
  ```
- **Confirmation Dialog**:
  ```java
  int choice = JOptionPane.showConfirmDialog(null, "Are you sure?");
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
