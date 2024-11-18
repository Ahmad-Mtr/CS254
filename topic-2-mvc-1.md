# Java Swing MVC Cheatsheet

## MVC Architecture
![MVC Diagram](https://cdn.jsdelivr.net/gh/Ahmad-Mtr/CS254@main/assets/mvc.png)
- **Model**: Encapsulates application data and logic.
  - Stores and manipulates data.
  - No reference to the view.
- **View**: Presents data to the user.
  - Displays data and provides interaction components (e.g., buttons).
  - No application logic or data storage.
- **Controller**: Links the model and the view.
  - Handles user actions and modifies the model.
  - Ensures user actions trigger the correct responses.

---

## MVC Advantages
- **Parallel Development**: Separate teams can work on the model, view, and controller.
- **Maintainability**: Update the view without modifying the model.
- **Multiple Views**: One model can support various views.

---

## MVC Example: Simple Calculator
### Model
- Handles logic and data storage.
```java
import java.math.BigInteger;

public class CalcModel {
    private BigInteger m_total = BigInteger.ZERO;

    public void reset() {
        m_total = BigInteger.ZERO;
    }

    public void multiplyBy(String operand) {
        m_total = m_total.multiply(new BigInteger(operand));
    }

    public String getValue() {
        return m_total.toString();
    }
}
```

### View
- Displays UI components.
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalcView extends JFrame {
    private JTextField m_userInputTf = new JTextField(5);
    private JTextField m_totalTf = new JTextField(20);
    private JButton m_multiplyBtn = new JButton("Multiply");
    private JButton m_clearBtn = new JButton("Clear");

    public CalcView() {
        m_totalTf.setEditable(false);

        JPanel content = new JPanel(new FlowLayout());
        content.add(new JLabel("Input"));
        content.add(m_userInputTf);
        content.add(m_multiplyBtn);
        content.add(new JLabel("Total"));
        content.add(m_totalTf);
        content.add(m_clearBtn);

        this.setContentPane(content);
        this.pack();
        this.setTitle("Simple Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setTotal(String total) {
        m_totalTf.setText(total);
    }

    public String getUserInput() {
        return m_userInputTf.getText();
    }

    public void addMultiplyListener(ActionListener mal) {
        m_multiplyBtn.addActionListener(mal);
    }

    public void addClearListener(ActionListener cal) {
        m_clearBtn.addActionListener(cal);
    }
}
```

### Controller
- Manages communication between model and view.
```java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcController {
    private CalcModel m_model;
    private CalcView m_view;

    public CalcController(CalcModel model, CalcView view) {
        m_model = model;
        m_view = view;

        m_view.addMultiplyListener(new MultiplyListener());
        m_view.addClearListener(new ClearListener());
    }

    class MultiplyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String userInput = m_view.getUserInput();
                m_model.multiplyBy(userInput);
                m_view.setTotal(m_model.getValue());
            } catch (NumberFormatException ex) {
                m_view.setTotal("Error: Invalid input");
            }
        }
    }

    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            m_model.reset();
            m_view.setTotal("0");
        }
    }
}
```

### Main
```java
public class CalcMVC {
    public static void main(String[] args) {
        CalcModel model = new CalcModel();
        CalcView view = new CalcView();
        new CalcController(model, view);

        view.setVisible(true);
    }
}
```

---

## MVC Example: Counter
### Model
```java
public class CounterModel {
    private int value = 0;

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    public int getValue() {
        return value;
    }
}
```

### View
```java
import javax.swing.*;
import java.awt.*;

public class CounterView extends JPanel {
    private JLabel valueLabel = new JLabel("0", SwingConstants.CENTER);
    private JButton incrementButton = new JButton("Up");
    private JButton decrementButton = new JButton("Down");

    public CounterView() {
        setLayout(new BorderLayout());
        add(incrementButton, BorderLayout.WEST);
        add(decrementButton, BorderLayout.EAST);
        add(valueLabel, BorderLayout.CENTER);
    }

    public void setValue(int value) {
        valueLabel.setText(String.valueOf(value));
    }

    public JButton getIncrementButton() {
        return incrementButton;
    }

    public JButton getDecrementButton() {
        return decrementButton;
    }
}
```

### Controller
```java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterController {
    private CounterModel model;
    private CounterView view;

    public CounterController(CounterModel model, CounterView view) {
        this.model = model;
        this.view = view;

        view.getIncrementButton().addActionListener(new IncrementListener());
        view.getDecrementButton().addActionListener(new DecrementListener());
    }

    class IncrementListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.increment();
            view.setValue(model.getValue());
        }
    }

    class DecrementListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.decrement();
            view.setValue(model.getValue());
        }
    }
}
```

### Main
```java
import javax.swing.*;

public class CounterApp {
    public static void main(String[] args) {
        CounterModel model = new CounterModel();
        CounterView view = new CounterView();
        new CounterController(model, view);

        JFrame frame = new JFrame("Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }
}
```
