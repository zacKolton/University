//-----------------------------------------
// NAME: Zac Kolton
// STUDENT NUMBER: 7838513
// COURSE: COMP 2160, SECTION: A01
// INSTRUCTOR: Mr. Wang
// ASSIGNMENT: assignment 3, QUESTION: question 1
// REMARKS: Run tests on a specified table
//			Prints out numebr of tests executed/passed/failed
//-----------------------------------------

#include <stdio.h>
#include <string.h>
#include <assert.h>
#include "Table.h"

#define TABLE_1 1
#define TABLE_2 2
#define TABLE_3 3

static int testsExec   = 0;
static int testFailed  = 0;

//-------------------------------------------------------------------------------------
// PROTOTYPES
//-------------------------------------------------------------------------------------

void   	     testInsert(Table *table, char *namesList);
void     testDeleteName(Table *table,char *name);
void    testdestroyTable(Table *table, int tableNum);
void         testSearch(Table *table,char *name);
void     printWTableNum(Table *table, int tableNum);
void       printOrgList(char *namesList, int list);
void test1(void);
void test2(void);
void test3(void);

//-------------------------------------------------------------------------------------
// FUNCTIONS
//-------------------------------------------------------------------------------------

//Prints out the orginal list of names
//Never is used to print out a table
void printOrgList(char *namesList,int listNum)
{
    printf("Names in list %d\n", listNum);
    char *token = NULL;
    char *copy  = strdup(namesList);
    token = strtok(copy," ");
    while(token)
    {
        printf("%s ",token);
        token = strtok(NULL," ");
    }
    printf("\n");
}

//Prints the summary of tests failed/passed/executed
void printSummary(void)
{
    printf("\nNumber of Tests: %d\n",testsExec);
    printf("Number of Tests passed: %d\n",testsExec-testFailed);
    printf("Number of Tests failed: %d\n",testFailed);
}


int main(void)
{
    test1();
    test2();
    test3();
    printSummary();
    printf("\nProgram ended normally\n\n");
    
}
//-------------------------------------------------------------------------------------
// TESTING FUNCTIONS
//-------------------------------------------------------------------------------------

void test1(void)
{
    printf("\n---------------------------------------Test 1 Testing Insertion-----------------------------------------------------------------------\n");
    Table *namesTable1 = createTable();
    char names1[] = "Angelic Lissa Cheryll Hortensia Teena Dana Lissa Nickole Teena Winifred Jerald Hortensia Theodore";
    
    printOrgList(names1, TABLE_1);
    testInsert(namesTable1, names1);
    printWTableNum(namesTable1,TABLE_1);
    testdestroyTable(namesTable1, TABLE_1);
    printWTableNum(namesTable1,TABLE_1);
}

void test2(void)
{
    printf("--------------------------------------Test 2 Testing Deletion of a name---------------------------------------------------------------\n");
    Table *namesTable2 = createTable();
    char names2[] = "Aundrea Modesto Margeret Roni Prudence Leida Livia Anglea Lore Napoleon";
    
    
    printOrgList(names2, TABLE_2);
    testInsert(namesTable2, names2);
    testDeleteName(namesTable2,"Modesto");
    testDeleteName(namesTable2,"Livia");
    testDeleteName(namesTable2,"Zac");
    testDeleteName(namesTable2,"Mode");
    testDeleteName(namesTable2,"Leida");
    printWTableNum(namesTable2,TABLE_2);
    testdestroyTable(namesTable2,TABLE_2);
    printWTableNum(namesTable2,TABLE_2);
    printf("Try to delete a name from the empty table...\n\n");
    testDeleteName(namesTable2,"Modesto");
    testDeleteName(namesTable2, "June");
    
}

void test3(void)
{
    printf("--------------------------------------Test 3 Testing search for a name----------------------------------------------------------------\n");
    Table *namesTable3 = createTable();
    char names3[] = "Lavern Treasa Lourie June Carita Tera Treasa Kenia Destiny Toshiko Madelaine";
    
    printOrgList(names3, TABLE_3);
    testInsert(namesTable3, names3);
    printWTableNum(namesTable3,TABLE_3);
    printf("\n");
    testSearch(namesTable3, "Lavern");
    testSearch(namesTable3, "Zac");
    testSearch(namesTable3, "Lavern");
    testSearch(namesTable3, "Destiny");
    testdestroyTable(namesTable3, TABLE_3);
    printWTableNum(namesTable3,TABLE_3);
    printf("Try to search for a name from the empty table...\n\n");
    testSearch(namesTable3, "Lavern");
    testSearch(namesTable3, "Destiny");
    printf("----------------------------------------------End of Tests----------------------------------------------------------------------------\n");
}

//------------------------------------------------------
//printWTableNum
//
// PURPOSE: Prints out the table with a number specifying which table
// INPUT PARAMETERS: A table pointer and a number for the table number
//------------------------------------------------------
void printWTableNum(Table *table,int tableNum)
{
    printf("\nNames in Table %d\n",tableNum);
    printTable(table);
}

//------------------------------------------------------
//testInsert
//
// PURPOSE: Inserts names into a table
// INPUT PARAMETERS: A table pointer and a list of names
//------------------------------------------------------
void testInsert(Table *table, char *namesList)
{
    printf("\nTesting Insertion...\n");
    char *token = NULL;
    token = strtok(namesList," ");
    Boolean noDup = true;
    while(token)
    {
        if(!search(table,token))
        {
            insert(table,token);
        }
        else
        {
            printf("Duplicate name in the list: '%s'\n",token);
            noDup = false;
        }
        
        token = strtok(NULL," ");
        testsExec++;
    }
    if(noDup)
    {
        printf("There was no two names that were the same!\n");
    }
    printf("\n");
}

//------------------------------------------------------
//testDeleteName
//
// PURPOSE: Deletes a name from a table
// INPUT PARAMETERS: A table pointer and a specified name
//------------------------------------------------------
void testDeleteName(Table *table,char *name)
{
    printf("Testing deletion of the name: '%s'...\n",name);
    if(isEmpty(table))
    {
        printf("There is no '%s' since the table is empty now\n",name);
    }
    else{
        if(!search(table,name))
        {
            printf("No name in the Table matches: '%s'!\n",name);
        }
        else if(!deleteNode(table,name) && search(table,name))
        {
            printf("The name was not deleted!\n");
            testFailed++;
        }
    }
    testsExec++;
}


//------------------------------------------------------
//testSearch
//
// PURPOSE: Searches for a name
// INPUT PARAMETERS: A table pointer and a specified name
//------------------------------------------------------
void testSearch(Table *table,char *name)
{
    
    printf("Testing search for the name: '%s'...\n",name);
    if(isEmpty(table))
    {
        printf("Not able to find '%s' since the table is empty now\n",name);
    }
    else{
        if(search(table, name))
        {
            printf("Found the name: '%s'\n",name);
        }
        else
        {
            printf("No name in the Table matches: '%s'\n",name);
        }
    }
    testsExec++;
}

//------------------------------------------------------
//testdestroyTable
//
// PURPOSE: Destroys a table
// INPUT PARAMETERS: A table pointer and a table number indicating which table we are working with
//------------------------------------------------------
void testdestroyTable(Table *table,int tableNum)
{
    printf("\nTesting deletion of Table %d...\n",tableNum);
    destroyTable(table);
    printf("\nConfirm Table %d is empty...",tableNum);
    if(!isEmpty(table))
    {
        printf("Table is not actually empty by checking the amount of Nodes");
        testFailed++;
    }
    testsExec++;
}







