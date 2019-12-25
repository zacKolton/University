import java.io.*;
import java.util.*;
import javax.swing.*;

/***********************************************************************************
 ***********************************************************************************
 * class A5Solution
 *
 *   Comparing BSTs and 2-3 trees.
 *
 *   DO NOT CHANGE ANYTHING IN THIS CLASS
 ***********************************************************************************
 ************************************************************************************/

public class A5KoltonZac {

    public static void main( String[] args ) {

        System.out.println( "\n\nComp 2140 Assignment 5 "
            +"BSTs and 2-3 Trees, Winter 2018\n" );

        getAndProcessFile();

        System.out.println( "\nProgram ended normally.\n" );

    } // end main

    private static void getAndProcessFile( ) {
        Scanner keyboard; // To read in the name of the input file
        String fileName; // The name of the input file typed in by the user
        BufferedReader in;  // Read text from a character-input stream.

        try {
            // Retrieve the file to be read using keyboard (console) input.

            keyboard = new Scanner( System.in );
            System.out.println( "\nEnter the input file name (.txt files only): " );
            fileName = keyboard.nextLine();
            in = new BufferedReader( new FileReader( fileName ) );

            // Now process the sequences in the input file.

            processSequences( in );

            // Finally, close the input file.

            in.close();

        } catch (IOException ex) {
            System.out.println( "\n\n***I/O error: " + ex.getMessage() + "\n\n" );
        }

    } // getAndProcessFile

    private static void processSequences( BufferedReader in ) {
        int numSequences = 0; // number of sequences
        int numInSequence = 0; // number of ints in the current sequence
        int [] data; // A sequence
        String inLine;   // A line of input from BufferedReader in.

        try {
            // Get the number of sequences.

            inLine = in.readLine();
            numSequences = Integer.parseInt( inLine );

            // Read in and perform the four steps with each sequence.

            for ( int seqNumber = 0; seqNumber < numSequences; seqNumber++ ) {
                // Get the number of integers in this sequence.

                inLine = in.readLine();
                numInSequence = Integer.parseInt( inLine );

                // Read the sequence into an array so it can be
                // re-used multiple time.

                data = new int[ numInSequence ];

                for ( int i = 0; i < numInSequence; i++ ) {
                    inLine = in.readLine();
                    data[i] = Integer.parseInt( inLine );
                }

                // Test the BST class with the sequence

                testBSTOnSequence( data );

                // Test the TwoThreeTree class with the sequence

                test23TreeOnSequence( data );

            } // end for seqNumber

        } catch (IOException ex) {
            System.out.println( "I/O error: " + ex.getMessage() );
        }

    } // end processSequences

    private static void testBSTOnSequence( int[] data ) {
        long start, stop, elapsed; // used in timing
        boolean allFound; // search() returns true if the item is found; false otherwise.

        // Create a new, empty BST

        BST tBST = new BST();

        // Time the BST insertions.

        start = System.nanoTime();
        for ( int i = 0; i < data.length; i++ ) {
            tBST.insert( data[i] );
        }
        stop = System.nanoTime();
        elapsed = stop - start;

        System.out.println( "\nInserting " + data.length
            + " integers into a BST: " + elapsed + " nanoseconds." );

        // Time the BST searches.

        allFound = true;
        start = System.nanoTime();
        for ( int i = 0; i < data.length; i++ ) {
            allFound = allFound && tBST.search( data[i] );
        }
        stop = System.nanoTime();
        elapsed = stop - start;

        System.out.println( "\nSearching for the " + data.length
            + " integers in the BST: " + elapsed + " nanoseconds." );
        System.out.println( "All items inserted were "
            + ( allFound? "" : "not ") + "found." );

        // Print out the first 20 integers in the BST

        System.out.println( "\nThe smallest 20 integers in the BST:" );
        tBST.printTree();

    } // end testBSTOnSequence

