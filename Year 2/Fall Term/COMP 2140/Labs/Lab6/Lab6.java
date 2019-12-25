import java.util.*; 
import java.io.*;

/*
 * Lab6
 * 
 * COMP 2140 SECTION
 * 
 * INSTRUCTORS Helen Cameron and Rob Guderian
 * LAB 6
 * AUTHOR     (solutions)
 * VERSION    27 Nov 2018
 *
 * Purpose: tests five different sorting methods: 
 *   - An insertion sort,
 *   - A recursive merge sort exactly as described in class,
 *   - A recursive quick sort that uses the
 *     median-of-three technique for choosing
 *     its pivot, and its base cases are lists
 *     of size 0 or 1 (do nothing) and 2 (swap if necessary), 
 *   - A hybrid quick sort, which uses insertion sort to sort
 *     any array (or part of an array) of fewer than BREAKPOINT items, and
 *   - A heap sort.
 */

public class Lab6 {

    // Control the testing
    private static final int ARRAY_SIZE=5;
    private static final int SAMPLE_SIZE = 20;

    // Control the randomness
    private static final int NUM_SWAPS = ARRAY_SIZE / 4;
    private static Random generator = new Random();

    // Control the breakpoint for hybrid quick sort
    // (fewer than breakpoint items will be sorted by insertion sort,
    // rather than by further calls to hybrid quick sort).
    private static int breakPoint = 50;

    /*********** main and the method it calls *************************/
    /*******************************************************************
     * main
     *
     * Purpose: Print out "bookend" messages and call the method does
     *          all the testing of the sorting algorithms.
     *
     ******************************************************************/
    public static void main( String[] args ) {
        System.out.println( "\n\nCOMP 2140 A1 Sample Solution --- Fall 2018\n" );

        testSorts();

        System.out.println( "\nProcessing ends normally\n" );
    } // end main

