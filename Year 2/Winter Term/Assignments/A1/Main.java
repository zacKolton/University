import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
/**
 * Class:           Main
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: This program reads in commands from a text file.
 *          The commands are made to create a simplified version or a simulation of a Cryptocurrency blockchain/investor.
 *          The program reads line by line and splits the line into seperate parts (String[]).
 *          There is no error checking as per the assignment instructions, there is checking on whether something exists
 *          or not, and insufficient currency checks. But there will be no checking if (for example) the command doesnt 
 *          exist.
 *          
 */
public class Main
{
    public static final int COMMAND = 0;    //Command position in String[] from file 
    /*
     * main
     * 
     * Purpose: Accept a file typed into a console, and begin to run the private runProgram(Scanner) method.
     *          If the file doesnt exist the program will end
     * 
     */
    public static void main(String[] args)
    {
        //tests();

        System.out.println("Type in file name (with .txt)");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        try
        {
            Scanner file = new Scanner(new FileReader(input));
            runProgram(file);
        }catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

    }

    

    /*
     * runProgram
     * 
     * Purpose: This method reads the file and puts it into a String[].
     *              - It does not check if the commands/input is in the correct spot (assignment guidlines)
     *          It creates a investorList of type List.
     *          It creates a currencyList of type Currency.
     *              - These are used to store a list of all investors and currency 
     *          The method will update the investors/currencies and their seprate lists accordingly with the inputted 
     *          commands. It will also output text to the console if needed.
     *          
     * Process: 
     *          - Read file line by line, split it by whitespace into a String[].
     *          - Commands are always in the 0 spot of the String[].
     *              - Investors/Currencies/units have their specified spots aswell   
     *          - "NEW"     creates a new investor and adds it to the investor list if possible.
     *          - "CRYPTO"  creates a new currency and adds it to the currency list if possible.
     *          - "MINE"    allows an investor to obtain units of a currency  (if possible).
     *              - Adds/updates a portfolioEntry to the investor
     *              - Adds a block to the blockChain of the currency
     *          - "TRADE"   allows investors to trade currency (if possible).
     *              - They can trade cash
     *              - They can trade cash for a cryptocurrency
     *              - They can trade two cryptocurrency
     *          - "REPORT"  prints out portfolio of investor (if possible/ not empty).
     *          - "CRYPORT" prints out blockChain of currency (if possible/ not empty).
     *          - "#"       echoes the line in the file.
     *          - "END"     ends the program.
     */
    private static void runProgram(Scanner file)
    {
        List investorsList = new List();
        List currencyList  = new List();
        String line = "";
        while(file.hasNextLine() && !line.equals("END"))
        {
            line = file.nextLine();
            String[] parts = line.split("\\s+");
            System.out.println("---------------------------------------------------------------------------------------------");
            if(parts[COMMAND].equals("NEW"))
            {
                System.out.println("Processing new INVESTOR...");
                Investor temp = new Investor(parts[1] +" "+ parts[2],parts[3],Integer.parseInt(parts[4]));
                if(investorsList.add(temp))                                                         //add returns a boolean if equal or not 
                {
                    System.out.println("Success:\n"+temp.toString());
                }
                else
                {
                    System.out.println("ERROR Duplicate:\n"+ temp.toString());
                }
            }
            else if(parts[COMMAND].equals("CRYPTO"))
            {
                System.out.println("Processing new CURRENCY...");
                Currency temp = new Currency(parts[1], parts[2], Integer.parseInt(parts[3]));
                if(currencyList.add(temp))                                                          //add returns a boolean if equal or not
                {
                    System.out.println("Success:\n"+temp.toString());
                }
                else
                {
                    System.out.println("ERROR Duplicate:\n"+temp.toString());
                }
            }
            else if(parts[COMMAND].equals("MINE"))
            {
                System.out.println("Processing new MINE...");
                System.out.println("\tUser : '"+parts[1]+"'\t| Mining : '"+parts[2]+"'\t|  Units: "+parts[3]);
                System.out.println("\nSearching for: '"+parts[1]+"' and '"+parts[2]+"'...");

                Investor emptyInvestor = new Investor("temp",parts[1],0);                           //create a temporary investor (use for the equals method)
                Currency emptyCurrency = new Currency("temp",parts[2],0);                           //create a temporary currency (use for the equals method)

                Investor tempInvestor =  (Investor)investorsList.searchBasicItem(emptyInvestor);    //Search for investor (provide a pointer)
                Currency tempCurrency =  (Currency)currencyList.searchBasicItem(emptyCurrency);     //Search for currency (provide a pointer)
                int amount = Integer.parseInt(parts[3]);
                if(sucessfullMine(tempInvestor,tempCurrency,amount))                                //test aspects before updating components 
                {
                    Mine mine = new Mine(tempInvestor,tempCurrency,amount);
                    mine.updateFinancialComponents();
                }
            }
            else if(parts[COMMAND].equals("TRADE"))
            {
                System.out.println("Processing new TRADE...");
                System.out.println("\tInvestor A: '"+parts[1]+"'\t| Trading: '"+parts[3]+"'\t| Amount of Units: "+parts[4]);
                System.out.println("\tInvestor B: '"+parts[2]+"'\t| Trading: '"+parts[5]+"'\t| Amount of Units: "+parts[6]);

                boolean cashPurchaseA = parts[3].equals("CAD");                                                 //check if cash purchase
                boolean cashPurchaseB = parts[5].equals("CAD");                                                 //check if cash purchase
                int amountA = Integer.parseInt(parts[4]);
                int amountB = Integer.parseInt(parts[6]);

                Investor investorA = (Investor)investorsList.searchBasicItem(new Investor("temp",parts[1],0));  //Search for investor with empty/temp investor
                Investor investorB = (Investor)investorsList.searchBasicItem(new Investor("temp",parts[2],0));  //Search for investor with empty/temp investor

                Currency currencyA = (Currency)currencyList.searchBasicItem(new Currency("temp",parts[3],0));   //Search for currency with empty/temp currency
                Currency currencyB = (Currency)currencyList.searchBasicItem(new Currency("temp",parts[5],0));   //Search for currency with empty/temp currency
                if(investorCheck(investorA,investorB))                                                          //Makes sure investors are NOT null
                {
                    Transaction t         = null;                                                               //Create a transaction pointer (polymorphism)
                    PortfolioEntry entryA = investorA.searchPortfolio(parts[3]);                                //Search for currency in investor portfolio
                    PortfolioEntry entryB = investorB.searchPortfolio(parts[5]);                                //Search for currency in investor portfolio
                    if(cashPurchaseA || cashPurchaseB)                                                          //Check if there is a cash purchase for either investor
                    {
                        if(cashPurchaseA && cashPurchaseB)
                        {
                            if(successfullCashTrade(investorA,investorB,amountA,amountB))                       //check amounts of cash 
                            {
                                investorA.increaseUnits(amountB - amountA);                                     //increase by the difference
                                investorB.increaseUnits(amountA - amountB);                                     //increase by the difference
                            }
                        }
                        else if(!cashPurchaseA && cashPurchaseB)
                        {
                            if(successfullPurchase(investorA, investorB, entryA, amountA, amountB))             //check amount of cash and currency units
                            {
                                t = new Purchase(investorA, investorB, entryA, amountA, amountB);               //create a new transaction (purchase)
                                t.updateFinancialComponents();                                                  //update components since successfull
                            }
                        }
                        else
                        {
                            if(successfullPurchase(investorB, investorA, entryB, amountB, amountA))             //switch around for other investor 
                            {
                                t = new Purchase(investorB, investorA, entryB, amountB, amountA);               //create new transaction (purchase)
                                t.updateFinancialComponents();                                                  //update components since sucessfull
                            }
                        }
                    }
                    else                                                                                        //if not a cash trade or purchase, it must be a currency trade
                    {
                        if(successfullTrade(investorA, investorB, entryA, entryB, amountA, amountB))            //check amounts in portfolioes
                        {
                            t = new CurrencyTrade(investorA, investorB, entryA, entryB, amountA, amountB);      //create new transaction (currencyTrade)
                            t.updateFinancialComponents();                                                      //update components since sucessfull
                        }
                    }
                }
            }
            else if(parts[COMMAND].equals("REPORT"))
            {
                System.out.println("Processing '"+parts[1]+"'s Portfolio report...");
                Investor investor = (Investor)investorsList.searchBasicItem(new Investor("temp",parts[1],0));   //Get a investor pointer
                if(successfullReport(investor,"Investor"))                                                      //checks values, gives readable feeback
                {
                    System.out.println(investor.getList().report());                                            //print report since successfull
                }
            }
            else if(parts[COMMAND].equals("CRYPORT"))
            {
                System.out.println("Processing '"+parts[1]+"'s BlockChain report...");
                Currency currency = (Currency)currencyList.searchBasicItem(new Currency("temp",parts[1],0));    //Get a currency pointer
                if(successfullReport(currency,"Currency"))                                                      //checks values, gives readable feeback
                {
                    System.out.println(currency.getList().report());                                            //print report since successfull
                }
            }
            else if(parts[COMMAND].equals("#"))
            {
                System.out.println("Comment: "+line.substring(1));                                              //prints line, not including command 
            }
            else if(parts[COMMAND].equals("END"))
            {
                System.out.println("Program Completed");                                                        //Completion message 
            }
        }
    }

