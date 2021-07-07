
/**
 * CheckSolution is a utility class which can check if
 * a board position in an Aquarium puzzle is a solution.
 *
 * @author Lyndon While
 * @author Farshad Ghanbari
 * @version 2020
 */
import java.util.Arrays; 

public class CheckSolution
{
    /**
     * Non-constructor for objects of class CheckSolution
     */
    private CheckSolution(){}
    
    /**
     * Returns the number of water squares in each row of Aquarium puzzle p, top down.
     */
    public static int[] rowCounts(Aquarium p)
    {
        // TODO 16
        int[] z = new int[p.getSize()];
        for (int r = 0; r < p.getSize(); r++)
            for (int c = 0; c < p.getSize(); c++)
                if (p.getSpaces()[r][c] == Space.WATER) z[r]++; 
        return z;
    }
    
    /**
     * Returns the number of water squares in each column of Aquarium puzzle p, left to right.
     */
    public static int[] columnCounts(Aquarium p)
    {
        // TODO 17
        int[] z = new int[p.getSize()];
        for (int r = 0; r < p.getSize(); r++)
            for (int c = 0; c < p.getSize(); c++)
                if (p.getSpaces()[r][c] == Space.WATER) z[c]++; 
        return z;
    }
    
    /**
     * Returns a 2-int array denoting the collective status of the spaces 
     * in the aquarium numbered t on Row r of Aquarium puzzle p. 
     * The second element will be the column index c of any space r,c which is in t, or -1 if there is none. 
     * The first element will be: 
     * 0 if there are no spaces in t on Row r; 
     * 1 if they're all water; 
     * 2 if they're all not-water; or 
     * 3 if they're a mixture of water and not-water. 
     */
    public static int[] rowStatus(Aquarium p, int t, int r)
    {
        // TODO 18
        int[] z = {0,-1};
        for (int c = 0; c < p.getSize(); c++)
            if (p.getAquariums()[r][c] == t)
            {
               z[1] = c;
               if (p.getSpaces()[r][c] == Space.WATER)
                    if (z[0] == 2) return new int[] {3,c}; 
                    else           z[0] = 1;
               else if (z[0] == 1) return new int[] {3,c}; 
                    else           z[0] = 2;
            }
        return z;
    }
    
    /**
     * Returns a statement on whether the aquarium numbered t in Aquarium puzzle p is OK. 
     * Every row must be either all water or all not-water, 
     * and all water must be below all not-water. 
     * Returns "" if the aquarium is ok; otherwise 
     * returns the indices of any square in the aquarium, in the format "r,c". 
     */
    public static String isAquariumOK(Aquarium p, int t)
    {
        // TODO 19
        // processing goes down the screen
        boolean seenWater = false; // if we ever see water, all lower rows must be water 
        for (int r = 0; r < p.getSize(); r++)
        {
            int[] z = rowStatus(p, t, r);
            if (z[0] == 3 || z[0] == 2 && seenWater) return r + "," + z[1]; 
            else                              
            if (z[0] == 1)                           seenWater = true;
        }
        return "";
    }
    
    /**
     * Returns a statement on whether we have a correct solution to Aquarium puzzle p. 
     * Every row and column must have the correct number of water squares, 
     * and all aquariums must be OK. 
     * Returns three ticks if the solution is correct; 
     * otherwise see the LMS page for the expected results. 
     */
    public static String isSolution(Aquarium p)
    {
        // TODO 20
        // check each aquarium
        for (int k = 1; k <= p.getAquariums()[p.getSize()-1][p.getSize()-1]; k++)
        {
            String s = isAquariumOK(p, k);
            if (!s.isEmpty()) return "The aquarium at " + s + " is wrong";
        }
        // check the rows
        int r = Arrays.mismatch(rowCounts(p), p.getRowTotals());
        if (r != -1) return "Row " + r + " is wrong";
        // check the columns
        int c = Arrays.mismatch(columnCounts(p), p.getColumnTotals());
        if (c != -1) return "Column " + c + " is wrong";
        return "\u2713\u2713\u2713";
    }
}
