//-------------------------------------------------------------
// Name: Zac Kolton
// Student Number: 7838513
// Course: COMP 2160, Section A01
// Instructor: Yang Wang
// Assignment: assignment#1, Question#2
//
// Purpose: Convert the java program Levenshtein to a c program
//--------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>

#define DIST_SIZE 3                         //Defines the size of the array dist[] initalized in levenshtein()
static int testsExecuted = 0;
static int testsFailed = 0;

static int minimum(int m[],int sizeDist)
//recieves a array of ints to compensate for java's varargs
//returns the minimum int of all the values in m[]
{
    int min = 0;
    assert(sizeDist > 0);
    if(sizeDist > 0)
    {
        min = m[0];
        for(int i = 1; i <sizeDist; i++)
        {
            if(m[i] < min)
            {
                min = m[i];
            }
        }
    }
    return min;
}

static void substring(char a[],char b[],int pos)
//recieves two strings from levenshtein()
//makes b[] into a substring of a[], from position 1 in a[] to the end of a[]
//equivalent to Java's String method substring(int)
//only works when pos is 1, specific to the java version substring input
//does not return anything
{
    while(a[pos] != '\0')                  //start at the position given from levenstein()
    {
        b[pos-1] = a[pos];                 //since we want b[] to start at 0, pos - 1
        pos++;
    }
    b[pos-1] = '\0';                       //have to add in the null terminator at the end of the substring b[]
}


static int lengthString(char a[])
//gets the length of a given string
//does ot include the null terminator
//was using strlen at first, but since strlen returns a long it...
//...didnt work very well with the majority of other types (int)
{
    int count = 0;
    while(a[count] != '\0')
    {
        count++;
    }
    return count;
}

#define T 1;                                //Define True = 1
#define F 0;                                //Define False= 0
static int endsWith(char a[], char b[])
//checks if the arrays are equal
//equivalent to java's endsWith() method
{
    int i = 0;
    int equals = T;
    while((a[i+1] != '\0') && (b[i] != '\0'))
    {
        if(a[i+1] != b[i])                   //a[i+1] is used because b[] is (at this point) a...
        {                                    //...substring of a[], which holds on less value than a[]...
            equals = F;                      //...compensating for int i being at the start of b[]
        }
        i++;
    }
    return equals;
}
static int levenshtein(char s[],char t[])
//recieves strings s[] and t[] from testDistance() to compute moves
{
    int cost;
    int distance;
    char deleteS[strlen(s)];
    char deleteT[strlen(t)];
    
    if(strlen(s) == 0)
    {
        distance = (int)strlen(t);
    }
    else if(strlen(t) == 0)
    {
        distance = (int)strlen(s);
    }
    else
    {
        if(s[0] == t[0])
        {
            cost = 0;
        }
        else
        {
            cost = 1;
        }
        substring(s,deleteS,1);                          //substring is called here with pos = 1
        substring(t,deleteT,1);                          //substring is called here with pos = 1
        
        int ds      = lengthString(deleteS);             //ds stands for deleteSLength
        int dt      = lengthString(deleteT);             //dt stands for deleteTLength
        int sLenght = lengthString(s);
        int tLenght = lengthString(t);
        
        assert(ds == (sLenght-1));                      //checking deleteSLength is s[] length minus 1
        assert(dt == (tLenght-1));                      //checking deleteTLength is t[] length minus 1
        
        assert(endsWith(s,deleteS));                     //endsWith check function called here
        assert(endsWith(t,deleteT));                     //endsWith check function called here
        
        int d0 = levenshtein(deleteS, t) + 1;            //I put each recursion call into its own...
        int d1 = levenshtein(s, deleteT) + 1;            //...variable for more organization and...
        int d2 = levenshtein(deleteS, deleteT) + cost;   //... easier debugging
        int dist[] = {d0,d1,d2};
        distance = minimum(dist,DIST_SIZE);
    }
    return distance;
}

static void testDistance(char s[],char t[], int expected)
{
    int distance = levenshtein(s, t);
    if(distance == expected)
    {
        printf("Passed! You can get from '%s' to '%s' in %d moves.",s,t,expected);
        printf("\n");
    }
    else
    {
        printf("Failed: i thought it would take %d moves, but apparently it takes %d moves to get from '%s' to '%s'.",expected,distance,s,t);
        printf("\n");
        testsFailed++;
    }
    testsExecuted++;
}
int main()
{
    printf("Testing typical cases.\n" );
    testDistance( "kitten", "kitten", 0 );
    testDistance( "kitten", "sitting", 3 );
    testDistance( "kitten", "mittens", 2 );
    testDistance( "balloon", "saloon", 2 );
    testDistance( "hello", "doggo", 4 );
    testDistance( "\t\thi", "\t\t\t\thi", 2 );
    
    
    printf( "\nTesting empty/edge cases.\n" );
    testDistance( "", "", 0 );
    testDistance( "hello", "", 5 );
    testDistance( "", "doggo", 5 );
    testDistance( "a", "b", 1 );
    testDistance( "b", "b", 0 );
    testDistance( " ", " ", 0 );
    printf( "\nThis might take a while...\n" );
    testDistance( "12345678901", "123456789012", 1 );   // how many chars are we looking at?
    
    printf( "\nThese tests will not succeed in the C version\n" );
    testDistance( "kitten", "mitten\0s", 3 );           // ????
    testDistance( "\0totally", "\0different", 9 );
    
    printf( "\nTotal number of tests executed: %d",testsExecuted );
    printf("\n");
    printf( "Number of tests passed: %d", testsExecuted - testsFailed);
    printf("\n");
    printf("Number of tests failed: %d",testsFailed);
    printf("\n");
    printf("Program completed normally.\n");
    return 0;
}