    /*
     * investorCheck
     * 
     * Purpose: Clean up code in main()
     *          Output something comprehensible
     *       
     * Parameters: Investor, Investor
     *          
     * Return: Boolean
     */
    private static boolean investorCheck(Investor a, Investor b)
    {
        boolean result = false;
        String s = "Result: ";
        if((a != null) && (b != null))
        {
            result = true;
        }
        else if((a != null) && (b == null))
        {
            System.out.println(s+"Failed, InvestorB not found");
        }
        else if((a == null) && (b != null))
        {
            System.out.println(s+"Failed, InvestorA not found");
        }
        else
        {
            System.out.println(s+"Failed, InvestorA and InvestorB not found");
        }
        return result;
    }

    /*
     * successfullReport
     * 
     * Purpose: Clean up code in main()
     *          Output something comprehensible
     *          Make sure components are in the clear (not null) before printing/nullpointerexception
     *       
     * Parameters: FinancialComponent, String
     *          
     * Return: Boolean
     */
    private static boolean successfullReport(FinancialComponent in, String instance)
    {
        boolean result = false;
        String s = "Result: ";
        if(in != null)
        {
            if(in.getList().isEmpty())
            {
                if(in instanceof Investor)                          //using instanceof to print either "portfolio" or "blockChain"
                {
                    System.out.println(s+"Portfolio is empty");
                }
                else if(in instanceof Currency)
                {
                    System.out.println(s+"BlockChain is empty ");
                }
            }
            else
            {
                result = true;
                System.out.println(s +"Success");
            }
        }
        else
        {
            System.out.println(s+instance+" Not found");
        }
        return result;
    }
    
    

