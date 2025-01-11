```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Registration;

public class Registration2 {

    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        Database db = new Database();
        WindowController lc = new WindowController(mf, db);
        mf.addWindowListener(lc);
        CheckBoxHandler ch = new CheckBoxHandler(mf);
        Controller c = new Controller(mf, db);
        FocusHandler fh = new FocusHandler(mf);
    }
}

```