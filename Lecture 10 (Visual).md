
### **Other Components**

#### **1. JColorChooser**

- **Purpose**: Allows users to select a color from a palette.
- **Methods**:
    - **`JColorChooser()`**: Default constructor to create a color chooser.
    - **`JColorChooser(Color initial)`**: Initializes with a specific color.
    - **`Color showDialog(Component parent, String title, Color initialColor)`**: Displays the dialog and returns the selected color or `null` if canceled.

---

#### **2. JList**

- **Purpose**: Displays a list of selectable text items.
- **Methods**:
    - **`JList()`**: Creates an empty list.
    - **`JList(String[] entries)`**: Creates a list populated with array entries.
    - **`setModel(ListModel lModel)`**: Sets the list's data model.
    - **`addListSelectionListener(ListSelectionListener lsl)`**: Adds a listener to handle selection changes.
    - **`setListData(String[] entries)`**: Sets list entries from an array.
    - **`setSelectionMode(int selectionMode)`**: Sets selection mode (e.g., single or multiple).
    - **`getSelectionMode()`**: Retrieves the current selection mode.
    - **`setVisibleRowCount(int rowNo)`**: Adjusts the visible rows.
    - **`getSelectedIndex()`**: Returns the index of the selected entry.
    - **`getSelectedIndices()`**: Returns indices of selected entries.
    - **`getSelectedValue()`**: Returns the selected value.
    - **`getSelectedValues()`**: Returns all selected values.

---

#### **3. JTable**

- **Purpose**: Displays data in a two-dimensional table format.
- **Methods**:
    - **`JTable(Object[][] content, Object[] columnNames)`**: Creates a table with given data and column headers.
    - **`setAutoResizeMode(int mode)`**: Sets resizing mode.
    - **`setFillsViewportHeight(boolean fills)`**: Ensures table fills its viewport height.
    - **`getSelectedRow()`**: Returns the first selected row index.
    - **`getSelectedColumn()`**: Returns the first selected column index.
    - **`getSelectedRows()`**, **`getSelectedColumns()`**: Returns arrays of selected row/column indices.
    - **`addColumn(TableColumn aColumn)`**: Adds a new column.
    - **`getModel()`**: Retrieves the table's data model.

---

#### **4. Tree Structures**

- **Classes**:
    - **`DefaultMutableTreeNode`**:
        - **`DefaultMutableTreeNode(Object label)`**: Creates a tree node with a label.
        - **`add(DefaultMutableTreeNode node)`**: Adds a child node.
    - **`DefaultTreeModel`**:
        - **`DefaultTreeModel(TreeNode root)`**: Creates a tree model with the given root.
        - **`insertNodeInto(MutableTreeNode child, MutableTreeNode parent, int index)`**: Inserts a node into the tree.
        - **`removeNodeFromParent(TreeNode child)`**: Removes a node and its subtree.

---

#### **5. JSplitPane**

- **Purpose**: Splits the UI into two resizable panes.
- **Methods**:
    - **`JSplitPane(int division)`**: Constructor specifying horizontal or vertical split.
    - **`setDividerLocation(int pos)`**: Sets the divider's position.
    - **`setDividerSize(int width)`**: Sets divider size.
    - **`setOneTouchExpandable(boolean b)`**: Enables/disables quick collapsing via arrows.

---

#### **6. JTabbedPane**

- **Purpose**: Provides tabbed navigation for multiple components.
- **Methods**:
    - **`JTabbedPane()`**: Default constructor with top tabs.
    - **`add(String title, Component comp)`**: Adds a new tab with a title.
    - **`setTitleAt(int index, String title)`**: Sets a tab's title.
    - **`setSelectedIndex(int index)`**: Selects a specific tab.
    - **`getSelectedComponent()`**: Returns the currently selected component.

---

#### **7. JToolBar**

- **Purpose**: Provides a movable container for buttons and actions.
- **Methods**:
    - **`JToolBar()`**: Creates a horizontal toolbar.
    - **`setFloatable(boolean b)`**: Enables or disables dragging the toolbar.
    - **`add(Component comp)`**: Adds a component to the toolbar.