```java
package Registration;

import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginPanel extends JPanel {

    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JTextField userNameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPanel() {
        this.setLayout(null);
        this.setSize(400, 300);
        userNameLabel = new JLabel("UserName:");
        userNameLabel.setSize(100, 50);
        userNameLabel.setLocation(100, 100);
        this.add(userNameLabel);

        userNameTextField = new JTextField("");
        userNameTextField.setSize(100, 50);
        userNameTextField.setLocation(220, 100);
        this.add(userNameTextField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setSize(100, 50);
        passwordLabel.setLocation(100, 170);
        this.add(passwordLabel);

        passwordField = new JPasswordField("");
        passwordField.setSize(100, 50);
        passwordField.setLocation(220, 170);
        this.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setSize(100, 50);
        loginButton.setLocation(160, 250);
        this.add(loginButton);
    }

    public void addLoginActionListener(ActionListener al) {
        this.getLoginButton().addActionListener(al);
    }

    /**
     * @return the userNameTextField
     */
    public JTextField getUserNameTextField() {
        return userNameTextField;
    }

    /**
     * @param userNameTextField the userNameTextField to set
     */
    public void setUserNameTextField(JTextField userNameTextField) {
        this.userNameTextField = userNameTextField;
    }

    /**
     * @return the passwordField
     */
    public JPasswordField getPasswordField() {
        return passwordField;
    }

    /**
     * @param passwordField the passwordField to set
     */
    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    /**
     * @return the loginButton
     */
    public JButton getLoginButton() {
        return loginButton;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clear() {
        userNameTextField.setText("");
        passwordField.setText("");
    }
}

```