package FrontendStaff;

import java.awt.EventQueue;
import BackendStaff.Database;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffUpdateStatus {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
    private JComboBox<String> statusBox;
    private JButton updateButton;
    
	private int staffId;
	private JButton btnNewButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffUpdateStatus window = new StaffUpdateStatus(1);
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StaffUpdateStatus(int staffId) {
		this.staffId = staffId;
		initialize();
		loadData();
	}

	private void initialize() {
		frame = new JFrame("Staff Update Order Status");
		frame.getContentPane().setBackground(new Color(224, 255, 255));
        frame.setBounds(100, 100, 800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        String[] columns = {"Order ID", "Customer Name", "Service Type", "Clothing Type", "Weight", "Price", "Order Date", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 44, 750, 250);
        frame.getContentPane().add(scrollPane);

        JLabel lblStatus = new JLabel("Update Status:");
        lblStatus.setBounds(20, 304, 100, 25);
        frame.getContentPane().add(lblStatus);

        statusBox = new JComboBox<>(new String[]{"Order Placed", "In Progress", "Completed"});
        statusBox.setBounds(130, 304, 200, 25);
        frame.getContentPane().add(statusBox);

        updateButton = new JButton("Update Selected");
        updateButton.setBounds(348, 304, 150, 25);
        frame.getContentPane().add(updateButton);
        
        btnNewButton = new JButton("Back to Home");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        		new StaffHome(staffId).show();
        	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnNewButton.setBounds(20, 13, 122, 21);
        frame.getContentPane().add(btnNewButton);

        updateButton.addActionListener(e -> updateStatus());
	}
	
	public void show() {
        frame.setVisible(true);
    }
	
	private void loadData() {
        tableModel.setRowCount(0); // Clear previous data
        Database.fetchAllOrders(data -> {
            for (Vector<Object> row : data) {
                tableModel.addRow(row);
            }
        });
    }
	
	private void updateStatus() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(frame, "Please select an order to update.");
			return;
		}

		int orderId = (int) tableModel.getValueAt(selectedRow, 0);
		String newStatus = (String) statusBox.getSelectedItem();

		Database.updateOrderStatus(orderId, newStatus);

		// Update UI
		tableModel.setValueAt(newStatus, selectedRow, 7);
		JOptionPane.showMessageDialog(frame, "Status updated successfully.");
	}

}
