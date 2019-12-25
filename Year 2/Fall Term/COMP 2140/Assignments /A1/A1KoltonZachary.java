import java.util.Arrays;
/**
 * A1KoltonZachary
 * 
 * COMP 2140 SECTION A02
 * Instructor: Mr. Guderian 
 * Assignment #1
 * @ZacKolton7838513
 * @Oct12/2018
 * 
 * Purpose: To compare 4 different sorting algorithms 
 */
public class A1KoltonZachary
{
    public static final int BREAKPOINT = 50;
    public static final int MAX_ITEMS  = 10000;
    public static final int TIMES_RUN = 100;
    public static void main(String[] args)
    {
        int[] testFinal = makeArray(MAX_ITEMS);
        System.out.println("Max Items:  "+MAX_ITEMS+"\n"+
                           "Times Run:  "+TIMES_RUN+"\n"+
                           "Breakpoint: "+BREAKPOINT);
        System.out.println("-----------------------------------------------");
        testTime(testFinal);
        System.out.println("Program made by Zac Kolton");
    }

    public static void testTime(int[] test)
    //this takes the random array made in main()
    //prints data of the different sorts to the consol
    //Mean,Standard Dev.,and Z-Scores are computed
    {
        long[] intSortRes    = new long[TIMES_RUN];
        long[] mergeSortRes  = new long[TIMES_RUN];
        long[] quickSortRes  = new long[TIMES_RUN];
        long[] hybridSortRes = new long[TIMES_RUN];
        
        //initalize all the Result arrays
        insertionSortTest(test, intSortRes);
        mergeSortTest(test,mergeSortRes);
        quickSortTest(test,quickSortRes);
        hybridSortTest(test,hybridSortRes);
        
        double intSortMean     = Stats.mean(intSortRes);
        double intSortStdDev   = Stats.standardDeviation(intSortRes);
        
        double mergeSortMean   = Stats.mean(mergeSortRes);
        double mergeSortStdDev = Stats.standardDeviation(mergeSortRes);
        
        double quickSortMean   = Stats.mean(quickSortRes);
        double quickSortStdDev = Stats.standardDeviation(quickSortRes);
        
        double hybridSortMean  = Stats.mean(hybridSortRes);
        double hybridSortStdDev= Stats.standardDeviation(hybridSortRes);
        
        double zStat1 = Stats.zTest(intSortRes,mergeSortRes);  //Insertion and merge sort
        double zStat2 = Stats.zTest(intSortRes,quickSortRes);  //Insertion and quick sort
        double zStat3 = Stats.zTest(intSortRes,hybridSortRes); //Insertion and hybrid quick sort
        
        double zStat4 = Stats.zTest(mergeSortRes,quickSortRes); //Merge and quick sort
        double zStat5 = Stats.zTest(mergeSortRes,hybridSortRes);//Merge and hybrid quick sort
        
        double zStat6 = Stats.zTest(quickSortRes,hybridSortRes);//Quick and hybrid quick sort
        
        System.out.println("Insertion Sort:");
        System.out.println("Mean =   "+intSortMean+"\nStdDev = "+intSortStdDev);
        System.out.println("-----------------------------------------------");
        System.out.println("Merge Sort:");
        System.out.println("Mean =   "+mergeSortMean+"\nStdDev = "+mergeSortStdDev);
        System.out.println("-----------------------------------------------");
        System.out.println("Quick Sort:");
        System.out.println("Mean =   "+quickSortMean+"\nStdDev = "+quickSortStdDev);
        System.out.println("-----------------------------------------------");
        System.out.println("Hybrid Quick Sort:");
        System.out.println("Mean =   "+hybridSortMean+"\nStdDev = "+hybridSortStdDev);
        System.out.println("-----------------------------------------------");
       
        System.out.println("Z-Scores:\n");
        System.out.println("Insertion Sort and Merge Sort:        " + zStat1);
        System.out.println("Insertion Sort and Quick Sort:        " + zStat2);
        System.out.println("Insertion Sort and Hybrid Quick Sort: " + zStat3);
        System.out.println();
        System.out.println("Merge Sort and Quick Sort:        " + zStat4);
        System.out.println("Merge Sort and Hybrid Quick Sort: " + zStat5);
        System.out.println();
        System.out.println("Quick Sort and Hybrid Quick Sort: " + zStat6);
        System.out.println("-----------------------------------------------");
        System.out.println("Quick Sort faster than insertion sort?:   "+   (quickSortMean < intSortMean));
        System.out.println("Hybrid Quick Sort faster than Quick Sort?:"+(hybridSortMean < quickSortMean));
        System.out.println("-----------------------------------------------");
        
    }
    
    
    private static void insertionSortTest(int[] test,long[] intSortRes)
    //test array and result array are passed for the specific sort
    //record the result of the time passed and put it into the array storing that information
    {
        int testsRun = 0;
        for(int i = 0; i < TIMES_RUN; i++)
        {
          long start,end,time;
          long result;   
          start = System.nanoTime();
          insertionSort(test);
          end = System.nanoTime();
          time = end - start;
          assert(isSorted(test));
          intSortRes[testsRun] = time;
          testsRun++;
          test = makeArray(MAX_ITEMS);
        }
    }
    