    /*******************************************************************
     * testSorts
     *
     * Purpose: Run each sorting algorithm SAMPLE_SIZE times,
     *          on an array of size ARRAY_SIZE with NUM_SWAPS
     *          random swaps performed on it.
     *          Store the timings of an algorithm in an array. Then
     *          compute the mean and the standard deviation of the 
     *          timings for each sorting algorithm.  Finally,
     *          perform a z-test between the timing samples for
     *          each of the pairs of sorts.
     *
     ******************************************************************/
    private static void testSorts() {
        int[] nums = new int[ ARRAY_SIZE ];

        long start, stop, elapsedTime;  // Time how long each sort takes.
        long[] insertionSortTime = new long[ SAMPLE_SIZE ];
        long[] mergeSortTime = new long[ SAMPLE_SIZE ];
        long[] quickSortTime = new long[ SAMPLE_SIZE ];
        long[] hybridQuickSortTime = new long[ SAMPLE_SIZE ];
        long[] heapSortTime = new long[ SAMPLE_SIZE ];
        Heap myHeap; // for heap sort only

        // Waste some time at the beginning so Java (or the OS) can get through
        // whatever game it is playing while we are doing work.

        for ( int i = 0; i < SAMPLE_SIZE; i++ ) {
            fillArray( nums, NUM_SWAPS );
            start = System.nanoTime();
            insertionSort( nums );
            stop = System.nanoTime();
            checkArray(nums, "Insertion sort");
            elapsedTime = stop - start;
            insertionSortTime[i] = elapsedTime;
        } // end for

        // Now the real work begins.

        for ( int i = 0; i < SAMPLE_SIZE; i++ ) {
            fillArray( nums, NUM_SWAPS );
            start = System.nanoTime();
            insertionSort( nums );
            stop = System.nanoTime();
            checkArray(nums, "Insertion sort");
            elapsedTime = stop - start;
            insertionSortTime[i] = elapsedTime;
        } // end for

        for ( int i = 0; i < SAMPLE_SIZE; i++ ) {
            fillArray( nums, NUM_SWAPS );
            start = System.nanoTime();
            mergeSort( nums );
            stop = System.nanoTime();
            checkArray(nums, "Merge sort");
            elapsedTime = stop - start;
            mergeSortTime[i] = elapsedTime;
        } // end for

        for ( int i = 0; i < SAMPLE_SIZE; i++ ) {
            fillArray( nums, NUM_SWAPS );
            start = System.nanoTime();
            quickSort( nums );
            stop = System.nanoTime();
            checkArray(nums, "Quick sort");
            elapsedTime = stop - start;
            quickSortTime[i] = elapsedTime;
        } // end for

        for ( int i = 0; i < SAMPLE_SIZE; i++ ) {
            fillArray( nums, NUM_SWAPS );
            start = System.nanoTime();
            hybridQuickSort( nums );
            stop = System.nanoTime();
            checkArray(nums, "Hybrid quick sort");
            elapsedTime = stop - start;
            hybridQuickSortTime[i] = elapsedTime;
        } // end for

        for ( int i = 0; i < SAMPLE_SIZE; i++ ) {
            fillArray( nums, NUM_SWAPS );
            start = System.nanoTime();
            myHeap = new Heap( nums );
            myHeap.sort();
            stop = System.nanoTime();
            checkArray(nums, "Heap sort");
            elapsedTime = stop - start;
            heapSortTime[i] = elapsedTime;
        } // end for

        System.out.println("Array size: " + ARRAY_SIZE + " number of swaps: " + NUM_SWAPS);
        System.out.println("Number of trials of each sort: " + SAMPLE_SIZE );
        System.out.println("Breakpoint for hybrid quick sort: " + breakPoint + "\n");

        // Stats for each run
        System.out.println("\nInsertion sort mean: " + Stats.mean(insertionSortTime)
            + " ns, std: " + Stats.standardDeviation(insertionSortTime));
        System.out.println("Merge sort mean: " + Stats.mean(mergeSortTime)
            + " ns, std: " + Stats.standardDeviation(mergeSortTime));
        System.out.println("Quick sort mean: " + Stats.mean(quickSortTime)
            + " ns, std: " + Stats.standardDeviation(quickSortTime));
        System.out.println("Hybrid quick sort mean: " + Stats.mean(hybridQuickSortTime)
            + " ns, std: " + Stats.standardDeviation(quickSortTime));
        System.out.println("Heap sort mean: " + Stats.mean(heapSortTime)
            + " ns, std: " + Stats.standardDeviation(heapSortTime));

        System.out.println("\nZ score for Merge sort and Insertion Sort: "
            + Stats.zTest(mergeSortTime, insertionSortTime));
        System.out.println("Z score for Quick sort and Insertion Sort: "
            + Stats.zTest(quickSortTime, insertionSortTime));
        System.out.println("Z score for Hybrid quick sort and Insertion Sort: "
            + Stats.zTest(hybridQuickSortTime, insertionSortTime));
        System.out.println("Z score for Heap sort and Insertion Sort: "
            + Stats.zTest(heapSortTime, insertionSortTime));
        System.out.println("Z score for Quick sort and Merge Sort: "
            +Stats.zTest(quickSortTime, mergeSortTime));
        System.out.println("Z score for Hybrid Quick sort and Merge Sort: "
            +Stats.zTest(hybridQuickSortTime, mergeSortTime));
        System.out.println("Z score for Heap sort and Merge Sort: "
            +Stats.zTest(heapSortTime, mergeSortTime));
        System.out.println("Z score for Quick sort and Heap Sort: "
            +Stats.zTest(quickSortTime, heapSortTime));
        System.out.println("Z score for HybridQuick sort and Heap Sort: "
            +Stats.zTest(hybridQuickSortTime, heapSortTime));
        System.out.println("Z score for Hybrid Quick sort and Quick Sort: "
            +Stats.zTest(hybridQuickSortTime, quickSortTime));
    } // end testSorts

    /************************* Insertion sort ********************************/
    
    /*******************************************************************
     * insertionSort --- public driver method
     *
     * Purpose: Call the private insertionSort method that does all the
     *          work, passing the correct values to its extra parameters
     *          (indices start and end) to make it sort the whole array.
     *
     ******************************************************************/
    public static void insertionSort( int[] nums ) {
        insertionSort( nums, 0, nums.length );
    }

