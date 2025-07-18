package FrontendCustomer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;


public class CustomerHome {

    private JFrame frame;
    private int customerId;
    private JTable priceTable;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CustomerHome window = new CustomerHome(1);
                window.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CustomerHome(int customerId) {
        this.customerId = customerId;
        initialize();
    }

    private void initialize() {
        Color pastelBlue = new Color(173, 216, 230);
        Color pastelBackground = new Color(245, 248, 255); 

        frame = new JFrame("Customer Home");
        frame.setBounds(100, 100, 720, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(pastelBackground);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Welcome to Laundrette Service");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setForeground(new Color(70, 130, 180));
        lblTitle.setBounds(185, 25, 350, 40);
        frame.getContentPane().add(lblTitle);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setBounds(120, 80, 160, 180);
        addPanel.setBackground(Color.WHITE);
        addPanel.setBorder(BorderFactory.createLineBorder(pastelBlue, 2));
        frame.getContentPane().add(addPanel);

        ImageIcon orderIcon = new ImageIcon("Images/laundry.jpg");
        Image orderImg = orderIcon.getImage().getScaledInstance(140, 100, Image.SCALE_SMOOTH);
        JLabel orderImageLabel = new JLabel(new ImageIcon(orderImg));
        orderImageLabel.setBounds(10, 10, 140, 100);
        orderImageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addPanel.add(orderImageLabel);

        JLabel lblAdd = new JLabel("Add Order");
        lblAdd.setBounds(43, 130, 82, 20);
        lblAdd.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblAdd.setForeground(new Color(70, 130, 180));
        addPanel.add(lblAdd);

        orderImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                CustomerAdd addPage = new CustomerAdd(customerId);
                addPage.show();
            }
        });

        JPanel checkPanel = new JPanel();
        checkPanel.setLayout(null);
        checkPanel.setBounds(420, 80, 160, 180);
        checkPanel.setBackground(Color.WHITE);
        checkPanel.setBorder(BorderFactory.createLineBorder(pastelBlue, 2));
        frame.getContentPane().add(checkPanel);

        ImageIcon docIcon = new ImageIcon("Images/checklist.jpg");
        Image docImg = docIcon.getImage().getScaledInstance(140, 100, Image.SCALE_SMOOTH);
        JLabel docImageLabel = new JLabel(new ImageIcon(docImg));
        docImageLabel.setBounds(10, 10, 140, 100);
        docImageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkPanel.add(docImageLabel);

        JLabel lblCheck = new JLabel("Check Orders");
        lblCheck.setBounds(33, 130, 100, 20);
        lblCheck.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblCheck.setForeground(new Color(70, 130, 180));
        checkPanel.add(lblCheck);

        docImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                CustomerCheck checkPage = new CustomerCheck(customerId);
                checkPage.show();
            }
        });


        String[] columnNames = {"Service", "Price Minimum(RM)"};
        Object[][] data = {
            {"Wash & Fold", "3.00"},
            {"Dry Cleaning", "4.50"},
            {"Ironing", "2.40"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };


        priceTable = new JTable(model);
        priceTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        priceTable.setRowHeight(25);
        priceTable.setBackground(Color.WHITE);
        priceTable.setGridColor(pastelBlue);

        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        priceTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
        priceTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 

        JTableHeader header = priceTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));
        header.setBackground(pastelBlue);
        header.setForeground(Color.DARK_GRAY);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER); 
        
        
        JScrollPane scrollPane = new JScrollPane(priceTable);
        int rowCount = model.getRowCount();
        int rowHeight = priceTable.getRowHeight();
        int tableHeight = rowCount * rowHeight;
        if (tableHeight > 150) tableHeight = 150;
        scrollPane.setBounds(190, 290, 320, tableHeight + 24);
        frame.getContentPane().add(scrollPane);
        
        JLabel LogOutLabel = new JLabel("Log Out");
        LogOutLabel.setBounds(651, 10, 50, 20);
        LogOutLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        LogOutLabel.setForeground(Color.RED);
        LogOutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.getContentPane().add(LogOutLabel);

        LogOutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose(); 
                CustomerLogin loginPage = new CustomerLogin();
                loginPage.show(); 
            }
        });        
    }

    public void show() {
        frame.setVisible(true);
    }
}