    private static void test23TreeOnSequence( int[] data ) {
        long start, stop, elapsed; // used in timing
        boolean allFound; // search() returns true if the key is found; otherwise false

        // Create a new, empty 2-3 tree

        TwoThreeTree t23 = new TwoThreeTree();

        // Time the 2-3 tree insertions.

        start = System.nanoTime();
        for ( int i = 0; i < data.length; i++ ) {
            t23.insert( data[i] );
        }
        stop = System.nanoTime();
        elapsed = stop - start;

        System.out.println( "\nInserting " + data.length
            + " integers into a 2-3 tree: " + elapsed + " nanoseconds." );

        // Time the 2-3 tree searches.

        allFound = true;
        start = System.nanoTime();
        for ( int i = 0; i < data.length; i++ ) {
            allFound = allFound && t23.search( data[i] );
        }
        stop = System.nanoTime();
        elapsed = stop - start;

        System.out.println( "\nSearching for the " + data.length
            + " integers in the 2-3 tree: " + elapsed + " nanoseconds.\n" );
        System.out.println( "All items inserted were "
            + ( allFound? "" : "not ") + "found.\n" );
        System.out.println( "The 2-3 tree "
            + ( (t23.treeOK())? "passes " : "does NOT pass ") + "all sanity checks." );

        // Print out the first 20 integers in the 2-3 tree.

        System.out.println( "\nThe smallest 20 integers in the 2-3 tree:" );
        t23.printTree();

    } // end test23TreeOnSequence

} // end class A5Solution

/**
 * A5KoltonZac
 * COMP 2140  SECTION A02
 * INSTRUCTOR Guderian (A02)
 * ASSIGNMENT #5
 * Zachary Kolton 7838513
 * Dec. 7th 2018
 *
 * PURPOSE: Create a 2-3 tree 
 * 
 * NOTES: Was not able to complete the splitAndPush() method, program runs however 
 */

class TwoThreeTree {
    public static final int KEY_LEAF = 0;
    public static final int KEY_MIN  = 0;
    public static final int KEY_MAX  = 1;

    public static final int LEFT_CHILD  = 0;
    public static final int MIN_CHILD   = 0;
    public static final int RIGHT_CHILD = 1;
    public static final int MIDD_CHILD  = 1;
    public static final int MAX_CHILD   = 2;

    public int printCount = 0;
    /*************************************************************************
     *************************************************************************
     * class TwoThreeNode
     *
     *   A node in a leaf-based 2-3 tree:
     *   - could be either a leaf (no children) or an 
     *     interior node (with 2 or 3 children)
     *   - data are stored only in leaves
     *   - interior nodes contain only index values to guide 
     *     searches to the correct leaf.
     *   
     *   Leaf:
     *   - contains exactly one data item and no children
     *   - therefore, the key array is of size 1 and the child array is not
     *     allocated.
     *
     *   Interior node:
     *   - contains 1 or 2 index values (which do NOT count as data items,
     *     searches must always go to the leaves!) and 2 or 3 children.
     *   - contains numIndexValues index values and numIndexValues+1 children
     *   - key array is always size 2 and child array is always size 3.
     *   - if the interior node has only 2 children, then:
     *       - it contains only 1 index value, which is stored in key[0]
     *       - child[0] is its left child
     *       - child[1] is its right child
     *   - if the interior node has 3 children, then:
     *       - it contains 2 index values, and the smaller index value is
     *         key[0] and the larger index value is key[1] --- that is,
     *         the key array is kept in sorted order.
     *       - child[0] is the leftmost child (values < key[0])
     *         child[1] is the middle child (values >= key[0] and < key[1])
     *         child[2] is the rightmost child (value >= key[1])
     *
     *************************************************************************
     *************************************************************************/

    private class TwoThreeNode {

        public TwoThreeNode parent; // A pointer to this node's parent
        public int numIndexValues; // The number of index values stored in this node.
        // ***An interior node has numIndexValues+1 children.*****
        public int[] key; // The data value in a leaf, or the index values(s)
        // in an interior node.
        public TwoThreeNode[] child; // The children of an interior node (null if leaf)


