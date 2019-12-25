
/**
 * Class Name: IntLinkedList
 * 
 * COMP 1020 Section: A01
 * Instructor: John Bate 
 * Name      : Zac Kolton
 * Assignment: 5
 * Question  : 1 Linked Lists, 2 Recursion with Linked Lists
 * 
 * Purpose   : Implements a linked list that will hold values of type int.
 */
public class IntLinkedList
{
    private IntNode top;
    public int size;

    public IntLinkedList()
    //Purpose   : Contructs a linked list as null and size = 0
    //Input     : Nothing
    //Output    : Nothing
    //Parameters: Nothing
    //Returned  : Nothing
    {
        top = null;
        size = 0;
    }

    public void add(int data)
    //Purpose   : Adds a new IntNode to IntLinkedList
    //Input     : int data for the IntNode
    //Output    : Nothing
    //Parameters: int data
    //Returned  : Nothing
    {
        IntNode newNode = new IntNode(data,top);
        top = newNode;
        size++;
    }

    public void add(IntNode n)
    //Purpose   : Adds a new IntNode to either the odds or evens IntLinkedList in void split(odds,evens)
    //Input     : IntNode n
    //Output    : Nothing
    //Parameters: IntNode n
    //Returned  : Nothing
    {
        IntNode next = top;
        top = n;
        n.setLink(next);
        size++;
    }

    public void split(IntLinkedList odds, IntLinkedList evens)
    //Purpose   : Splits "this"/the IntLinkedList into odd and even IntLinkedList(s), does not create new IntNodes
    //Input     : "this"/the IntLinkedList to be split into such IntLinkedList(s)
    //Output    : Nothing
    //Parameters: 2 IntLinkedList(s), one for odd ints (odds), one for even ints (evens)
    //Returned  : Nothing
    {
        if(size == 0)
        {
            return; //escape 
        }
        else
        {
            IntNode next = findKey(size - 1); //starts at the end of the linkedlist an moves back
            if(next.getInt()%2 == 0)
            {
                evens.add(next);
                size--;
                split(odds,evens); //recursion call
            }
            else //if not positive 
            {
                odds.add(next);
                size--;
                split(odds,evens); //recursion call
            }
        }

    }

    public int frequency(int key)
    //Purpose   : Count and return the number of times that the value key appears in IntLinkedList
    //Input     : int key 
    //Output    : int of how many times key shows up
    //Parameters: int key
    //Returned  : int
    {
        return frequency(top,key);
    }

    public static int frequency(IntNode next, int key)
    //Purpose   : Will find and return the number of times that the value key appears in the 
    //            linked list that begins with the node referred to by IntNode next
    //Input     : IntNode next, int key 
    //Output    : int of howmany times key appears
    //Parameters: IntNode next, int key
    //Returned  : int
    {
        if(next == null)
        {
            return 0;
        }
        else if(next.getInt() == key)
        {
            return 1 + frequency(next.getLink(),key);
        }
        else
        {
            return frequency(next.getLink(),key);
        }
    }

    public IntLinkedList(int[] a)
    //Purpose   : Will  create a linked list containing the same values as the 
    //            int[ ] array, and in the same order, contructs a IntLinkedList from an array 
    //Input     : int[] a (the array)
    //Output    : Nothing
    //Parameters: int[] a 
    //Returned  : Nothing
    {
        for(int i = a.length-1; i>=0; i--)
        {
            int temp = a[i];
            add(temp);
        }
    }

    public void remove(int index)
    //Purpose   : Removes a IntNode from the list at the specified index (int index)
    //Input     : int representing the index 
    //Output    : Nothing
    //Parameters: int index
    //Returned  : Nothing
    {
        IntNode key = findKey(index); //implements private findKey method to find the node at index
        IntNode prev = null;
        IntNode next = top;
        while(key != null && (next.getInt() != key.getInt()))
        {
            prev = next;
            next=next.getLink();
        }

        if(key != null){
            if(prev == null)
            {
                top = next.getLink();
            }
            else
            {
                prev.setLink(next.getLink());
            }
        }

    }

    private IntNode findKey(int index)
    //Purpose   : Private helper method to find the IntNode at the specified index
    //Input     : int index
    //Output    : IntNode at index
    //Parameters: int index
    //Returned  : IntNode
    {
        IntNode key = null;
        IntNode next= top;
        if(index >= 0 && index < size)
        {
            int count = 0;
            while(next != null)
            {
                if(count == index)
                {
                    key = next;
                    break; //stop if the Node has been found 
                }
                else 
                {
                    next = next.getLink();
                    count++; //else continue through the list
                }
            }
        }
        else
        {
            key = null; // if nothing is found return null
        }
        return key;
    }

    public String toString()
    //Purpose   : Print out IntLinkedList integer values
    //Input     : Nothing
    //Output    : String representing IntLinkedList integer values
    //Parameters: Nothing
    //Returned  : String
    {
        String answer = "<<";
        IntNode next = top;
        for(int i = 0; i < size; i++){
            if(next.getLink() == null)
            {
                answer += next.getInt();
                break;
            }
            else
            {
                answer += next.getInt() + ",";
                next = next.getLink();
            }
        }
        return answer + ">>";
    }

    public boolean empty()
    //Purpose   : Checks if the IntLinkedList is empty
    //Input     : Nothing
    //Output    : boolean true if the list is empty, false otherwise
    //Parameters: None
    //Returned  : boolean 
    {
        return top == null;
    }

    public IntLinkedList clone() 
    //Purpose   : Performs a deep copy of a specified IntLinkedList
    //Input     : Nothing
    //Output    : A new IntLinkedList
    //Parameters: None
    //Returned  : IntLinkedList
    {
         IntLinkedList newList = new IntLinkedList();
        IntNode toCopy = top;
        for(int i = size-1; i>=0; i--)
        {
            toCopy = findKey(i);
            int dataCopy = toCopy.getInt();
            newList.add(dataCopy);
        }
        return newList;
    }
    }
    