    /*******************************************************************
     * insertionSort --- private non-recursive helper method
     *
     * Purpose: Does an insertion sort ONLY on nums[start], nums[start+1], 
     *          ..., nums[end-1] (the rest of the array is untouched).
     *
     ******************************************************************/
    private static void insertionSort( int[] nums, int start, int end ) {
        int siftVal; // The value being sifted down
        int emptyIndex; // The empty position during the sifting

        // BE CAREFUL.  THIS LOOP CANNOT USE 1 OR nums.length
        // (it must use start+1 and end, instead).
        for ( int i = start+1; i < end; i++ ) {
            // nums[start], ..., nums[i-1] is a sorted sub-list.
            // Sift nums[i] down to its correct position in the sorted sub-list by
            // moving larger values in the sorted sub-list one position to the right.
            siftVal = nums[i];
            emptyIndex = i;
            // BE CAREFUL.  THIS LOOP CANNOT USE 0, (it must use start, instead).
            while ( emptyIndex > start && nums[emptyIndex-1] > siftVal ) {
                // Move a larger value one position right.
                nums[emptyIndex] = nums[emptyIndex-1]; // larger value moves into the empty spot
                emptyIndex--; // empty spot has moved one position left
            } // end while
            // Now place siftVal where it belongs in the sorted sub-list (in the empty spot)
            nums[emptyIndex] = siftVal;
        } // end for
    } // end insertionSort

    /************************* Merge sort ********************************/
    /*******************************************************************
     * mergeSort --- public driver method
     *
     * Purpose: Call the private mergeSort method that does all the
     *          work, passing the correct values to its extra parameters
     *          (indices start and end, and the temporary array used in the
     *          merge step) to make it sort the whole array.
     *
     * Note: Array temp is used only by the merge method that merges
     *       two sorted sublists into one sorted list.  However, for
     *       efficiency reasons, it is created once in the public
     *       driver method and passed around between all the methods.
     *
     ******************************************************************/
    public static void mergeSort( int[] nums ) {
        if ( nums != null ) {
            int[] temp = new int[ nums.length ];
            mergeSort( nums, 0, nums.length, temp );
        }
    } // end mergeSort (public driver method)

    /*******************************************************************
     * mergeSort --- private recursive helper method
     *
     * Purpose: Does an merge sort ONLY on nums[start], nums[start+1], 
     *          ..., nums[end-1] (the rest of the array is untouched).
     *
     * Note: Array temp is used only by the merge method that merges
     *       two sorted sublists into one sorted list.  However, for
     *       efficiency reasons, it is created once in the public
     *       driver method and passed around between all the methods.
     *
     ******************************************************************/
    private static void mergeSort( int[] nums, int start, int end, int[] temp ) {
        if ( end - start > 1 ) {
            // Recursive case: if 2 or more items, we have work to do!
            int mid = start + (end - start) / 2; // middle index between start and end
            mergeSort( nums, start, mid, temp ); // merge sort first half
            mergeSort( nums, mid, end, temp ); // merge sort second half
            merge( nums, start, mid, end, temp ); // merge two sorted halves into one sorted list
        }
        // Base case: Do nothing if 0 items or only 1 item to sort
    } // end mergeSort (private recuvsive method)

    /*******************************************************************
     * merge --- private non-recursive helper method
     *
     * Purpose: Merges the two sorted sublists nums[start], ..., nums[mid-1]
     *          and nums[mid], ..., nums[end-1] into ONE sorted sublist
     *          in temp[start], ..., temp[end-1].  Then it copies
     *          temp[start], ..., temp[end-1] back into nums[start], ..., nums[end-1].
     *          (The rest of the array is untouched.)
     *
     * Note: Array temp is used only by this method. However, for
     *       efficiency reasons, it is created once in the public
     *       driver method and passed around between all the methods.
     *       (Creating arrays takes a long time, so only do it once!)
     *
     ******************************************************************/
    private static void merge( int[] nums, int start, int mid, int end, int[] temp ) {
        int currL = start; // index of current item in left half
        int currR = mid; // index of current item in right half

        // Move the items, one at a time in sorted order, from the two sorted halves
        // in nums into temp
        for ( int currT = start; currT < end; currT++ ) {
            // Decide whether the next item should come from the left half
            // or the right half
            if ( currL < mid &&
            ( currR >= end || nums[currL] < nums[currR] ) ) {
                // If there are remaining items in the left half AND
                // either there are no remaining items in the right half
                // or the current item in the left half is smaller than
                // the current item in the right half, then move the
                // current item from the left half into temp.
                temp[ currT ] = nums[ currL ];
                currL++;
            } else { // Otherwise, move the current item from the right half into temp.
                temp[ currT ] = nums[ currR ];
                currR++;
            }
        } // end for

        // Copy the one sorted list from temp back into nums
        // (this could be a simple for-loop instead).
        System.arraycopy( temp, start, nums, start, end-start );

    } // end merge

