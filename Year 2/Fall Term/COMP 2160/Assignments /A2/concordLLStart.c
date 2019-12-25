#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
//-------------------------------------------------------------------------------------
// CONSTANTS and TYPES
//-------------------------------------------------------------------------------------

typedef enum BOOL { false, true } Boolean;


typedef struct NODE Node;

struct NODE{
  char *item;
  Node *next;
};


//-------------------------------------------------------------------------------------
// VARIABLES
//-------------------------------------------------------------------------------------

//Purpose: delcare a head node to keep track of the head of the list
Node *head;


//-------------------------------------------------------------------------------------
// PROTOTYPES
//-------------------------------------------------------------------------------------

// add an element to the beginning of the linked liat
Boolean insert( char *new_string );

// empty the list so that we clear all memory and can start a fresh list
void clearList();

// tells us whether or not the given string is in the list
Boolean search(char *target);

// starts a list traversal by getting the data at top
char * firstItem();

// increment the traversal and gets the data at the current traversal node
char * nextItem(); 


//-------------------------------------------------------------------------------------
// FUNCTIONS
//-------------------------------------------------------------------------------------

//Purpose: insert a node into the beginning of this list
//
//Pre-Condition:
//               - It is assumed the new string being inserted is the only one of its kind
//                 (ie. no repeates).
//               - It is assumed the head node has been delcared already, so that you can add
//                 a new node to the list.
//               - It is assumed that this function will create a new node that contains a string
//               - It is assumed the node will be added to the beginning of the list
//
//Post-Condition:
//               - It is assumed the new string will be inserted to a new node, which will be pointed
//                 to by the previous node
//               - It is assmued there was duplicate nodes (no two items were the same)
//               - It is assumed head is not null anymore once a node has been inserted (if it wasnt
//                 already)
//               - The node is at the beginning of the list
//               - A new string is created
//                      - using strdup()
//               - Head is not equal to null anymore
//                      - Will return true
//
//Invariant:
//               - The passed string (new_string) is the only of its kind
//               - The passed string (new_string) is != NULL
//               - A new Node is created and assigned a new item
Boolean insert(char *new_string)
{
    Node *newNode = malloc(sizeof(Node));
    newNode->item = strdup(new_string);
    newNode->next = head;
    head = newNode;
    return head == NULL;
}

//Purpose: Clear the list and free up the allocated memory
//Pre-Condition:
//              - The list is not empty (ie. head != NULL)
//
//Post-Condition:
//              - The list is now empty and allocated memory is cleared
//              - Head is now set to null
//Invariant:
//              - Curr moves down the linked list
void clearList()
{
    assert(head != NULL);
    Node *curr = malloc(sizeof(Node));
    Node *nextNode = NULL;
    curr = head;
    while(curr != NULL)
    {
        nextNode = curr->next;
        free(curr);
        assert(curr == NULL);
        curr = nextNode;
    }
    head = NULL;
    assert(head == NULL);
}


//Purpose: return the first time in a linked list
//Method:
//  - return the head node item
//  - set head to the next node
//      - This is for nextItem() 
//Pre-Condition:
//              - The list is not empty (ie. head != NULL)
//
//Post-Condition:
//              - The first item is retured as a new duplicated string
//              - There will be a print out IF the head node is NULL
//Invariant:
//              - Curr moves down the linked list
//              - Head is set to the next node at the end (before return)
//                  - For when next item is called, it will actually print out the next item... not the head node again
char * firstItem()
{
    Node *curr = malloc(sizeof(Node));
    curr = head;
    if(curr == NULL)
    {
        printf("head is null\n");
    }
    char *output = strdup(head->item);
    assert(curr->next != NULL);
    head = curr->next;
    return output;
}

//Purpose: To return the next item in the list
//Method:
//  - I used a node to keep track of where we are in the list
//  - node is set to head every time at the start
//  - after outPut is inialized, node is set to the next node (node = node-> next) and head is set to the node (head = node)
//  - I used this way because i couldnt find another way to keep track of where i was in the list without a global Node (head)
//      - Otherwise the head node would be printed out every time
//  - This doesnt print out the head node twice because in the firstItem() method, i set the head node to the next node (head = curr->next)
//Pre-Condition:
//              - The next item in the list will be returned
//              - "node" will eventually get to the end of the list
//Post-Condition:
//              -
//Invariant:
//              - outPut will always return a new string creted from node->item or NULL
char * nextItem()
{
    Node *node = malloc(sizeof(Node));
    node = head;
    char *outPut = NULL;
    if(node != NULL)
    {
        outPut = strdup(node->item);
        node = node->next;
        head = node;
    }
    return outPut;
}

//Purpose: To search search for a particular string and check if it already in the list or not
//Method:
//  - curr is set to head, to keep track of where i am in the list
//  - a boolean flag (isThere) is initalized as false
//  - curr moves through the list and char *test makes acopy of the item in curr
//  - test is compared with targert using strcmp()
//      - if it equals, isThere will be equal to true
//  - isThere is returned
//Pre-Condition:
//              - Head != NULL
//              - a boolean is retured
//Post-Condition:
//              - curr has made it to the end of the list or up to a node that has the same item
//                  - Where isThere has either remained as false or been switched to true
//Invariant:
//              - Curr moves down the linked list
//              - a copy of curr->item is made given curr != NULL
Boolean search( char *target )
{
    Node *curr = malloc(sizeof(Node));
    curr = head;
    Boolean isThere = false;
    while(curr != NULL)
    {
        char *test = strdup(curr->item);
        if(strcmp(test,target) == 0)
        {
            isThere = true;
        }
        curr = curr->next;
    }
    return isThere;
}


// read from standard input, tokenize and insert words into a linked list
// note that we must ensure that there are no duplicates in our list
void loadFile()
{
#define LINE_SIZE 256
  char input[LINE_SIZE];
  char *token = NULL;
  
    while (fgets( input, LINE_SIZE, stdin))
  {
    // parse the data into separate elements
    token = strtok( input, " \t\n" );
    while ( token )
    {
      if ( !search( token ) )
        insert( token );
      
      token = strtok( NULL, " \t\n" );
    }
  }
}

// print the contents of the linked list to standard output
void printConcordance()
{
  char *theWord = firstItem();
  
  while ( theWord )
  {
    printf( "%s\n", theWord );
    theWord = nextItem();
  }
}

int main( int argc, char *argv[] )
{
  loadFile();
  printConcordance();
  clearList();
  return EXIT_SUCCESS;
}
