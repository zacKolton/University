
/**
 * A3KoltonZachary
 * 
 * COMP 2140 SECTION A02
 * Instructor: Mr. Guderian 
 * Assignment #3
 * @ZacKolton7838513
 * @Nov 8th/2018
 * 
 * Purpose: This class calls the other class "Game", where the game is to be played
 * 
 */
public class A3KoltonZac
{
    /**
     * Purpose:
     *      - Test class game
     *      - You can uncomment "testAlot(Game)" and comment "game.playGame()" to run hundreds of tests
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        System.out.println("Game in process...\n");
        //testAlot(game);
        game.playGame();
        System.out.println("---------------------------------------");
        System.out.println("Programmed by Zac Kolton");
        
    }

    /*****************************Test Stuff**********************************/
    

    private static void testAlot(Game g)
    {
        int tests = 0;
        while(tests < 200)
        {
            g.playGame();
            if(g.noWinner())
            {
                System.out.println("This one failed");
                break;
            }
            tests++;
        }
        System.out.println("Tests: "+ tests);
    }
}

/**
 * Purpose:
 *      - Set up a new game and play the game 
 *      - Holds a deck, two Hands (denoted as playerA and playerB), two stacks of revealed cards "PlayerStack" 
 *        (denoted as revealedA and revealedB)
 *      
 */
class Game
{
    Deck deck;
    Hand playerA;
    Hand playerB;
    PlayerStack revealedA;
    PlayerStack revealedB;
    /**
     * Purpose:
     *      - initialize the deck,hand queue(s),and revealed stack(s)
     */
    public Game()
    {
        deck      = new Deck();
        playerA   = new Hand();
        playerB   = new Hand();
        revealedA = new PlayerStack();
        revealedB = new PlayerStack();
    }

    /**
     * Purpose:
     *      - Set up the game
     *          - Deal the half deak into either hands (playerA and playerB)
     *      - Count each round and add it to the print out
     *      - Print the results of the winner "printResults()" and confirm it is the winner with a card count method
     * 
     * Process:
     *      - While neither of the hands are empty (ie. there isnt a winner yet) continue the game
     *      - Reveal a card from playerA and playerB
     *          - Then compare the ranks of those cards
     *          
     *      - A player wins the round if the ranks of its card is greater than the other players card
     *          - If they are equal, both players enter war "playWar()"
     *          - If they win the round, the winner gets their cards back and the losers cards until one of the
     *            players have no more cards
     * 
     * Variables:
     *      - revealA and revealB:
     *          - Store a int value from the front of each queue
     *          - These are the raw int values from the deck that do not have any rank yet
     *      - rankA and rankB:
     *          - Store the rank of RevealA and RevealB
     *      - winA and winB:
     *          - Store a boolean value 
     *          - true if one of the ranks is greater than the other
     *          - Used to make if statement more readeable 
     *      - update:
     *          - Stores a String value
     *          - prints out the update of the game 
     *          - Used to make pint out statements more readeable/cleaner
     */
    public void playGame()
    {
        deck.dealDeck(playerA,playerB);
        int round = 0;
        while((!playerA.isEmpty()) && (!playerB.isEmpty()))
        {

            int revealA = playerA.leave();
            int revealB = playerB.leave();

            revealedA.push(revealA);
            revealedB.push(revealB);

            int rankA = deck.getRank(revealA);
            int rankB = deck.getRank(revealB);

            boolean winA = rankA > rankB;
            boolean winB = rankB > rankA;

            String suitA = deck.getSuitName(revealA);
            String suitB = deck.getSuitName(revealB);
            String update = "Round: "+round+" | "+printUpdate(rankA,suitA,rankB,suitB);
            if(winA)
            {
                playerA.enter(revealedA.pop());
                playerA.enter(revealedB.pop());
                update += " ---> Player 1 won the round and takes both cards";
                System.out.println(update);
            }
            else if(winB)
            {
                playerB.enter(revealedB.pop());
                playerB.enter(revealedA.pop());
                update += " ---> Player 2 won the round and takes both cards";
                System.out.println(update);
            }
            else
            {
                update += " ---> Go to war";
                System.out.println(update);
                System.out.println("\nPlayer 1 and Player 2 are going to war...");
                System.out.println(playWar());
                System.out.println("--------------------------------------");
            }
            round++;
        }
        printResults();
    }

