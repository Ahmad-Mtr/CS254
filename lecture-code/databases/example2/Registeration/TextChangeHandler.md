```java
package Registration;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class TextChangeHandler implements DocumentListener {

    MainFrame mf;

    public TextChangeHandler(MainFrame mf) {
        this.mf = mf;
        mf.getRp().addvalidateDocumentListener(this);
    }

    public void insertUpdate(DocumentEvent e) {
        updateLog(e, "inserted into");
    }

    public void removeUpdate(DocumentEvent e) {
        updateLog(e, "removed from");
    }

    public void changedUpdate(DocumentEvent e) {
        //Plain text components do not fire these events
    }

    public void updateLog(DocumentEvent e, String action) {
        Document doc = (Document) e.getDocument();

        int length = doc.getLength();
        if (length < 8) {
            this.mf.getRp().showMessage("Password must contains 8 charachters at least");
        }
    }
}

```