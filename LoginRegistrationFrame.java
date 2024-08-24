import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegistrationFrame extends JFrame {
    private UserManager userManager;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    
    public LoginRegistrationFrame(UserManager userManager) {
        this.userManager = userManager;

        setTitle("User Authentication");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create login panel
        JPanel loginPanel = createLoginPanel();
        cardPanel.add(loginPanel, "Login");

        // Create registration panel
        JPanel registerPanel = createRegisterPanel();
        cardPanel.add(registerPanel, "Register");

        // Add cardPanel to the frame
        add(cardPanel);

        // Show the login panel initially
        cardLayout.show(cardPanel, "Login");
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Username
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        JTextField userTextField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(userTextField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);

        // Status message
        JLabel statusLabel = new JLabel();
        gbc.gridy = 3;
        panel.add(statusLabel, gbc);

        // Switch to registration panel
        JButton switchToRegisterButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(switchToRegisterButton, gbc);

        loginButton.addActionListener(e -> {
            String username = userTextField.getText();
            String password = new String(passwordField.getPassword());
            if (userManager.login(username, password)) {
                statusLabel.setText("Login successful!");
                statusLabel.setForeground(Color.GREEN);
                // Open Quiz Frame
                openQuizFrame(username);
                this.setVisible(false); // Hide the login frame
            } else {
                statusLabel.setText("Invalid username or password.");
                statusLabel.setForeground(Color.RED);
            }
        });

        switchToRegisterButton.addActionListener(e -> cardLayout.show(cardPanel, "Register"));

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Username
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        JTextField userTextField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(userTextField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Register button
        JButton registerButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(registerButton, gbc);

        // Status message
        JLabel statusLabel = new JLabel();
        gbc.gridy = 3;
        panel.add(statusLabel, gbc);

        // Switch to login panel
        JButton switchToLoginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(switchToLoginButton, gbc);

        registerButton.addActionListener(e -> {
            String username = userTextField.getText();
            String password = new String(passwordField.getPassword());
            if (userManager.register(username, password)) {
                statusLabel.setText("Registration successful!");
                statusLabel.setForeground(Color.GREEN);
                // Switch to login panel after successful registration
                cardLayout.show(cardPanel, "Login");
            } else {
                statusLabel.setText("Username already exists.");
                statusLabel.setForeground(Color.RED);
            }
        });

        switchToLoginButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));

        return panel;
    }

    private void openQuizFrame(String username) {
        new Quiz(username); // Pass the username to the Quiz constructor
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        SwingUtilities.invokeLater(() -> {
            LoginRegistrationFrame frame = new LoginRegistrationFrame(userManager);
            frame.setVisible(true);
        });
    }
}