    /************************* Quick sort and Hybrid quick sort ********************************/
    /*******************************************************************
     * quickSort --- public driver method
     *
     * Purpose: Call the private quickSort method that does all the
     *          work, passing the correct values to its extra parameters
     *          (indices start and end) to make it sort the whole array.
     *
     ******************************************************************/
    public static void quickSort( int[] nums ) {
        if ( nums != null )
            quickSort( nums, 0, nums.length );
    } // end quickSort (public driver method)

    /*******************************************************************
     * quickSort --- private recursive helper method
     *
     * Purpose: Does a quick sort ONLY on nums[start], nums[start+1], 
     *          ..., nums[end-1] (the rest of the array is untouched).
     *
     * Base cases: If there are 0 items or only 1 item, the list is
     *             already sorted.
     *             If there are 2 items to sort, then swap them if they
     *             are out of order. (Needed because we choose the pivot
     *             using the median-of-three method, which requires at
     *             least three items.)
     *
     ******************************************************************/
    private static void quickSort( int[] nums, int start, int end ) {
        int pivotPosn; // Where pivot ends up after partitioning is done

        if ( (end - start) == 2 ) { // One of the base cases: just two items to sort
            // If the two items are out of order, swap them
            if ( nums[ start] > nums[ start + 1 ] ) {
                swap( nums, start, start+1 );
            }
        } else if ( (end - start) > 2 ) { // Recursive case
            choosePivot( nums, start, end ); // Choose pivot and put into nums[start]
            pivotPosn = partition( nums, start, end ); // Partition into smalls;pivot;bigs
            quickSort( nums, start, pivotPosn ); // Quick sort smalls
            quickSort( nums, pivotPosn+1, end ); // Quick sort bigs
        } // end if-elseif 
        // Base case: Do nothing if 0 items or just 1 item
    } // end quickSort (private recursive method)

    /*******************************************************************
     * hybridQuickSort --- public driver method
     *
     * Purpose: Call the private hybridQuickSort method that does all the
     *          work, passing the correct values to its extra parameters
     *          (indices start and end) to make it sort the whole array.
     *
     ******************************************************************/
    public static void hybridQuickSort( int[] nums ) {
        hybridQuickSort( nums, 0, nums.length );
    }

    /*******************************************************************
     * hybridQuickSort --- private recursive helper method
     *
     * Purpose: Does quick sort ONLY on nums[start], nums[start+1], 
     *          ..., nums[end-1] (the rest of the array is untouched).
     *
     * Base cases: If there are fewer than BREAKPOINT items, then
     *             they are sorted using an insertion sort (not
     *             using further calls to hybridQuickSort).
     *
     ******************************************************************/
    private static void hybridQuickSort( int[] nums, int start, int end ) {
        int pivotPosn; // Where pivot ends up after partitioning is done

        if ( (end - start) < breakPoint ) {
            insertionSort( nums, start, end );
        } else { // Recursive case
            choosePivot( nums, start, end ); // Choose pivot and put into nums[start]
            pivotPosn = partition( nums, start, end ); // Partition into smalls;pivot;bigs
            hybridQuickSort( nums, start, pivotPosn ); // Hybrid quick sort smalls
            hybridQuickSort( nums, pivotPosn+1, end ); // Hybrid quick sort bigs
        } // end if-else
    } // end hybridQuickSort (private recursive method)