    /**
     * Purpose:
     *      - This is called from "playGame()" if the ranks of two revealed cards are equal
     *      - Returns a String to present the winner of each war game
     *          - Will return "Something went wrong" if there is no winner for whatever reason, if anything it
     *            would be from the boolean variables "winA" and "winB"
     * Process:
     *      - First it is checked if either players hand is already empty
     *          - If so, then it will send a notification "That was player (1/2)'s last card before playing war!!!
     *          - If it passes as true, it will enter into the while loop where war is played
     *          
     *      - Player A and B will remove two cards from their hand, called the "ante"
     *          - The revealed card is saved in a variable and pushed to the revealed stack
     *          - If they do not have enough cards to do so, the other player will win the war
     *              - The anties will be printed out if either player has them
     *              - You may notice playerB's ante wont be printed out on the first check for if playerA is now empty
     *                after the first reveal for the playerA's ante
     *                This is because if playerA is empty now, then playerB has all the cards.
     *                  -Therefore playerB wins the game and gets the last card that playerA had
     *          
     *      - Player A and B will also remove one more card on top of the ante, called the "top" card
     *          - These are the cards that will be use to compare ranks "compareA and compareB"
     *          - It is also checked if either player STILL has enough cards to provide a top card
     *              - If they do not have enough cards to do so, the other player will win the war
     *                  - The top cards will be printed out if either player has one
     *                  
     *      - The top cards are now compared
     *          - The winner of the war gets all of the other players cards
     *              - using playerAWarWin() or playerBWarWin();
     *          - If they are the same, the war continues and the above steps are made again
     *          
     *      - If any of the conditions are met the while loop will break to stop from any other value being pushed to 
     *        the revealed stack
     *          
     * Variables:
     *      - boolean winA and winB:
     *          - These are the deciding factors of who in the end wins the war
     *          - They are set to true on times when a player is empty 
     *          - They are set to true on times when a players rank is greater than the others
     *          - You will notice the opposite win(A/B) will be used on the player
     *            EXCEPT for when a players rank is greater than the other
     *              - ie. winA is used with a condition regarding the state of playerB
     *              - this is because playerA will win when playerB is false
     *              
     *      - boolean chooseA and chooseB:
     *          - These are used for the print out functions 
     *          - Mainly to minimize the amount of times System.out.println() is used
     *          - They choose which player to print out for 
     *              - chooseA is used to print out playerA's state
     *              - chooseB is used to print out playerB's state
     *              
     *      - String s:
     *          - Simply returns the winner of the war in a string format/readeable version
     *      
     *      - int revealA1,revealA2,revealB1,and revealB2:
     *          - holds the value of the ante card
     *          - never used to compare
     *          - only used to build the ante 
     *      
     *      - int compareA and compareB:
     *          - holds the value of the top card
     *          - passed to rankA and rankB to compare ranks of each players card
     *      
     *      -int rankA and rankB
     *          - holds the rank of the top card
     *          - computed by a function in a Deck "getRank(int)"
     *          - use to cpompare ranks to see who wins the war
     *              
     * 
     */
    public String playWar()
    {
        boolean winA = false;
        boolean winB = false;

        boolean chooseA = true;
        boolean chooseB = true;

        String s = "";
        if((!playerA.isEmpty()) && (!playerB.isEmpty())){
            while(((!playerA.isEmpty()) && (!playerB.isEmpty())) && (!winA && !winB))
            {
                //Reveal two cards from A for A's ante--------------------------------//
                //and check if player A is empty after removing
                int revealA1 = playerA.leave();
                revealedA.push(revealA1);
                if(playerA.isEmpty())
                {
                    winB = true;
                    printAnte(revealA1,chooseA,!chooseB);
                    System.out.println("Player 1 doesnt have a second ante!!!");
                    playerBWarWin();
                    break;
                }
                int revealA2 = playerA.leave();
                revealedA.push(revealA2);

                //Reveal two cards from B for B's ante--------------------------------//
                //and check if player B is empty after removing
                int revealB1 = playerB.leave();
                revealedB.push(revealB1);
                if(playerB.isEmpty())
                {
                    winA = true;
                    printAnte(revealA1,revealA2,chooseA,!chooseB);
                    printAnte(revealB1,!chooseA,chooseB);
                    System.out.println("Player 2 doesnt have a second ante!!!");
                    playerAWarWin();
                    break;
                }
                int revealB2 = playerB.leave();
                revealedB.push(revealB2);

                //Reveal one more card from A to compare with B-----------------------//
                //check A to make sure it isnt empty already
                if(playerA.isEmpty())
                {
                    winB = true;
                    printBothAnte(revealA1,revealA2,revealB1,revealB2);
                    System.out.println("\nPlayer 1 has no more cards for a top card!!!");
                    playerBWarWin();
                    break;
                }           
                int compareA = playerA.leave();
                revealedA.push(compareA);

                //Reveal one more card from B to compare with A-----------------------//
                //check B to make sure it isnt empty already
                if(playerB.isEmpty())
                {
                    winA = true;
                    printBothAnte(revealA1,revealA2,revealB1,revealB2);
                    topCardPrint(compareA,chooseA,!chooseB);
                    System.out.println("Player 2 has no more cards for a top card!!!");
                    playerAWarWin();
                    break;
                }
                int compareB = playerB.leave();
                revealedB.push(compareB);

                //Get the ranks of the two cards from A and B
                int rankA = deck.getRank(compareA);
                int rankB = deck.getRank(compareB);

                //Construct boolean conditions for winning cases
                boolean playerAWins = rankA > rankB;
                boolean playerBWins = rankB > rankA;  
                printWar(revealA1,revealA2,revealB1,revealB2,rankA,rankB,compareA,compareB);
                if(playerAWins)
                {
                    playerAWarWin();
                    winA = true;
                    break;
                }
                else if(playerBWins)
                {
                    playerBWarWin();
                    winB = true;
                    break;
                }
                else
                {
                    System.out.println("\nPlayer 1 and Player 2 are going to war...again");
                    checkPlayersInWar();
                    if(playerA.isEmpty())
                    {
                        printAnte(revealB1,revealB2,!chooseA,chooseB);
                        topCardPrint(compareB,!chooseA,chooseB);
                        playerBWarWin();
                        winB = true;
                        break;
                    }
                    else if(playerB.isEmpty())
                    {
                        printAnte(revealA1,revealA2,chooseA,!chooseB);
                        topCardPrint(compareA,chooseA,!chooseB);
                        playerAWarWin();
                        winA = true;
                        break;
                    }
                    
                }
            }
        }
        else
        {
            if(playerA.isEmpty())
            {
                System.out.println("\nThat was player 1's last card before playing war!!!\n");
                playerBWarWin();
                winB = true;
            }
            else if(playerB.isEmpty())
            {
                System.out.println("\nThat was player 2's last card before playing war!!!\n");
                playerAWarWin();
                winA = true;
            }
        }

        if(winA)
        {
            s = "\nPlayer 1 won the war";
        }
        else if(winB)
        {
            s = "\nPlayer 2 won the war";
        }
        else
        {
            s = "\nSomething went wrong";
        }
        return s;
    }

