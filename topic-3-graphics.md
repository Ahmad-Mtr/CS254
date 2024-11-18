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

## Finding screen parameters
```java
Toolkit tk = Toolkit.getDefaultToolkit();
Dimension screenDim = tk.getScreenSize();
int screenHeight = screenDim.height;
int screenWidth = screenDim.width;
int pixPerInch = tk.getScreenResolution();
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

| Method                                        | Description                                                                                               |
| --------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| `draw(Shape shape)`                           | Draws the outline of a shape.                                                                             |
| `fill(Shape shape)`                           | Draws and fills the interior of a shape.                                                                  |
| `rotate(angle)`                               | Rotates future drawings by a specified angle.                                                             |
| `getPaint(), setPaint(Paint)`                 | the current paint used for drawing (`Color` is one kind of `Paint`)                                       |
| `getStroke(), setStroke(Stroke)`              | the current line stroke style used for drawing (*thin* \| *thick*, *solid* \| *dashed* \| *dotted*, etc.) |
| `scale(sx, sy)`                               | Scales future drawings by the given x and y factors.                                                      |
| `translate(dx, dy)`                           | Moves future drawings by the specified x and y amounts.                                                   |
| `setRenderingHint(RenderingHints.KEY, VALUE)` | Enables anti-aliasing or other rendering hints.                                                           |


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
### Common Shapes in `java.awt.geom`

```java
new Rectangle2D.Double(x, y, w, h); // Rectangle 
new Ellipse2D.Double(x, y, w, h); // Ellipse 
new Line2D.Double(x1, y1, x2, y2); // Line 
new RoundRectangle2D.Double(x, y, w, h, arcW, arcH); // Rounded Rectangle 
new Arc2D.Double(x, y, w, h, start, extent, type); // Arc segment 
new Path2D.Double(); // Custom polygons new GeneralPath(); // Customizable shapes
```


- **Methods**:
### Common Shape Methods

| Method                        | Description                                                |
| ----------------------------- | ---------------------------------------------------------- |
| `contains(x, y)`              | Checks if the given point is inside the shape.             |
| `contains(Point)`             | Checks if the given point is inside the shape.             |
| `contains(Rectangle)`         | Checks if the given rectangle is inside the shape.         |
| `getBounds()`                 | Returns the bounding box around the shape as a rectangle.  |
| `getCenterX() / getCenterY()` | Gets the center coordinates of the shape.                  |
| `getMinX() / getMinY()`       | Gets the minimum X or Y coordinate of the shape.           |
| `getMaxX() / getMaxY()`       | Gets the maximum X or Y coordinate of the shape.           |
| `intersects(x, y, w, h)`      | Checks if the shape intersects the given rectangle.        |
| `intersects(Rectangle)`       | Checks if the shape intersects the given rectangle object. |

#### Example of `GeneralPath`
The `GeneralPath` class is used for creating complex, customizable shapes, like the one shown below:

```java
GeneralPath path = new GeneralPath();
path.moveTo(50, 50);    // Start point
path.lineTo(100, 100);  // Draw a line
path.lineTo(50, 150);   // Draw another line
path.lineTo(0, 100);    // Complete the polygon
path.closePath();       // Close the shape
```


---

## Colors & Paints
- **Set a color for drawing and filling shapes**:
  ```java
  g.setColor(Color.RED);   // Sets the drawing color to red
  g.fillRect(50, 50, 100, 100); // Draws a filled rectangle
  ```
- **Custom Colors**:
  ```java
  Color customColor = new Color(128, 64, 192); // RGB values
  g.setColor(customColor);
  g.fillOval(100, 100, 50, 50); // Draws a filled oval
  ```
- **Gradient Paints**:
  ```java
  Graphics2D g2d = (Graphics2D) g;
  GradientPaint gradient = new GradientPaint(50, 50, Color.RED, 150, 150, Color.BLUE);
  g2d.setPaint(gradient);
  g2d.fillRect(50, 50, 100, 100);
  ```

## Strokes
- **Setting Stroke Styles**:
  ```java
  Graphics2D g2d = (Graphics2D) g;
  g2d.setStroke(new BasicStroke(5)); // Set stroke width to 5
  g2d.drawLine(50, 50, 150, 150);   // Draw a thick line
  ```
- **Dashed Strokes**:
  ```java
  float[] dashPattern = {10, 5}; // Dash, gap
  g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
  g2d.drawRect(50, 50, 100, 100); // Dashed rectangle
  ```

## Repainting
- **Calling `repaint()` to refresh the UI**:
  ```java
  public void paintComponent(Graphics g) {
      super.paintComponent(g); // Clears the previous drawing
      g.setColor(Color.BLUE);
      g.fillRect(50, 50, 100, 100);
  }
  
  // Trigger repainting
  myPanel.repaint();
  ```
- **Why Use `repaint()`**: It ensures the drawing is scheduled on the Event Dispatch Thread (EDT) and prevents UI glitches.

## Anti-Aliasing
- **Enable Anti-Aliasing for Smoother Graphics**:
  ```java
  Graphics2D g2d = (Graphics2D) g;
  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  g2d.drawOval(50, 50, 100, 100); // Draws a smooth oval
  ```

## Creating Images
- **Create an Off-Screen Image**:
  ```java
  BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
  Graphics2D g2d = image.createGraphics();
  g2d.setColor(Color.GREEN);
  g2d.fillRect(50, 50, 100, 100); // Draw on the image
  g2d.dispose(); // Release resources
  ```
- **Draw the Image on a Component**:
  ```java
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(image, 0, 0, null); // Draw the created image
  }
  ```
- **Save the Image to a File** (Requires `javax.imageio`):
  ```java
  try {
      ImageIO.write(image, "png", new File("output.png"));
  } catch (IOException e) {
      e.printStackTrace();
  }
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
