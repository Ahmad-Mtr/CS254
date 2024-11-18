# Java Swing Other Events Cheatsheet

## Window Events
- **WindowListener Interface**:
  Handles window-specific events (e.g., opening, closing).
  ```java
  public void windowActivated(WindowEvent e);
  public void windowClosed(WindowEvent e);
  public void windowClosing(WindowEvent e);
  public void windowDeactivated(WindowEvent e);
  public void windowDeiconified(WindowEvent e);
  public void windowIconified(WindowEvent e);
  public void windowOpened(WindowEvent e);
  ```
  - Add a listener:
    ```java
    frame.addWindowListener(WindowListener listener);
    ```
- **WindowAdapter**:
  Use this abstract class to override only required methods.

---

## Key Events
- **KeyListener Interface**:
  Detects keyboard actions.
  ```java
  public void keyPressed(KeyEvent e);
  public void keyReleased(KeyEvent e);
  public Object getSource();
  public void keyTyped(KeyEvent e);
  ```
  - Add a listener:
    ```java
    component.addKeyListener(KeyListener listener);
    ```

- **KeyEvent Methods**:
  ```java
  char getKeyChar();         // Character of the key pressed
  int getKeyCode();          // Virtual keycode (e.g., VK_A)
  int getModifiers();        // Modifier keys (e.g., SHIFT_MASK)
  boolean isActionKey();     // Check if the key is an action key
  ```

---

## Example: WindowListener
```java
import javax.swing.*;
import java.awt.event.*;

public class WindowExample extends JFrame implements WindowListener {
    public WindowExample() {
        this.addWindowListener(this);
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Window opened.");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Window closing.");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Window closed.");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Window minimized.");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Window restored.");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("Window activated.");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("Window deactivated.");
    }

    public static void main(String[] args) {
        new WindowExample();
    }
}
```

---

## Example: KeyListener
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyExample extends JFrame implements KeyListener {
    private JTextArea textArea;

    public KeyExample() {
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        this.add(textArea);

        this.addKeyListener(this);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        textArea.append("Key pressed: " + e.getKeyText(e.getKeyCode()) + "\n");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        textArea.append("Key released: " + e.getKeyText(e.getKeyCode()) + "\n");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        textArea.append("Key typed: " + e.getKeyChar() + "\n");
    }

    public static void main(String[] args) {
        new KeyExample();
    }
}
```

---
## KeyAdapter
- Used for handling key events by overriding methods like `keyPressed()`, `keyReleased()`, etc.

**Methods:**
- `void keyPressed(KeyEvent e)`: Invoked when a key is pressed.
- `void keyReleased(KeyEvent e)`: Invoked when a key is released.
- `void keyTyped(KeyEvent e)`: Invoked when a key is typed (a key is pressed and released).

**Usage Example:**
```java
component.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key press
    }
});
```

### KeyAdapter Example
- Use `KeyAdapter` to simplify the implementation by overriding only necessary methods:
```java
import javax.swing.*;
import java.awt.event.*;

public class KeyAdapterExample extends JFrame {
    public KeyAdapterExample() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Key pressed: " + e.getKeyText(e.getKeyCode()));
            }
        });
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new KeyAdapterExample();
    }
}
```

---

## FocusAdapter
- Used for handling focus events by overriding `focusGained()` and `focusLost()` methods.

**Methods:**
- `void focusGained(FocusEvent e)`: Invoked when the component gains focus.
- `void focusLost(FocusEvent e)`: Invoked when the component loses focus.

**Usage Example:**
```java
component.addFocusListener(new FocusAdapter() {
    @Override
    public void focusGained(FocusEvent e) {
        // Handle focus gained
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Handle focus lost
    }
});
```



---

## Summary
- **Window Events**:
  - Use `WindowListener` or `WindowAdapter` for managing JFrame lifecycle.
- **Key Events**:
  - Use `KeyListener` or `KeyAdapter` for detecting keyboard inputs.
- **Adapters**:
  - Simplify event handling by overriding only the methods you need.
