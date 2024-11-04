package controller;

import model.Order;
import model.Pizza;
import view.OrderView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderController {
    private Order order;
    private OrderView view;

    public OrderController(Order order, OrderView view) {
        this.order = order;
        this.view = view;
        view.addPizzaButton.addActionListener(new AddPizzaListener());
        view.calculateTotalButton.addActionListener(new CalculateTotalListener());
        view.restartOrderButton.addActionListener(new RestartOrderListener());
    }

    class AddPizzaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String size = getSelectedSize();
            String crust = getSelectedCrust();
            ArrayList<String> toppings = getSelectedToppings();

            // Create a new Pizza object and add it to the order list
            Pizza pizza = new Pizza(size, crust, toppings);
            order.addPizza(pizza);

            // Update order details area
            view.orderDetailsArea.append("Pizza #" + order.getPizzas().size() + ": " + pizza.toString() + "\n");
        }

        private String getSelectedSize() {
            for (Component comp : view.sizePanel.getComponents()) {
                if (comp != null && ((JRadioButton) comp).isSelected()) {
                    return ((JRadioButton) comp).getText();
                }
            }
            return "Medium"; // default if none selected
        }

        private String getSelectedCrust() {
            for (Component comp : view.crustPanel.getComponents()) {
                if (comp instanceof JRadioButton && ((JRadioButton) comp).isSelected()) {
                    return ((JRadioButton) comp).getText();
                }
            }
            return "Regular"; // default if none selected
        }

        private ArrayList<String> getSelectedToppings() {
            ArrayList<String> selectedToppings = new ArrayList<>();
            for (JCheckBox checkbox : view.toppingCheckboxes) {
                if (checkbox.isSelected()) {
                    selectedToppings.add(checkbox.getText());
                }
            }
            return selectedToppings;
        }
    }

    class CalculateTotalListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Pizza> pizzas = order.getPizzas();
            String pizzaString = "";
            for (Pizza pizza :
                    pizzas) {
                pizzaString += pizza.toString() + "\n";
            }
            view.orderDetailsArea.append("Order by: " + view.customerNameField.getText() + "\n"
                    + "Total Price: " + order.calculateTotalCost() + "\n" + pizzaString);

            view.totalAmountField.setText(""+order.getCost());
        }
    }

    class RestartOrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            order.restart();
            view.orderDetailsArea.setText("");
            view.totalAmountField.setText("");
            view.customerNameField.setText("");

            // Reset size selection to Medium
            ((JRadioButton) view.sizePanel.getComponents()[1]).setSelected(true);

            // Reset crust selection to Stuffed
            ((JRadioButton) view.crustPanel.getComponents()[1]).setSelected(true);
            // Uncheck all toppings
            for (JCheckBox checkbox : view.toppingCheckboxes) {
                checkbox.setSelected(false);
            }
        }
    }
}