        /***********************************************************************
         * Constructor (version 1)
         *
         * Creates a new leaf for key k with parent p.
         *
         * Notice that the child array is not allocated (a leaf has no children)
         * and the key array has size 1 because we will only store ONE data item
         * in a leaf.  (A leaf NEVER becomes an interior node, so this is OK.)
         *
         ************************************************************************/
        public TwoThreeNode( int k, TwoThreeNode p ) {
            key = new int[1];
            key[0] = k;
            numIndexValues = 1;
            child = null;
            parent = p;
        }

        /***********************************************************************
         * Constructor (version 2)
         *
         *  Create a new interior node to contain one index value indexValue
         *  with parent p and two children left and right.
         *
         *  Notice that because a new interior node always has only two children,
         *  this node is currently using:
         *  - key[0], but not key[1]
         *  - child[0] (its left child) and child[1] (its right child), 
         *    but child[2] is unused.
         *
         *  An interior node could gain another index value and child in
         *  a split-and-push-up in an insertion, so we always allocate
         *  big enough key[] and child[] arrays to allow for that.
         *
         ************************************************************************/
        public TwoThreeNode( int indexValue, TwoThreeNode p,TwoThreeNode left, TwoThreeNode right )  {
            key = new int[2];
            key[0] = indexValue;
            numIndexValues = 1;
            child = new TwoThreeNode[3];
            child[0] = left;
            child[1] = right;
            child[2] = null;
            parent = p;
        }

        /***********************************************************************
         * The usual accessors and mutators, which you are NOT required to use.
         ***********************************************************************/
        public int getNumIndexValues() {
            return numIndexValues;
        }

        public int getKey( int index ) {
            return key[ index ];
        }

        public void setKey( int index, int newValue ) {
            key[ index ] = newValue;
        }

        public TwoThreeNode getParent() {
            return parent;
        }

        public void setParent( TwoThreeNode newParent ) {
            parent = newParent;
        }

        public TwoThreeNode getChild( int index ) {
            return child[ index ];
        }

        public void setChild( int index, TwoThreeNode newChild ) {
            child[ index ] = newChild;
        }

        /************************************************************
         *  isLeaf
         *
         *    Return true if the TwoThreeNode is a leaf; false
         *    otherwise.
         *
         *    A TwoThreeNode is a leaf if it has no children
         *    and if it has no children, then child is null;
         *    and child is null ONLY when there are no children.
         *
         **************************************************************/
        public boolean isLeaf() {
            return (child == null);
        }

        /************************************************************
         *  isInteriorNode
         *
         *    Return true if the TwoThreeNode is an interior node; false
         *    otherwise.
         *
         *    A TwoThreeNode is an interior if it has children
         *    and if it has children, then child is not null.
         *    (Child is null ONLY when there are no children.)
         *
         **************************************************************/
        public boolean isInteriorNode() {
            return (child != null);
        }

        /************************************************************
         *  parentChildPointersOK
         *
         *    A debugging method that helps sanity-check the 2-3 tree.
         *
         *    Returns true if, for "this" and all its descendants,
         *    each parent's child points back at its parent with its
         *    parent pointer; returns false only if a problem is detected.
         *
         *    The method does a traversal of the 2-3 tree starting
         *    at "this".  It checks if the children of "this" point
         *    at "this" with their parent pointers, and does the
         *    same check recursively at each child.  The base case
         *    of the recursion: if we're at a leaf, do nothing (return
         *    true) because they have no children, so no problem can
         *    happen at a leaf.
         *
         **************************************************************/
        public boolean parentChildPointersOK() {
            boolean pointersOK = true;
            if ( isInteriorNode() ) {
                for ( int i = 0; i < this.numIndexValues+1 && pointersOK; i++ ) {
                    pointersOK = pointersOK && ( child[i].parent == this );
                    if ( ! pointersOK ) {
                        System.out.print( "Parent / child pointer problem: " );
                        System.out.print( "parent contains " );
                        for ( int j = 0; j < numIndexValues; j++ )
                            System.out.print( key[j] + " " );
                        System.out.print( "\n    " + i + "th child contains " );
                        for ( int j = 0; j < child[i].numIndexValues; j++ )
                            System.out.print( child[i].key[j] + " " );
                        System.out.println();
                    }
                    pointersOK = pointersOK && ( child[i].parentChildPointersOK() );
                } // end for i
            } // end if child != null
            return pointersOK;
        } // end parentChildPointersOK

