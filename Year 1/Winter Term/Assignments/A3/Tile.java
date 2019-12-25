
/**
 * Abstract class Tile - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Tile
{
    private String symbol;
    protected boolean passable;
    protected Content content;

    public Tile(String s, boolean b)
    {
        symbol   = s;
        passable = b;
    }
    
    public Tile(String s,boolean b, Content c)
    {
        symbol = s;
        passable = b;
        content = c;
    }

    public String getSymbol()
    {
        String answer = symbol;
        if(content != null)
        {
            answer = content.getSymbol();
        }
        return answer;
    }

    public boolean isPassable()
    {
        return passable;
    }
    
    public void removeContent()
    {
        content = null;
    }
    
    public Content getContent()
    {
        return content;
    }
    
    public void setContent(Content c)
    {
        content = c;
    }
}