    /*******************************************************************
     * choosePivot: 
     *
     * Purpose: Choose one of the items in nums[start], ..., nums[end-1]
     *          to be the pivot, and swap the pivot into nums[start].
     *
     * Methodology: Figure out which item would be in the middle if you 
     *              sorted the three items nums[start], nums[mid], and 
     *              nums[end-1] --- that's the median of the three items
     *              and that's our pivot!
     *
     * Efficiency: Try to do as few comparisons as possible to find the
     *             median.  (This implementation does at most 3
     *             comparisons to find the median.)
     *
     ******************************************************************/
    private static void choosePivot( int[] nums, int start, int end ) {
        int mid = start + (end - start) / 2; // the middle index between indices start and end

        if ( nums[mid] <= nums[start] ) {
            if ( nums[end-1] <= nums[mid] ) {
                // nums[end-1] <= nums[mid] <= nums[start]
                // nums[mid] is the median
                swap( nums, start, mid );
            } else if ( nums[end-1] <= nums[start] ) {
                // nums[mid] < nums[end-1] <= nums[start]
                // nums[end-1] is the median
                swap( nums, start, end-1 );
            } // end if-else-if
            // else nums[mid] <= nums[start] < nums[end-1]
            // nums[start] is the median, so we don't have to do anything
        } else { // nums[start] < nums[mid]
            if ( nums[mid] <= nums[end-1] ) { // nums[start] < nums[mid] <= nums[end-1]
                // nums[mid] is the median
                swap( nums, start, mid );
            } else if ( nums[start] <= nums[end-1] ) { // nums[start] <= nums[end-1] < nums[mid]
                // nums[end-1] is the median
                swap( nums, start, end-1 );
            } // end if-else-if
            // else nums[end-1] < nums[start] < nums[mid]
            // nums[start] is the median, so we don't have to do anything
        } // end if-else
    } // end choosePivot

    /*******************************************************************
     * partition 
     *
     * Purpose: Partition nums[start], ..., nums[end-1] into the
     *          the following form:
     *          items < pivot (smalls), pivot, items >= pivot (bigs)
     *          Note that the smalls and the bigs are not necessarily
     *          in sorted order.
     *          (Really: swap items with one another until this form
     *          is achieved, using one left-to-right pass through
     *          the items.)
     *          Return the position that the pivot ends up in, after
     *          the correct form is achieved.
     *
     ******************************************************************/
    private static int partition( int[] nums, int start, int end ) {
        int bigStart = start+1; // index of first item in bigs (if any)
        int pivot = nums[start]; // for convenience, not really necessary

        for ( int curr = start+1; curr < end; curr++ ) {
            // Current state of the array:
            // nums[start] contains the pivot;
            // nums[start+1],...,nums[bigStart-1] contains processed items < pivot;
            // nums[bigStart],...,nums[curr-1] contains processed items >= pivot;
            // nums[curr],...,nums[end-1] contains items we haven't processed yet.
            // Now process nums[curr].
            if ( nums[curr] < pivot ) { // nums[curr] belongs in the smalls.
                swap( nums, bigStart, curr );
                bigStart++;
            } // end if
            // If nums[curr] >= pivot, it belongs in the bigs: do NOTHING!
        } // end for

        // Put the pivot into its correct position between the smalls and the bigs,
        // by swapping it with the last item in the smalls.
        swap( nums, start, bigStart-1 );

        // Return the final position of the pivot.
        return bigStart-1;
    } // end partition

    /****************** Other miscellaneous methods ********************/
    
    /*******************************************************************
     * swap 
     *
     * Purpose: Swap the items stored in positions i and j in array nums.
     *
     ******************************************************************/
    private static void swap( int[] nums, int i, int j ) {
        int temp = nums[ i ];
        nums[ i ] = nums[ j ];
        nums[ j ] = temp;
    } // end swap

    /*******************************************************************
     * isSorted 
     *
     * Purpose: Return true if the input array nums is sorted into 
     *          ascending order; return false otherwise.
     *
     * Idea: If every item is <= to the item immediately after it,
     *       then the whole list is sorted.
     *
     ******************************************************************/
    public static boolean isSorted( int[] nums ) { 
        boolean sorted = true;

        // Loop through all adjacent pairs in the
        // array and check if they are in proper order.
        // Stops at first problem found.
        for ( int i = 1; sorted && (i < nums.length); i++ ) 
            sorted = nums[i-1] <=  nums[i];
        return sorted;
    } // end method isSorted

