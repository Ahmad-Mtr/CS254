```java
package Registration;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class FocusHandler implements FocusListener {

    MainFrame mf;

    public FocusHandler(MainFrame mf) {
        this.mf = mf;
        this.mf.getRp().addConfirmFocusListener(this);
    }

    public FocusHandler() {

    }

    public void focusGained(FocusEvent fe) {
        // Get what textfield got focus

    }

    public void focusLost(FocusEvent fe) {
        // Get what textfield lost focus
        JPasswordField t = (JPasswordField) fe.getSource();
        if (t.equals(mf.getRp().getPasswordField()) && t.getPassword().length < 8) {
            mf.getRp().showMessage("Password must contains 8 charachters at least");
        }
        if (t.equals(mf.getRp().getConfirmPassword())) {
            if (!Arrays.equals(t.getPassword(), mf.getRp().getPasswordField().getPassword())) {
                mf.getRp().showMessage("Password does not match");
            }
        }
    }

}

```