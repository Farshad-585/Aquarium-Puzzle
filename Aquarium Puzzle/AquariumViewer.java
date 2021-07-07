
/**
 * AquariumViewer represents an interface for playing a game of Aquarium.
 *
 * @author Lyndon While
 * @version 2020
 */
import java.awt.*;
import java.awt.event.*; 
import javax.swing.SwingUtilities;

public class AquariumViewer implements MouseListener
{
    private final int BOXSIZE       = 40;           // the size of each square
    private final int OFFSET        = BOXSIZE * 2;  // the gap around the board
    private final int WINDOWSIZE;                   // set this in the constructor 
    private final int FONTSIZE      = BOXSIZE * 2 / 5; 
    private final int LINETHICKNESS = Math.min(5, BOXSIZE / 10); 
    
    private final Color BACKCOLOR     = Color.WHITE; 
    private final Color LINECOLOR     = Color.BLACK; 
    private final Color AQUARIUMCOLOR = Color.RED; 
    private final Color DIGITCOLOR    = Color.BLACK; 
    private final Color WATERCOLOR    = Color.CYAN; 
    private final Color AIRCOLOR      = Color.PINK; 
    
    private Aquarium puzzle; // the internal representation of the puzzle
    private final int  size; // the puzzle is size x size
    private SimpleCanvas sc; // the display window

    /**
     * Main constructor for objects of class AquariumViewer.
     * Sets all fields, and displays the initial puzzle.
     */
    public AquariumViewer(Aquarium puzzle)
    {
        // TODO 8
        this.puzzle = puzzle;
        size = puzzle.getSize();
        WINDOWSIZE = OFFSET * 2 + size * BOXSIZE;
        sc = new SimpleCanvas("Aquarium", WINDOWSIZE, WINDOWSIZE, BACKCOLOR);
        sc.addMouseListener(this);
    }
    
    /**
     * Selects from among the provided files in folder Examples. 
     * xyz selects axy_z.txt. 
     */
    public AquariumViewer(String filePath)
    {
        this(new Aquarium(filePath));
    }
    
    /**
     * Returns the current state of the puzzle.
     */
    public Aquarium getPuzzle()
    {
        // TODO 7a
        return puzzle;
    }
    
    /**
     * Returns the size of the puzzle.
     */
    public int getSize()
    {
        // TODO 7b
        return size;
    }

    /**
     * Returns the current state of the canvas.
     */
    public SimpleCanvas getCanvas()
    {
        // TODO 7c
        return sc;
    }
    
    /**
     * Displays the initial puzzle; see the LMS page for the format.
     */
    public void displayPuzzle()
    {
        // TODO 13
        displayGrid();
        displayNumbers();
        displayAquariums();
        displayButtons();
    }
    
    /**
     * Displays the grid in the middle of the window.
     */
    public void displayGrid()
    {
        // TODO 9
        for (int k = 0; k <= size; k++)
        {
            int z = OFFSET + k * BOXSIZE;
            // horizontal lines
            sc.drawLine(OFFSET, z, WINDOWSIZE - OFFSET, z, LINECOLOR);
            // vertical lines
            sc.drawLine(z, OFFSET, z, WINDOWSIZE - OFFSET, LINECOLOR);
        }
    }
    
    /**
     * Displays the numbers around the grid.
     */
    public void displayNumbers()
    {
        // TODO 10
        sc.setFont(new Font("Times", 1, FONTSIZE));
        for (int k = 0; k < size; k++)
        {
            // along the top
            int z = puzzle.getColumnTotals()[k];
            sc.drawString(z, OFFSET + BOXSIZE * k + FONTSIZE * (24 - 7 * (z + "").length()) / 20, // adjustment for multidigit numbers
                             OFFSET - BOXSIZE / 2, DIGITCOLOR);
            // down the left
            z = puzzle.getRowTotals()[k];
            sc.drawString(z, OFFSET - BOXSIZE * (2 + (z + "").length()) / 4, // adjustment for multidigit numbers
                             OFFSET + BOXSIZE * k + BOXSIZE * 2 / 3, DIGITCOLOR);
        }
    }
    
