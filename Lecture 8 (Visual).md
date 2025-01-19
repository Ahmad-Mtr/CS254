
### **Dialogs and Text Components**

#### **1. JEditorPane**

- **Purpose**: Provides basic text editing functionalities like displaying, editing, and copying text.
- **Methods**:
    - **`JEditorPane()`**: Constructor to create a JEditorPane.
    - **`read(Reader myReader, Object description)`**: Reads text from a `Reader` and displays it. The `description` provides additional information about the text type.
    - **`getText()`**: Returns the text currently displayed in the editor as a string.
    - **`setText(String text)`**: Sets the text content of the editor pane.
    - **`setContentType(String type)`**: Sets the content type (e.g., "text/plain", "text/html").
    - **`getContentType()`**: Retrieves the current content type.
    - **`setEditable(boolean editable)`**: Enables or disables text editing.
    - **`getSelectedText()`**: Returns the currently selected text.

---

#### **2. JScrollPane**

- **Purpose**: Adds scroll functionality to components.
- **Methods**:
    - **`setHorizontalScrollBarPolicy(int policy)`**: Determines when horizontal scroll bars are visible.
        - Policies: `HORIZONTAL_SCROLLBAR_ALWAYS`, `HORIZONTAL_SCROLLBAR_AS_NEEDED`, `HORIZONTAL_SCROLLBAR_NEVER`.
    - **`setVerticalScrollBarPolicy(int policy)`**: Determines when vertical scroll bars are visible.
        - Similar policies as horizontal scroll bars.

---

#### **3. JFileChooser**

- **Purpose**: Provides a dialog to select files.
- **Methods**:
    - **`JFileChooser(String startDirectory)`**: Creates a file chooser starting in `startDirectory`.
    - **`int showOpenDialog(Component parent)`**: Displays a dialog for opening files. Returns `APPROVE_OPTION` or `CANCEL_OPTION`.
    - **`int showSaveDialog(Component parent)`**: Displays a dialog for saving files. Works similarly to `showOpenDialog`.
    - **`File getSelectedFile()`**: Retrieves the selected file after `APPROVE_OPTION` is selected.

---

#### **4. JDialog**

- **Purpose**: Allows creating custom dialog windows.
- **Methods**:
    - **`JDialog(Frame parent, String title, boolean modal)`**: Constructor for creating a dialog with a title and modality.
    - **`getContentPane.add(Component comp)`**: Adds components to the dialog's content pane.
    - **`setVisible(boolean b)`**: Sets the dialog's visibility.
    - **`pack()`**: Optimizes the dialog size to fit its content.