    /*
     * successfullCashTrade
     * 
     * Purpose: Clean up code in main()
     *          Output something comprehensible
     *          Make sure components are in the clear (not null or insufficient) before making any updates 
     *       
     * Parameters: Investor,Investor,int,int 
     *          
     * Return: Boolean
     */
    private static boolean successfullCashTrade(Investor investorA, Investor investorB, int cashUnitsA, int cashUnitsB)
    {
        boolean result = false;
        String s = "Result: ";

        if(investorA.isEqual(investorB))
        {
            System.out.println(s+"Failed, Same trader");
        }
        else
        {
            if((investorA.getCash() < cashUnitsA) && (investorB.getCash() < cashUnitsB))
            {
                System.out.println(s+"Failed,Both Investors have insufficient funds");
            }
            else if((investorA.getCash() < cashUnitsA) && (investorB.getCash() >= cashUnitsB))
            {
                System.out.println(s+"Failed, Investor: '"+investorA.getUserID()+"' has insufficient funds");
            }
            else if((investorA.getCash() >= cashUnitsA) && (investorB.getCash() < cashUnitsB))
            {
                System.out.println(s+"Failed, Investor '"+investorB.getUserID()+"' has insufficient funds");
            }
            else
            {
                result = true;
                System.out.println(s+"Success");
            }
        }

        return result;
    }