        /************************************************************
         *  valuesOK
         *
         *    A debugging method that helps sanity-check the 2-3 tree.
         *
         *    Returns true if, for "this" and all its descendants,
         *    each index value in an interior node (could be 1 or 2)
         *    is > the largest value stored in the child to its left
         *    and <= the smallest value stored in the child to its right,
         *    and is also > the largest data value stored in any leaf
         *    descendant of the child to its left and <= the smallest
         *    data value stored in any leaf descendant of its child to
         *    its right.
         *    Returns false only if a problem is detected.
         *
         *    The method does a traversal of the 2-3 tree starting
         *    at "this".  It checks each index value in "this" against
         *    
         *    at "this" with their parent pointers, and does the
         *    same check recursively at each child.  The base case
         *    of the recursion: if we're at a leaf, do nothing (return
         *    true) because they have no children, so no problem can
         *    happen at a leaf.
         *
         **************************************************************/
        public boolean valuesOK() {
            boolean valuesWork = true;
            if ( isInteriorNode() ) {
                if ( child[0].getMaxDataValue() < key[0]
                && key[0] <= child[1].getMinDataValue() ) {
                    if ( child[0].key[child[0].numIndexValues-1] < key[0]
                    && key[0] <= child[1].key[0] ) {
                        valuesWork = child[0].valuesOK() && child[1].valuesOK();
                        if ( numIndexValues == 2 ) {
                            if ( key[0] < key[1] ) {
                                if ( child[1].getMaxDataValue() < key[1]
                                && key[1] <= child[2].getMinDataValue() ) {
                                    if ( child[1].key[child[1].numIndexValues-1] < key[1]
                                    && key[1] <= child[2].key[0] ) {
                                        valuesWork = child[2].valuesOK();
                                    } else {
                                        System.out.println( "2nd index value " + key[1]
                                            + " is wrong with "
                                            + " left child index value "
                                            + child[1].key[child[1].numIndexValues-1]
                                            + " or right child index value "
                                            + child[2].key[0] );
                                        valuesWork = false;
                                    }
                                } else {
                                    System.out.println( "2nd index value " + key[1]
                                        + " is wrong with "
                                        + "max left leaf descendant "
                                        + child[1].getMaxDataValue()
                                        + " or min right leaf descendant "
                                        + child[2].getMinDataValue() );
                                    valuesWork = false;
                                }
                            } else {
                                System.out.println( "Index values are not in sorted order: key[0] = " + key[0]
                                    + " key[1] = " + key[1] );
                                valuesWork = false;
                            }
                        } // end if numIndexValue == 2
                    } else {
                        System.out.println( "1st index value " + key[0] + " is wrong with "
                            + " left child index value "
                            + child[0].key[child[0].numIndexValues-1]
                            + " or right child index value "
                            + child[1].key[0] );
                        valuesWork = false;
                    }
                } else {
                    System.out.println( "1st index value " + key[0] + " is wrong with "
                        + "max left descendant " + child[0].getMaxDataValue()
                        + " or min right descendant "
                        + child[1].getMinDataValue() );
                    valuesWork = false;
                }
            }
            return valuesWork;
        } // end valuesOK

        /************************************************************
         *  getMinDataValue
         *
         *    Return the smallest data item stored in any leaf
         *    descendant of "this".
         *
         *    Goes to the leftmost child until it reaches a leaf,
         *    and returns key[0] stored in the leaf.
         *    
         **************************************************************/
        private int getMinDataValue() {
            TwoThreeNode curr = this;

            while ( curr.isInteriorNode() ) {
                curr = curr.child[ 0 ];
            }
            return curr.key[ 0 ];
        } // end getMinDataValue

