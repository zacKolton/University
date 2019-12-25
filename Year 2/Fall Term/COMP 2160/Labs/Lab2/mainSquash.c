//
//  squacshMain.c
//  squash
//
//  Created by Zac  Kolton on 2018-09-26.
//  Copyright © 2018 Zac Kolton. All rights reserved.
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define WORDMAX 100
int main(void) {
    char word[WORDMAX], sig[WORDMAX], oldsig[WORDMAX];
    int linenum = 0;
    strcpy(oldsig,"");
    while(scanf("%s %s",sig,word)!= EOF)
    {
        if((strcmp(oldsig,sig)!= 0) && (linenum >0))
        {
            printf("\n");
        }
        strcpy(oldsig,sig);
        linenum++;
        printf("%s ",word);
    }
    printf("\n");
    return 0;
}
