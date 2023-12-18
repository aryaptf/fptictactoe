/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #9
 * 1 - 5026221131 - Maulina Nur Laila
 * 2 - 5026221172 - Arya Putra Tsabitah Firjatulloh
 * 3 - 5026221179 - Kadek Mawar Kumala Dewi
 */

import java.awt.*;
import javax.swing.*;

public class Cell {

    public static final int SIZE = 180; // cell width/height (square)
    private Image xImage = new ImageIcon("src/angry.png").getImage();
    private Image oImage = new ImageIcon("src/marah.png").getImage();

    // Define properties (package-visible)
    /** Content of this cell (Seed.EMPTY, Seed.CROSS, or Seed.NOUGHT) */
    Seed content;
    /** Row and column of this cell */
    int row, col;

    /** Constructor to initialize this cell with the specified row and col */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        content = Seed.NO_SEED;
    }

    /** Reset this cell's content to EMPTY, ready for new game */
    public void newGame() {
        content = Seed.NO_SEED;
    }

    /** Paint itself on the graphics canvas, given the Graphics context */
    public void paint(Graphics g) {
        int x = col * SIZE;
        int y = row * SIZE;

        if (content == Seed.CROSS) {
            g.drawImage(xImage, x, y, SIZE, SIZE, null);
        } else if (content == Seed.NOUGHT) {
            g.drawImage(oImage, x, y, SIZE, SIZE, null);
        }
        g.setColor(Color.BLACK);
        g.drawRect(x, y, SIZE, SIZE);
    }
}