    /*************************************Update functions**********************************/

    /**
     * Purpose:
     *      - To minimize amount of repetitive code in playWar()
     *      - This function is called when playerA wins the war 
     *      - It adds to playerA's cards from both revealed stacks 
     * 
     * Process:
     *      - While each of the stacks are not empty, enter in the top card from the revealed stack 
     *          - Do this for both stacks, beacuse during playWar() the stacks are added to 
     * 
     * Notes:
     *      - I didnt use chooseA and chooseB for these functions because I found it was actually more code to write 
     */
    private void playerAWarWin()
    {
        while(!revealedB.isEmpty())
        {
            playerA.enter(revealedB.pop());
        }
        while(!revealedA.isEmpty())
        {
            playerA.enter(revealedA.pop());
        }
    }

    /**
     * Purpose:
     *      - To minimize amount of repetitive code in playWar()
     *      - This function is called when playerB wins the war 
     *      - It adds to playerB's cards from both revealed stacks 
     * 
     * Process:
     *      - While each of the stacks are not empty, enter in the top card from the revealed stack 
     *          - Do this for both stacks, beacuse during playWar() the stacks are added to 
     * 
     * Notes:
     *      - I didnt use chooseA and chooseB for these functions because I found it was actually more code to write 
     */
    private void playerBWarWin()
    {
        while(!revealedA.isEmpty())
        {
            playerB.enter(revealedA.pop());
        }
        while(!revealedB.isEmpty())
        {
            playerB.enter(revealedB.pop());
        }
    }

    

