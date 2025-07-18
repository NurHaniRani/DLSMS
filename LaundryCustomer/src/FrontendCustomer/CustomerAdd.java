package FrontendCustomer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import BackendCustomer.Database;

public class CustomerAdd {

    private JFrame frame;
    private JTextField PriceTxtFld;
    private int customerId;
    private JComboBox<String> WeightComboBox;

    public CustomerAdd(int customerId) {
        this.customerId = customerId;
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CustomerAdd window = new CustomerAdd(1);
                window.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initialize() {
        Color pastelBlue = new Color(173, 216, 230);
        Color pastelBackground = new Color(245, 248, 255);

        frame = new JFrame("Add Service Order");
        frame.setBounds(100, 100, 720, 520); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(pastelBackground);

        JLabel lblTitle = new JLabel("Add Service Order");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setForeground(new Color(70, 130, 180));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(210, 25, 300, 40);
        frame.getContentPane().add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(180, 80, 360, 300); 
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(pastelBlue, 2));
        frame.getContentPane().add(formPanel);

        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);

        JLabel lblService = new JLabel("Service Type:");
        lblService.setFont(labelFont);
        lblService.setBounds(30, 30, 120, 25);
        formPanel.add(lblService);

        JComboBox<String> ServiceTypeComboBox = new JComboBox<>();
        ServiceTypeComboBox.setFont(labelFont);
        ServiceTypeComboBox.setBounds(160, 30, 160, 25);
        formPanel.add(ServiceTypeComboBox);

        JLabel lblClothing = new JLabel("Clothing Type:");
        lblClothing.setFont(labelFont);
        lblClothing.setBounds(30, 70, 120, 25);
        formPanel.add(lblClothing);

        JComboBox<String> ClothingTypeComboBox = new JComboBox<>();
        ClothingTypeComboBox.setFont(labelFont);
        ClothingTypeComboBox.setBounds(160, 70, 160, 25);
        formPanel.add(ClothingTypeComboBox);

        JLabel lblWeight = new JLabel("Extra Weight (kg):");
        lblWeight.setFont(labelFont);
        lblWeight.setBounds(30, 110, 120, 25);
        formPanel.add(lblWeight);

        WeightComboBox = new JComboBox<>();
        WeightComboBox.setFont(labelFont);
        WeightComboBox.setBounds(160, 110, 160, 25);
        formPanel.add(WeightComboBox);

        JLabel lblPrice = new JLabel("Price (RM):");
        lblPrice.setFont(labelFont);
        lblPrice.setBounds(30, 150, 120, 25);
        formPanel.add(lblPrice);

        PriceTxtFld = new JTextField();
        PriceTxtFld.setFont(labelFont);
        PriceTxtFld.setEditable(false);
        PriceTxtFld.setBounds(160, 150, 160, 25);
        formPanel.add(PriceTxtFld);

        JButton OrderBtn = new JButton("Place Order");
        OrderBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        OrderBtn.setBackground(new Color(173, 216, 230)); 
        OrderBtn.setForeground(Color.BLACK);
        OrderBtn.setFocusPainted(false);
        OrderBtn.setBorder(BorderFactory.createLineBorder(new Color(100, 160, 200))); 
        OrderBtn.setBounds(110, 200, 140, 30);
        formPanel.add(OrderBtn);

        JLabel BackLabel = new JLabel("← Back to Home");
        BackLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        BackLabel.setForeground(Color.BLUE.darker());
        BackLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BackLabel.setBounds(125, 250, 120, 20);
        formPanel.add(BackLabel);

        // Add Service and Clothing Types
        ServiceTypeComboBox.addItem("Wash & Fold");
        ServiceTypeComboBox.addItem("Dry Clean");
        ServiceTypeComboBox.addItem("Iron Only");

        ClothingTypeComboBox.addItem("T-shirt");
        ClothingTypeComboBox.addItem("Pants");
        ClothingTypeComboBox.addItem("Baju Kurung");
        ClothingTypeComboBox.addItem("Jacket");
        ClothingTypeComboBox.addItem("Blanket (Light)");
        ClothingTypeComboBox.addItem("Blanket (Heavy)");
        ClothingTypeComboBox.addItem("Curtain");
        ClothingTypeComboBox.addItem("Carpet");
        ClothingTypeComboBox.addItem("Bedsheet");

        // Initialize WeightComboBox with extra weight from 0.0
        updateWeightComboBox();

        // Price calculation logic
        ActionListener recalculate = e -> {
            String service = (String) ServiceTypeComboBox.getSelectedItem();
            String clothing = (String) ClothingTypeComboBox.getSelectedItem();
            String weightStr = (String) WeightComboBox.getSelectedItem();

            if (service != null && clothing != null && weightStr != null) {
                double extraWeight = Double.parseDouble(weightStr);

                double baseClothingPrice = switch (clothing) {
                    case "T-shirt" -> 3.0;
                    case "Pants" -> 4.0;
                    case "Baju Kurung" -> 5.0;
                    case "Jacket" -> 6.0;
                    case "Blanket (Light)" -> 10.0;
                    case "Blanket (Heavy)" -> 15.0;
                    case "Curtain" -> 12.0;
                    case "Carpet" -> 20.0;
                    case "Bedsheet" -> 8.0;
                    default -> 0.0;
                };

                double serviceMultiplier = switch (service) {
                    case "Wash & Fold" -> 1.0;
                    case "Dry Clean" -> 1.5;
                    case "Iron Only" -> 0.8;
                    default -> 1.0;
                };

                double basePrice = baseClothingPrice * serviceMultiplier;

                // Example extra weight rate (RM per kg)
                double extraWeightRate = 5.0;
                double extraCharge = extraWeight * extraWeightRate;

                double total = basePrice + extraCharge;

                PriceTxtFld.setText(String.format("%.2f", total));
            }
        };

        ServiceTypeComboBox.addActionListener(recalculate);
        ClothingTypeComboBox.addActionListener(recalculate);
        WeightComboBox.addActionListener(recalculate);

        OrderBtn.addActionListener(e -> {
            String service = (String) ServiceTypeComboBox.getSelectedItem();
            String clothing = (String) ClothingTypeComboBox.getSelectedItem();
            String weightStr = (String) WeightComboBox.getSelectedItem();
            String priceStr = PriceTxtFld.getText();

            if (service == null || clothing == null || weightStr == null || priceStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double extraWeight = Double.parseDouble(weightStr);
                double price = Double.parseDouble(priceStr);

                Database.createOrder(customerId, service, clothing, extraWeight, price);
                JOptionPane.showMessageDialog(frame, "Order placed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                ServiceTypeComboBox.setSelectedIndex(0);
                ClothingTypeComboBox.setSelectedIndex(0);
                updateWeightComboBox();
                PriceTxtFld.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        BackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new CustomerHome(customerId).show();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                BackLabel.setText("<html><u>← Back to Home</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                BackLabel.setText("← Back to Home");
            }
        });
    }

    private void updateWeightComboBox() {
        WeightComboBox.removeAllItems();
        for (double i = 0.0; i <= 10.0; i += 0.1) {
            WeightComboBox.addItem(String.format("%.1f", i));
        }
    }

    public void show() {
        frame.setVisible(true);
    }
}








