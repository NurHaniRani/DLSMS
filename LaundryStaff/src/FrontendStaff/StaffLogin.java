package FrontendStaff;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import BackendStaff.Database;
import FrontendStaff.StaffHome;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class StaffLogin {

	private JFrame frame;
	private JTextField emailField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffLogin window = new StaffLogin();
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StaffLogin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(50, 80, 380, 240);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Staff Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(145, 15, 120, 22);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(60, 60, 80, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(60, 100, 80, 15);
		panel.add(lblNewLabel_2);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailField.setBounds(150, 58, 180, 22);
		panel.add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setBounds(150, 98, 180, 22);
		panel.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(e -> {
			String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            int staffId = Database.loginStaff(email, password);
            if (staffId != -1) {
                JOptionPane.showMessageDialog(frame, "Login Successful");
                StaffHome homeWindow = new StaffHome(staffId);
                homeWindow.show();
                frame.dispose();// proceed to main
            } else {
                JOptionPane.showMessageDialog(frame, "Login Failed");
            }
        });
		
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		loginButton.setBounds(144, 150, 100, 30);
		panel.add(loginButton);
	
		
	}
	
	public void show() {
        frame.setVisible(true);
    }
	
	
}