    /*********************Print out functions*****************************/
    
    
    /**
     * Purpose:
     *      - To minimize amount of repetitive code in playWar()
     *      - A simple print out showing if playerA or playerB was empty before playing war for the second/third/fourth...
     *        time
     *          - They may be empty because during war the cards are removed (leave()) from the players queues to the 
     *           revealed stacks
     */
    private void checkPlayersInWar()
    {
        if(playerA.isEmpty())
        {
            System.out.println("Player 1 was already empty, before playing war again!!!\n");
        }
        else if(playerB.isEmpty())
        {
            System.out.println("Player 2 was already empty, before playing war again!!!\n");
        }
    }
    
    /**
     * Purpose:
     *      - Print out an update of the game 
     */
    private String printUpdate(int rankA,String suitA, int rankB, String suitB)
    {
        return "Player 1: "+deck.printRank(rankA)+suitA +" | Player 2: "+deck.printRank(rankB)+suitB;
    }

    /**
     * Purpose:
     *      - Print out an update of the war 
     * 
     * Notes:
     *      - I could have used a smaller signature (removing rankA and rankB), but i felt it was just eaiser to use
     *        variables that already had the rank instead of typing out 
     *              "deck.printRank(deck.getRank(int)" twice
     *        - What i am reffering to is noted below
     */
    private void printWar(int revealA1,int revealA2,int revealB1,int revealB2,int rankA,int rankB,int compareA,int compareB)
    {
        System.out.println("\nPlayer 1 ante:");
        System.out.println(deck.printRank(deck.getRank(revealA1)) + deck.getSuitName(revealA1));
        System.out.println(deck.printRank(deck.getRank(revealA2)) + deck.getSuitName(revealA2));
        System.out.println("Player 2 ante:");
        System.out.println(deck.printRank(deck.getRank(revealB1)) + deck.getSuitName(revealB1));
        System.out.println(deck.printRank(deck.getRank(revealB2)) + deck.getSuitName(revealB2));
        System.out.println("\nCompare the top cards");
        //See notes for below 
        System.out.println("Player 1: "+deck.printRank(rankA)+ deck.getSuitName(compareA)); //here and
        System.out.println("Player 2: "+deck.printRank(rankB)+ deck.getSuitName(compareB)); //here 
    }

    /**
     * Purpose:
     *      - Clean up code in playWar()
     *      - By not always having to print out which player, instead it "chooses" which player to print out
     *      - This printAnte() only prints out the ante if there are two cards in the ante
     *
     * Note:
     *      - There is more than one function with the name printAnte(),
     *      - They each have a certain purpose 
     */
    private void printAnte(int reveal1,int reveal2,boolean chooseA, boolean chooseB)
    {
        if(chooseA)
        {
            System.out.println("\nPlayer 1 ante");
        }
        else if(chooseB)
        {
            System.out.println("\nPlayer 2 ante");
        }
        System.out.println(deck.printRank(deck.getRank(reveal1)) + deck.getSuitName(reveal1));
        System.out.println(deck.printRank(deck.getRank(reveal2)) + deck.getSuitName(reveal2));
    }

