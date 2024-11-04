Here's a concise cheatsheet for Java Visual Programming (Swing & AWT) with snippets for each topic to help with quick revision.

---

### Java Swing & AWT Cheatsheet

---

#### Basic Component Configuration

- **`validate()`**: Re-validates component layout after dynamic changes.
  
  ```java
  container.validate();
  ```

- **`setLayout(LayoutManager mgr)`**: Sets layout manager for containers.
  
  ```java
  frame.setLayout(new FlowLayout());
  ```

- **`setBackground(Color c)`**: Sets background color.

  ```java
  panel.setBackground(Color.BLUE);
  ```

- **`setPreferredSize(Dimension d)`**: Sets preferred dimensions.
  
  ```java
  component.setPreferredSize(new Dimension(200, 100));
  ```

#### Custom Panel (ColorPanel)

- **`JPanel`** with color customization.

  ```java
  class ColorPanel extends JPanel {
      public ColorPanel(Color color) {
          setBackground(color);
      }
  }
  ```

---

#### Layout Managers

- **FlowLayout**: Arranges components in a row, wrapping as needed.
  
  ```java
  container.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
  ```

- **BorderLayout**: Divides container into five regions.
  
  ```java
  container.setLayout(new BorderLayout());
  container.add(component, BorderLayout.NORTH);
  ```

- **GridLayout**: Arranges components in a grid.
  
  ```java
  container.setLayout(new GridLayout(2, 3, 5, 5));  // 2x3 grid with gaps
  ```

  - **`setHgap(int hgap)`** & **`setVgap(int vgap)`**: Horizontal and vertical gaps.
  
    ```java
    gridLayout.setHgap(10);
    gridLayout.setVgap(10);
    ```

#### LayoutFrame

A quick way to organize frame layouts.

```java
class LayoutFrame extends JFrame {
    public LayoutFrame() {
        setLayout(new BorderLayout());
        add(new JButton("North"), BorderLayout.NORTH);
    }
}
```

---

#### Common Component Properties

- **`setEnabled(boolean b)`**: Enables or disables a component.
- **`setToolTipText(String text)`**: Adds a tooltip to component.
  
  ```java
  button.setEnabled(false);
  button.setToolTipText("Click me!");
  ```

---

#### Events & Listeners

- **`ActionListener`**: Captures action events, like button clicks.
  
  ```java
  button.addActionListener(e -> System.out.println("Button Clicked!"));
  ```

- **Common Events**:
  - `ActionEvent`
  - `MouseEvent`
  - `KeyEvent`

---

#### ActionEvents

To handle specific actions.

```java
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action performed!");
    }
});
```

---

#### Graphics & Canvas

- **Canvas**: Basic area for drawing.

  ```java
  Canvas canvas = new Canvas() {
      public void paint(Graphics g) {
          g.drawRect(10, 10, 100, 100);
      }
  };
  ```

- **Graphics Methods**:
  - `drawLine(int x1, int y1, int x2, int y2)`
  - `drawRect(int x, int y, int width, int height)`
  - `drawOval(int x, int y, int width, int height)`

---

#### Screen Parameters

Get screen dimensions for responsive design.

```java
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
int width = screenSize.width;
int height = screenSize.height;
```

---

Here's an extended cheatsheet with common Swing components:

---

### Core Swing Components

---

- **JButton**: A button component for user interactions.
  
  ```java
  JButton button = new JButton("Click Me");
  button.addActionListener(e -> System.out.println("Button Clicked!"));
  ```

- **JLabel**: Displays a non-editable text or image.
  
  ```java
  JLabel label = new JLabel("Label Text");
  label.setHorizontalAlignment(JLabel.CENTER);  // Align text center
  ```

- **JTextField**: Single-line text input.

  ```java
  JTextField textField = new JTextField(20);  // 20 characters wide
  textField.setText("Default Text");
  ```

- **JPasswordField**: Password input, masks text.
  
  ```java
  JPasswordField passwordField = new JPasswordField(20);
  String password = new String(passwordField.getPassword());
  ```

- **JOptionPane**: Pop-up dialogs for messages, inputs, and confirmations.

  - **Message Dialog**:
    ```java
    JOptionPane.showMessageDialog(null, "Message text", "Title", JOptionPane.INFORMATION_MESSAGE);
    ```

  - **Input Dialog**:
    ```java
    String input = JOptionPane.showInputDialog("Enter your name:");
    ```

  - **Confirmation Dialog**:
    ```java
    int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
    ```

---

Based on the image, here are some additional components and Action/Event listeners that weren't covered in the previous cheatsheet:

---

### Additional Components

- **JRadioButton**: For selecting one option among multiple choices (used in "Size" and "Crust" sections).
  
  ```java
  JRadioButton smallSize = new JRadioButton("Small");
  JRadioButton mediumSize = new JRadioButton("Medium");
  JRadioButton largeSize = new JRadioButton("Large");
  
  ButtonGroup sizeGroup = new ButtonGroup();
  sizeGroup.add(smallSize);
  sizeGroup.add(mediumSize);
  sizeGroup.add(largeSize);
  ```

  Similar setup for "Crust" options.

- **JCheckBox**: For selecting multiple options independently (used in "Toppings" section).
  
  ```java
  JCheckBox extraCheese = new JCheckBox("Extra Cheese");
  JCheckBox greenPeppers = new JCheckBox("Green Peppers");
  ```

- **JTextArea**: Multi-line text area for displaying output (used in the bottom section to show order details).

  ```java
  JTextArea orderDetails = new JTextArea(5, 30);
  orderDetails.setEditable(false);
  ```

- **JScrollPane**: Adds scroll functionality to a JTextArea or other components if the content overflows.

  ```java
  JScrollPane scrollPane = new JScrollPane(orderDetails);
  ```

---

### Additional Action/Event Listeners

- **ActionListener** for Buttons (e.g., "Add Pizza", "Calculate Order Total", "Restart Order"):

  ```java
  addPizzaButton.addActionListener(e -> addPizzaToOrder());
  calculateTotalButton.addActionListener(e -> calculateTotalCost());
  restartOrderButton.addActionListener(e -> restartOrder());
  ```

  Each button triggers specific actions when clicked.

---

These additions should cover all components and listeners observed in the provided image.