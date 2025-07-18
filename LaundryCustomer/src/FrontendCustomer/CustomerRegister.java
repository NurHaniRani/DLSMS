package FrontendCustomer;

import java.awt.*;
import javax.swing.*;
import BackendCustomer.Database;

public class CustomerRegister {

    public JFrame frame;
    private JTextField NameTxtFld;
    private JTextField EmailTxtFld;
    private JPasswordField PasswordTxtFld;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CustomerRegister window = new CustomerRegister();
                window.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CustomerRegister() {
        initialize();
    }

    private void initialize() {
        Color pastelBlue = new Color(173, 216, 230);
        Color pastelBackground = new Color(245, 248, 255);

        frame = new JFrame("Customer Register");
        frame.setBounds(100, 100, 720, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(pastelBackground);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Register New Customer");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setForeground(new Color(70, 130, 180));
        lblTitle.setBounds(230, 50, 300, 40);
        frame.getContentPane().add(lblTitle);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblName.setBounds(200, 130, 80, 20);
        frame.getContentPane().add(lblName);

        NameTxtFld = new JTextField();
        NameTxtFld.setFont(new Font("SansSerif", Font.PLAIN, 13));
        NameTxtFld.setBounds(290, 130, 200, 25);
        frame.getContentPane().add(NameTxtFld);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblEmail.setBounds(200, 170, 80, 20);
        frame.getContentPane().add(lblEmail);

        EmailTxtFld = new JTextField();
        EmailTxtFld.setFont(new Font("SansSerif", Font.PLAIN, 13));
        EmailTxtFld.setBounds(290, 170, 200, 25);
        frame.getContentPane().add(EmailTxtFld);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPassword.setBounds(200, 210, 80, 20);
        frame.getContentPane().add(lblPassword);

        PasswordTxtFld = new JPasswordField();
        PasswordTxtFld.setFont(new Font("SansSerif", Font.PLAIN, 13));
        PasswordTxtFld.setBounds(290, 210, 200, 25);
        frame.getContentPane().add(PasswordTxtFld);

        JButton RegisterBtn = new JButton("Register");
        RegisterBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        RegisterBtn.setBackground(pastelBlue);
        RegisterBtn.setForeground(Color.DARK_GRAY);
        RegisterBtn.setBounds(290, 260, 100, 30);
        frame.getContentPane().add(RegisterBtn);

        JLabel LoginLabel = new JLabel("Login Page");
        LoginLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        LoginLabel.setBounds(290, 310, 100, 20);
        LoginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.getContentPane().add(LoginLabel);

        // Register Button Action
        RegisterBtn.addActionListener(e -> {
            String name = NameTxtFld.getText().trim();
            String email = EmailTxtFld.getText().trim();
            String password = new String(PasswordTxtFld.getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            new Thread(() -> {
                Database.registerCustomer(name, email, password);  // Use REST method

                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(frame, "Customer registered. Please proceed to login.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new CustomerLogin().show();
                });
            }).start();
        });
    }

    public void show() {
        frame.setVisible(true);
    }
}