    /**
     * Purpose:
     *      - Clean up code in playWar()
     *      - This just calls the printAnte() above twice, instead of typing out the one above
     *      
     * Notes:
     *      - The boolean's are just fillers for the printAnte() signature 
     *          - They also choose which ante to print
     */
    private void printBothAnte(int revealA1,int revealA2,int revealB1,int revealB2)
    {
        boolean chooseA = true;
        boolean chooseB = true;
        printAnte(revealA1,revealA2,chooseA,!chooseB);
        printAnte(revealB1,revealB2,!chooseA,chooseB);

    }

    /**
     * Purpose:
     *      - Clean up code in playWar()
     *      - By not always having to print out which player, instead it "chooses" which player to print out
     *      - This printAnte() only prints out the ante if there is only one card in the ante
     *
     * Note:
     *      - There is more than one function with the name printAnte(),
     *      - They each have a certain purpose 
     */
    private void printAnte(int compareCard,boolean chooseA,boolean chooseB)
    {
        if(chooseA)
        {
            System.out.println("\nPlayer 1 ante");
        }
        else if(chooseB)
        {
            System.out.println("Player 2 ante");
        }
        System.out.println(deck.printRank(deck.getRank(compareCard)) + deck.getSuitName(compareCard));
    }

    /**
     * Purpose:
     *      - Clean up code in playWar()
     *      - Specifically made to print out the top card of each player in playWar()
     */
    private void topCardPrint(int compare,boolean chooseA,boolean chooseB)
    {
        if(chooseA)      
        {
            System.out.println("\nPlayer 1 top card:");
            System.out.println(deck.printRank(deck.getRank(compare)) + deck.getSuitName(compare));
        }
        else if(chooseB) 
        {
            System.out.println("\nPlayer 2 top card:");
            System.out.println(deck.printRank(deck.getRank(compare)) + deck.getSuitName(compare));
        }
    }
    
    
    /**
     * Purpose:
     *      - Used to print out the results if either player wins
     *      - Called in playGame()
     *      - Also used for debugging 
     *          - Hence the "checkAmount()" function
     */
    public void printResults() 
    {
        if(playerA.isWinner())
        {
            System.out.println("\n\nPlayer 1 won the game and has: " + playerA.checkAmount() + " cards");
        }

        if(playerB.isWinner())
        {
            System.out.println("\n\nPlayer 2 won the game and has: " + playerB.checkAmount() + " cards");
        }
    }
    
    /*********************TestStuff*****************************/
    
    /**
     * Purpose:
     *      - Specifically made for testing/dubggging in the class A3KoltonZac 
     *      - It will stop the game being played if there was no winner 
     *      - Is not called if "testAlot(Game g)" in A3KoltonZac is uncommented
     */
    public boolean noWinner()
    {
        return !playerA.isWinner() && !playerB.isWinner();
    }

}


/**
 * Purpose:
 *      - Represents a deck of cards 
 * 
 * Variables:
 *        MAX_CARDS = 52; --> represents the amx amount of cards int a deck/array of ints
 *        
 *        - These are variables that are used in getSuitName(int c)
 *            SPADES    = 0;  --> represents the spades suit
 *            DIAMONDS  = 1;  --> represents the diamonds suit
 *            HEARTS    = 2;  --> represents the hearts suit
 *            CLUBS     = 3;  --> represents the clubs suit
 *        - int[] deck --> represents the arra of elements in a deck 
 */
class Deck
{
    public static final int MAX_CARDS = 52;
    public static final int SPADES    = 0;
    public static final int DIAMONDS  = 1;
    public static final int HEARTS    = 2;
    public static final int CLUBS     = 3;
    private int[] deck;

    public Deck()
    {
        deck = makeDeck();
    }

