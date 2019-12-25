/*
  lab5.c
  COMP 2160 Lab 6 Exercise 1
 */
#include <stdio.h>
#include <stdlib.h>

typedef unsigned int PackedDate;
#define SHIFT 4

PackedDate pack(int day, int month, int year){
  PackedDate result = 0x0000;
 
  int tens = 0;
  int ones = 0;
  if(day >= 10)
    {
      ones = day%10;     //for 23, ones would be 3
      printf("Ones: %d\n",ones);
      tens = (int)day/10; //for 23, tens would be 2
      printf("Tens: %d\n",tens);
      result |= tens<<0; //00000001
      result <<=SHIFT;   //00000010
      result |= ones<<0; //00000023
      
    }
  else
    {                   //for say "1"
      result |= day<<0; //00000001
    }
  
  result <<=SHIFT;


  int tensM = 0;
  int onesM = 0;
  if(month >= 10)
    {
      onesM = month%10;
      tensM = (int)month/10;
      result |= tensM<<0;
      result <<= SHIFT;
      result |= onesM<<0;
    }
  else
    {
      result |= 0<<0;
      result <<=SHIFT;
      result |= month<<0;
    }
  
  result <<= SHIFT;
  
  int onesY     = 0;
  int tenTemp   = 0;
  int tensY     = 0;
  int hundsTemp = 0;
  int hunds     = 0;
  int thous     = 0;

  onesY   = year%10;
  printf("Ones (year): %d\n",onesY);
  tenTemp = (int)year/10;
  tensY   = tenTemp%10;
  printf("tens (year): %d\n",tensY);
  hundsTemp = (int)tenTemp/10;
  hunds = hundsTemp%10;
  printf("hunds (year): %d\n",hunds);
  thous = (int)hundsTemp/10;
  printf("thous (year): %d\n",thous);

  result |= thous<<0;
  result <<= SHIFT;
  result |= hunds<<0;
  result <<= SHIFT;
  result |= tensY<<0;
  result <<= SHIFT;
  result |= onesY<<0;
  
  return result;
}// pack

int main( void ){
  int day;
  int month;
  int year;
  PackedDate date;

  while ( EOF != scanf( "%d-%d-%d", &day, &month, &year ) )
  {
    date = pack( day, month, year );
    printf( "%d-%d-%d %08x\n", day, month, year, date );
  }
  
  return EXIT_SUCCESS;
}// main
