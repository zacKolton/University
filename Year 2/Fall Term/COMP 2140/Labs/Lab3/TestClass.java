import java.util.*; 
import java.io.*;
/**
 * Write a description of class TestClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestClass
{
    private static final int NUM_ITEMS = 10; // How many random items to put in the test list.
    private static final int MAX_ITEM  = 10; // Maximum value of a random item.

    public static void main( String[] args ) {
        System.out.println( "\n\n COMP 2140 Lab 3 (Fall 2018)\n" );
        tester();
    }

    public static void tester()
    {
        List myList = new List();
        Random generator = new Random();

        // create a random linked list of a given size
        for ( int i = 0; i < NUM_ITEMS; i++ ) {
            myList.insert(generator.nextInt( MAX_ITEM ));
        }
        System.out.println("\nThe unsorted list before quick sorting:" );
        myList.printList();
        System.out.println("\nDeleting start:" );
        myList.deleteNode(4);
        myList.printList();
    }
}

class List {

    //===============================================================

    // A Node for the doubly-linked list
    private class DNode {
        public int item;
        public DNode forward; // pointer to the node AFTER this one
        public DNode back; // pointer to the node BEFORE this one

        public DNode( int newItem, DNode newFwd, DNode newBack ) {
            item = newItem;
            forward = newFwd;
            back = newBack;
        }

        // Getters and setters, in case you like using them (you do NOT have to use them)
        public int getItem() { return item; }

        public DNode getForward() { return forward; }

        public DNode getBack() { return back; }
        // No setItem() because you are not allowed to change the item stored in a node.
        public void setForward( DNode newFwd ) { forward = newFwd; }

        public void setBack( DNode newBack ) { back = newBack; }

    } // end class DNode
    private DNode start; // pointer to the first node (if any)
    private DNode end; // pointer to the last node (if any)

    public List() { // creates an empty list
        start = end = null;
    }

    /*****************
     * insert
     *
     * Purpose: Add newItem to the end of the list.
     *
     ******************/
    public void insert( int newItem ) {
        if ( end != null ) {
            end.forward = new DNode( newItem, null, end );
            end = end.forward; // Make end point at new last node
        } else  // The new node is the only node in the list
            start = end = new  DNode( newItem, null, null );
    } // end insert

    /*****************
     * middleNode
     *
     * Purpose: Return a pointer to the middle node in "this".
     *          If there is an even number of nodes, there are two "middle" nodes. We can
     *          return either of them.
     * Assumption: The list has at least three nodes.
     *
     ******************/
    public DNode middleNode() 
    {
        DNode startCurr = start;
        DNode endCurr  = end;
        int count = 0; //for clarity
        while((startCurr != endCurr)&& (startCurr.forward != endCurr))
        {
            startCurr = startCurr.forward;
            endCurr   = endCurr.back;
            count++;
        }
        return startCurr;
    } // end middleNode
    
    /*****************
     * partition
     *
     * Purpose: Partition "this" using the pivot node (parameter pivot, which is NOT in the list "this").
     *          At the end of the partition:
     *          - "this" contains the bigs (nodes containing values >= pivot)
     *          - smalls is a new list containing the smalls (nodes containing values < pivot).
     *          Return list smalls.
     *
     ******************/
    public List partition(DNode pivot)
    { // returns the smalls in a new list, the bigs in "this"
        List smallsList = new List();
        DNode curr      = start;
        while(curr != null)
        {
            if(curr.getItem() < pivot.getItem())
            {
                //smallsList.addToSmalls(curr);
                this.deleteNode();
            }
            curr = curr.forward;
        }

        return smallsList;
    } // end partition

    public void deleteNode()
    {
        start = start.forward;
    }
    
    public void deleteNode(int a)
    {
        DNode curr = start;
        DNode last = null;
        
        while(curr.getItem() != a)
        {
            last = curr;
            curr = curr.forward;
        }
        DNode temp = curr;
        last.forward = curr.forward;
        
    }

    /*****************
     * printList
     *
     * Purpose: To print out the items in a list, separated by blanks.
     *
     ******************/
    public void printList() {
        DNode curr = start;
        while ( curr != null ) {
            System.out.print(curr.item+" ");
            curr = curr.forward;
        } // end for
        System.out.println("\nMiddle Node Value: "+middleNode().getItem()); 
    }
    

}