    /**
     * Prupose:
     *      - return the rank of a card
     */
    public int getRank(int c)
    {
        return 1 + (c % 13);
    }

    /**
     * Purpose:
     *      - Return a String representing the number/letter rank of a card
     * 
     * Notes:
     *      - the int passed to this function is already the rank 
     *      - so there no need to convert the int to a rank in this function
     */
    public String printRank(int c)
    {
        String s = "";
        if(c <= 10)
        {
            if(c == 1)
            {
                s = "A";
            }
            else
            {
                s = Integer.toString(c);
            }
        }
        else
        {
            if(c == 11)
            {
                s = "J";
            }
            else if(c == 12)
            {
                s = "Q";
            }
            else
            {
                s = "K";
            }
        }
        return s;
    }

    /**
     * Purpose:
     *      - Return the symbol of the suit 
     *
     * Notes:
     *      - Now that i thnk of it i should have named it "getSuitSym()"
     */
    public String getSuitName(int c)
    {
        int temp = c/13;
        String s = "";
        if(temp == DIAMONDS)    //1
        {
            s = "♦"; 
        }
        else if(temp == HEARTS) //2
        {
            s = "♥";
        }
        else if(temp == CLUBS)  //3
        {
            s = "♣";
        }
        else
        {
            s = "♠";
        }
        return s;
    }

    /**
     * Purpose:
     *      - Deal the deack into both Hands evenly 
     *      - One deck gets the first have and the other deck gets the other half 
     */
    public void dealDeck(Hand a,Hand b)
    {
        int halfDeck = MAX_CARDS/2;
        int i  = 0;
        int j  = halfDeck;
        while((i< halfDeck) && (j < MAX_CARDS))
        {
            a.enter(deck[i]);
            b.enter(deck[j]);
            i++;
            j++;
        }
    }

    /**
     * Purpose:
     *      - Make a generic array of ints 0-51
     *      - Then shuffle/randomize the deck
     * Notes:
     *      - I thought of just adding in random numbers in each slot, but then i realized you would not 
     *        always/ever get all the values from 0-51 
     */
    private int[] makeDeck()
    {
        int[] temp = new int[MAX_CARDS];
        for(int i = 0; i<temp.length; i++)
        {
            temp[i] = i;
        }
        shuffleDeck(temp);
        return temp;
    }

    /**
     * Purpose:
     *      - Shuffle the deck by swapping random elements 
     *      - If the postions in the array are the same (next to impossible but possible) 
     *        change the y position 
     *      - Then swap the elements
     *        
     */
    private void shuffleDeck(int[] d)
    {
        for(int i = 0; i<d.length*2;i++) //shuffle everything twice 
        {
            int x = (int)(Math.random() * d.length);
            int y = (int)(Math.random() * d.length);
            if(x == y)
            {
                y = (int)(Math.random() * d.length-1);
            }
            swap(d,x,y);
        }
    }
    
    private void swap(int[] d, int x,int y)
    {
        int temp = d[x];
        d[x] = d[y];
        d[y] = temp;
    }

    /********************************Testing functions********************************/
    
    /**
     * Purpose:
     *      - Mainly for testing/debugging
     *      - Is never actually called
     *      - Was used to make sure deck was randomized
     */
    public void print()
    {
        String s = "[";
        for(int i = 0; i<deck.length;i++)
        {
            if(i == deck.length-1)
            {
                s+=deck[i];
            }
            else
            {
                s+=deck[i]+" ";
            }
        }
        s+= "]";
        System.out.println(s);
    }

}

/**
 * Purpose:
 *      - Represent a Stack of revealed cards from the players hands
 *      - Array implementation
 *
 * Variables:
 *      - final int MAX_CARDS --> represents the max amount of cards a player can have revealed 
 *           - The chances of this actually happening are next to impossible 
 *           
 *      - int top -----------> represnts the element at the top of the stack
 *      - int[] stackArray --> represents a stack of elements 
 */