    /*
     * successfullPurchase
     * 
     * Purpose: Clean up code in main()
     *          Output something comprehensible
     *          Make sure components are in the clear (not null or insufficient) before making any updates 
     *       
     * Parameters: Investor,Investor,PortfolioEntry,int,int 
     *          
     * Return: Boolean
     */
    private static boolean successfullPurchase(Investor seller, Investor buyer, PortfolioEntry pe,int currencyUnits, int cashUnits)
    {
        boolean result = false;
        String s = "Result: ";
        if(pe != null)
        {
            if(seller.isEqual(buyer))
            {
                System.out.println(s+"Failed, Same trader");
            }
            else
            {
                if((buyer.getCash() < cashUnits) && (pe.getUnits() > currencyUnits))
                {
                    System.out.println(s+"Failed, Investor '"+buyer.getUserID()+"' has insufficient funds");
                }
                else if((buyer.getCash() >= cashUnits) && (pe.getUnits() <= currencyUnits))
                {
                    System.out.println(s+"Failed, Investor '"+seller.getUserID()+"' has insufficient funds of '"+pe.getCurrency().getSymbol()+"'");
                }
                else if((buyer.getCash() < cashUnits) && (pe.getUnits() <= currencyUnits))
                {
                    System.out.println(s+"Failed, Both InvestorA and InvestorB have insufficient funds");
                }
                else
                {
                    result = true;
                    System.out.println(s+"Success");
                }
            }
        }
        else
        {
            System.out.println(s+"Failed, Investor '"+seller.getUserID()+"'s Currency not found");
        }
        return result;
    }

    /*
     * successfullTrade
     * 
     * Purpose: Clean up code in main()
     *          Output something comprehensible
     *          Make sure components are in the clear (not null or insufficient) before making any updates 
     *       
     * Parameters: Investor,Investor,PortfolioEntry,PortfolioEntry,int,int 
     *          
     * Return: Boolean
     */
    private static boolean successfullTrade(Investor investorA, Investor investorB, PortfolioEntry peA, PortfolioEntry peB, int unitsA, int unitsB)
    {
        boolean result = false;
        String s = "Result: ";
        if((peA != null) && (peB != null))
        {
            if(investorA.isEqual(investorB))
            {
                System.out.println(s+"Failed, Same trader");
            }
            else
            {
                if((peA.getUnits() < unitsA) && (peB.getUnits() < unitsB))
                {
                    System.out.println(s+"Failed, Both Investors have insufficient funds");
                }
                else if((peA.getUnits() < unitsA) && (peB.getUnits() >= unitsB))
                {
                    System.out.println(s+"Failed, Investor '"+investorA.getUserID()+"' has insufficient funds");
                }
                else if((peA.getUnits() >= unitsA) && (peB.getUnits() < unitsB))
                {
                    System.out.println(s+"Failed, Investor '"+investorB.getUserID()+"' has insufficient funds");
                }
                else 
                {
                    result = true;
                    System.out.println(s+"Success");
                }
            }
        }
        else if((peA != null) && (peB == null))
        {
            System.out.println(s+"Failed, Investor '"+investorB.getUserID()+"'s currency not found");
        }
        else
        {
            System.out.println(s+"Failed, Investor '"+investorA.getUserID()+"'s currency not found");
        }
        return result;
    }