        /************************************************************
         *  getMaxDataValue
         *
         *    Return the largest data item stored in any leaf
         *    descendant of "this".
         *
         *    Goes to the rightmost child until it reaches a leaf,
         *    and returns key[0] stored in the leaf.
         *    
         **************************************************************/
        private int getMaxDataValue() {
            TwoThreeNode curr = this;

            while ( curr.isInteriorNode() ) {
                curr = curr.child[ curr.numIndexValues ];
            }
            return curr.key[ 0 ];
        } // end getMaxDataValue

    } // end class TwoThreeNode

    /**************************************************************
     **************************************************************
     * Back to class TwoThreeTree
     **************************************************************
     **************************************************************/

    private TwoThreeNode root;

    /**************************************************************
     * Constructor
     * 
     *    Creates an empty leaf-based 2-3 tree.
     **************************************************************/
    public TwoThreeTree() {
        root = null;
    }

    /************************************************************
     * searchToLeaf
     *    
     * PURPOSE: To return the leaf where a search for key searchKey
     *          ends in the 2-3 tree.
     *          If the tree is completely empty, return null.
     *
     **************************************************************/
    private TwoThreeNode searchToLeaf( int searchKey ) 
    {
        TwoThreeNode result = null;
        TwoThreeNode curr = root;
        if(root != null)
        {
            while(curr.isInteriorNode())
            {
                if(curr.numIndexValues == 2)
                {
                    if(searchKey < curr.key[KEY_MIN])
                    {
                        curr = curr.child[MIN_CHILD];
                    }
                    else if((curr.key[KEY_MIN] <= searchKey) && (searchKey < curr.key[KEY_MAX]))
                    {
                        curr = curr.child[MIDD_CHILD];
                    }
                    else
                    {
                        curr = curr.child[MAX_CHILD];
                    }
                }
                else 
                {
                    if(searchKey < curr.key[KEY_MIN])
                    {
                        curr = curr.child[LEFT_CHILD];
                    }
                    else
                    {
                        curr = curr.child[RIGHT_CHILD];
                    }
                }
            }
            result = curr;
        }
        return result;
    } // end searchToLeaf

    /************************************************************
     * search
     *
     *    Search for key searchKey in the 2-3 tree, returning
     *    true if searchKey is found and 
     *    false otherwise.
     *
     **************************************************************/
    public boolean search( int searchKey ) 
    {
        boolean found = false;
        TwoThreeNode curr = root;
        while(curr.isInteriorNode())
        {
            if(curr.numIndexValues == 2)
            {
                if(searchKey < curr.key[KEY_MIN])
                {
                    curr = curr.child[MIN_CHILD];
                }
                else if((curr.key[KEY_MIN] <= searchKey) && (searchKey < curr.key[KEY_MAX]))
                {
                    curr = curr.child[MIDD_CHILD];
                }
                else
                {
                    curr = curr.child[MAX_CHILD];
                }
            }
            else // may have to change this case if there are 3 index values (MAYBE)
            {
                if(searchKey < curr.key[KEY_MIN])
                {
                    curr = curr.child[LEFT_CHILD];
                }
                else
                {
                    curr = curr.child[RIGHT_CHILD];
                }
            }
        }

        if(curr.key[KEY_LEAF] == searchKey)
        {
            found = true;
        }
        return found;
    } // end search

