
// import view.GameView;
// import model.GameModel;
// import controller.GameLogic;

// public class MainC {
//     public static void main(String[] args) {
//         GameView gameView = new GameView();
//         GameModel gameModel = new GameModel();
//         GameLogic gameLogic = new GameLogic(gameModel, gameView);
//         // gameView.setController(gameLogic);
//     }

// }

// // Steps to create a Tic Tac Toe game using MVC pattern:

// // 1. Create the Model (GameModel):
// //    - Define the game state (e.g., board, current player).
// //    - Implement methods to update the game state (e.g., makeMove, checkWinner).

// // 2. Create the View (GameView):
// //    - Design the user interface (e.g., buttons for the grid, labels for status).
// //    - Implement methods to update the UI based on the game state.


// // 3. Create the Controller (GameLogic):
// //    - Handle user input (e.g., button clicks).
// //    - Update the model based on user actions.
// //    - Update the view based on changes in the model.


// // Example of initializing the MVC components:


// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.util.Random;

// public class SnakeGame extends JPanel implements ActionListener {
//     private static final int TILE_SIZE = 25; // Size of each tile
//     private static final int WIDTH = 500;   // Game width
//     private static final int HEIGHT = 500;  // Game height
//     private static final int GAME_UNITS = (WIDTH * HEIGHT) / (TILE_SIZE * TILE_SIZE);
//     private static final int DELAY = 100;   // Speed of the game

//     private final int[] x = new int[GAME_UNITS];
//     private final int[] y = new int[GAME_UNITS];
//     private int bodyParts = 5;
//     private int foodX, foodY;
//     private int score = 0;

//     private char direction = 'R'; // Start moving right
//     private boolean running = false;
//     private Timer timer;
//     private Random random;

//     public SnakeGame() {
//         random = new Random();
//         setPreferredSize(new Dimension(WIDTH, HEIGHT));
//         setBackground(Color.BLACK);
//         setFocusable(true);
//         addKeyListener(new MyKeyAdapter());
//         startGame();
//     }

//     private void startGame() {
//         spawnFood();
//         running = true;
//         timer = new Timer(DELAY, this);
//         timer.start();
//     }

//     private void spawnFood() {
//         foodX = random.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE;
//         foodY = random.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
//     }

//     private void move() {
//         for (int i = bodyParts; i > 0; i--) {
//             x[i] = x[i - 1];
//             y[i] = y[i - 1];
//         }

//         switch (direction) {
//             case 'U' -> y[0] -= TILE_SIZE; // Move up
//             case 'D' -> y[0] += TILE_SIZE; // Move down
//             case 'L' -> x[0] -= TILE_SIZE; // Move left
//             case 'R' -> x[0] += TILE_SIZE; // Move right
//         }
//     }

//     private void checkFoodCollision() {
//         if ((x[0] == foodX) && (y[0] == foodY)) {
//             bodyParts++;
//             score++;
//             spawnFood();
//         }
//     }

//     private void checkCollisions() {
//         // Check if head collides with body
//         for (int i = bodyParts; i > 0; i--) {
//             if ((x[0] == x[i]) && (y[0] == y[i])) {
//                 running = false;
//                 break;
//             }
//         }

//         // Check if head touches borders
//         if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
//             running = false;
//         }

//         if (!running) {
//             timer.stop();
//         }
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         if (running) {
//             move();
//             checkFoodCollision();
//             checkCollisions();
//         }
//         repaint();
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);

//         if (running) {
//             // Draw food
//             g.setColor(Color.RED);
//             g.fillOval(foodX, foodY, TILE_SIZE, TILE_SIZE);

//             // Draw snake
//             for (int i = 0; i < bodyParts; i++) {
//                 if (i == 0) {
//                     g.setColor(Color.GREEN); // Head
//                 } else {
//                     g.setColor(new Color(45, 180, 0)); // Body
//                 }
//                 g.fillRect(x[i], y[i], TILE_SIZE, TILE_SIZE);
//             }

//             // Draw score
//             g.setColor(Color.WHITE);
//             g.setFont(new Font("Ink Free", Font.BOLD, 20));
//             g.drawString("Score: " + score, 0, g.getFont().getSize());
//         } else {
//             gameOver(g);
//         }
//     }

//     private void gameOver(Graphics g) {
//         // Display Game Over
//         g.setColor(Color.RED);
//         g.setFont(new Font("Ink Free", Font.BOLD, 50));
//         g.drawString("Game Over", WIDTH / 4, HEIGHT / 2);

//         // Display score
//         g.setFont(new Font("Ink Free", Font.BOLD, 20));
//         g.drawString("Score: " + score, WIDTH / 2 +50, HEIGHT / 2 + 50);
//     }

//     private class MyKeyAdapter extends KeyAdapter {
//         @Override
//         public void keyPressed(KeyEvent e) {
//             switch (e.getKeyCode()) {
//                 case KeyEvent.VK_LEFT:
//                     if (direction != 'R') {
//                         direction = 'L';
//                     }
//                     break;
//                 case KeyEvent.VK_RIGHT:
//                     if (direction != 'L') {
//                         direction = 'R';
//                     }
//                     break;
//                 case KeyEvent.VK_UP:
//                     if (direction != 'D') {
//                         direction = 'U';
//                     }
//                     break;
//                 case KeyEvent.VK_DOWN:
//                     if (direction != 'U') {
//                         direction = 'D';
//                     }
//                     break;
//             }
//         }
//     }

//     public static void main(String[] args) {
//         JFrame frame = new JFrame("Snake Game");
//         SnakeGame gamePanel = new SnakeGame();

//         frame.add(gamePanel);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.pack();
//         // frame.setLocationRelativeTo(null);
//         frame.setVisible(true);
//     }
// }
