package view;

import java.awt.FlowLayout;

import javax.accessibility.AccessibleContext;
import javax.swing.*;

public class SultanView extends JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem NewOrder, ViewOrders;

    public SultanView() {

        setLayout(new FlowLayout());

        menuBar = new JMenuBar();
        menu = new JMenu("Order");

        NewOrder = new JMenuItem("New Order");
        NewOrder.addActionListener(e -> NewOrderView());
        
        ViewOrders = new JMenuItem("View Orders");
        ViewOrders.addActionListener(e -> NewOrderDashboard());

        menu.add(NewOrder);
        menu.add(ViewOrders);

        menuBar.add(menu);

        setJMenuBar(menuBar);

        // JButton g = new JButton();
        // add(g);
        setSize(500, 500);
        setTitle("Sultan's work");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void NewOrderView() {
        // OrderView orderView = new OrderView();
        // newOrderController = new OrderController(new Order(), orderView);
        // orderView.setLocationRelativeTo(orderView.getParent());
        // orderView.setVisible(true);
    }

    private void NewOrderDashboard() {
        // OrderDashboard orderDashboard = new OrderDashboard();
        // orderDashboard.setLocationRelativeTo(orderDashboard.getParent());
        // orderDashboard.setVisible(true);
    }
}
