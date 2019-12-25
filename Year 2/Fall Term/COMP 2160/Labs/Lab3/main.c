//
//  main.c
//  lab3
//
//  Created by Zac  Kolton on 2018-10-09.
//  Copyright © 2018 Zac Kolton. All rights reserved.
//

#include <stdio.h>
#include <string.h>
#define LINE 1000


int main() {
    
    FILE *f = fopen("data.txt","r");
    char *customers[30][10];
    char buff[LINE];
    int numCust = 0;
    int token_num = 0;
    while((fgets(buff,LINE,f)) != NULL)
    {
        token_num = 0;
        char *token = strtok(buff,"|\n");
        while(token != NULL)
        {
            customers[numCust][token_num] = strdup(token);
            token_num++;
            token = strtok(NULL, "|\n");
        }
        numCust++;
    }
    for(int i = 0; i < numCust; i++) //good
    {
        for(int j = 0; j < token_num; j++)
        {
        printf("%s ",customers[i][j]);
        }
        printf("\n");
    }
    
    FILE *t = fopen("template.txt","r");
    char buffTemp[LINE];
    while(fgets(buffTemp, LINE, t) != NULL)
    {
        for(int i = 0; i<numCust; i++)
        {
            for(int j = 0; j<LINE;j++)
            {
                if(buffTemp[j] == '$')
                {
                    printf("found");
                }
            }
        }
    }
          
    return 0;
}