class PlayerStack
{
    public static final int MAX_CARDS = 52;
    private int top;
    private int[] stackArray;

    public PlayerStack()
    {
        top = -1;
        stackArray = new int[MAX_CARDS];
    }

    /**
     * Purpose:
     *      - add an element to the top of the stack
     *      - change top to point to the new element 
     */
    public void push(int x)
    {
        if(!isFull())
        {
            stackArray[++top] = x;
        }
    }

    /**
     * Purpose:
     *      - remove an element from the top of the stack 
     *      - change top to point to the element before top if the stack isnt empty
     * Notes:
     *      - Will return Integer.MIN_VALUE if a function tries to remove something from an empty stack
     */
    public int pop()
    {
        int result = Integer.MIN_VALUE;
        if(!isEmpty())
        {
            result = stackArray[top];
            top--;
        }
        return result;
    }

    /**
     * Purpose:
     *      - return the top element
     */
    public int top()
    {
        return stackArray[top];
    }

    public boolean isEmpty()
    {
        return top == -1;
    }

    public boolean isFull()
    {
        return top == MAX_CARDS-1;
    }

    
    /*************************************Testing Stuff***************************************/
    
    
    /**
     * Purpose:
     *      - Print out the stack
     *      - Mainly was used for testing/debugging
     */
    public void print()
    {
        String s = "[";
        for(int i = 0; i<=top;i++)
        {
            if(i == top)
            {
                s+= stackArray[i];
            }
            else
            {
                s+=stackArray[i]+" ";
            }
        }
        s += ">";
        System.out.println(s);
    }
}

/**
 * Purpose:
 *      - Represent a Queue of cards from the Deck
 *      - Array implementation
 *      - Used for each player in the game
 *      
 * Variables:
 *      - final int MAX_CARDS --> represents the max amount of cards a hand can hold 
 *      - int front ----> represents a pointer to the front of the queue
 *      - int end   ----> represents a pointer to the end of the queue
 *      - int[] queue --> represents a queue by using an array implementation
 */
class Hand 
{
    public static final int MAX_CARDS = 52;
    private int front;
    private int end;
    private int[] queue;

    public Hand()
    {
        front = 0;
        end = MAX_CARDS-1;
        queue = new int[MAX_CARDS];
    }

    /**
     * Purpose:
     *      - Increment the end pointer
     *      - Add a int to the end of the queue
     */
    public void enter(int x)
    {
        end = (end+1)%MAX_CARDS;
        queue[end] = x;
    }

    /**
     * Purpose:
     *      - Return the result at the front
     *      - Move all the values in the queue one closer to the front
     *      - Decrement end pointer
     */
    public int leave()
    {
        int result = queue[front];
        for(int i = 0; i<=end;i++)
        {
            queue[i] = queue[i+1];
        }
        end--;
        return result;
    }

    public int front()
    {
        return queue[front];
    }

    public boolean isEmpty()
    {
        return ((end+1)%MAX_CARDS) == 0;
    }
    
    /**
     * Purpose:
     *      - to check how many cards a hand has to validate it is actaully a winner
     * Process:
     *      - count up to (and including) end
     *      - return the count
     */
    public int checkAmount()
    {
        int count = 0;
        while(count<=end)
        {
            count++;
        }
        return count;
    }
    
    /**
     * Purpose:
     *      - Return a boolean deciding if the hand is winner or not
     *      - Hand is a winner if pointer end has MAX_CARDS-1 cards
     */
    public boolean isWinner()
    {
        return end == MAX_CARDS-1;
    }

    /**********************************Testing Stuff************************/
    
    /**
     * Purpose:
     *      - Print out the queue
     *      - Mainly for testing 
     *          - Never actually called
     */
    public void print()
    {
        String s = "<";
        if(!isEmpty())
        {
            for(int i = 0; i<=end;i++)
            {
                if(i == end)
                {
                    s+= queue[i];
                }
                else
                {
                    s+= queue[i]+" ";
                }
            }
        }
        s+="<";
        System.out.println(s);
    }
}
