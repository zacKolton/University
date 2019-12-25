
/**
 * Class:           BasicItem
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: Made to help organize following subclasses and provide for information hiding 
 *          for the financial components (Currency/Investor) and Entries (Portfolio/Blockchain)
 *     
 */
public abstract class BasicItem extends ListItem
{
    public abstract boolean isEqual(BasicItem in);
    public abstract String toString();
}
