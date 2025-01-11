```java
package Registration;

import java.awt.event.*;

public class WindowController extends WindowAdapter {

    private MainFrame mainFrame;
    private Database db;

    public WindowController(MainFrame frame, Database db) {
        mainFrame = frame;
        this.db = db;
    }

    public void windowOpened(WindowEvent event) {
        try {
            db.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        db.disconnect();
    }

}

```