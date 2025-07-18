package FrontendCustomer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import BackendCustomer.Database;

public class CustomerLogin {

    public JFrame frame;
    private JTextField EmailTxtFld;
    private JPasswordField PasswordTxtFld;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CustomerLogin window = new CustomerLogin();
                window.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CustomerLogin() {
        initialize();
    }

    private void initialize() {
        Color pastelBlue = new Color(173, 216, 230);
        Color pastelBackground = new Color(245, 248, 255);

        frame = new JFrame("Customer Login");
        frame.setBounds(100, 100, 720, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(pastelBackground);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Customer Login");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setForeground(new Color(70, 130, 180));
        lblTitle.setBounds(260, 50, 300, 40);
        frame.getContentPane().add(lblTitle);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblEmail.setBounds(200, 120, 80, 20);
        frame.getContentPane().add(lblEmail);

        EmailTxtFld = new JTextField();
        EmailTxtFld.setBounds(290, 120, 200, 25);
        EmailTxtFld.setFont(new Font("SansSerif", Font.PLAIN, 13));
        frame.getContentPane().add(EmailTxtFld);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPassword.setBounds(200, 160, 80, 20);
        frame.getContentPane().add(lblPassword);

        PasswordTxtFld = new JPasswordField();
        PasswordTxtFld.setBounds(290, 160, 200, 25);
        PasswordTxtFld.setFont(new Font("SansSerif", Font.PLAIN, 13));
        frame.getContentPane().add(PasswordTxtFld);

        JButton LoginBtn = new JButton("Login");
        LoginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        LoginBtn.setBackground(pastelBlue);
        LoginBtn.setForeground(Color.DARK_GRAY);
        LoginBtn.setBounds(290, 210, 100, 30);
        frame.getContentPane().add(LoginBtn);

        JLabel RegisterLabel = new JLabel("Register Here");
        RegisterLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        RegisterLabel.setBounds(290, 260, 100, 20);
        RegisterLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.getContentPane().add(RegisterLabel);

        RegisterLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                CustomerRegister registerWindow = new CustomerRegister();
                registerWindow.show();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                RegisterLabel.setText("<html><u>Register Here</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                RegisterLabel.setText("Register Here");
            }
        });

        LoginBtn.addActionListener(e -> {
            String email = EmailTxtFld.getText().trim();
            String password = new String(PasswordTxtFld.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter both email and password.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                new Thread(() -> {
                    int customerId = Database.loginCustomer(email, password); // Use REST-based method

                    if (customerId != -1) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(frame, "Login successful. Welcome!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            CustomerHome homeWindow = new CustomerHome(customerId);
                            homeWindow.show();
                            frame.dispose();
                        });
                    } else {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(frame, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                        });
                    }
                }).start();
            }
        });

    }

    public void show() {
        frame.setVisible(true);
    }
}