    /*
     * successfullMine
     * 
     * Purpose: Clean up code in main()
     *          Output something comprehensible
     *          Make sure components are in the clear (not null or insufficient) before making any updates 
     *       
     * Parameters: Investor, Currency, int 
     *          
     * Return: Boolean
     */
    private static boolean sucessfullMine(Investor in, Currency c, int n)
    {
        String s = "Result: ";
        boolean result = false;
        if((in != null) && (c != null))
        {
            if(c.getUnits() < n)
            {
                System.out.println(s+"Failed, Investor found, Insufficient Currency");
            }
            else
            {
                result = true;
                System.out.println(s+"Success");
            }
        }
        else if((in != null) && (c == null))
        {
            System.out.println(s+"Failed, Investor found, Currency not found");
        }
        else if((in == null) && (c != null))
        {
            if(c.getUnits() < n)
            {
                System.out.println(s+"Failed, Investor not found, Insufficient Currency");
            }
            else
            {
                System.out.println(s+"Failed, Investor not found, Currency found");
            }
        }
        else
        {
            System.out.println(s+"Failed, Investor not found, Currency not found");
        }
        return result;
    }

    //|-------------------------------------------------------------------------------|
    //|-------------------------------------------------------------------------------|
    //| THE FOLLOWING IS ONLY TEST METHODS, THEY ARE NOT CALLED THROUGHOUT THE PROGRAM|
    //|-------------------------------------------------------------------------------|
    //|-------------------------------------------------------------------------------|
    
    private static void tests()
    {
        testPortfolio();
        testCashTrade();
        testCurrencyPurchase();
        testPurchaseUpdate();
        testTradeUpdate();
    }
    
    
    private static void testTradeUpdate()
    {
        Investor a = new Investor("Zac","zkolton",20);
        Investor b = new Investor("Jill","jilly",20);

        Currency curr1 = new Currency("coin1","C1",20);
        Currency curr2 = new Currency("coin2","C2",20);

        Mine mine1 = new Mine(a,curr1,20);
        mine1.updateFinancialComponents();

        Mine mine2 = new Mine(b,curr2,10);
        mine2.updateFinancialComponents();

        System.out.println(a.toString()+"\n");
        System.out.println(b.toString()+"\n");
        System.out.println(curr1.toString()+"\n");
        System.out.println(curr2.toString()+"\n");

        System.out.println("Now test a trade: \nzkolton trading 5 of C1 with \njilly trading 10 of C2");
        int amountA = 5;
        int amountB = 10;
        PortfolioEntry pe = a.getList().searchPortfolio("C1");
        PortfolioEntry pe1 = b.getList().searchPortfolio("C2");
        if(successfullTrade(a,b,pe,pe1,amountA,amountB))
        {
            CurrencyTrade ct = new CurrencyTrade(a,b,pe,pe1,amountA,amountB);
            ct.updateFinancialComponents();
        }
        System.out.println("Results: \n");
        System.out.println(a.toString()+"\n");
        System.out.println(b.toString()+"\n");
        System.out.println(curr1.toString()+"\n");
        System.out.println(curr2.toString()+"\n");

    }

    private static void testPurchaseUpdate()
    {
        Investor a = new Investor("Zac","zkolton",20);
        Investor b = new Investor("Jill","jilly",20);

        Currency curr1 = new Currency("coin1","C1",20);
        Mine mine1 = new Mine(a,curr1,20);

        mine1.updateFinancialComponents();

        System.out.println("Testing the method...");
        PortfolioEntry pe = a.getList().searchPortfolio("C1");
        int unitsA = 15;
        int unitsB = 10;
        System.out.println(a.toString()+"\n");
        System.out.println(b.toString()+"\n");
        System.out.println(curr1.toString()+"\n");
        if(successfullPurchase(a,b,pe,unitsA,unitsB))
        {
            Purchase p = new Purchase(a,b,pe,unitsA,unitsB);
            p.updateFinancialComponents();
        }

        System.out.println("\nResults: ");
        System.out.println(a.toString()+"\n");
        System.out.println(b.toString()+"\n");
        System.out.println(curr1.toString()+"\n");

    }

