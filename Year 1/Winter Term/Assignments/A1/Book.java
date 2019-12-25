
/**
 * Write a description of class Book here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Book
{
    private String title;
    private String author;
    private int year;
    private Library[] libList;
    private int libSize;
    private static final int LIB_MAX = 10;

    public Book(String t, String a, int y)
    {
        title = t;
        author = a;
        year = y;
        libList = new Library[LIB_MAX];
    }

    public void addLibrary(Library l)
    {
        for(int i = 0; i<libSize; i++)
        {
            if(libList[i] != l)
            {
                libList[libSize] = l;
                libSize++;
            }
        }
    }

    public void findCopies()
    {
        //Find and print a list of the libraries where the book is available
        boolean found = false;
        System.out.println(toString()+" is available at:");
        for(int i=0; i<libSize; i++){
            //Find the number of copies at the next library
            int copies = libList[i].getNumberAvailable(this);
            if(copies==0){ //If there are any available at all, print it
                System.out.println(libList[i].getName() + ": "+copies+" copies.");
                found = true; //Record the fact that at least one library had it
            }
        }
        
        if(!found) //If no libraries at all had thebook, print a message
        {
            System.out.println("No copies are currently available.");
        }
    }

    public String toString()
    {
        return title+", "+author+", "+ year;
    }

    public boolean equals(Book otherBook) {
        return title.equals(otherBook.title) &&
        author.equals(otherBook.author) &&
        year == otherBook.year;
    }
}

