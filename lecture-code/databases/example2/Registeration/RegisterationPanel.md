```java
package Registration;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RegistrationPanel extends JPanel {

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
     * @return the fullNameTextField
     */
    public JTextField getFullNameTextField() {
        return fullNameTextField;
    }

    /**
     * @param fullNameTextField the fullNameTextField to set
     */
    public void setFullNameTextField(JTextField fullNameTextField) {
        this.fullNameTextField = fullNameTextField;
    }

    /**
     * @return the bg
     */
    public ButtonGroup getBg() {
        return bg;
    }

    /**
     * @param bg the bg to set
     */
    public void setBg(ButtonGroup bg) {
        this.bg = bg;
    }

    /**
     * @return the emailTextField
     */
    public JTextField getEmailTextField() {
        return emailTextField;
    }

    /**
     * @param emailTextField the emailTextField to set
     */
    public void setEmailTextField(JTextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    /**
     * @return the majorTextField
     */
    public JTextField getMajorTextField() {
        return majorTextField;
    }

    /**
     * @param majorTextField the majorTextField to set
     */
    public void setMajorTextField(JTextField majorTextField) {
        this.majorTextField = majorTextField;
    }

    /**
     * @return the skillsTextArea
     */
    public JTextArea getSkillsTextArea() {
        return skillsTextArea;
    }

    /**
     * @param skillsTextArea the skillsTextArea to set
     */
    public void setSkillsTextArea(JTextArea skillsTextArea) {
        this.skillsTextArea = skillsTextArea;
    }

    /**
     * @return the registerButton
     */
    public JButton getRegisterButton() {
        return registerButton;
    }

    /**
     * @param registerButton the registerButton to set
     */
    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }
    // br.write("UserName,Password,FullName,Gender,Email,Major,Skills");

    JLabel userNameLabel;
    private JTextField userNameTextField;
    JLabel passwoLabel;
    private JPasswordField passwordField;
    JLabel fullNameLabel;
    private JTextField fullNameTextField;
    JLabel genderLabel;
    JRadioButton MaleButton;
    JRadioButton FemaleButton;
    private ButtonGroup bg;
    JLabel emailLabel;
    private JTextField emailTextField;
    JLabel majorLabel;
    private JTextField majorTextField;
    JLabel skillsLabel;
    private JTextArea skillsTextArea;
    private JPasswordField confirmPassword;
    private JCheckBox acceptCheckBox;
    private JButton registerButton;

    public RegistrationPanel() {
        this.setLayout(null);
        this.setSize(700, 700);
        userNameLabel = new JLabel("UserName:");
        userNameLabel.setSize(100, 40);
        userNameLabel.setLocation(100, 50);
        this.add(userNameLabel);

        userNameTextField = new JTextField("");
        userNameTextField.setSize(100, 40);
        userNameTextField.setLocation(220, 50);
        this.add(userNameTextField);

        passwoLabel = new JLabel("Password:");
        passwoLabel.setSize(100, 40);
        passwoLabel.setLocation(100, 100);
        this.add(passwoLabel);

        passwordField = new JPasswordField("");
        passwordField.setSize(100, 40);
        passwordField.setLocation(220, 100);
        this.add(passwordField);

        JLabel confirmPasswodLabel = new JLabel("Confirm Password:");
        confirmPasswodLabel.setSize(150, 40);
        confirmPasswodLabel.setLocation(330, 100);
        this.add(confirmPasswodLabel);

        confirmPassword = new JPasswordField("");
        confirmPassword.setSize(100, 40);
        confirmPassword.setLocation(480, 100);
        this.add(confirmPassword);

        fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setSize(100, 40);
        fullNameLabel.setLocation(100, 150);
        this.add(fullNameLabel);

        fullNameTextField = new JTextField("");
        fullNameTextField.setSize(100, 40);
        fullNameTextField.setLocation(220, 150);
        this.add(fullNameTextField);

        genderLabel = new JLabel("Gender:");
        genderLabel.setSize(100, 40);
        genderLabel.setLocation(100, 200);
        this.add(genderLabel);

        MaleButton = new JRadioButton("Male");
        MaleButton.setActionCommand("Male");
        MaleButton.setSelected(true);
        MaleButton.setSize(100, 40);
        MaleButton.setLocation(220, 200);
        this.add(MaleButton);

        FemaleButton = new JRadioButton("Female");
        FemaleButton.setActionCommand("Female");
        FemaleButton.setSize(100, 40);
        FemaleButton.setLocation(340, 200);
        this.add(FemaleButton);

        bg = new ButtonGroup();
        bg.add(MaleButton);
        bg.add(FemaleButton);

        emailLabel = new JLabel("Email:");
        emailLabel.setSize(100, 40);
        emailLabel.setLocation(100, 250);
        this.add(emailLabel);

        emailTextField = new JTextField("");
        emailTextField.setSize(100, 40);
        emailTextField.setLocation(220, 250);
        this.add(emailTextField);

        majorLabel = new JLabel("Major:");
        majorLabel.setSize(100, 40);
        majorLabel.setLocation(100, 300);
        this.add(majorLabel);

        majorTextField = new JTextField("");
        majorTextField.setSize(100, 40);
        majorTextField.setLocation(220, 300);
        this.add(majorTextField);

        skillsLabel = new JLabel("Skills:");
        skillsLabel.setSize(100, 40);
        skillsLabel.setLocation(100, 350);
        this.add(skillsLabel);

        skillsTextArea = new JTextArea(20, 50);
        skillsTextArea.setSize(100, 40);
        skillsTextArea.setLocation(220, 350);
        //skillsTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        //JScrollPane jp = new JScrollPane(skillsTextArea);
        //jp.setLocation(220, 400);
        //this.add(jp);
        this.add(skillsTextArea);

        acceptCheckBox = new JCheckBox();
        acceptCheckBox.setSize(200, 30);
        acceptCheckBox.setText("Accept Terms and Conditions");
        acceptCheckBox.setLocation(160, 400);
        this.add(acceptCheckBox);

        registerButton = new JButton("Register");
        registerButton.setSize(100, 40);
        registerButton.setEnabled(false);
        registerButton.setLocation(160, 450);
        this.add(registerButton);
    }

    public void addRegisterActionListener(ActionListener al) {
        this.getRegisterButton().addActionListener(al);
    }

    public void addConfirmFocusListener(FocusListener al) {
        this.getConfirmPassword().addFocusListener(al);
        this.getPasswordField().addFocusListener(al);
    }

    public void addvalidateDocumentListener(DocumentListener al) {
        this.getPasswordField().getDocument().addDocumentListener(al);
    }

    public void addAcceptItemListener(ItemListener al) {
        this.getAcceptCheckBox().addItemListener(al);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * @return the acceptCheckBox
     */
    public JCheckBox getAcceptCheckBox() {
        return acceptCheckBox;
    }

    /**
     * @param acceptCheckBox the acceptCheckBox to set
     */
    public void setAcceptCheckBox(JCheckBox acceptCheckBox) {
        this.acceptCheckBox = acceptCheckBox;
    }

    /**
     * @return the confirmPassword
     */
    public JPasswordField getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(JPasswordField confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void clear() {
        userNameTextField.setText("");
        passwordField.setText("");
        confirmPassword.setText("");
        fullNameTextField.setText("");
        emailTextField.setText("");
        majorTextField.setText("");
        skillsTextArea.setText("");
        MaleButton.setSelected(false);
        FemaleButton.setSelected(false);
        acceptCheckBox.setSelected(false);
    }
}

```