    private static void testCurrencyPurchase()
    {
        System.out.println("\n---------Testing Currency Purchase------------\n");
        Investor seller = new Investor("Zac","zkolton",20);
        Investor buyer  = new Investor("Jill","jilly",20);

        Currency curr1 = new Currency("coin1","C1",20);
        Currency curr2 = new Currency("coin2","C2",10);
        Currency curr3 = new Currency("coin1","C3",20);

        Mine mine1 = new Mine(seller,curr1,20);
        Mine mine2 = new Mine(seller,curr2,10);
        Mine mine3 = new Mine(seller,curr3,20);

        mine1.updateFinancialComponents();
        mine2.updateFinancialComponents();
        mine3.updateFinancialComponents();

        System.out.println(seller.toString()+"\n");
        System.out.println(buyer.toString()+"\n");
        System.out.println(curr1.toString()+"\n");
        System.out.println(curr2.toString()+"\n");
        System.out.println(curr3.toString());

        System.out.println("Test 'zkolton' selling  2 'C1' to 'jilly' for 9 CAD");
        PortfolioEntry pe = seller.getList().searchPortfolio("C1");
        int amountB = 9;
        int amountA = 2;
        if(successfullPurchase(seller,buyer,pe,amountA, amountB))
        {
            pe.decreaseUnits(amountA);
            seller.increaseUnits(amountB);
            buyer.addToList(new PortfolioEntry(pe.getCurrency(),amountA));
            buyer.decreaseUnits(amountB);
        }
        System.out.println("Show results...\n");
        System.out.println(seller.toString()+"\n");
        System.out.println(buyer.toString()+"\n");
        System.out.println(curr1.toString()+"\n");

        System.out.println("\nTest 'zkolton' selling 3 'C4' to 'jilly' for 9 CAD");
        int amountB1 = 9;
        int amountA1 = 3;
        if(successfullPurchase(seller,buyer,null,amountA1,amountB1))
        {
            pe.decreaseUnits(amountA1);
            seller.increaseUnits(amountB1);
            buyer.addToList(new PortfolioEntry(pe.getCurrency(),amountA1));
            buyer.decreaseUnits(amountB1);
        }
        System.out.println("Show results...\n");
        System.out.println(seller.toString()+"\n");
        System.out.println(buyer.toString()+"\n");
        System.out.println("\nC4 didnt exist");

        System.out.println("\nTest 'zkolton' selling 1 'C1' to 'jilly' for 200 CAD");
        int amountB2 = 200;
        int amountA2 = 1;
        PortfolioEntry pe1 = seller.getList().searchPortfolio("C1");
        if(successfullPurchase(seller,buyer,pe1,amountA2,amountB2))
        {
            pe1.decreaseUnits(amountA2);
            seller.increaseUnits(amountB2);
            buyer.addToList(new PortfolioEntry(pe1.getCurrency(),amountA2));
            buyer.decreaseUnits(amountB2);
        }
        System.out.println("Show results...\n");
        System.out.println(seller.toString()+"\n");
        System.out.println(buyer.toString()+"\n");
        System.out.println(curr1.toString()+"\n");

        System.out.println("\nTest 'zkolton' selling 200 'C3' to 'jilly' for 2 CAD");
        int amountB3 = 2;
        int amountA3 = 200;
        PortfolioEntry pe2 = seller.getList().searchPortfolio("C3");
        if(successfullPurchase(seller,buyer,pe2,amountA3,amountB3))
        {
            pe2.decreaseUnits(amountA3);
            seller.increaseUnits(amountB3);
            buyer.addToList(new PortfolioEntry(pe2.getCurrency(),amountA3));
            buyer.decreaseUnits(amountB3);
        }

        System.out.println("Show results...\n");
        System.out.println(seller.toString()+"\n");
        System.out.println(buyer.toString()+"\n");
        System.out.println(curr3.toString()+"\n");

        System.out.println("\nTest for a buyer with an empty portfolio");
        System.out.println("\nTest 'jilly' selling 200 'C3' to 'zkolton' for 2 CAD");
        int amountB4 = 2;
        int amountA4 = 200;
        PortfolioEntry pe3 = buyer.getList().searchPortfolio("C3");
        if(successfullPurchase(buyer,seller,pe3,amountA4,amountB4))
        {
            pe3.decreaseUnits(amountA4);
            buyer.increaseUnits(amountB4);
            seller.addToList(new PortfolioEntry(pe3.getCurrency(),amountA4));
            seller.decreaseUnits(amountB4);
        }
        System.out.println("Show results...\n");
        System.out.println(seller.toString()+"\n");
        System.out.println(buyer.toString()+"\n");
        System.out.println(curr3.toString()+"\n");

    }

