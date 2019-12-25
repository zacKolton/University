import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * Write a description of class GameBoard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameBoard
{
    private Tile[][] board;

    public GameBoard(String f)
    {
        try{
            FileReader file = new FileReader(f);
            Scanner inFile = new Scanner (file);

            String size = inFile.nextLine();
            Scanner currentSize = new Scanner(size);
            int rows = currentSize.nextInt();
            int cols = currentSize.nextInt();
            board = new Tile[rows][cols];
            currentSize.close();
            for(int r = 0; r < rows; r++)
            {
                String line = inFile.nextLine();
                for(int c = 0; c< cols; c++)
                {
                    board[r][c] = makeTile(line.substring(c,c+1));
                }
            }
            inFile.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private Tile makeTile(String s)
    {
        Tile newTile;
        if(s.equals("@"))
        {
            newTile = new OpenSpace(new Player());
        }
        else if(s.equals("t"))
        {
            newTile = new OpenSpace(new Troll());
        }
        else if(s.equals("T"))
        {
            newTile = new OpenSpace(new GreaterTroll());
        }
        else if(s.equals("Y"))
        {
            newTile = new OpenSpace(new Amulet());
        }
        else if(s.equals("."))
        {
            newTile = new OpenSpace();
        }
        else if(s.equals("h"))
        {
            newTile = new OpenSpace(new HealthPotion());
        }
        else if(s.equals("^"))
        {
            newTile = new OpenSpace(new Trap());
        }
        else 
        {
            newTile = new Wall();
        }
        return newTile;
    }
    
    public String toString()
    {
        String answer = "";
        Player player = null;
        
        for(Tile[] row : board)
        {
            for(Tile t : row)
            {
                answer += t.getSymbol();
                if(t.getContent() != null && t.getContent() instanceof Player)
                {
                    player = (Player) t.getContent();
                }
            }
            answer += "\n";
        }
        answer = "Health of nigga: "+player.getHP()+ "\n" + answer;
        return answer;
    }

}


