# Chapter - Text Editors and File Operations

## Core Components Overview
This summary covers JEditorPane for text editing, JScrollPane for scrollable content, and JFileChooser for file operations, along with custom dialogs using JDialog.

## JEditorPane
```java
// Basic Editor Creation
JEditorPane editor = new JEditorPane();
editor.setContentType("text/plain");  // or "text/html", "text/rtf"
editor.setText("Initial text");

// Common Methods
editor.setEditable(true);
editor.setFont(new Font("Serif", Font.BOLD, 16));
String selectedText = editor.getSelectedText();
```

## JScrollPane
```java
// Creating Scrollable Content
JEditorPane editor = new JEditorPane();
JScrollPane scrollPane = new JScrollPane(editor);

// Scroll Policy Configuration
scrollPane.setHorizontalScrollBarPolicy(
    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scrollPane.setVerticalScrollBarPolicy(
    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
```

## JFileChooser
```java
// File Selection Dialog
JFileChooser fileChooser = new JFileChooser("startDirectory");

// Open File Dialog
int result = fileChooser.showOpenDialog(parentComponent);
if (result == JFileChooser.APPROVE_OPTION) {
    File selectedFile = fileChooser.getSelectedFile();
}

// Save File Dialog
result = fileChooser.showSaveDialog(parentComponent);
```

## Complete Example
```java
public class TextEditorDemo extends JFrame {
    private JEditorPane editor;
    
    public TextEditorDemo() {
        setTitle("Text Editor with File Operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create Editor with ScrollPane
        editor = new JEditorPane();
        editor.setContentType("text/plain");
        JScrollPane scrollPane = new JScrollPane(editor);
        add(scrollPane);
        
        // Create Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        // Menu Items
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
    
    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (FileReader fr = new FileReader(chooser.getSelectedFile())) {
                editor.read(fr, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error reading file: " + ex.getMessage());
            }
        }
    }
    
    private void saveFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (FileWriter fw = new FileWriter(chooser.getSelectedFile())) {
                fw.write(editor.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error saving file: " + ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TextEditorDemo().setVisible(true);
        });
    }
}
```

## Best Practices
1. **Text Editor Implementation**
   - Set appropriate content type
   - Handle different text formats
   - Implement proper text handling
   - Consider undo/redo operations

2. **Scrolling**
   - Use appropriate scroll policies
   - Consider viewport size
   - Handle large content efficiently
   - Maintain performance with large documents

3. **File Operations**
   - Implement proper error handling
   - Use try-with-resources for file operations
   - Provide user feedback
   - Handle file paths correctly

4. **User Interface**
   - Provide clear navigation
   - Implement standard shortcuts
   - Show loading indicators for large files
   - Maintain responsive interface

This summary covers essential components for creating text editors with file operations and scrollable content in Swing applications, providing users with familiar text editing capabilities.