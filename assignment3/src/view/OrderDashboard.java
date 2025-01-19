package view;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDashboard extends JFrame {
    private Connection connection;
    public JTextField searchField;
    public JButton searchButton;
    public JButton backToMenuButton;

    public OrderDashboard() {

        setTitle("Pizza Orders");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        // Container of all Contained
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(10);

        searchPanel.add(new JLabel("Customer Name:"));
        searchPanel.add(searchField);

        // To color a radio button
        // Icon unselectedIcon = new CircleIcon(new Color(0x585b70), new
        // Color(0x313244));
        // Icon selectedIcon = new CircleIcon(new Color(0x313244), new Color(0x89dceb));

        // Buttons init
        searchButton = new JButton("Search");
        backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.addActionListener(e -> {
            this.setVisible(false);
        });

        searchPanel.add(searchButton);
        searchPanel.add(backToMenuButton);

        add(searchPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // Table
        // Column names
        String[] columnNames = { "Order ID", "Customer Name", "Total Cost", "Date", "Pizza Details" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable orderTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/database",
                    "root",
                    "hU$$ein76");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed");
        }
        performSearch(searchButton, tableModel);
        mainPanel.add(scrollPane);

    }

    private void performSearch(JButton searchButton, DefaultTableModel tableModel) {
        searchButton.addActionListener(e -> {
            String customerName = searchField.getText().trim();
            System.out.println("Searching for: " + customerName);
            // Clear existing rows
            tableModel.setRowCount(0);
            try {
                String query = """
                             select * from orders where customer_name like ?;
                        """;
                PreparedStatement pstmt = connection.prepareStatement(query);

                pstmt.setString(1, "%" + customerName + "%");

                ResultSet rs = pstmt.executeQuery();

                // Populate table with search results
                while (rs.next()) {

                    int orderId = rs.getInt("order_id");
                    String query1 = """
                            select size, crust, toppings from order_details where order_id like ? ;
                            """;
                    PreparedStatement pstmt1 = connection.prepareStatement(query1);
                    pstmt1.setString(1, "%" + orderId + "%");
                    ResultSet rs1 = pstmt1.executeQuery();
                    String pizzaDetails = "";
                    while (rs1.next()) {
                        String detail = String.format("size: %s, toppings: %s, crust: %s",
                                rs1.getString("size"),
                                rs1.getString("toppings"),
                                rs1.getString("crust"));
                        pizzaDetails += detail + " | \n";
                    }

                    tableModel.addRow(new Object[] {
                            orderId,
                            rs.getString("customer_name"),
                            rs.getDouble("total"),
                            rs.getDate("order_date"),
                            pizzaDetails
                    });
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Search failed: " + ex.getMessage());

            }
        });
    }


}