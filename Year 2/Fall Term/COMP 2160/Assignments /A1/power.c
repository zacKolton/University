//-------------------------------------------------------------
// Name: Zac Kolton
// Student Number: 7838513
// Course: COMP 2160, Section A01
// Instructor: Yang Wang
// Assignment: assignment#1, Question#1
//
// Purpose: Convert the java program Power to a c program
//--------------------------------------------------------------

#include <stdio.h>

static int count;
static double power1(double base, int index)
//returns a double
{
    double retValue;
    if(index == 0)
    {
        retValue = 1;
    }
    else
    {
        retValue = base * power1(base,index-1);
        count++;
    }
    return retValue;
}
static double power2(double base, int index)
//returns a double
{
    double retValue;
    double temp;
    if(index == 0)
    {
        retValue = 1;
    }
    else if ((index%2)==1)
    {
        retValue = base * power2(base,index-1);
        count++;
    }
    else
    {
        temp = power2(base,index/2);
        retValue = temp*temp;
        count++;
    }
    return retValue;
}
#define SIZE 4                                      //defining size of array for main()...
                                                    //...to compensate for java's array.length
int main() {
    double base[] = {1.4, 1.3, 1.2, 1.1 };
    int index[] = { 5, 20, 63, 73 };
    double value;
    printf("Test two algorithms for powering:\n\n");
    int i;
    for(i =0;i<SIZE;i++)
    {
        count = 0;
        value = power1(base[i],index[i]);
        printf("1: %0.1f^%d = %f, used %d multiplies\n", base[i],index[i],value,count);
        count = 0;
        value = power2(base[i], index[i]);
        printf("2: %0.1f^%d = %f, used %d multiplies\n", base[i],index[i],value,count);
        printf("\n");
    }
    printf("\nEnd of Processing\n\n");
    return 0;
}
