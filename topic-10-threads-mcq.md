```diff
1. Which thread should be used to modify Swing components after they're visible?
+ Event Dispatch Thread (EDT)
- Worker Thread
- Any Thread
- Background Thread

2. What happens if you call SwingUtilities.invokeAndWait() from the EDT?
+ Throws an Error and could cause deadlock
- Works normally
- Throws RuntimeException
- Nothing happens

3. Which method does NOT need to use invokeAndWait() or invokeLater()?
+ Adding event listeners
- Modifying component text
- Changing component colors
- Updating component position

4. What's the purpose of making a variable volatile in Swing threading?
+ Ensures visibility of changes between different threads
- Makes the variable final
- Prevents null pointer exceptions
- Improves performance

5. Which method blocks until the EDT completes the requested operation?
+ SwingUtilities.invokeAndWait()
- SwingUtilities.invokeLater()
- SwingUtilities.isEventDispatchThread()
- SwingUtilities.updateUI()

6. When using invokeLater(), where should exception handling be placed?
+ Inside the Runnable's run() method
- In the calling method's try-catch block
- No exception handling needed
- In a separate error handler

7. What's the correct way to perform a long-running operation in a Swing application?
+ Use a worker thread and update GUI via invokeAndWait/invokeLater
- Perform operation directly in EDT
- Use synchronized blocks
- Disable the GUI during operation

8. Which operation can safely be performed by any thread at any time?
+ Calling repaint()
- Setting component text
- Changing component size
- Moving component position

9. For animation in Swing, which is the recommended approach?
+ Use a separate thread with sleep intervals and EDT updates
- Direct updates from animation thread
- Continuous updates without sleep
- Random timing updates

10. What's the main problem with running too many animation threads?
+ High CPU usage and potential performance degradation
- Memory leaks
- Thread deadlocks
- GUI freezing

11. When implementing a timer in Swing, which is more accurate?
+ Using System.currentTimeMillis() with sleep adjustment
- Using Thread.sleep() alone
- Using wait() and notify()
- Using Timer class without adjustment

12. What's required when sharing data between worker thread and EDT?
+ Make shared variables volatile or use proper synchronization
- Make variables final
- Use static variables
- No special requirements needed

13. In a Swing animation, why use invokeAndWait() instead of invokeLater()?
+ To prevent the worker thread from getting ahead of the EDT
- To improve performance
- To reduce memory usage
- To prevent exceptions

14. Before components are visible, which statement is true?
+ Any thread can safely modify the components
- Only EDT can modify components
- Components are thread-safe
- Modifications are not allowed

15. What's the best practice for cancelling a long-running operation in Swing?
+ Use a volatile flag and interrupt the worker thread
- Stop the EDT
- Use Thread.stop()
- Close the application
```