package FrontendCustomer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import BackendCustomer.Database;

public class CustomerCheck {

    private JFrame frame;
    private int customerId;
    private JTable Ordertable;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CustomerCheck window = new CustomerCheck(1);
                window.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CustomerCheck(int customerId) {
        this.customerId = customerId;
        initialize();
    }

    private void initialize() {
        Color backgroundColor = new Color(255, 250, 250); 
        Color buttonColor = new Color(173, 216, 230);   
        Color labelColor = new Color(51, 51, 102);  
        Color headerColor = new Color(230, 230, 250); 
        Color rowColor = new Color(255, 255, 255);   

        frame = new JFrame();
        frame.setTitle("Check Order Status");
        frame.setBounds(100, 100, 720, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(backgroundColor);

        JLabel lblTitle = new JLabel("Order List");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitle.setForeground(labelColor);
        lblTitle.setBounds(270, 20, 120, 25);
        frame.getContentPane().add(lblTitle);

        JButton homeButton = new JButton("← Home Page");
        homeButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        homeButton.setBounds(30, 10, 120, 30);
        homeButton.setFocusPainted(false);
        homeButton.setBackground(buttonColor);
        homeButton.setForeground(Color.BLACK);
        frame.getContentPane().add(homeButton);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerHome(customerId).show();
            }
        });

        Ordertable = new JTable();
        JScrollPane scrollPane = new JScrollPane(Ordertable);
        scrollPane.setBounds(30, 70, 570, 250);
        frame.getContentPane().add(scrollPane);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Service");
        columnNames.add("Clothing");
        columnNames.add("Weight (kg)");
        columnNames.add("Price (RM)");
        columnNames.add("Order Date");
        columnNames.add("Status");

        Database.fetchOrders(customerId, data -> {
            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };

            Ordertable.setModel(model);

            JTableHeader header = Ordertable.getTableHeader();
            header.setBackground(headerColor);
            header.setForeground(labelColor);
            header.setFont(new Font("SansSerif", Font.BOLD, 12));

            Ordertable.setFont(new Font("SansSerif", Font.PLAIN, 12));
            Ordertable.setRowHeight(22);
            Ordertable.setSelectionBackground(new Color(216, 191, 216));
            Ordertable.setBackground(rowColor);
            Ordertable.setGridColor(Color.LIGHT_GRAY);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < Ordertable.getColumnCount(); i++) {
                Ordertable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            int rowCount = data.size();
            int rowHeight = Ordertable.getRowHeight();
            int tableHeight = rowCount * rowHeight;

            if (tableHeight > 300) tableHeight = 300; 

            scrollPane.setBounds(50, 80, 600, tableHeight + 24);
        });

    }

    public void show() {
        frame.setVisible(true);
    }
}




