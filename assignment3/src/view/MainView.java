package view;

import javax.swing.*;
import javax.swing.border.Border;

import controller.OrderController;
import model.Order;
import utils.ctp;

import java.awt.*;

public class MainView extends JFrame {
    private final ctp clrs;
    private OrderController newOrderController;

    public MainView() {
        clrs = new ctp();
        getContentPane().setBackground(clrs.getBase());
        getContentPane().setForeground(clrs.getText());
        applyCtpTheme(clrs);
        setTitle("Pizza ordering sys");
        setSize(1100, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFont(new Font("Helvetica", Font.PLAIN, 18));
        setLocationRelativeTo(null);

        // controller = new OrderController();

        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu orderMenu = new JMenu("Order");
        JMenuItem createOrderItem = new JMenuItem("New Order");
        createOrderItem.addActionListener(e -> openOrderView());
        JMenuItem viewOrdersItem = new JMenuItem("View Orders");
        viewOrdersItem.addActionListener(e -> openOrderDashboard());
        orderMenu.add(createOrderItem);
        orderMenu.add(viewOrdersItem);
        menuBar.add(orderMenu);
        setJMenuBar(menuBar);
    }

    private void openOrderView() {
        OrderView orderView = new OrderView();
        newOrderController = new OrderController(new Order(), orderView);
        orderView.setLocationRelativeTo(orderView.getParent());
        orderView.setVisible(true);
    }

    private void openOrderDashboard() {
        OrderDashboard orderDashboard = new OrderDashboard();
        orderDashboard.setLocationRelativeTo(orderDashboard.getParent());
        orderDashboard.setVisible(true);
    }

    public void applyCtpTheme(ctp theme) {
        // Frame background and foreground
        UIManager.put("Frame.background", theme.getBase());
        UIManager.put("Frame.foreground", theme.getText());
        // MenuBar styling
        UIManager.put("MenuBar.background", theme.getCrust());
        UIManager.put("MenuBar.foreground", theme.getText());
        // Menu styling
        UIManager.put("Menu.background", theme.getCrust());
        UIManager.put("Menu.selectionBackground", theme.getSky());
        UIManager.put("Menu.foreground", theme.getText());

        UIManager.put("OptionPane.foreground", theme.getText());
        UIManager.put("OptionPane.messageForeground", theme.getText());
        UIManager.put("OptionPane.background", theme.getBase());

        // MenuItem styling
        UIManager.put("MenuItem.background", theme.getCrust());
        UIManager.put("MenuItem.foreground", theme.getText());
        UIManager.put("MenuItem.border", theme.getSky());

        Border outerBorder = BorderFactory.createLineBorder(theme.getSurface2(), 2);
        Border innerPadding = BorderFactory.createEmptyBorder(5, 0, 5, 0);
        UIManager.put("MenuBar.border",
                BorderFactory.createCompoundBorder(outerBorder, innerPadding));
        UIManager.put("MenuItem.selectionBackground", theme.getSky());
    }
}