    /************************************************************
     * insert
     *
     *    Insert new key newKey into the tree.
     *     - First, search for newKey.
     *     - We end up at a leaf.
     *     - If the leaf contains newKey, simply return (no duplicates!)
     *     - Otherwise, handle the insertion (including any splitting and
     *       pushing up required).
     *
     * Note: Was not able to complete splitAndPush() Method
     **************************************************************/
    public void insert( int newKey ) 
    {
        boolean added = false;
        TwoThreeNode curr = root;
        if(curr == null)
        { 
            root = new TwoThreeNode(newKey,null);
        }
        else if(curr.isLeaf() && (curr.key[KEY_LEAF] != newKey))
        {
            if(newKey < curr.key[KEY_LEAF])
            {
                TwoThreeNode left   = new TwoThreeNode(newKey,null);
                TwoThreeNode right  = new TwoThreeNode(curr.key[KEY_LEAF],null);
                TwoThreeNode parent = new TwoThreeNode(curr.key[KEY_LEAF],null,left,right);
                left.parent = parent;  //make a helper for this
                right.parent = parent; //make a helper for this
                root = parent;         //make a helper for this
            }
            else
            {
                TwoThreeNode left   = new TwoThreeNode(curr.key[KEY_LEAF],null);
                TwoThreeNode right  = new TwoThreeNode(newKey,null);
                TwoThreeNode parent = new TwoThreeNode(newKey,null,left,right);
                left.parent  = parent; //make a helper for this 
                right.parent = parent; //make a helper for this
                root = parent;         //make a helper for this
            }
        }
        else
        {
            TwoThreeNode searchNode = searchToLeaf(newKey);
            if(!search(newKey) && (searchNode != null))
            {
                if(searchNode.parent.numIndexValues == 1)
                {
                    if(newKey < searchNode.key[KEY_LEAF])
                    {
                        int temp = searchNode.parent.key[0];
                        searchNode.parent.key[KEY_MIN] = searchNode.key[KEY_LEAF];
                        searchNode.parent.key[KEY_MAX] = temp;
                        searchNode.parent.child[MAX_CHILD] = searchNode.parent.child[RIGHT_CHILD];
                        searchNode.parent.child[MIDD_CHILD]= searchNode;
                        searchNode.parent.child[MIN_CHILD] = new TwoThreeNode(newKey,searchNode.parent);
                    }
                    else
                    {
                        searchNode.parent.key[KEY_MAX] = newKey;
                        searchNode.parent.child[MAX_CHILD] = new TwoThreeNode(newKey,searchNode.parent);
                    }
                    searchNode.parent.numIndexValues++;
                }
                else
                {
                    TwoThreeNode currSplit = searchNode.parent;
                    if(currSplit.parent == null)
                    {
                        TwoThreeNode oldLeftChild = currSplit.child[MIN_CHILD];
                        TwoThreeNode oldMiddChild = currSplit.child[MIDD_CHILD];
                        TwoThreeNode oldRightChild= currSplit.child[MAX_CHILD];
                        TwoThreeNode newRoot;
                        TwoThreeNode newLeftParent;
                        TwoThreeNode newRightParent;
                        if(newKey < oldLeftChild.key[KEY_LEAF])
                        {
                            newRoot        = new TwoThreeNode(oldMiddChild.key[KEY_LEAF],null,null,null); //maybe make the scope bigger
                            newLeftParent  = new TwoThreeNode(oldLeftChild.key[KEY_LEAF],newRoot,null,null);
                            newRightParent = new TwoThreeNode(oldRightChild.key[KEY_LEAF],newRoot,null,null);

                            //set children-----------------------------------------------------------
                            newLeftParent.child[LEFT_CHILD] = new TwoThreeNode(newKey,newLeftParent);
                            newLeftParent.child[RIGHT_CHILD]= oldLeftChild;

                            newRightParent.child[LEFT_CHILD] = oldMiddChild;
                            newRightParent.child[RIGHT_CHILD]= oldRightChild;

                            newRoot.child[LEFT_CHILD] = newLeftParent;
                            newRoot.child[RIGHT_CHILD]= newRightParent;
                            //------------------------------------------------------------------------
                            //set parents------------------------
                            oldLeftChild.parent  = newLeftParent;
                            oldMiddChild.parent  = newRightParent;
                            oldRightChild.parent = newRightParent;
                            //-----------------------------------

                        }
                        else if((oldLeftChild.key[KEY_LEAF] <= newKey) && (newKey < oldRightChild.key[KEY_LEAF]))
                        {
                            newRoot = new TwoThreeNode(newKey,null,null,null);
                            newLeftParent  = new TwoThreeNode(oldMiddChild.key[KEY_LEAF],newRoot,null,null);
                            newRightParent = new TwoThreeNode(oldRightChild.key[KEY_LEAF],newRoot,null,null);
                            //set children-----------------------------------------------------------
                            newLeftParent.child[LEFT_CHILD]  = oldLeftChild;
                            newLeftParent.child[RIGHT_CHILD] = oldMiddChild;

                            newRightParent.child[LEFT_CHILD] = new TwoThreeNode(newKey,newRightParent);
                            newRightParent.child[RIGHT_CHILD]= oldRightChild;

                            newRoot.child[LEFT_CHILD]  = newLeftParent;
                            newRoot.child[RIGHT_CHILD] = newRightParent;
                            //------------------------------------------------------------------------
                            //set parents------------------------
                            oldLeftChild.parent  = newLeftParent;
                            oldMiddChild.parent  = newLeftParent;
                            oldRightChild.parent = newRightParent;
                            //-----------------------------------

                        }
                        else
                        {
                            newRoot = new TwoThreeNode(oldRightChild.key[KEY_LEAF],null,null,null);
                            newLeftParent = new TwoThreeNode(oldMiddChild.key[KEY_LEAF],newRoot,null,null);
                            newRightParent= new TwoThreeNode(newKey,newRoot,null,null);

                            //set children-----------------------------------------------------------
                            newLeftParent.child[LEFT_CHILD]  = oldLeftChild;
                            newLeftParent.child[RIGHT_CHILD] = oldMiddChild;

                            newRightParent.child[LEFT_CHILD] = oldRightChild;
                            newRightParent.child[RIGHT_CHILD]= new TwoThreeNode(newKey,newRightParent);

                            newRoot.child[LEFT_CHILD]  = newLeftParent;
                            newRoot.child[RIGHT_CHILD] = newRightParent;
                            //------------------------------------------------------------------------
                            //set parents------------------------
                            oldLeftChild.parent  = newLeftParent;
                            oldMiddChild.parent  = newLeftParent;
                            oldRightChild.parent = oldRightChild;
                            //-----------------------------------

                        }
                        root = newRoot;
                    }
                    else
                    {
                        //splitAndPush(currSplit); 
                    }
                }
            }
        }
    } // end insert

