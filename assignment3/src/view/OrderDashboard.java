package view;

import utils.CircleIcon;
import utils.ctp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class OrderDashboard extends JFrame {
    private final ctp clrs;
    private Connection connection;
    public JTextField searchField;
    public JButton searchButton;
    public JButton backToMenuButton;

    public OrderDashboard() {
        this.setFont(new Font("Helvetica", Font.PLAIN, 18));
        clrs = new ctp();
        applyCtpTheme(clrs);

        getContentPane().setBackground(clrs.getBase());
        getContentPane().setForeground(clrs.getText());
        setTitle("Pizza Orders Dashboard");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        // Container of all Contained
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(10);
        searchPanel.setForeground(clrs.getText());

        searchPanel.add(new JLabel("Customer Name:"));
        searchPanel.add(searchField);

        // To color a radio button
        // Icon unselectedIcon = new CircleIcon(new Color(0x585b70), new
        // Color(0x313244));
        // Icon selectedIcon = new CircleIcon(new Color(0x313244), new Color(0x89dceb));

        // Buttons init
        searchButton = new JButton("Search");
        backToMenuButton = new JButton("Back to Menu");
        stylizeButton(searchButton);
        stylizeButton(backToMenuButton);
        searchPanel.add(searchButton);
        searchPanel.add(backToMenuButton);

        add(searchPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // Table
        // Column names
        String[] columnNames = { "Order ID", "Customer Name", "Date", "Total", "Status" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable orderTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);

        try {
            connection = DriverManager.getConnection(
                // Used port 3307 since I've mapped docker's one 3306 to 3307 
                    "jdbc:mysql://localhost:3307/pleaseGiveThisCourseInFlutterOrTauriOrPythonAsThoseAreTheFuture",
                    "root",
                    "password123");
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
                // Prepare and execute search query
                PreparedStatement pstmt = connection.prepareStatement(
                        "SELECT * FROM orders WHERE customer_name LIKE ?");
                pstmt.setString(1, "%" + customerName + "%");

                ResultSet rs = pstmt.executeQuery();

                // Populate table with search results
                while (rs.next()) {
                    tableModel.addRow(new Object[] {
                            rs.getInt("order_id"),
                            rs.getString("customer_name"),
                            rs.getDate("order_date"),
                            rs.getDouble("total_amount"),
                            rs.getString("status")
                    });
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Search failed: " + ex.getMessage());
            }
        });
    }

    private void stylizeButton(JButton button) {
        // Padding and border
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(clrs.getSky(), 2),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
        button.setFocusPainted(false);

        // Hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(clrs.getSky());
                button.setForeground(clrs.getCrust());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(clrs.getCrust());
                button.setForeground(clrs.getText());
            }

        });
    }

    public void applyCtpTheme(ctp theme) {
        // Text
        UIManager.put("Label.foreground", theme.getText());
        UIManager.put("Button.foreground", theme.getText());
        UIManager.put("TextField.foreground", theme.getText());

        // bckgrnd
        UIManager.put("Frame.background", theme.getBase());
        UIManager.put("Panel.background", theme.getBase());
        UIManager.put("Button.background", theme.getCrust());
        UIManager.put("TextField.background", theme.getCrust());
        UIManager.put("RadioButton.background", theme.getMantle());

        // Others
        // UIManager.put("Button.border",
        // BorderFactory.createLineBorder(theme.getSky()));
        UIManager.put("TextField.border", BorderFactory.createLineBorder(theme.getSurface0()));
        UIManager.put("RadioButton.focus", theme.getSurface());

        UIManager.put("Button.margin", new Insets(10, 20, 10, 20));
        UIManager.put("Button.border", new LineBorder(clrs.getSky(), 2));
        UIManager.put("Button.preferredSize", new Dimension(200, 50));
        // Header Styling

        UIManager.put("TableHeader.background", theme.getBase());

        UIManager.put("TableHeader.foreground", theme.getText());

        UIManager.put("TableHeader.font", new Font("Arial", Font.BOLD, 14));

        UIManager.put("TableHeader.cellBorder",

                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(theme.getSurface()),

                        BorderFactory.createEmptyBorder(5, 5, 5, 5)

                )

        );

        // Table Body Styling

        UIManager.put("Table.background", theme.getCrust());

        UIManager.put("Table.foreground", theme.getText());

        UIManager.put("Table.selectionBackground", theme.getSurface());

        UIManager.put("Table.selectionForeground", theme.getText());

        // Grid and Border

        UIManager.put("Table.gridColor", theme.getSurface0());

        UIManager.put("Table.showVerticalLines", true);

        UIManager.put("Table.showHorizontalLines", true);

        // Dimensions and Font

        UIManager.put("Table.rowHeight", 25);

        UIManager.put("Table.font", new Font("Helvetica", Font.PLAIN, 18));
    }

}