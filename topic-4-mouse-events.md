# Java Swing Mouse Events Cheatsheet

## Mouse Events Overview
- **Purpose**:
  - Detect mouse clicks, movement, and other interactions.
  - Enable interactive programs driven by mouse activity.
- **Listener Assignment**:
  - Mouse events are tracked on a component, not the mouse itself.

---

## Mouse Events Interfaces
- **MouseListener**:
  - Handles button actions (click, press, release, etc.).
  ```java
  public void mouseClicked(MouseEvent e);
  public void mouseEntered(MouseEvent e);
  public void mouseExited(MouseEvent e);
  public void mousePressed(MouseEvent e);
  public void mouseReleased(MouseEvent e);
  ```
  - Add a listener:
    ```java
    comp.addMouseListener(MouseListener listener);
    ```

- **MouseMotionListener**:
  - Handles motion actions (move, drag).
  ```java
  public void mouseMoved(MouseEvent e);
  public void mouseDragged(MouseEvent e);
  ```
  - Add a listener:
    ```java
    comp.addMouseMotionListener(MouseMotionListener listener);
    ```

---

## Handling Mouse Events
- **MouseEvent Object**:
  - Provides details about the mouse action.
  - Useful Methods:
    ```java
    int getX();               // X-coordinate of the event
    int getY();               // Y-coordinate of the event
    Point getPoint();         // Point of the event
    int getClickCount();      // Number of clicks
    Object getSource();       // Get the event source.
    int getModifiers();       // Get modifier keys (use masks).
    boolean isLeftMouseButton(MouseEvent e);
    boolean isRightMouseButton(MouseEvent e);
    boolean isMiddleMouseButton(MouseEvent e);
    ```

---

## Example: MouseListener
```java
import javax.swing.*;
import java.awt.event.*;

public class MouseExample extends JPanel implements MouseListener {
    public MouseExample() {
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at: " + e.getPoint());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered the component.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited the component.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse button pressed.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse button released.");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mouse Listener Example");
        frame.add(new MouseExample());
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

---

## Adapter Classes
- **Problem**: Tedious to implement unused methods.
- **Solution**: Use `MouseAdapter` or `MouseMotionAdapter` to override only the needed methods.
  ```java
  public class MyMouseAdapter extends MouseAdapter {
      @Override
      public void mousePressed(MouseEvent e) {
          System.out.println("Mouse pressed!");
      }
  }
  ```

---

## Example: MouseAdapter
```java
import javax.swing.*;
import java.awt.event.*;

public class MouseAdapterExample extends JPanel {
    public MouseAdapterExample() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed at: " + e.getPoint());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mouse Adapter Example");
        frame.add(new MouseAdapterExample());
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

---
## Mouse Wheel Scrolling

**MouseWheelListener Interface**:
- Interface to handle mouse wheel events.

**Method:**
- `void mouseWheelMoved(MouseWheelEvent event)`: Invoked when the mouse wheel is moved.

**Usage Example:**
```java
component.addMouseWheelListener(new MouseWheelListener() {
    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        // Handle mouse wheel movement
    }
});
```

**Method to Add Listener:**
- `public void addMouseWheelListener(MouseWheelListener ml)`: Adds a mouse wheel listener to a component.
```java
component.addMouseWheelListener(listener);
```


---

## MouseMotionListener Example
```java
import javax.swing.*;
import java.awt.event.*;

public class MouseMotionExample extends JPanel {
    public MouseMotionExample() {
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("Mouse dragged at: " + e.getPoint());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("Mouse moved to: " + e.getPoint());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mouse Motion Example");
        frame.add(new MouseMotionExample());
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

---

## Using Abstract Classes
- **Why Use Abstract Classes**:
  - To implement shared logic and behavior for mouse event handling.
  - Cannot be instantiated directly; must be extended.
  ```java
  public abstract class MyMouseAdapter extends MouseAdapter {
      public abstract void onMouseAction(MouseEvent e);
  }
  ```

---

## Full Example: Combined Mouse Listener
```java
import javax.swing.*;
import java.awt.event.*;

public class CombinedMouseExample extends JPanel {
    public CombinedMouseExample() {
        MyMouseInputAdapter adapter = new MyMouseInputAdapter();
        this.addMouseListener(adapter);
        this.addMouseMotionListener(adapter);
    }

    private class MyMouseInputAdapter extends MouseInputAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse pressed at: " + e.getPoint());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("Mouse dragged to: " + e.getPoint());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Combined Mouse Example");
        frame.add(new CombinedMouseExample());
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```
