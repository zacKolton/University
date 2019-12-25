import java.util.*; 
import java.io.*;

/*
 * Lab 3    Fall2018
 * 
 * COMP 2140 SECTION <your section>
 * 
 * INSTRUCTORS Helen Cameron and Rob Guderian
 * AUTHOR     <your name>
 * VERSION    17-19 October 2018
 *
 * Purpose: Quick sorts a doubly-linked list and verifies that the list is sorted.
 *   It is not very efficient because the choosePivot method takes O(n) time on n items.
 *   This is a recursive quick sort that uses the median-of-three technique for choosing
 *   its pivot, and its base cases are lists of size 0 or 1 (do nothing) and 2 (swap if necessary), 
 *
 *   The doubly-linked list has NO dummy nodes, but it has both a start (first node) 
 *   and end (last node) pointer.
 */

public class Lab3 {

    // Constants to control the test of quick sort
    private static final int NUM_ITEMS = 10; // How many random items to put in the test list.
    private static final int MAX_ITEM = 1001; // Maximum value of a random item.

    public static void main( String[] args ) {
        System.out.println( "\n\n COMP 2140 Lab 3 (Fall 2018)\n" );

        tester();

        System.out.println( "\nLab program ends normally..." );
    }

    /*****************
     * tester
     *
     * Purpose: Test the quick sort in the doubly-linked list class.
     *
     ******************/
    private static void tester() {
        List myList = new List();

        Random generator = new Random();

        // create a random linked list of a given size
        for ( int i = 0; i < NUM_ITEMS; i++ ) {
            myList.insert( generator.nextInt( MAX_ITEM ) );
        }
        System.out.println( "\nThe unsorted list before quick sorting:" );
        myList.printList();

        //Quick sort it
        myList.quickSort();
        System.out.println( "\nThe list after quick sorting:" );
        myList.printList();
        // test that it is sorted
        if ( myList.isSorted() )
            System.out.println( "\nThe list is correctly sorted!" );
        else
            System.out.println( "\n**** ERROR **** The list was NOT sorted." );
    } // end tester

} // end class Lab3

//===================================================================
// A doubly-linked list with no dummy nodes

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

    //===============================================================

    // Here we are back in class List

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
     * quick sort
     *
     * Purpose: To quick sort the doubly-linked list.
     *
     ******************/
    public void quickSort() {
        List smalls;
        DNode pivot;
        if ( start != null && start != end ) { // at least two nodes
            if ( start.forward == end ) { // Two nodes (swap if out of order)
                if ( start.item > end.item ) {
                    DNode temp = start;
                    start = end;
                    end = temp;
                    start.back = end.forward = null;
                    start.forward = end;
                    end.back = start;
                }
            } else { // more than two nodes
                pivot = choosePivot();
                smalls = partition( pivot ); // bigs remain in "this"
                smalls.quickSort(); // sort the smalls
                this.quickSort(); // sort the bigs
                // put the pivot in between the sorted smalls and bigs
                smalls.end.forward = pivot;
                pivot.back = smalls.end;
                pivot.forward = this.start;
                this.start.back = pivot;
                this.start = smalls.start;
            } // end if two nodes - else
        } // end if at least two nodes
    } // end quickSort

    /*****************
     * choosePivot
     *
     * Purpose: Use the median-of-three method to choose a good pivot.
     *          The pivot should be the median of the first, middle and last nodes in the list.
     *          Unlink the node containing the pivot from the list and return the node.
     *
     ******************/
    private DNode choosePivot() {
        DNode mid = middleNode(); // Find the middle node in the list
        DNode pivot = null;
        if ( start.item < mid.item ) {
            if ( mid.item < end.item ) { // start.item < mid.item < end.item: mid is pivot
                pivot = mid;
                // unlink the pivot from the middle
                pivot.back.forward = pivot.forward;
                pivot.forward.back = pivot.back;
            } else { // end.item <= mid.item
                if ( start.item < end.item ) { // start.item < end.item <= mid.item: end is pivot
                    pivot = end;
                    // unlink the pivot from the end
                    end = end.back;
                    end.forward = null;
                } else { // end.item <= start.item < mid.item: start is pivot
                    pivot = start;
                    // unlink the pivot from the start
                    start = start.forward;
                    start.back = null;
                }
            }
        } else { // mid.item <= start.item
            if ( start.item < end.item ) { // mid.item <= start.item < end.item: start is pivot
                pivot = start;
                // unlink the pivot from the start
                start = start.forward;
                start.back = null;
            } else { // end.item <= start.item
                if ( mid.item < end.item ) { // mid.item < end.item <= start.item: end is pivot
                    pivot = end;
                    // unlink the pivot from the end
                    end = end.back;
                    end.forward = null;
                } else { // end.item <= mid.item <= start.item: mid is pivot
                    pivot = mid;
                    // unlink the pivot from the middle
                    pivot.back.forward = pivot.forward;
                    pivot.forward.back = pivot.back;
                }
            }
        }
        return pivot;
    } // end choosePivot

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
    private List partition(DNode pivot)
    { // returns the smalls in a new list, the bigs in "this"
        List smallsList = new List();
        DNode curr = start;
        DNode last = null;
        int pivotVal = pivot.getItem();
        while(curr != null)
        {
            if(curr.getItem() < pivotVal)
            {
                DNode temp = curr;
                smallsList.addToSmalls(temp);
                smallsList.printList();
            }
            last = curr;
            curr = curr.forward;
        }
        return smallsList;
    } // end partition

    private void deleteNode(DNode node)
    {
        
    }
    
     private void addToSmalls(DNode node)
    {
         if(this.start == null)
         {
             this.start = node;
             node.forward = null;
         }
         else
         {
             DNode curr = start;
             curr.back = node;
             node.forward = curr;
         }
    }

    /*****************
     * middleNode
     *
     * Purpose: Return a pointer to the middle node in "this".
     *          If there is an even number of nodes, there are two "middle" nodes. We can
     *          return either of them.
     * Assumption: The list has at least three nodes.
     *
     ******************/
    private DNode middleNode() 
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
     * isSorted
     *
     * Purpose: To verify that the list is sorted.
     *
     ******************/
    public boolean isSorted() {
        DNode curr;
        boolean sorted = true;
        if ( start != null && start.forward != null ) { // At least 2 nodes
            curr = start;
            while ( sorted && curr != end ) { // >= 2 nodes from curr to end and still sorted
                sorted = curr.item <= curr.forward.item;
                curr = curr.forward;
            } // end while
        }
        return sorted;
    } // end isSorted

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

} // end class List
