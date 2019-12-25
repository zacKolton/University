//-----------------------------------------
// NAME: Zac Kolton
// STUDENT NUMBER: 7838513
// COURSE: COMP 2160, SECTION: A01
// INSTRUCTOR: Mr. Wang
// ASSIGNMENT: assignment 3, QUESTION: question 1
// REMARKS: Linked list implementation of a table
//-----------------------------------------
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include "Table.h"

struct NODE
{
    char *item;
    Node *next;
};

struct TABLE
{
    Node *head;
    Node *traverseNode;
    
    int numNodes;
    int numTraversals;
};

Table *createTable(void)
{
    Table *table;
    table = (Table*)malloc((int)sizeof(Table));
    table->head = NULL;
    table->traverseNode = NULL;
    table->numNodes = 0;
    table->numTraversals = 0;
    return table;
}

//-------------------------------------------------------------------------------------
// PROTOTYPES
//-------------------------------------------------------------------------------------


Boolean     insert(Table *table,char *new_string);  // add an element to the beginning of the linked list
Boolean deleteNode(Table *table,char *target);
Boolean     search(Table *table,char *target);      // tells us whether or not the given string is in the list
Boolean    isEmpty(Table *table);
void     clearList(Table *table);                   // empty the list so that we clear all memory and can start a fresh list
char *   firstItem(Table *table);                   // starts a list traversal by getting the data at top
char *    nextItem(Table *table);                   // increment the traversal and get the data at the current traversal node



//-------------------------------------------------------------------------------------
// FUNCTIONS
//-------------------------------------------------------------------------------------

void printTable(Table *table){
    char *theWord = firstItem(table);
    while ( theWord )  {
        printf("%s ", theWord );
        theWord = nextItem(table);
    }
    printf("\n");
}// printTable

//-------------------------------------------------------------------------------------
// Linked List (Table) Implementation
//-------------------------------------------------------------------------------------
void validateTable(Table *table){
    if ( table->numNodes == 0 )
        assert( table->head == NULL );
    else if ( table->numNodes == 1 )
        assert( table->head->next == NULL );
    else // numNodes > 1
        assert( table->head != NULL && table->head->next != NULL );
    // how far should we really go???
}// validateTable

Boolean insert(Table *table,char *new_string)
{
    Boolean rc = false;
    Node *newNode = NULL;
    
    assert( new_string != NULL );
    if ( new_string ){
        newNode = (Node *)malloc( sizeof( Node ) );
        assert( newNode != NULL );
        if ( newNode ){
            // note that we need to have space for the string as well!
            newNode->item = strdup(new_string);
            assert( newNode->item != NULL );
            if (newNode->item){
                newNode->next = table->head;
                table->head = newNode;
                table->numNodes++;
                rc = true;
                // make sure the list is stable
                validateTable(table);
            } else {
                free( newNode );
            }// if ( newNode->item )
        }// if ( newNode )
    }// if( new_string )
    return rc;
}

Boolean deleteNode(Table *table,char *target)
{
    Boolean deleted = false;
    assert(target != NULL);
    if(search(table,target))
    {
        Node *prev = NULL;
        Node *curr = table->head;
        while(curr != NULL && !deleted)
        {
            if(strcmp(target, curr->item) == 0)
            {
                prev->next = curr->next;
                table->numNodes--;
                free(curr);
                deleted = true;
            }
            else
            {
                prev = curr;
                curr = curr->next;
            }
        }
    }
    return deleted;
}

Boolean search(Table *table, char *target)
{
    Boolean found = false;
    char *curr = firstItem(table);
    int search_count = 0;     // how far in the list do we go?
    
    assert( target != NULL );
    if ( target != NULL ) {
        while ( curr != NULL && !found ){
            if ( strcmp( target, curr ) == 0 ){
                found = true;
                
                // make sure we're still in our list...
                assert( search_count <= table->numNodes );
            } else {
                curr = nextItem(table);
                search_count++;
            }// else
        }// while
    }// if( target != NULL )
    
    // if it's not found then we should be at the end of the list
    assert( found || (search_count == table->numNodes) );
    
    return found;
}



char * firstItem(Table *table){
    char *the_item = NULL;
    
    table->traverseNode = table->head;
    if ( table->traverseNode != NULL ) {
        the_item = table->traverseNode->item;
        assert( the_item != NULL );
        
        table->numTraversals = 1;
        // make sure we're still in our list...
        assert(table->numTraversals <= table->numNodes );
    }// if
    
    // this isn't the safest (caller can modify the string),
    // but we don't have to worry about extra memory mgmt...
    return the_item;
}// firstItem

char * nextItem(Table *table){
    char *the_item = NULL;
    
    // try to advance the traversal first
    if ( table->traverseNode != NULL ) {
        table->traverseNode = table->traverseNode->next;
        table->numTraversals++;
    }
    // if we are still in the list, get the item
    if ( table->traverseNode != NULL ) {
        the_item = table->traverseNode->item;
        assert( the_item != NULL );
        // make sure we're still in our list...
        assert( table->numTraversals <= table->numNodes );
    }
    // this isn't the safest (caller can modify the string),
    // but we don't have to worry about extra memory mgmt...
    return the_item;
}// nextItem

//Deletes the table
void destroyTable(Table *table){
    Node *curr = table->head;
    assert(curr != NULL);
    while ( table->head != NULL ){
        table->head = table->head->next;
        free( curr->item );
        free( curr );
        curr = table->head;
        
        table->numNodes--;
    }// while
    
    validateTable(table);
}// deleteTable

//Checks whether the list is empty or not
Boolean isEmpty(Table *table)
{
    Boolean empty = false;
    if(table->numNodes == 0)
    {
        empty = true;
    }
    return empty;
}
