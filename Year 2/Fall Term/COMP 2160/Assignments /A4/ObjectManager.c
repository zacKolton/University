//-----------------------------------------
// NAME: Zac Kolton
// STUDENT NUMBER: 7838513
// COURSE: COMP 2160, SECTION: A01
// INSTRUCTOR: Mr. Wang
// ASSIGNMENT: assignment 4, QUESTION: question 1
// REMARKS: Implement a garbage collector algorithm (mark and sweep) in compact and keep track of memory useage

// NOTES: Was not able to pass various tests, not successful in writing into a node address... goes into infinit loop
//-----------------------------------------
#include "ObjectManager.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct NODE Node;
typedef struct OBJECTMGR ObjectMgr;

typedef enum BOOL {false,true} Boolean;


struct NODE
{
    Ref ref;	    // the index
    ulong size;		// the size of bytes
    int refCount;	// how many nodes point to this node
    ulong offSet; 	// where in the buffer each object beings
    Node *next;
};

struct OBJECTMGR
{
    Node *head;
};



static ObjectMgr *newObjectMgr()
{
    ObjectMgr *obMrg;
    obMrg = (ObjectMgr*)malloc((int)sizeof(ObjectMgr));
    obMrg->head = NULL;
    return obMrg;
}

static Node *newNode(Ref referenceNum,ulong newSize,int referenceCount,ulong offSetAmount)
{
    Node *newNode;
    newNode = (Node*)malloc((int)sizeof(Node));
    newNode->ref = referenceNum;
    newNode->size = newSize;
    newNode->refCount = referenceCount;
    newNode->offSet = offSetAmount;
    newNode->next = NULL;
    return newNode;
}
//-------------------------------------------------------------------------------------
// PROTOTYPES
//-------------------------------------------------------------------------------------

Ref insertObject( ulong size );
static ulong totalSize(void);
static void deleteNode(Ref ref);
static void compact(void);

//-------------------------------------------------------------------------------------
// VARIABLES
//-------------------------------------------------------------------------------------


ObjectMgr *mgr;

uchar bufferA[MEMORY_SIZE];
uchar bufferB[MEMORY_SIZE];
uchar *currBuff;
int refId = 0;
ulong freePtr = 0;
ulong sizeDeleted = 0;
int numObjects = 0;

//-------------------------------------------------------------------------------------
// FUNCTIONS
//-------------------------------------------------------------------------------------

void initPool(void)
{
    mgr = newObjectMgr();
    currBuff = bufferA;
}


Ref insertObject(ulong size)
{
    Ref newRef = NULL_REF;
    if(size > (MEMORY_SIZE - freePtr))
    {
        compact();
        if(size < (MEMORY_SIZE - freePtr))
        {
            insertObject(size);
        }
    }
    else
    {
        newRef = ++refId;
        if(mgr->head == NULL)
        {
            mgr->head = newNode(newRef,size,1,0);
        }
        else
        {
            Node *curr = mgr->head;
            Node *prev = NULL;
            while(curr)
            {
                prev = curr;
                curr = curr->next;
            }
            prev->next = newNode(newRef, size, 1, freePtr-1);
        }
        freePtr = (totalSize() + sizeDeleted) +1;
    }
    return newRef;
}
static void compact()
{
    Node *curr = mgr->head;
    for(ulong i = 0; i < MEMORY_SIZE; i++)
    {
        if(curr && (curr->size == i))
        {
            if(curr->refCount > 0)
            {
                numObjects++;
                printf("adding node to buffer\n");
                memcpy(bufferB, bufferA, curr->size);
                curr = curr->next;
            }
            else
            {
                deleteNode(curr->ref);
            }
        }
    }
    
    currBuff = bufferB;
    Node *trav = mgr->head;
    while(trav)
    {
        ulong updateOffset = trav->size;
        if(trav->next)
        {
            trav->next->offSet = updateOffset;
        }
        else if(trav && !(trav->next))
        {
            trav->offSet = totalSize();
        }
        curr = curr->next;
    }
    fprintf(stdout,"\nNumber of Objects: %d\n",numObjects);
    fprintf(stdout,"Number of bytes in use: %lu\n",totalSize());
    fprintf(stdout,"Number of bytes collected: %lu\n",freePtr);
    freePtr = totalSize()+1;
}

void *retrieveObject( Ref ref )
{
    void *result = NULL;
    Node *curr = mgr->head;
    Boolean found = false;
    while(curr && !found)
    {
        if(curr->ref == ref)
        {
            found = true;
            result = &curr;
        }
        else
        {
        curr = curr->next;
        }
    }
    return result;
}

void addRefernce(Ref ref)
{
    Node *curr = mgr->head;
    Boolean added = false;
    while(curr && !added)
    {
        if(curr->ref == ref)
        {
            curr->refCount++;
            added = true;
        }
        curr = curr->next;
    }
}

void dropReference(Ref ref)
{
    Node *curr = mgr->head;
    Boolean decremented = false;
    while(curr && !decremented)
    {
        if(curr->ref == ref)
        {
            curr->refCount--;
            if(curr->refCount == 0)
            {
                deleteNode(curr->ref);
            }
            decremented = true;
        }
        curr = curr->next;
    }
}



void dumpPool()
{
    printf("\n-----------Dump Pool-------------------\n");
    Node *curr = mgr->head;
    while(curr)
    {
        fprintf(stdout,"\nObject ID: %lu\n",curr->ref);
        fprintf(stdout,"	Starting adress: %lu\n",curr->offSet);
        fprintf(stdout,"	Size in bytes  : %lu\n",curr->size);
        curr = curr->next;
    }
     printf("---------------------------------------\n");
}

void destroyPool()
{
    Node *curr = mgr->head;
    while(curr)
    {
        mgr->head = mgr->head->next;
        free(curr);
        curr = mgr->head;
    }
}
//-------------------------------------------------------------------------------------
// HELPER FUNCTIONS
//-------------------------------------------------------------------------------------


static void deleteNode(Ref ref)
{
    Node *curr = mgr->head;
    Node *prev = NULL;
    Boolean found = false;
    if(curr)
    {
        while(curr && !found)
        {
            if(curr->ref == ref)
            {
                found = true;
            }
            else
            {
            prev = curr;
            curr = curr->next;
            }
        }
        if(found)
        {
            sizeDeleted += curr->size;
            prev->next = curr->next;
            free(curr);
        }
    }
}
static ulong totalSize()
{
    ulong size = 0;
    Node *curr = mgr->head;
    while(curr)
    {
        size += curr->size;
        curr = curr->next;
    }
    return size;
}


//-------------------------------------------------------------------------------------
// TESTING FUNCTIONS
//-------------------------------------------------------------------------------------

void printManager()
{
    Node *curr = mgr->head;
    int i = 0;
    while(curr)
    {
        i++;
        curr = curr->next;
    }
}