    /**
     * Displays the aquariums.
     */
    public void displayAquariums()
    {
        // TODO 11
        // all around the edges
        for (int k : new int[] {0, size})
        {
            sc.drawRectangle(OFFSET - LINETHICKNESS / 2,                  OFFSET + k * BOXSIZE - LINETHICKNESS / 2,
                             OFFSET + size * BOXSIZE + LINETHICKNESS / 2, OFFSET + k * BOXSIZE + LINETHICKNESS / 2, AQUARIUMCOLOR);
            sc.drawRectangle(OFFSET + k * BOXSIZE - LINETHICKNESS / 2,    OFFSET - LINETHICKNESS / 2,
                             OFFSET + k * BOXSIZE + LINETHICKNESS / 2,    OFFSET + size * BOXSIZE + LINETHICKNESS / 2, AQUARIUMCOLOR);
        }
        // internal boundaries
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
            {
                // horizontal internal boundaries
                if (r < size && c < size - 1 && puzzle.getAquariums()[c][r] != puzzle.getAquariums()[c+1][r])
                   sc.drawRectangle(OFFSET +  r    * BOXSIZE - LINETHICKNESS / 2, OFFSET + (c+1) * BOXSIZE - LINETHICKNESS / 2, 
                                    OFFSET + (r+1) * BOXSIZE + LINETHICKNESS / 2, OFFSET + (c+1) * BOXSIZE + LINETHICKNESS / 2, AQUARIUMCOLOR);
                // vertical internal boundaries
                if (r < size - 1 && c < size && puzzle.getAquariums()[c][r] != puzzle.getAquariums()[c][r+1])
                   sc.drawRectangle(OFFSET + (r+1) * BOXSIZE - LINETHICKNESS / 2, OFFSET +  c    * BOXSIZE - LINETHICKNESS / 2, 
                                    OFFSET + (r+1) * BOXSIZE + LINETHICKNESS / 2, OFFSET + (c+1) * BOXSIZE + LINETHICKNESS / 2, AQUARIUMCOLOR);
            }
    }
    
    /**
     * Displays the buttons below the grid.
     */
    public void displayButtons()
    {
        // TODO 12
        sc.setFont(new Font("Times", 1, FONTSIZE));
        sc.drawString("SOLVED?", OFFSET,                                WINDOWSIZE - OFFSET * 11 / 20, DIGITCOLOR);
        sc.drawString("CLEAR",   WINDOWSIZE - OFFSET - BOXSIZE * 3 / 2, WINDOWSIZE - OFFSET * 11 / 20, DIGITCOLOR);
    }
    
    /**
     * Updates the display of Square r,c. 
     * Sets the display of this square to whatever is in the squares array. 
     */
    public void updateSquare(int r, int c)
    {
        // TODO 14
        Color z = BACKCOLOR;
        if (puzzle.getSpaces()[r][c] == Space.WATER) z = WATERCOLOR;
        sc.drawRectangle(OFFSET + c     * BOXSIZE + LINETHICKNESS / 2, OFFSET + r     * BOXSIZE + LINETHICKNESS / 2, 
                         OFFSET + (c+1) * BOXSIZE - LINETHICKNESS / 2, OFFSET + (r+1) * BOXSIZE - LINETHICKNESS / 2, z);
        // draw air if necessary
        if (puzzle.getSpaces()[r][c] == Space.AIR)
           sc.drawCircle(OFFSET + c * BOXSIZE + BOXSIZE / 2, OFFSET + r * BOXSIZE + BOXSIZE / 2, BOXSIZE / 5, AIRCOLOR);
    }
    
    /**
     * Responds to a mouse click. 
     * If it's on the board, make the appropriate move and update the screen display. 
     * If it's on SOLVED?,   check the solution and display the result. 
     * If it's on CLEAR,     clear the puzzle and update the screen display. 
     */
    public void mousePressed(MouseEvent e) 
    {
        // TODO 15
        sc.drawRectangle(0, WINDOWSIZE - OFFSET / 2, WINDOWSIZE, WINDOWSIZE, BACKCOLOR); // always clear any previous solution statement
        if (OFFSET <= e.getX() && e.getX() < WINDOWSIZE - OFFSET && OFFSET <= e.getY() && e.getY() < WINDOWSIZE - OFFSET)
        {
           int r = (e.getY() - OFFSET) / BOXSIZE;
           int c = (e.getX() - OFFSET) / BOXSIZE;
           if (SwingUtilities.isLeftMouseButton(e))  puzzle.leftClick(r, c);
           else
           if (SwingUtilities.isRightMouseButton(e)) puzzle.rightClick(r, c);
           updateSquare(r, c);
        }
        else
        if (OFFSET < e.getX() && e.getX() < WINDOWSIZE / 2 && e.getY() > WINDOWSIZE - OFFSET)
        {
           sc.drawString(CheckSolution.isSolution(puzzle), OFFSET, WINDOWSIZE - OFFSET / 4, DIGITCOLOR);
        }
        else
        if (WINDOWSIZE / 2 < e.getX() && e.getX() < WINDOWSIZE - OFFSET && e.getY() > WINDOWSIZE - OFFSET)
        {
           puzzle.clear();
           sc.drawRectangle(0, 0, WINDOWSIZE, WINDOWSIZE, BACKCOLOR);
           displayPuzzle();
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
