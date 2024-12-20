Topic 6:

Here is a concise Java Swing Mouse Events Cheatsheet formatted in markdown, ready for Obsidian:

# Java Swing Mouse Events Cheatsheet

## Mouse Event Listeners
- **MouseListener**: Handles mouse button interactions.
  - `mouseClicked(MouseEvent e)`
  - `mouseEntered(MouseEvent e)`
  - `mouseExited(MouseEvent e)`
  - `mousePressed(MouseEvent e)`
  - `mouseReleased(MouseEvent e)`

- **MouseMotionListener**: Handles mouse movement.
  - `mouseMoved(MouseEvent e)`
  - `mouseDragged(MouseEvent e)`

- **MouseWheelListener**: Handles mouse wheel scrolling.
  - `mouseWheelMoved(MouseWheelEvent e)`

- **MouseInputListener**: Combines `MouseListener` and `MouseMotionListener`.

---

## Adding Listeners to Components
```java
component.addMouseListener(new MouseAdapter() {
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed!");
    }
});
component.addMouseMotionListener(new MouseMotionAdapter() {
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse Dragged!");
    }
});
component.addMouseWheelListener(new MouseWheelListener() {
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println("Mouse Wheel Moved!");
    }
});
MouseEvent Methods
Position:
getX() / getY(): X/Y coordinates.
getPoint(): Returns a Point object with X/Y.
Button Pressed:
SwingUtilities.isLeftMouseButton(e)
SwingUtilities.isMiddleMouseButton(e)
SwingUtilities.isRightMouseButton(e)
Click Count:
getClickCount(): Number of clicks.
Check for double-click: if (e.getClickCount() == 2)
Modifiers:
getModifiers(): Key/Mouse modifiers (CTRL_MASK, SHIFT_MASK).
Adapter Pattern
Problem: Implementing interfaces requires all methods, even if unused.
Solution: Use adapter classes like MouseAdapter or MouseMotionAdapter.
Example with Adapter
public class MyMouseAdapter extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed at " + e.getPoint());
    }
}
component.addMouseListener(new MyMouseAdapter());
Abstract Classes vs. Interfaces
Abstract Class: Can include method bodies and states. Supports single inheritance.
Interface: No state, multiple implementations allowed.
Mouse Input Example
public class MyMouseInputAdapter extends MouseInputAdapter {
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed");
    }
    public void mouseDragged(MouseEvent e) {
        Point p = e.getPoint();
        System.out.println("Mouse Dragged at " + p);
    }
}
MyMouseInputAdapter adapter = new MyMouseInputAdapter();
panel.addMouseListener(adapter);
panel.addMouseMotionListener(adapter);
Key References
MouseListener Docs
MouseMotionListener Docs
MouseWheelListener Docs

This cheatsheet covers all the essential syntax and examples for working with mouse events in Java Swing. Let me know if you need any adjustments!


Topic 7:

Here is the Java Swing Events Cheatsheet (Window and Key Events) in markdown, ready for Obsidian:

# Java Swing Events Cheatsheet (Window and Key Events)

## Window Events
- **Interface**: `WindowListener`
- **Common Methods**:
  - `windowOpened(WindowEvent e)`: First time the window is made visible.
  - `windowClosing(WindowEvent e)`: Invoked when the user attempts to close the window.
  - `windowClosed(WindowEvent e)`: Invoked after the window is closed.
  - `windowActivated(WindowEvent e)`: Invoked when the window becomes active.
  - `windowDeactivated(WindowEvent e)`: Invoked when the window is no longer active.
  - `windowIconified(WindowEvent e)`: Invoked when the window is minimized.
  - `windowDeiconified(WindowEvent e)`: Invoked when the window is restored.

### Adding a Window Listener
```java
frame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
        System.out.println("Window is closing");
        System.exit(0);
    }
});
Key Events
Interface: KeyListener
Common Methods:
keyPressed(KeyEvent e): Called when a key is pressed.
keyReleased(KeyEvent e): Called when a key is released.
keyTyped(KeyEvent e): Called when a key is typed (pressed and released).
Adding a Key Listener
component.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Enter key pressed!");
        }
    }
});
KeyEvent Methods
Key Information:
getKeyChar(): Character representation of the key.
getKeyCode(): Integer key code (use with KeyEvent.VK_* constants).
getModifiers(): Returns active modifiers (CTRL_MASK, SHIFT_MASK, etc.).
getModifiersEx(): Returns extended modifiers.
Check Modifiers:
KeyEvent.VK_*: Constants for keys (e.g., VK_A, VK_ENTER).
isShiftDown(), isControlDown(), isAltDown(): Check specific modifier keys.
Example: Key Listener with Modifiers
component.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("Ctrl+S was pressed!");
        }
    }
});
KeyEvent Example
public class PacManKeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Move right!");
        }
    }
}
frame.addKeyListener(new PacManKeyListener());
Key Demo Application
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyDemo extends JFrame implements KeyListener {
    private JTextArea textArea;

    public KeyDemo() {
        super("Key Events Demo");
        textArea = new JTextArea(10, 15);
        textArea.setText("Press any key...");
        textArea.setEnabled(false);
        getContentPane().add(textArea);
        addKeyListener(this);
        setSize(350, 100);
        setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        textArea.setText("Key Pressed: " + e.getKeyText(e.getKeyCode()));
    }

    public void keyReleased(KeyEvent e) {
        textArea.setText("Key Released: " + e.getKeyText(e.getKeyCode()));
    }

    public void keyTyped(KeyEvent e) {
        textArea.setText("Key Typed: " + e.getKeyChar());
    }

    public static void main(String[] args) {
        new KeyDemo().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
Window vs. Key Event Summary
Feature	Window Events	Key Events
Interface	WindowListener, WindowAdapter	KeyListener, KeyAdapter
Common Usage	Manage window state changes (open, close, minimize).	Capture and handle key presses.
Typical Methods	windowClosing, windowActivated	keyPressed, keyReleased
Example Usage	Exit application on close.	Add shortcut keys (e.g., Ctrl+S).

This markdown file contains concise, practical syntax and examples for working with window and key events in Java Swing. Let me know if you need further adjustments or additional sections!


Topic 8:

Here is the Java Swing Events and Components Cheatsheet in markdown, ready for Obsidian:

# Java Swing Events and Components Cheatsheet

## Focus Events
- **Interface**: `FocusListener`
- **Methods**:
  - `focusGained(FocusEvent e)`: Invoked when a component gains focus.
  - `focusLost(FocusEvent e)`: Invoked when a component loses focus.

### Adding a Focus Listener
```java
component.addFocusListener(new FocusAdapter() {
    public void focusGained(FocusEvent e) {
        component.setBackground(Color.LIGHT_GRAY);
    }

    public void focusLost(FocusEvent e) {
        component.setBackground(Color.WHITE);
    }
});
Component Events
Interface: ComponentListener
Methods:
componentHidden(ComponentEvent e): Invoked when a component becomes invisible.
componentShown(ComponentEvent e): Invoked when a component becomes visible.
componentMoved(ComponentEvent e): Invoked when a component is moved.
componentResized(ComponentEvent e): Invoked when a component is resized.
Adding a Component Listener
component.addComponentListener(new ComponentAdapter() {
    public void componentResized(ComponentEvent e) {
        System.out.println("Component resized");
    }
});
JList Selection Events
Interface: ListSelectionListener
Method: valueChanged(ListSelectionEvent e)
Usage:
jList.addListSelectionListener(e -> {
    System.out.println("Selected: " + jList.getSelectedValue());
});
Document Events
Interface: DocumentListener
Methods:
insertUpdate(DocumentEvent e): Text inserted.
removeUpdate(DocumentEvent e): Text removed.
changedUpdate(DocumentEvent e): Document attributes changed.
Adding a Document Listener
textComponent.getDocument().addDocumentListener(new DocumentListener() {
    public void insertUpdate(DocumentEvent e) {
        System.out.println("Text inserted");
    }

    public void removeUpdate(DocumentEvent e) {
        System.out.println("Text removed");
    }

    public void changedUpdate(DocumentEvent e) {
        System.out.println("Document changed");
    }
});
Item Events
Interface: ItemListener
Method: itemStateChanged(ItemEvent e)
State Constants:
ItemEvent.SELECTED
ItemEvent.DESELECTED
Adding an Item Listener
checkBox.addItemListener(e -> {
    if (e.getStateChange() == ItemEvent.SELECTED) {
        System.out.println("Item selected");
    } else {
        System.out.println("Item deselected");
    }
});
Change Events
Interface: ChangeListener
Method: stateChanged(ChangeEvent e)
Usage:
slider.addChangeListener(e -> {
    System.out.println("Slider value: " + slider.getValue());
});
Menus
JMenuBar
JMenuBar menuBar = new JMenuBar();
frame.setJMenuBar(menuBar);
JMenu
JMenu menu = new JMenu("File");
menuBar.add(menu);
menu.addSeparator();
JMenuItem
JMenuItem item = new JMenuItem("Open");
item.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
item.addActionListener(e -> System.out.println("Open clicked"));
menu.add(item);
Mnemonics and Accelerators
Mnemonics: Assign a shortcut to focus on a menu item (e.g., Alt + Key).
menuItem.setMnemonic('O');
Accelerators: Assign a global hotkey for any component.
menuItem.setAccelerator(KeyStroke.getKeyStroke('T', KeyEvent.CTRL_DOWN_MASK));
JSlider
Features:
Orientation: Horizontal/Vertical.
Tick marks: Major and Minor.
Snap-to-ticks.
JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
slider.setMajorTickSpacing(10);
slider.setMinorTickSpacing(1);
slider.setPaintTicks(true);
slider.setPaintLabels(true);
slider.addChangeListener(e -> System.out.println("Value: " + slider.getValue()));
Summary of Key Listeners
Listener	Description	Key Methods
FocusListener	Tracks focus gain/loss for components.	focusGained, focusLost
ComponentListener	Detects component visibility, size, or position changes.	componentResized, componentShown
ListSelectionListener	Tracks selection changes in JList.	valueChanged
DocumentListener	Monitors changes in JTextComponent.	insertUpdate, removeUpdate
ItemListener	Tracks item selection/deselection.	itemStateChanged
ChangeListener	Tracks changes in component states (e.g., sliders).	stateChanged

This cheatsheet includes syntax, examples, and concise descriptions for additional events and components in Java Swing. Let me know if you need further adjustments or enhancements!