    /*******************************************************************
     * checkArray 
     *
     * Purpose: Print an error message if the array nums is not 
     *          correctly sorted into ascending order.
     *          (If the array is correctly sorted, checkArray does nothing.)
     *
     ******************************************************************/
    private static void checkArray(int[] nums, String sortType) {
        if ( !isSorted( nums ) )
            System.out.println( sortType + " DID NOT SORT CORRECTLY *** ERROR!!" );
    }

    /*******************************************************************
     * fillArray
     *
     * Purpose: Fills array nums with 0 to nums.length-1, then
     *          does numberOfSwaps swaps of randomly-chosen positions
     *          in the array.
     *
     ******************************************************************/
    public static void fillArray( int[] nums, int numberOfSwaps ) {
        for ( int i = 0; i < nums.length; i++ ) {
            nums[i] = i;
        } // end for

        for ( int count = 0; count < numberOfSwaps; count++ ) {
            int i = generator.nextInt( nums.length );
            int j = generator.nextInt( nums.length );
            swap( nums, i, j );
        }
    } // end fillArray

} // end class Lab6

//===============================================================================
// Class Stats:
//     - The same Stats class you used in Assignment 1
class Stats {
    /*
     * Creates a mean based from a passed array
     */
    public static double mean(long data[]) {
        double sum = 0;
        for (int i = 0; i < data.length; i++)
            sum += data[i];
        return sum / (double)data.length;
    }

    /*
     * Creates a standard deviation from a passed array
     */
    public static double standardDeviation(long data[]) {
        double mean = mean(data);

        double summation = 0;
        for (int i = 0; i < data.length; i++) {
            summation += Math.pow(data[i] - mean, 2);
        }
        return Math.sqrt(1/(double)data.length * summation);
    }

    /*
     * Return a z-statistic, difference, reported in standard deviations
     */
    public static double zTest(long left[], long right[]) {
        return (mean(left) - mean(right))/
        Math.sqrt(Math.pow(standardDeviation(left),2) + Math.pow(standardDeviation(right),2));
    }
} // end class Stats

//===============================================================================
// Class Heap:
//   - Implements a max heap:
//             - A larger value is a higher priority.
//             - "In heap order" means parent's priority >= child's priority.
class Heap {

    private int[] heap;
    private int heapSize;

    /*******************************************************************
     * Constructor 1
     *
     * Purpose: Creates an empty heap that can hold up to size items.
     *
     ******************************************************************/
    public Heap( int size ) {
        heap = new int[ size ];
        heapSize = 0;
    }

    /*******************************************************************
     * Constructor 2
     *
     * Purpose: Create a new heap using existing array a as the heap array.
     *
     *          Note that array a is already completely filled
     *          with randomly-ordered items, so it has to be put into
     *          heap order (which is what heapify() does).
     *
     * Note: This is the constructor to use if you want to heap sort
     *       an array of integers!  It "shares" the array to be 
     *       sorted with the heap.
     *
     ******************************************************************/
    public Heap( int[] a ) {
        heap = a;
        heapSize = heap.length;
        heapify();
    }