    private static void testCashTrade()
    {
        System.out.println("---------Testing cash trade------------");
        System.out.println("Testing good entry...");
        Investor investorA = new Investor("Zac Kolton","zkolton",20);
        Investor investorB = new Investor("Jill Babah","myBabe",10);
        System.out.println("'"+investorA.getUserID()+"' Trading 15 cash with '"+investorB.getUserID()+"' Trading 2 cash");
        int amountA = 15;
        int amountB = 2;
        System.out.println(investorA.toString()+"\n");
        System.out.println(investorB.toString());
        if(successfullCashTrade(investorA,investorB,amountA,amountB))
        {
            investorA.increaseUnits(amountB - amountA);
            investorB.increaseUnits(amountA - amountB);   
        }
        System.out.println(investorA.toString()+"\n");
        System.out.println(investorB.toString());

        System.out.println("\n---------Testing bad cash trade------------\n");
        Investor investorA1 = new Investor("Zac Kolton","zkolton",20);
        Investor investorB1 = null;
        if(successfullCashTrade(investorA1,investorB1,amountA,amountB))
        {
            investorA.increaseUnits(amountB - amountA);
            investorB.increaseUnits(amountA - amountB);   
        }

    }

    private static void testPortfolio()
    {
        Investor invest = new Investor("Zac Kolton","zkolton",10);
        Currency curr   = new Currency("Curr","C25",10);
        Currency jill   = new Currency("jill","JillyCoin",15);

        Mine mine1 = new Mine(invest,curr,2);
        Mine mine2 = new Mine(invest,curr,2);
        Mine mine3 = new Mine(invest,curr,2);
        Mine mine4 = new Mine(invest,curr,2);
        Mine mine5 = new Mine(invest,curr,2);

        Mine mine6 = new Mine(invest,jill,3);
        Mine mine7 = new Mine(invest,jill,3);
        Mine mine8 = new Mine(invest,jill,3);
        Mine mine9 = new Mine(invest,jill,3);
        Mine mine10 = new Mine(invest,jill,3);

        mine1.updateFinancialComponents();
        mine2.updateFinancialComponents();
        mine3.updateFinancialComponents();
        mine4.updateFinancialComponents();
        mine5.updateFinancialComponents();

        mine6.updateFinancialComponents();
        mine7.updateFinancialComponents();
        mine8.updateFinancialComponents();
        mine9.updateFinancialComponents();
        mine10.updateFinancialComponents();        

        System.out.println(invest.toString());
        System.out.println(curr.toString());
        System.out.println(jill.toString());

        System.out.println("Test search for 'JillyCoin' and 'C25'...");
        PortfolioEntry pe = invest.getList().searchPortfolio("JillyCoin");
        PortfolioEntry pe1= invest.getList().searchPortfolio("C25");
        if((pe != null) && (pe1 != null))
        {
            System.out.println("Found: '"+pe.getCurrency().getSymbol()+"'");
            System.out.println("Found: '"+pe1.getCurrency().getSymbol()+"'");
        }
        else
        {
            System.out.println("Not found");
        }

    }
}

