```java
package Registration;

import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    /**
     * @return the registerMenuItem
     */
    public JMenuItem getRegisterMenuItem() {
        return registerMenuItem;
    }

    /**
     * @return the loginMenuItem
     */
    public JMenuItem getLoginMenuItem() {
        return loginMenuItem;
    }

    /**
     * @param loginMenuItem the loginMenuItem to set
     */
    public void setLoginMenuItem(JMenuItem loginMenuItem) {
        this.loginMenuItem = loginMenuItem;
    }

    private RegistrationPanel rp;
    private LoginPanel lp;
    private JMenuBar menuBar;
    JMenu openMenu;
    private JMenuItem registerMenuItem;
    private JMenuItem loginMenuItem;

    public MainFrame() {
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        openMenu = new JMenu("Open");
        menuBar.add(openMenu);
        registerMenuItem = new JMenuItem("Register");
        loginMenuItem = new JMenuItem("Login");
        openMenu.add(registerMenuItem);
        openMenu.add(loginMenuItem);

        rp = new RegistrationPanel();
        this.getContentPane().add(rp);
        this.setTitle("User Registration");

        lp = new LoginPanel();
        this.setSize(750, 750);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addWindowHandler(WindowListener wl) {
        this.addWindowListener(wl);
    }

    public void addMenuActionListener(ActionListener l) {
        this.getRegisterMenuItem().addActionListener(l);
        this.getLoginMenuItem().addActionListener(l);
    }

    /**
     * @return the rp
     */
    public RegistrationPanel getRp() {
        return rp;
    }

    /**
     * @param rp the rp to set
     */
    public void setRp(RegistrationPanel rp) {
        this.rp = rp;
    }

    /**
     * @return the lp
     */
    public LoginPanel getLp() {
        return lp;
    }

    /**
     * @param lp the lp to set
     */
    public void setLp(LoginPanel lp) {
        this.lp = lp;
    }

}

```