    /*
     * Note: was not able to complete method
     */
    private void splitAndPush(TwoThreeNode curr)
    {

    }

    /************************************************************
     *  printTree
     *
     *    Print an appropriate message if the tree is empty;
     *    otherwise, call a recursive method to print the
     *    first twenty keys in an inorder traversal.
     *    NOTE: index values are NOT printed, only real data values
     *          which are stored exclusively in the leaves are printed.
     *
     **************************************************************/
    public void printTree() 
    {
        printTree(root);
    } // end printTree

    private void printTree(TwoThreeNode curr)
    {
        if(printCount < 20)
        {
            if(curr.isLeaf())
            {
                System.out.println(curr.key[KEY_LEAF]);
                printCount++;
            }
            else
            {
                printTree(curr.child[LEFT_CHILD]);
                if(curr.numIndexValues == 2)
                {
                    printTree(curr.child[MIDD_CHILD]);
                }
                printTree(curr.child[RIGHT_CHILD]);
            }
            
        }
    }

    /************************************************************
     *  treeOK
     *
     *    A debugging method that sanity-checks the 2-3 tree.
     *
     *    It checks that each child of a parent points to its parent.
     *    (A recursive helper method does this check in a 
     *    traversal of the tree.)
     *
     *    It checks that each index value correctly differentiates
     *    between the two children on either side of it.
     *    (Another recursive helper method does this check in
     *    another traversal of the tree.)
     *
     *    It returns true only if NO problems are found; otherwise,
     *    if any problem is found, it returns false.
     *
     **************************************************************/
    public boolean treeOK() {
        boolean allOK = true; // An empty tree has NO problems!

        if ( root != null ) {
            if ( root.parent == null ) {
                if ( root.parentChildPointersOK() ) {
                    allOK = root.valuesOK();
                    if ( ! allOK )
                        System.out.println( "Error: something wrong with values" );
                } else {
                    allOK = false;
                    System.out.println( "ERROR: something wrong with parent/child pointers" );
                }
            } else {
                allOK = false;
                System.out.println( "ERROR: root's parent pointer is NOT null!" );
            }
        }
        return allOK;
    } // end treeOK

} // end class TwoThreeTree

