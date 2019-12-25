
/**
 * Write a description of class TwoThreeTreeTesting here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TwoThreeTreeTesting
{
    public static void main(String[] args)
    {
        test1(); //inserting and using the search() method 
    }

    private static void test1()
    {
        TwoThreeTree test1 = new TwoThreeTree();
        System.out.println("------------------------------------");
        System.out.println("Inserting...");
        test1.insert(0);
        test1.insert(2);
        test1.insert(-1);
        test1.insert(3);
        test1.printTree();
        System.out.println("\nInserting done");
        System.out.println("------------------------------------");
    }

}

class TwoThreeTree
{
    public static final int KEY_LEAF = 0;
    public static final int KEY_MIN  = 0;
    public static final int KEY_MAX  = 1;

    public static final int LEFT_CHILD  = 0;
    public static final int MIN_CHILD   = 0;
    public static final int RIGHT_CHILD = 1;
    public static final int MIDD_CHILD  = 1;
    public static final int MAX_CHILD   = 2;
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

        /**
         * Constructor 2
         * Interior Node
         */
        public TwoThreeNode( int indexValue, TwoThreeNode p,TwoThreeNode left, TwoThreeNode right )  
        {
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

        public boolean isLeaf() {
            return (child == null);
        }

        public boolean isInteriorNode() {
            return (child != null);
        }

        private int getMinDataValue() {
            TwoThreeNode curr = this;
            while ( curr.isInteriorNode() ) {
                curr = curr.child[ 0 ];
            }
            return curr.key[ 0 ];
        } // end getMinDataValue

        private int getMaxDataValue() {
            TwoThreeNode curr = this;
            while ( curr.isInteriorNode() ) {
                curr = curr.child[ curr.numIndexValues ];
            }
            return curr.key[ 0 ];
        }

    } // end class TwoThreeNode
    private TwoThreeNode root;

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
    private TwoThreeNode searchToLeaf( int searchKey ) {
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
    public boolean search( int searchKey ) {
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
     **************************************************************/
    public void insert( int newKey ) 
    {
        boolean added = false;
        TwoThreeNode curr = root;
        System.out.println("\nInserting: "+ newKey);
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
                        splitAndPush(currSplit); //if this isnt working it MAY be because of the numIndexValues
                    }
                }
            }
        }

    } // end insert
    
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
    public void printTree() {
        printTree(root);
    } // end printTree

    private void printTree(TwoThreeNode curr)
    {
        if(curr.isLeaf())
        {
            System.out.println(curr.key[KEY_LEAF]);
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
