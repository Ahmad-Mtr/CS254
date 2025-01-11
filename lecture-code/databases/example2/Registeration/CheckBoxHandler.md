```java
package Registration;

import java.awt.event.*;
import javax.swing.*;

class CheckBoxHandler implements ItemListener {

    private MainFrame mf;

    public CheckBoxHandler(MainFrame mf) {
        this.mf = mf;
        mf.getRp().addAcceptItemListener(this);
    }
    // respond to checkbox events

    public void itemStateChanged(ItemEvent event) {
        // process bold checkbox events
        if (((JCheckBox) event.getSource()).isSelected()) {
            mf.getRp().getRegisterButton().setEnabled(true);
        } else {
            mf.getRp().getRegisterButton().setEnabled(false);
        }

    } // end method itemStateChanged

} // end private inner class CheckBoxHandler

```