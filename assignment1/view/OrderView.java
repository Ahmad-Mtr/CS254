package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrderView extends JFrame {
    public JTextField customerNameField;
    public JTextField totalAmountField;
    public JTextArea orderDetailsArea;
    public JPanel sizePanel;
    public JPanel crustPanel;
    JComboBox<String> crustBox;
    public JCheckBox[] toppingCheckboxes;

    public JButton addPizzaButton;
    public JButton calculateTotalButton;
    public JButton restartOrderButton;

    public OrderView() {
        setTitle("Pizza Order");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout setup
        setLayout(new BorderLayout());

        // Customer name and total amount panel --> Panel 1
        JPanel customerPanel = new JPanel(new FlowLayout());
        customerNameField = new JTextField(10);
        totalAmountField = new JTextField(10);
        totalAmountField.setEditable(false);

        customerPanel.add(new JLabel("Customer Name:"));
        customerPanel.add(customerNameField);
        customerPanel.add(new JLabel("Total:"));
        customerPanel.add(totalAmountField);

        // Pizza selection panel ---> panel2
        JPanel pizzaPanel = new JPanel(new GridLayout(2, 1));

        // Size selection
        sizePanel = new JPanel(new GridLayout(3 ,1 ));
        sizePanel.setBorder(BorderFactory.createTitledBorder("Size"));
        // Create radio buttons
        JRadioButton smallButton = new JRadioButton("Small");
        JRadioButton mediumButton = new JRadioButton("Medium", true); // Default selected
        JRadioButton largeButton = new JRadioButton("Large");
        // Add radio buttons to a ButtonGroup to make them mutually exclusive
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(smallButton);
        sizeGroup.add(mediumButton);
        sizeGroup.add(largeButton);
        // Add radio buttons to the panel
        sizePanel.add(smallButton);
        sizePanel.add(mediumButton);
        sizePanel.add(largeButton);




        // Crust selection
        crustPanel = new JPanel(new GridLayout(3 ,1 ));
        crustBox = new JComboBox<>(new String[]{"Pan", "Stuffed", "Regular"});
        crustPanel.setBorder(BorderFactory.createTitledBorder("Crust"));
        // Create radio buttons
        JRadioButton panButton = new JRadioButton("Pan");
        JRadioButton stuffedButton = new JRadioButton("Stuffed", true); // Default selected
        JRadioButton regularButton = new JRadioButton("Regular");
        // Add radio buttons to a ButtonGroup to make them mutually exclusive
        ButtonGroup crustGroup = new ButtonGroup();
        crustGroup.add(panButton);
        crustGroup.add(stuffedButton);
        crustGroup.add(regularButton);
        // Add radio buttons to the panel
        crustPanel.add(panButton);
        crustPanel.add(stuffedButton);
        crustPanel.add(regularButton);

        // Toppings selection
        JPanel toppingPanel = new JPanel(new GridLayout(2, 3));
        toppingPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
        String[] toppings = {"Extra Cheese", "Tomatoes", "Olives", "Green Peppers", "Onions"};
        toppingCheckboxes = new JCheckBox[toppings.length];
        for (int i = 0; i < toppings.length; i++) {
            toppingCheckboxes[i] = new JCheckBox(toppings[i]);
            toppingPanel.add(toppingCheckboxes[i]);
        }


        JPanel buttonsAreaPanel = new JPanel(); // Panel 4
        buttonsAreaPanel.setLayout(new BoxLayout(buttonsAreaPanel, BoxLayout.Y_AXIS));

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addPizzaButton = new JButton("Add Pizza");
        calculateTotalButton = new JButton("Calculate Order Total");
        restartOrderButton = new JButton("Restart Order");
        buttonPanel.add(addPizzaButton);
        buttonPanel.add(calculateTotalButton);
        buttonPanel.add(restartOrderButton);

        // Order details area
        orderDetailsArea = new JTextArea(10, 40);
        orderDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderDetailsArea);

        buttonsAreaPanel.add(buttonPanel);
        buttonsAreaPanel.add(orderDetailsArea);

        pizzaPanel.add(sizePanel);
        pizzaPanel.add(toppingPanel);
        pizzaPanel.add(crustPanel);
        pizzaPanel.add(buttonsAreaPanel);

        // Adding components to frame
        add(customerPanel, BorderLayout.NORTH);
        add(pizzaPanel, BorderLayout.CENTER);
//        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);


    }
}
