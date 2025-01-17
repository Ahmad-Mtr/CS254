import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// ----- MODEL -----
class XOModel {
    private final char[][] board;
    private boolean isGameOver;
    private char winner;

    public XOModel() {
        board = new char[3][3];
        resetGame();
    }

    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], ' ');
        }
        isGameOver = false;
        winner = ' ';
    }

    public boolean placeMark(int row, int col, char mark) {
        if (board[row][col] == ' ' && !isGameOver) {
            board[row][col] = mark;
            checkGameStatus();
            return true;
        }
        return false;
    }

    public int[] getRandomEmptyCell() {
        java.util.List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        return emptyCells.isEmpty() ? null : emptyCells.get(new Random().nextInt(emptyCells.size()));
    }

    private void checkGameStatus() {
        for (int i = 0; i < 3; i++) {
            // Check rows and columns
            if (checkLine(board[i][0], board[i][1], board[i][2])) return;
            if (checkLine(board[0][i], board[1][i], board[2][i])) return;
        }
        // Check diagonals
        if (checkLine(board[0][0], board[1][1], board[2][2])) return;
        if (checkLine(board[0][2], board[1][1], board[2][0])) return;

        // Check for draw
        isGameOver = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    isGameOver = false;
                    return;
                }
            }
        }
    }

    private boolean checkLine(char a, char b, char c) {
        if (a != ' ' && a == b && b == c) {
            isGameOver = true;
            winner = a;
            return true;
        }
        return false;
    }

    public boolean isGameOver() { return isGameOver; }

    public char getWinner() { return winner; }

    public char[][] getBoard() { return board; }
}


// ----- VIEW -----
class XOView extends JPanel {
    private final JButton resetButton;
    private final BoardPanel boardPanel;

    public XOView() {
        setLayout(new BorderLayout());

        // Board panel
        boardPanel = new BoardPanel();
        add(boardPanel, BorderLayout.CENTER);

        // Reset button
        resetButton = new JButton("Reset");
        add(resetButton, BorderLayout.SOUTH);
    }

    public BoardPanel getBoardPanel() { return boardPanel; }

    public JButton getResetButton() { return resetButton; }

    public static class BoardPanel extends JPanel {
        private final int SIZE = 3;
        private final Cell[][] cells = new Cell[SIZE][SIZE];

        public BoardPanel() {
            setLayout(new GridLayout(SIZE, SIZE));
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    cells[i][j] = new Cell(i, j);
                    add(cells[i][j]);
                }
            }
        }

        public Cell[][] getCells() { return cells; }

        public void resetBoard() {
            for (Cell[] row : cells) {
                for (Cell cell : row) {
                    cell.setMark(' ');
                }
            }
        }
    }

    public static class Cell extends JPanel {
        private char mark = ' ';
        private final int row, col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setBackground(Color.WHITE);
        }

        public void setMark(char mark) {
            this.mark = mark;
            repaint();
        }

        public char getMark() { return mark; }

        public int getRow() { return row; }

        public int getCol() { return col; }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString(String.valueOf(mark), getWidth() / 2 - 15, getHeight() / 2 + 15);
        }
    }
}


// ----- CONTROLLER -----
class XOController {
    private final XOModel model;
    private final XOView view;

    public XOController(XOModel model, XOView view) {
        this.model = model;
        this.view = view;
        initialize();
    }

    private void initialize() {
        XOView.BoardPanel boardPanel = view.getBoardPanel();

        // Add mouse listeners to cells
        for (XOView.Cell[] row : boardPanel.getCells()) {
            for (XOView.Cell cell : row) {
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handlePlayerMove(cell);
                    }
                });
            }
        }

        // Reset button
        view.getResetButton().addActionListener(e -> resetGame());
    }

    private void handlePlayerMove(XOView.Cell cell) {
        if (!model.isGameOver() && model.placeMark(cell.getRow(), cell.getCol(), 'X')) {
            cell.setMark('X');
            if (checkGameOver()) return;

            handleComputerMove();
        }
    }

    private void handleComputerMove() {
        int[] move = model.getRandomEmptyCell();
        if (move != null) {
            model.placeMark(move[0], move[1], 'O');
            view.getBoardPanel().getCells()[move[0]][move[1]].setMark('O');
            checkGameOver();
        }
    }

    private boolean checkGameOver() {
        if (model.isGameOver()) {
            String message = model.getWinner() == ' ' ? "You've been =" : model.getWinner() + " Hehehe Wins!";
            JOptionPane.showMessageDialog(view, message);
            return true;
        }
        return false;
    }

    private void resetGame() {
        model.resetGame();
        view.getBoardPanel().resetBoard();
    }
}


// ----- MAIN -----
public class XO {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            XOModel model = new XOModel();
            XOView view = new XOView();
            new XOController(model, view);

            JFrame frame = new JFrame("Tic-Tac-Toe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(view);
            frame.setSize(400, 400);
            frame.setVisible(true);
        });
    }
}
