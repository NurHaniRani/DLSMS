package FrontendStaff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

import BackendStaff.Database;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class StaffViewOrder {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	
	private int staffId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffViewOrder window = new StaffViewOrder(1);
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StaffViewOrder(int staffId) {
		this.staffId = staffId;
		initialize();
		loadData();
	}

	private void initialize() {
		frame = new JFrame("Staff View Orders");
		frame.getContentPane().setBackground(new Color(224, 255, 255));
        frame.setBounds(100, 100, 843, 492);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
		
		// Column Names
        String[] columns = {"Order ID", "Customer Name", "Service Type", "Clothing Type", "Weight", "Price", "Order Date", "Status"};

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(31, 47, 750, 300);
        frame.getContentPane().add(scrollPane);
        
        lblNewLabel = new JLabel("View Order List");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(342, 24, 111, 13);
        frame.getContentPane().add(lblNewLabel);
        
        btnNewButton = new JButton("Back to Home");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        		new StaffHome(staffId).show();
        	}
        });
        btnNewButton.setBounds(10, 16, 122, 21);
        frame.getContentPane().add(btnNewButton);
	}
	
	public void show() {
	    frame.setVisible(true);
	}
	
	private void loadData() {
        Database.fetchAllOrders(data -> {
            for (Vector<Object> row : data) {
                tableModel.addRow(row);
            }
        });
    }

}
