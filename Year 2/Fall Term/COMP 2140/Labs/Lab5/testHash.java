
/**
 * Write a description of class testHash here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class testHash
{
    
    
    public static void main(String[] args)
    {
        Table table = new Table();
        table.insert("zac",1);
        table.printTable();
        table.insert("nothing",1);
        table.printTable();
        table.insert("sk;jnkv",20);
        table.printTable();
        table.insert("",34);
        table.insert("gz",22);
        table.printTable();
    }
    
    
}

class Table
{
    private static final int hashCoe   = 13;
    private static final int tableSize = 23;
    private int[] table;
    
    public Table()
    {
        table = new int[tableSize];
    }
    
    public int hash(String temp)
    {
        int hashValue = 0;
        for(int i = 0; i< temp.length(); i++)
        {
            hashValue += (temp.charAt(i)*hashCoe);
        }
        return hashValue;
    }
    
    public void insert(String temp,int data)
    {
        int hashValue = hash(temp);
        table[hashValue % tableSize] = data;
    }
    
    public void printTable()
    {
        String s ="[ ";
        for(int i = 0; i< tableSize; i++)
        {
            s += table[i]+ " ";
        }
        System.out.println(s+"]");
    }
}
