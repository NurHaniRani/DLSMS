package FrontendStaff;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;

import FrontendStaff.StaffViewOrder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import javax.swing.JLabel;

import FrontendStaff.StaffLogin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StaffHome {

	private JFrame frame;
	private int staffId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffHome window = new StaffHome(1);
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StaffHome(int staffId) {
		this.staffId = staffId;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton viewButton = new JButton("View Order");
		viewButton.setBackground(new Color(135, 206, 235));
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                StaffViewOrder viewOrder = new StaffViewOrder(staffId);
                viewOrder.show();			}
		});
		viewButton.setBounds(69, 70, 130, 100);
		frame.getContentPane().add(viewButton);
		
		JButton updateButton = new JButton("Update Order");
		updateButton.setBackground(new Color(255, 128, 192));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                StaffUpdateStatus updateStatus = new StaffUpdateStatus(staffId);
                updateStatus.show();
			}
		});
		updateButton.setBounds(237, 70, 120, 100);
		frame.getContentPane().add(updateButton);
		
		JLabel lblNewLabel = new JLabel("Log Out");
		lblNewLabel.setBounds(358, 10, 56, 13);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose(); 
                StaffLogin loginPage = new StaffLogin();
                loginPage.show(); 
			}
		});
		lblNewLabel.setBounds(369, 10, 45, 13);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public void show() {
        frame.setVisible(true);
    }

}
