//
//  main.c
//  sign
//
//  Created by Zac  Kolton on 2018-09-26.
//  Copyright © 2018 Zac Kolton. All rights reserved.
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int charcomp(const void *x,const void *y)
{
    return (*(char *) x - *(char *) y);
}
#define WORDMAX 100
int main(void)
{
    char word[WORDMAX],sig[WORDMAX];
    while(scanf("%s ",word)!= EOF)
    {
        strcpy(sig,word);
        qsort(sig,strlen(sig), sizeof(char),charcomp);
        printf("%s %s\n",sig,word);
        
    }
    return 0;
    
}

