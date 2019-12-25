
/**
 * Write a description of class Board2048 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board2048
{
    private int[][] grid;
    private int size;
    public static final int UP   = 0;
    public static final int DOWN = 1;
    public static final int RIGHT= 2;
    public static final int LEFT = 3;
    public Board2048(int x)
    {
        grid = new int[x][x];
        size = x;
    }

    public Board2048(int[][] x)
    {
        grid = x;
        size = x.length;
    }
    
    public int[][] getMatrix()
    {
        return grid;
    }
    

    public String toString()
    {
        String answer = "";
        for(int r = 0; r < size; r++)
        {
            for(int c = 0; c < size; c++)
            {
                if(grid[r][c] == 0)
                {
                    answer += "-\t";
                }
                else
                {
                    answer += grid[r][c] + "\t";
                }
            }
            answer += "\n";
        }
        return answer;
    }

    public int[] extractLine(int i, boolean vertical, boolean reverse)
    {
        int[] line = new int[size];
        for(int j = 0; j < size; j++)
        {
            if(vertical)
            {
                if(reverse)
                {
                    line[j] = grid[size-1-j][i];
                }
                else 
                {
                    line[j] = grid[j][i];
                }
            }
            else
            {
                if(reverse)
                {
                    line[j] = grid[i][size-1-j];
                }
                else
                {
                    line[j] = grid[i][j];
                }
            }
        }
        return line;
    }

    public void insertLine(int[] line, int index, boolean vertical, boolean reverse)
    {
        for(int i =0; i<size; i++)
        {
            if(vertical)
            {
                if(reverse)
                {
                    grid[size-1-i][index] = line[i];
                }
                else
                {
                    grid[i][index] = line[i];
                }
            }
            else
            {
                if(reverse)
                {
                    grid[index][size-1-i] = line[i];
                }
                else
                {
                    grid[index][i] = line[i];
                }
            }
        }
    }

    public Board2048 shift(int direction)
    {
        Board2048 newBoard = new Board2048(size);
        boolean vertical = (direction == UP    || direction == DOWN);
        boolean reverse  = (direction == DOWN || direction == RIGHT);

        for(int i = 0; i<size; i++)
        {
            int[] line = extractLine(i,vertical,reverse);
            alterOneLine(line);
            newBoard.insertLine(line,i,vertical,reverse);
        }
        return newBoard;
    }

    public int numEmpty()
    {
        int total = 0;
        for(int r = 0; r < size; r++)
        {
            for(int c = 0; c < size; c++)
            { 
                if(grid[r][c] == 0)
                {
                    total++;
                }
            }
        }
        return total;
    }

    public void newTile()
    {
        int totalEmpty = numEmpty();
        int randomSpot= (int)(Math.random() * totalEmpty);
        int randomNum = (Math.random() < 0.1)?4:2;
        int countZero = 0;
        for(int r = 0; r < size; r++)
        {
            for(int c = 0; c < size; c++)
            { 
                if(grid[r][c] == 0)
                {
                    countZero++;
                    if(countZero == randomSpot)
                    {
                        grid[r][c] = randomNum;
                        return;
                    }
                }
            }
        }
    }

    public static boolean alterOneLine(int[] a)
    {
        boolean changed = false;
        int limit = 0;
        for(int i = 1; i<a.length; i++)
        {
            for(int j = i; j> limit; j--)
            {
                if(a[j] != 0 && a[j-1] ==0)
                {
                    a[j-1] = a[j];
                    a[j] =0 ;
                    changed = true;
                }

                if(a[j] != 0 && a[j] == a[j-1])
                {
                    a[j-1] *= 2;
                    a[j] = 0;
                    limit = j;
                    changed = true;

                }
            }
        }
        return changed;
    }

    public boolean equals(Board2048 board)
    {
        if(size != board.size)
        {
            return false;
        }

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(grid[i][j] != board.grid[i][j])
                {
                    return false;
                }

            }

        }
        return true;
    }
    
    public boolean validMove(int direction)
    {
        return !this.equals(shift(direction));
    }
    
    public boolean gameOver()
    {
        
        if((numEmpty() == 0) && !validMove(UP)
                             && !validMove(DOWN)
                             && !validMove(RIGHT)
                             && !validMove(LEFT))
        {
            return true;
        }
        return false;
    }

}
