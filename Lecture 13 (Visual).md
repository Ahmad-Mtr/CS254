
### **Threads**

#### **1. What is a Thread?**

- **Definition**: A thread is an independent unit of execution within a process. Multiple threads can share resources and run concurrently.
- **Examples**:
    - Video games use separate threads for graphics, user interaction, and networking.

---

#### **2. Advantages of Threads**

- Simplifies programming by assigning one thread per task.
- Improves performance as threads only run when needed.
- Threads can utilize multiple processors if available.

---

#### **3. Creating Threads**

##### **Method 1: Extending the `Thread` Class**

- **Steps**:
    1. Extend the `Thread` class.
    2. Override the `run()` method to define thread behavior.
    3. Call `.start()` to begin execution.
- **Example**:
    
    java
    
    CopyEdit
    
    `class Output extends Thread {     private String toSay;     public Output(String st) { toSay = st; }     public void run() {         try {             for (;;) {                 System.out.println(toSay);                 sleep(1000);             }         } catch (InterruptedException e) {             System.out.println(e);         }     } }`
    

##### **Method 2: Implementing the `Runnable` Interface**

- **Steps**:
    
    1. Implement the `Runnable` interface.
    2. Define the `run()` method.
    3. Use a `Thread` object to start the thread.
- **Example**:
    
    java
    
    CopyEdit
    
    `class Output implements Runnable {     private String toSay;     public Output(String st) { toSay = st; }     public void run() {         try {             for (;;) {                 System.out.println(toSay);                 Thread.sleep(1000);             }         } catch (InterruptedException e) {             System.out.println(e);         }     } }`
    
- **Advantage**: Allows the class to extend another class.
    

---

#### **4. Controlling Threads**

- **Methods**:
    - **`.start()`**: Begins execution of the thread.
    - **`.join()`**: Waits for the thread to complete.
    - **`.setPriority(int priority)`**: Sets thread priority.
    - **`.stop()`**, **`.suspend()`**, **`.resume()`**: Deprecated.

---

#### **5. Event-Driven Programming and GUI Threads**

- GUI activities run on the **Event Dispatch Thread (EDT)**.
- **Rules**:
    - Avoid time-consuming tasks on the EDT to prevent unresponsiveness.
    - Use `SwingUtilities.invokeLater()` for safe GUI updates.
- **Example**:
    
    java
    
    CopyEdit
    
    `SwingUtilities.invokeLater(() -> {     JFrame frame = new JFrame("Title");     frame.setVisible(true); });`
    

---

#### **6. SwingWorker**

- **Purpose**: Handles long-running tasks in the background while updating the GUI.
- **Key Methods**:
    - **`doInBackground()`**: Executes in a background thread.
    - **`done()`**: Runs on the EDT after the background task completes.
    - **`publish()`**: Sends intermediate results to `process()`.
    - **`process(List<V> chunks)`**: Handles intermediate results on the EDT.
- **Example**:
    
    java
    
    CopyEdit
    
    `class MeaningOfLifeFinder extends SwingWorker<String, Object> {     @Override     protected String doInBackground() {         return "42"; // Simulates finding the meaning of life     }     @Override     protected void done() {         try {             String result = get();             System.out.println("The meaning of life is: " + result);         } catch (Exception e) {             e.printStackTrace();         }     } }`