# Java Swing Graphics Cheatsheet

## Graphics in Java Swing
- **Concept**: Panels serve as canvases for custom 2D drawings.
- **Coordinate System**: Origin `(0,0)` at the top-left. X-axis increases to the right, Y-axis increases downward.
- **Custom Components**:
  - Extend `JPanel`.
  - Override `paintComponent(Graphics g)` for custom rendering.
  - Always call `super.paintComponent(g)` at the start to retain background painting.

---

## Drawing Basics
- **Graphics Methods**:
  ```java
  drawLine(x1, y1, x2, y2);   // Line
  drawRect(x, y, width, height);   // Rectangle outline
  drawOval(x, y, width, height);   // Ellipse outline
  drawString(text, x, y);   // Text
  fillRect(x, y, width, height);   // Filled rectangle
  fillOval(x, y, width, height);   // Filled ellipse
  setColor(Color c);   // Set drawing color
  ```
- **Repainting**:
  ```java
  repaint(); // Requests the panel to redraw.
  ```

---

## Example: Simple Graphics
```java
import java.awt.*;
import javax.swing.*;

public class SimpleGraphicsPanel extends JPanel {
    public SimpleGraphicsPanel() {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(300, 300));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(10, 10, 100, 100);
        g.setColor(Color.RED);
        g.drawRect(50, 50, 100, 50);
        g.setColor(Color.BLUE);
        g.drawString("Hello Graphics!", 80, 200);
    }
}

public class SimpleGraphicsApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graphics Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SimpleGraphicsPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
```

---

## Advanced Graphics with `Graphics2D`
- **Features**:
  - Supports transformations, custom shapes, and anti-aliasing.
  - Cast `Graphics` to `Graphics2D`:
    ```java
    Graphics2D g2 = (Graphics2D) g;
    ```
- **Key Methods**:
  ```java
  draw(Shape shape);   // Draw shape outline
  fill(Shape shape);   // Draw filled shape
  rotate(angle);   // Rotate future drawings
  scale(sx, sy);   // Scale future drawings
  translate(dx, dy);   // Move future drawings
  setRenderingHint(RenderingHints.KEY, VALUE);   // Enable anti-aliasing
  ```

---

## Example: Drawing with Graphics2D
```java
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class AdvancedGraphicsPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw a rectangle
        g2.setColor(Color.GREEN);
        g2.fill(new Rectangle2D.Double(50, 50, 100, 50));

        // Draw an ellipse
        g2.setColor(Color.BLUE);
        g2.fill(new Ellipse2D.Double(200, 50, 100, 50));

        // Anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.RED);
        g2.drawString("Smooth Text", 100, 150);
    }
}

public class AdvancedGraphicsApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Advanced Graphics Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new AdvancedGraphicsPanel());
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
```

---

## Shapes (`java.awt.geom`)
- **Common Shapes**:
  ```java
  new Rectangle2D.Double(x, y, w, h);
  new Ellipse2D.Double(x, y, w, h);
  new Line2D.Double(x1, y1, x2, y2);
  new RoundRectangle2D.Double(x, y, w, h, arcWidth, arcHeight);
  new Path2D.Double(); // Custom polygons
  ```
- **Methods**:
  ```java
  contains(x, y);   // Point inside shape
  getBounds();      // Get bounding rectangle
  intersects(rect); // Check overlap with rectangle
  ```

---

## Colors and Strokes
- **Color**:
  ```java
  new Color(r, g, b);       // RGB
  new Color(r, g, b, alpha); // RGBA (alpha for transparency)
  ```
- **Stroke**:
  ```java
  g2.setStroke(new BasicStroke(width)); // Set pen width
  ```

---

## Full Example: Dynamic Graphics
```java
import javax.swing.*;
import java.awt.*;

public class DynamicGraphicsPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw dynamic rectangle based on panel size
        int width = getWidth() / 3;
        int height = getHeight() / 3;
        g2.setColor(Color.ORANGE);
        g2.fillRect(width, height, width, height);
    }
}

public class DynamicGraphicsApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dynamic Graphics Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DynamicGraphicsPanel());
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
```