    /*******************************************************************
     * swap
     *
     * Purpose: Swap heap[i] and heap[j].
     *
     * Note: Since we're in the heap class, we will always be swapping
     *       within the array heap.  So the array doesn't need to be
     *       a parameter of this method.  This is an instance method
     *       and so has direct access to the array already.
     *
     ******************************************************************/
    private void swap( int i, int j ) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    } // end swap

    /*******************************************************************
     * isEmpty
     *
     * Purpose: Return true if the heap is empty; return false otherwise.
     *
     ******************************************************************/
    private boolean isEmpty() {
        return heapSize == 0;
    }

    /*******************************************************************
     * parentIndex
     *
     * Purpose: Return the index of the parent of the item at index i.
     *
     * Note: Should only be called on a non-root position --- it doesn't
     *       check if index i _has_ a parent or not.
     *
     ******************************************************************/
    private int parentIndex( int i ) 
    {
        return (i-1)/2;
    }

    /*******************************************************************
     * leftChildIndex
     *
     * Purpose: Return the index of the left child of the item at index i.
     *
     * Note: Doesn't check if there is a left child, just computes the index.
     *
     ******************************************************************/
    private int leftChildIndex( int i ) {
        return (2*i) + 1;
    }

    /*******************************************************************
     * rightChildIndex
     *
     * Purpose: Return the index of the right child of the item at index i.
     *
     * Note: Doesn't check if there is a right child, just computes the index.
     *
     ******************************************************************/
    private int rightChildIndex( int i ) {
        return (2*i) + 2;
    }

    /*******************************************************************
     * siftDown
     *
     * Purpose: Sift down an item that may not be heap-ordered with 
     *          respect to its children (but no other problems with 
     *          the heap order exist among this item and its descendants).
     *
     * This method swaps the dis-ordered item with its highest-priority child
     * until this item has equal or higher priority than both its children (or its
     * only child, if it has just one child) OR it is in a leaf.
     *
     ******************************************************************/
    private void siftDown( int curr ) 
    {
        boolean doneSift = false;
        while(!doneSift) //maybe put noChildren here
        {
            int leftIndex = leftChildIndex(curr);
            int rightIndex= rightChildIndex(curr);
            boolean twoChildren =  (leftIndex < heapSize) &&  (rightIndex < heapSize);
            boolean leftOnly    =  (leftIndex < heapSize) && !(rightIndex < heapSize);
            boolean rightOnly   = !(leftIndex < heapSize) &&  (rightIndex < heapSize);
            if(twoChildren && ((heap[curr] < heap[leftIndex]) && (heap[curr] < heap[rightIndex])))
            {
                if(heap[leftIndex] > heap[rightIndex])
                {
                    swap(curr,leftIndex);
                    curr = leftIndex;
                }
                else
                {
                    swap(curr,rightIndex);
                    curr = rightIndex;
                }
            }
            else if(leftOnly && (heap[curr] < heap[leftIndex]))
            {
                swap(curr,leftIndex);
                curr = leftIndex;
            }
            else if(rightOnly && (heap[curr] < heap[rightIndex]))
            {
                swap(curr,rightIndex);
                curr = rightIndex;
            }
            else
            {
                doneSift = true;
            }
        }
    } // end siftDown

    /*******************************************************************
     * deleteMax
     *
     * Purpose: Remove and return the highest-priority item.
     *
     * Note: Returns Integer.MIN_VALUE if there are no items in the heap.
     *          (Must leave a proper heap!)
     *
     ******************************************************************/
    public int deleteMax() 
    {
        int result = Integer.MIN_VALUE;
        int curr = 0;
        if(!isEmpty())
        {
            result = heap[0];
            heap[0] = heap[heapSize-1];
            heapSize--;
            siftDown(curr);
            
        }
        return result;
    } // end deleteMax

    /*******************************************************************
     * heapify
     *
     * Purpose: Turn an array of unordered items into a heap-ordered array.
     *
     * Note: heap-ordered means parent's priority is >= child's priority
     *       for ALL parents and children in the tree.
     *
     ******************************************************************/
    private void heapify() 
    {
        
        for(int i = parentIndex(heapSize-1); i >= 0; i--)
        {
            siftDown(i);
        }
    } // end heapify

    /*******************************************************************
     * sort
     *
     * Purpose: Sort the items in the heap until they are in ascending
     *          order in the array (and not a heap anymore).
     *
     ******************************************************************/
    // Sorts the items in the heap until they are in ascending order
    // in the array (and not a heap anymore).
    public void sort() 
    {
        heapify();
        
        for(int i = heapSize-1; i>0; i--)
        {
            int temp = deleteMax();
            heap[heapSize-1] = temp;
        }
        printArray();
    } // end sort

    private void printArray()
    {
        String s = "[";
        for(int i = 0; i < 5; i++)
        {
            s+= heap[i]+" ";
        }
        System.out.println(s+"]");
    }

} // end class Heap