    private static void mergeSortTest(int[] test, long[] mergeSortRes)
    //test array and result array are passed for the specific sort
    //record the result of the time passed and put it into the array storing that information
    {
        int testsRun = 0;
        for(int i = 0; i < TIMES_RUN; i++)
        {
          long start,end,time;
          long result;   
          start = System.nanoTime();
          mergeSort(test);
          end = System.nanoTime();
          time = end - start;
          assert(isSorted(test));
          mergeSortRes[testsRun] = time;
          testsRun++;
          test = makeArray(MAX_ITEMS);
        }
    }
    
    private static void quickSortTest(int[] test,long[] quickSortRes)
    //test array and result array are passed for the specific sort
    //record the result of the time passed and put it into the array storing that information
    {
        int testsRun = 0;
        for(int i = 0; i < TIMES_RUN; i++)
        {
          long start,end,time;
          long result;   
          start = System.nanoTime();
          quickSort(test);
          end = System.nanoTime();
          time = end - start;
          assert(isSorted(test));
          quickSortRes[testsRun] = time;
          testsRun++;
          test = makeArray(MAX_ITEMS);
        }
    }
    
    private static void hybridSortTest(int[] test,long[] hybridSortRes)
    //test array and result array are passed for the specific sort
    //record the result of the time passed and put it into the array storing that information
    {
        int testsRun = 0;
        for(int i = 0; i < TIMES_RUN; i++)
        {
          long start,end,time;
          long result;   
          start = System.nanoTime();
          hybridQuickSort(test);
          end = System.nanoTime();
          time = end - start;
          assert(isSorted(test));
          hybridSortRes[testsRun] = time;
          testsRun++;
          test = makeArray(MAX_ITEMS);
        }
    }
    

    public static void insertionSort(int[] nums)
    {
        insertionSort(nums,0,nums.length);
    }

    public static void mergeSort(int[] nums)
    {
        int[] temp = new int[nums.length];
        mergeSort(nums,0,nums.length-1,temp);
    }

    public static void quickSort(int[] nums)
    {
        quickSort(nums,0,nums.length-1);
    }

    public static void hybridQuickSort(int[] nums)
    {
        hybridQuickSort(nums,0,nums.length);
    }

    private static void insertionSort(int[] nums, int start,int end)
    
    {
        if((nums.length > 0) && (end > 0)&& (start >=0) && (start < end)) //making sure for extra error while testing
        //goes through whole list and checks every value 
        {
            for(int i = start; i<end; i++)
            {
                int temp = nums[i];
                int j = i-1;
                while((j>=start) && (nums[j] > temp))
                {
                    nums[j+1] = nums[j];
                    j--;
                }
                nums[j+1] = temp;
            }
        }

    }
    
    private static void mergeSort(int[] array, int start,int end, int[] temp)
    {
        if(end <= start) //base case
        {
            return;
        }
        else
        {
            System.arraycopy(array,start,temp,start,(end-start)+1);
            int mid = (start+end)/2;
            mergeSort(temp,start,mid,array);
            mergeSort(temp,mid+1,end,array);
            merge(temp,temp,array,start,mid+1,start,mid,end);
        }
    }