/*
 * A5KoltonZac
 * COMP 2140  SECTION A02
 * INSTRUCTOR Guderian (A02)
 * ASSIGNMENT #5
 * Zachary Kolton 7838513
 * Nov. 22nd 2018
 *
 * PURPOSE: Implement a BST
 */
class BST {
    public int printCount = 0;
    /**************************************************************
     **************************************************************
     * class BSTNode - implements a binary search tree node
     **************************************************************
     **************************************************************/

    private class BSTNode {
        public int item;
        public BSTNode left;
        public BSTNode right;

        /**************************************************************
         * Constructor
         *
         *    Creates a leaf containing the new item.
         *
         **************************************************************/
        public BSTNode( int newItem ) {
            item = newItem;
            left = null;
            right = null;
        }

        /* You don't need the following methods, but you can use them if you want to. */
        public int getItem() { return item; }

        public BSTNode getLeft() { return left; }

        public BSTNode getRight() { return right; }

        public void setLeft( BSTNode l ) { left = l; }

        public void setRight( BSTNode r ) { right = r; }
        // No, you can't have setItem(). (You really shouldn't use it in this application.)

    } // end class BSTNode

    /**************************************************************
     **************************************************************
     * Back to class BST
     **************************************************************
     **************************************************************/

    private BSTNode root;

    /************************************************************
     *  Constructor
     *
     *    Create an empty BST.
     *    
     **************************************************************/
    public BST() 
    {
        root = null;
    }

    /************************************************************
     *  insert
     *
     *    Insert newKey into the BST, if newKey is not already
     *    in the tree. (No duplicates!)
     *    
     **************************************************************/
    public void insert( int newKey )
    {
        if(root == null)
        {
            root = new BSTNode(newKey);
        }
        else
        {
            BSTNode curr = root;
            boolean added = false;
            while((!added) && (curr.item != newKey))
            {
                if(curr.item > newKey)
                {
                    if(curr.left == null)
                    {
                        curr.left = new BSTNode(newKey);
                        added = true;
                    }
                    else
                    {
                        curr = curr.left;
                    }
                }
                else
                {
                    if(curr.right == null)
                    {
                        curr.right = new BSTNode(newKey);
                        added = true;
                    }
                    else
                    {
                        curr = curr.right;
                    }
                }
            }
        }

    } // end insert

    /************************************************************
     *  search
     *
     *    Search for searchKey in a BST.
     *    
     *    Return true if searchKey is found; false otherwise.
     *
     **************************************************************/
    public boolean search( int searchKey ) 
    {
        boolean found = false;
        BSTNode curr = root;
        while((!found) && (curr != null))
        {
            if(curr.item == searchKey)
            {
                found = true;
            }
            else
            {
                if(curr.item > searchKey)
                {
                    if(curr.left == null)
                    {
                        curr.left = new BSTNode(searchKey);
                        found = true;
                    }
                    else
                    {
                        curr = curr.left;
                    }
                }
                else
                {
                    if(curr.right == null)
                    {
                        curr.right = new BSTNode(searchKey);
                        found = true;
                    }
                    else
                    {
                        curr = curr.right;
                    }
                }
            }
        }
        return found;
    } // end search

    /************************************************************
     *  printTree
     *
     *    Print an appropriate message if the tree is empty;
     *    otherwise, call a recursive method to print the
     *    first twenty keys in an inorder traversal.
     *
     **************************************************************/
    public void printTree() 
    {
        if(root == null)
        {
            System.out.println("the tree is empty");
        }
        else
        {
            printTree(root);
        }
    } // end printTree

    private void printTree(BSTNode curr)
    {
        if((curr != null) && (printCount < 20))
        {
            printCount++;
            printTree(curr.left);
            System.out.println(curr.item);
            printTree(curr.right);
        }
    }

} // end class BST

