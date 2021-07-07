
/**
 * Aquarium represents a single problem in the game Aquarium.
 *
 * @author Lyndon While
 * @author Farshad Ghanbari
 * @version 2020
 */
public class Aquarium
{
    private int   size;         // the board is size x size
    private int[] columnTotals; // the totals at the top of the columns, left to right
    private int[] rowTotals;    // the totals at the left of the rows, top to bottom 
    
    // the board divided into aquariums, numbered from 1,2,3,...
    // spaces with the same number are part of the same aquarium
    private int[][] aquariums;
    // the board divided into spaces, each empty, water, or air
    private Space[][] spaces;

    /**
     * Constructor for objects of class Aquarium. 
     * Creates, initialises, and populates all of the fields.
     */
    public Aquarium(String filename)
    {
        // TODO 3
        FileIO file  = new FileIO(filename);
        columnTotals = parseLine(file.getLines().get(0));
        rowTotals    = parseLine(file.getLines().get(1));
        size = columnTotals.length;
        aquariums = new int[size][size];
        for (int r = 0; r < size; r++)
            aquariums[r] = parseLine(file.getLines().get(r + 3));
        spaces = new Space[size][size];
        clear();
    }
    
    /**
     * Uses the provided example file on the LMS page.
     */
    public Aquarium()
    {
        this("Examples/a4_2.txt");
    }

    /**
     * Returns an array containing the ints in s, 
     * each of which is separated by one space. 
     * e.g. if s = "1 299 34 5", it will return {1,299,34,5} 
     */
    public static int[] parseLine(String s)
    {
        // TODO 2
        String[] line = s.split(" ");
        int[] z = new int[line.length];
        for (int k = 0; k < line.length; k++)
            z[k] = Integer.parseInt(line[k]);
        return z;
    }
    
    /**
     * Returns the size of the puzzle.
     */
    public int getSize()
    {
        // TODO 1a
        return size;
    }
    
    /**
     * Returns the column totals.
     */
    public int[] getColumnTotals()
    {
        // TODO 1b
        return columnTotals;
    }
    
    /**
     * Returns the row totals.
     */
    public int[] getRowTotals()
    {
        // TODO 1c
        return rowTotals;
    }
    
    /**
     * Returns the board in aquariums.
     */
    public int[][] getAquariums()
    {
        // TODO 1d
        return aquariums;
    }
    
    /**
     * Returns the board in spaces.
     */
    public Space[][] getSpaces()
    {
        // TODO 1e
        return spaces;
    }
    
    /**
     * Performs a left click on Square r,c if the indices are legal, o/w does nothing. 
     * A water space becomes empty; other spaces become water. 
     */
    public void leftClick1(int r, int c)
    {
        // TODO 4
        if (0 <= r && r < size && 0 <= c && c < size)
           if (spaces[r][c] == Space.WATER) 
                spaces[r][c] = Space.EMPTY;
           else spaces[r][c] = Space.WATER;
    }
    
    /**
     * A version of leftClick that uses exceptions. 
     */
    public void leftClick(int r, int c)
    {
        // TODO 4
        try
        {
           if (spaces[r][c] == Space.WATER) 
                spaces[r][c] = Space.EMPTY;
           else spaces[r][c] = Space.WATER;
        }
        catch (Exception e) {} // do nothing if the array access fails
    }
    
    /**
     * Performs a right click on Square r,c if the indices are legal, o/w does nothing. 
     * An air space becomes empty; other spaces become air. 
     */
    public void rightClick1(int r, int c)
    {
        // TODO 5
        if (0 <= r && r < size && 0 <= c && c < size)
           if (spaces[r][c] == Space.AIR) 
                spaces[r][c] = Space.EMPTY;
           else spaces[r][c] = Space.AIR;
    }
    
    /**
     * A version of rightClick that uses exceptions. 
     */
    public void rightClick(int r, int c)
    {
        // TODO 5
        try
        {
           if (spaces[r][c] == Space.AIR) 
                spaces[r][c] = Space.EMPTY;
           else spaces[r][c] = Space.AIR;
        }
        catch (Exception e) {} // do nothing if the array access fails
    }
    
    /**
     * Empties all of the spaces.
     */
    public void clear()
    {
        // TODO 6
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++) 
                spaces[r][c] = Space.EMPTY;
    }
}