    private static void merge(int[] array1,int[] array2,int[] finalArray,
                              int start1,int start2,int startFinal,
                              int end1  ,int end2)
    {
        int posArray1     = start1;
        int posArray2     = start2;
        int posFinal      = startFinal;
        while((posArray1 <= end1) && (posArray2 <= end2))
        {
            if(array1[posArray1] <= array2[posArray2])
            {
                finalArray[posFinal] = array1[posArray1];
                posArray1++;
            }
            else
            {
                finalArray[posFinal] = array2[posArray2];
                posArray2++;
            }
            posFinal++;
        }
        //move up the list to fix up remaing integers to be sorted
        while(posArray1 <= end1)
        {
            finalArray[posFinal] = array1[posArray1];
            posFinal++;
            posArray1++;
        }
        //move up the list to fix up remaing integers to be sorted
        while(posArray2 <= end2)
        {
            finalArray[posFinal] = array2[posArray2];
            posFinal++;
            posArray2++;
        }

    }

    private static void quickSort(int[] a, int start, int end)
    {
        if((end - start) + 1  <= 3) //base case, the sortCase is then called
        {
            sortCase(a,start,end);
        }
        else
        {
            int pivot = partition(a,start,end);
            quickSort(a,start,pivot);
            quickSort(a,pivot+1,end);
        }
    }

    private static int getPivot(int[] a,int start,int end)
    //helper method
    //Finds a pivot point using median of three method 
    //returns a position to pivot around
    //recieves data from array, start choice, end choice
    {
        int mid = (start+end)/2;
        if(a[start] >= a[mid])
        {
            swap(a,start,mid);
        }
        if(a[mid] >= a[end])
        {
            swap(a,mid,end);
        }
        if(a[start] >= a[mid])
        {
            swap(a,start,mid);
        }
        return a[mid];
    }

    private static int partition(int[] a,int start,int end)
    //returns the position of where the pivot is suppose to go after the partition
    {
        int pivot = getPivot(a,start,end);
        int bigStart = start + 1;
        int curr     = start + 1;
        do{
            if(a[curr] < pivot)
            {
                swap(a,bigStart, curr); 
                bigStart++;            //increments big start to increase the "bigs" portion
            }
            curr++;
        }while(end>curr);

        return bigStart-1;
    }

    private static void sortCase(int[] a,int start,int end)
    //this method swaps for arrays of size 3 or less
    //doesnt return anything
    {
        int caseSize = (end-start)+1;
        if(caseSize <= 1) //just return, nothing to swap
        {
            return;
        }
        if(caseSize == 2)
        {
            if(a[start] >= a[end]) //only swap if the second one is less than the first
            {
                swap(a,start,end);
            }
            return;
        }
        else 
        {
            //for cases of size 3
            //example, this is what it looks like
            //9,4,2
            //4,9,2
            //2,9,4
            //2,4,9
            int mid = (start+end)/2;
            if(a[start] >= a[mid])
            {
                swap(a,start,mid);
            }
            if(a[start] >= a[end])
            {
                swap(a,start,end);
            }
            if(a[mid] >= a[end]) //called once more to make sure its all sorted 
            {
                swap(a,mid,end);
            }
        }
    }

    private static void swap(int[] a, int swapX, int swapY)
    //swaps two values
    //returns nothing
    {
        int temp = a[swapX];
        a[swapX] = a[swapY];
        a[swapY] = temp;
    }

    private static void hybridQuickSort(int[] a,int start,int end)
    //based on whatever break point is
    //this sort will choose to either do quickSort (when array size is atleast >= breakpoint)
    //or do insertion (when array size is less than breakpoint)
    {
        if(end >= BREAKPOINT)
        {
            quickSort(a,0,end-1);
        }
        else
        {
            insertionSort(a,0,end);
        }
    }

    private static boolean isSorted(int[] a)
    //checks if list is sorted
    {
        boolean sorted = true;
        for(int i = 1; i<a.length;i++)
        {
            if(a[i-1] > a[i])
            {
                sorted = false;
            }
        }
        return sorted;
    }

    private static int[] makeArray(int size)
    //makes a completly random array
    //ignoring array of size 0 as per Mr. Guderian
    //returns a random int[] array
    {
        if(size < 2)
        {
            System.out.println("Nothing to sort, size < 2");
        }
        int[] result = new int[size];
        for(int i = 0; i<size;i++)
        {
            result[i] = (int)(Math.random()*(size));
        }
        for(int i = 0; i<size; i++)
        {
            int xPos = (int)(Math.random()*(size));
            int yPos = (int)(Math.random()*(size));
            if(xPos == yPos) 
            //this is to catch if there are two of the same positions in the array
            //highly unlikely but might as well catch it if it happens
            {
                yPos = (int)(Math.random()*(size-1));
            }
            swap(result,xPos,yPos);
        }
        return result;
    }

} 
