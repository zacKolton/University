//-----------------------------------------
// NAME: Zac Kolton
// STUDENT NUMBER: 7838513
// COURSE: COMP 2160, SECTION: A01
// INSTRUCTOR: Mr. Wang
// ASSIGNMENT: assignment 3, QUESTION: question 1
// REMARKS: Header File for table.c
//-----------------------------------------

#ifndef Table_h
#define Table_h

#include <stdio.h>

typedef struct NODE Node;
typedef struct TABLE Table;
typedef enum BOOL { false, true } Boolean;

Table *createTable(void);
Boolean     insert(Table *table,char *new_string ); // add an element to the beginning of the linked list
Boolean deleteNode(Table *table,char *target);
Boolean     search(Table *table,char *target );     // tells us whether or not the given string is in the list
Boolean    isEmpty(Table *table);
char *   firstItem(Table *table);                   // starts a list traversal by getting the data at top
char *    nextItem(Table *table);                   // increment the traversal and get the data at the current traversal node
void vaildateTable(Table *table);
void    printTable(Table *table);
void   destroyTable(Table *table);                   // empty the list so that we clear all memory and can start a fresh list




#endif
