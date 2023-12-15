/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #9
 * 1 - 5026221131 - Maulina Nur Laila
 * 2 - 5026221172 - Arya Putra Tsabitah Firjatulloh
 * 3 - 5026221179 - Kadek Mawar Kumala Dewi
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameMain extends JPanel {
    private static final long serialVersionUID = 1L;

    //player's name
    private String playerName1;
    private String playerName2;

    private int xWin = 0;
    private int oWin = 0;

    private static final String TITLE = "Tic Tac Toe";
    private static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
    private static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);

    //name getter
    public String getPlayerName1() {
        return playerName1;
    }
    public String getPlayerName2() {
        return playerName2;
    }

    private Board board;
    private State currentState;
    private Seed currentPlayer;
    private static JLabel statusBar;
    private JButton newGameButton;

    public GameMain() {
        //input player's name
        playerName1 = JOptionPane.showInputDialog("Enter player name 1:");
        playerName2 = JOptionPane.showInputDialog("Enter player name 2:");
        if (playerName1 == null || playerName1.trim().isEmpty()) {
            playerName1 = "RED";
        }

        if (playerName2 == null || playerName2.trim().isEmpty()) {
            playerName2 = "PINK";
        }

        // This JPanel fires MouseEvent
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int row = mouseY / Cell.SIZE;
                int col = mouseX / Cell.SIZE;

                if (currentState == State.PLAYING) {
                    if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                            && board.cells[row][col].content == Seed.NO_SEED) {
                        // Update cells[][] and return the new game state after the move
                        currentState = board.stepGame(currentPlayer, row, col);
                        // Switch player
                        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                    }
                } else {        // game over
                    newGame();  // restart the game
                }
                // Refresh the drawing canvas
                repaint();      // Callback paintComponent().
            }
        });

        // Setup the status bar (JLabel) to display status message
        statusBar = new JLabel();
        statusBar.setFont(FONT_STATUS);
        statusBar.setBackground(COLOR_BG_STATUS);
        statusBar.setOpaque(true);
        statusBar.setPreferredSize(new Dimension(300, 30));
        statusBar.setHorizontalAlignment(JLabel.CENTER);
        statusBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        super.setLayout(new BorderLayout());
        super.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 54));
        setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, false));

        // Set up game
        initGame();
        newGame();

    }

    /** Initialize the game (run once) */
    public void initGame() {
        board = new Board();    // allocate the game-board
    }

    /** Reset the game-board contents and the current-state, ready for new game */
    public void newGame() {
        for (int row = 0; row < Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                board.cells[row][col].content = Seed.NO_SEED; // all cells empty
            }
        }
        currentPlayer = Seed.CROSS;    // cross plays first
        currentState = State.PLAYING;  // ready to play
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon backgroundImage = new ImageIcon("src\\download.jpeg");
        Image img = backgroundImage.getImage();

        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

        board.paint(g);

        if (currentState == State.PLAYING) {
            statusBar.setForeground(Color.BLACK);
            statusBar.setText((currentPlayer == Seed.CROSS) ? playerName1 + "'s Turn" : playerName2 + "'s Turn");
        } else if (currentState == State.DRAW || currentState == State.CROSS_WON || currentState == State.NOUGHT_WON) {
            String message = "";
            Color textColor = Color.BLACK;

            if (currentState == State.DRAW) {
                message = "It's a Draw!";
            } else if (currentState == State.CROSS_WON) {
                xWin++;
                message = playerName1 + " Won! Poin: " + xWin;
                textColor = Color.RED;
                updateScore();
            } else if (currentState == State.NOUGHT_WON) {
                oWin++;
                message = playerName2 + " Won! Poin: " + oWin;
                textColor = Color.BLACK;
                updateScore();
            }

            statusBar.setForeground(textColor);
            statusBar.setText(message + " | Click anywhere to play again.");
        }
    }

    public void quitActionPerformed(ActionEvent evt){
        System.exit(0);
    }

    public void newGameActionPerformed(ActionEvent evt) {
        repaint();
        newGame();
    }

    public static void main(String[] args) {
        // Run GUI construction codes in Event-Dispatching thread for thread safety
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //pop up untuk welcome
                JOptionPane.showMessageDialog(null, "Welcome! click OK to start game! Tic Tac Toe");

                JFrame frame = new JFrame(TITLE);

                //Create Menu Bar
                JMenuBar menubar = new JMenuBar();
                JMenu menu = new JMenu("Menu");
                JMenuItem restartGame = new JMenuItem("Restart Game");
                JMenuItem quit = new JMenuItem("Quit");

                //Setup main JPanel
                GameMain mainPanel= new GameMain();
                Board board1 = new Board();

                // Set the content-pane of the JFrame to an instance of main JPanel
                frame.setContentPane(mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.pack();
                frame.setLocationRelativeTo(null); // center the application window

                //Add to menubar
                menubar.add(menu);

                menu.add(restartGame);
                menu.add(quit);
                frame.setJMenuBar(menubar);

                restartGame.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e) {
                        mainPanel.newGameActionPerformed(e);
                    }
                });

                quit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mainPanel.quitActionPerformed(e);
                    }
                });
                frame.setVisible(true);            // show it
                frame.setResizable(false);
            }
        });
    }

    public void updateScore() {
        if (xWin == 3) {
            JOptionPane.showMessageDialog(this,playerName1 + " is the winner! Congratulations");
            xWin = 0;
            oWin = 0;
        } else if (oWin == 3) {
            JOptionPane.showMessageDialog(this, playerName2 + " is the winner! Congratulations");
            xWin = 0;
            oWin = 0;
        }
    }
}
