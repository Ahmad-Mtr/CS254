```java
package Registration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Amani
 */
public class Controller implements ActionListener {

    private MainFrame mainFrame;
    Database db;

    public void register(User usr) throws SQLException {
        db.register(usr);
    }

    public void load() throws SQLException {
        db.load();
    }

    public void connect() throws Exception {
        db.connect();
    }

    public void disconnect() {
        db.disconnect();
    }

    public Controller(MainFrame frame, Database db) {
        mainFrame = frame;
        this.db = db;
        mainFrame.addMenuActionListener(this);
        mainFrame.getLp().addLoginActionListener(this);
        mainFrame.getRp().addRegisterActionListener(this);
    }

    public User registerUser() {
        User usr = new User();
        usr.setUserName(mainFrame.getRp().getUserNameTextField().getText());
        usr.setPassword(mainFrame.getRp().getPasswordField().getText());
        usr.setFullName(mainFrame.getRp().getFullNameTextField().getText());
        usr.setGender(mainFrame.getRp().getBg().getSelection().getActionCommand());
        usr.setEmail(mainFrame.getRp().getEmailTextField().getText());
        usr.setMajor(mainFrame.getRp().getMajorTextField().getText());
        String str = mainFrame.getRp().getSkillsTextArea().getText();
        String skills[] = str.split("\n");
        for (String s : skills) {
            usr.getSkills().add(s);
        }

        return usr;
    }

    public User getUserLoginCredintials() {
        User usr = new User();
        usr.setUserName(mainFrame.getLp().getUserNameTextField().getText());
        usr.setPassword(mainFrame.getLp().getPasswordField().getText());
        return usr;
    }

    public void setUserInfo(User user) {
        JOptionPane.showMessageDialog(mainFrame, "Hello " + user.getFullName() + "!");
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(this.mainFrame.getRp().getRegisterButton())) {
            User usr = registerUser();
            if (db.checkUserName(usr.getUserName()) != 1) {
                try {
                    register(usr);
                    usr = null;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                mainFrame.getRp().showMessage("Registered Successfully");

            } else {
                mainFrame.getRp().showMessage("UserName is not available, please select another user name!");
            }
        } else if (event.getSource().equals(this.mainFrame.getLp().getLoginButton())) {

            this.checkLoginInfo();
        } else if (event.getSource().equals(this.mainFrame.getLoginMenuItem())) {
            mainFrame.remove(mainFrame.getRp());
            mainFrame.add(mainFrame.getLp());
            mainFrame.getLp().clear();
            mainFrame.getLoginMenuItem().setEnabled(false);
            mainFrame.getRegisterMenuItem().setEnabled(true);

            mainFrame.repaint();
        } else if (event.getSource().equals(this.mainFrame.getRegisterMenuItem())) {
            mainFrame.getRp().clear();
            mainFrame.remove(mainFrame.getLp());
            mainFrame.add(mainFrame.getRp());
            mainFrame.getLoginMenuItem().setEnabled(true);
            mainFrame.getRegisterMenuItem().setEnabled(false);

            mainFrame.repaint();
        }
    }

    private void checkLoginInfo() {
        String userName = mainFrame.getLp().getUserNameTextField().getText();
        String password = mainFrame.getLp().getPasswordField().getText();
        User user;
        try {
            user = db.login(userName, password);

            if (user != null) {
                setUserInfo(user);
            } else {
                mainFrame.getLp().showMessage("Incorrect Username or Password!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

```