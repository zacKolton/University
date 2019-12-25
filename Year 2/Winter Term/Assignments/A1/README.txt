 Duplicate information at the end if that makes it easier for you

 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1

How to compile: 
I have put all the java source files into one folder (as well as this text doc). All that has to be done is compile them.

Notes on output:
I tried to make the output as clean as possible, and as organized as possible. The only hurdle I found was when there was comments (#). All you have to know is when a comment is made, the corresponding output will be below it, that's the only thing I found that may mistake you for a few seconds. 

You will see "Action" in the report of the blockChain, this is just to specify what created this particular action. It made it easier to debug actually.

Everything is separated by "---------", this was to make it more readable, for myself at least.

Last notes about assignment itself: 
There is testing methods in my Main class, I left those in so you could see how I tested the "trade" functions of my code, if that's part of the marks or anything. It is all at the bottom and clearly denoted.

****************************************************************

Type in file name (with .txt)
A1input.txt
---------------------------------------------------------------------------------------------
Comment:  create four investors
---------------------------------------------------------------------------------------------
Processing new INVESTOR...
Success:
	Name:	Investor One
	UserID:	invone
	Cash:	1000
---------------------------------------------------------------------------------------------
Processing new INVESTOR...
Success:
	Name:	Investor Two
	UserID:	invtwo
	Cash:	1000
---------------------------------------------------------------------------------------------
Processing new INVESTOR...
Success:
	Name:	Investor Three
	UserID:	invthree
	Cash:	1000
---------------------------------------------------------------------------------------------
Processing new INVESTOR...
Success:
	Name:	Investor Four
	UserID:	invfour
	Cash:	0
---------------------------------------------------------------------------------------------
Comment:  attempt to create duplicate investor
---------------------------------------------------------------------------------------------
Processing new INVESTOR...
ERROR Duplicate:
	Name:	I Nvone
	UserID:	invone
	Cash:	1000
---------------------------------------------------------------------------------------------
Comment:  create five cryptocurrencies
---------------------------------------------------------------------------------------------
Processing new CURRENCY...
Success:
	Name:	CRYPTO1
	Symbol:	CRY1
	Quant:	1000
---------------------------------------------------------------------------------------------
Processing new CURRENCY...
Success:
	Name:	CRYPTO2
	Symbol:	CRY2
	Quant:	1000
---------------------------------------------------------------------------------------------
Processing new CURRENCY...
Success:
	Name:	CRYPTO3
	Symbol:	CRY3
	Quant:	1000
---------------------------------------------------------------------------------------------
Processing new CURRENCY...
Success:
	Name:	CRYPTO4
	Symbol:	CRY4
	Quant:	1000
---------------------------------------------------------------------------------------------
Processing new CURRENCY...
Success:
	Name:	CRYPTO5
	Symbol:	CRY5
	Quant:	1000
---------------------------------------------------------------------------------------------
Comment:  attempt to create duplicate cryptocurrency
---------------------------------------------------------------------------------------------
Processing new CURRENCY...
ERROR Duplicate:
	Name:	CRYPTO55
	Symbol:	CRY5
	Quant:	0
---------------------------------------------------------------------------------------------
Comment:  successful mines
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'invone'	| Mining : 'CRY1'	|  Units: 500

Searching for: 'invone' and 'CRY1'...
Result: Success
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'invtwo'	| Mining : 'CRY1'	|  Units: 400

Searching for: 'invtwo' and 'CRY1'...
Result: Success
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'invtwo'	| Mining : 'CRY5'	|  Units: 10

Searching for: 'invtwo' and 'CRY5'...
Result: Success
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'invone'	| Mining : 'CRY2'	|  Units: 1000

Searching for: 'invone' and 'CRY2'...
Result: Success
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'invthree'	| Mining : 'CRY3'	|  Units: 100

Searching for: 'invthree' and 'CRY3'...
Result: Success
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'invthree'	| Mining : 'CRY3'	|  Units: 200

Searching for: 'invthree' and 'CRY3'...
Result: Success
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'invtwo'	| Mining : 'CRY3'	|  Units: 200

Searching for: 'invtwo' and 'CRY3'...
Result: Success
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'invone'	| Mining : 'CRY5'	|  Units: 10

Searching for: 'invone' and 'CRY5'...
Result: Success
---------------------------------------------------------------------------------------------
Comment:  investor not found
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'notfound'	| Mining : 'CRY1'	|  Units: 99

Searching for: 'notfound' and 'CRY1'...
Result: Failed, Investor not found, Currency found
---------------------------------------------------------------------------------------------
Comment:  currency not found
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'invone'	| Mining : 'CRY6'	|  Units: 1

Searching for: 'invone' and 'CRY6'...
Result: Failed, Investor found, Currency not found
---------------------------------------------------------------------------------------------
Comment:  insufficient funds - CRY4
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'invthree'	| Mining : 'CRY4'	|  Units: 1001

Searching for: 'invthree' and 'CRY4'...
Result: Failed, Investor found, Insufficient Currency
---------------------------------------------------------------------------------------------
Comment:  insufficient funds - CRY1
---------------------------------------------------------------------------------------------
Processing new MINE...
	User : 'invone'	| Mining : 'CRY1'	|  Units: 101

Searching for: 'invone' and 'CRY1'...
Result: Failed, Investor found, Insufficient Currency
---------------------------------------------------------------------------------------------
Comment:  trade - currency not found - CRY7
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY7'	| Amount of Units: 100
Result: Failed, Investor 'invtwo's currency not found
---------------------------------------------------------------------------------------------
Comment:  trade - investor not found - invten
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invten'	| Trading: 'CRY5'	| Amount of Units: 100
Result: Failed, InvestorB not found
---------------------------------------------------------------------------------------------
Comment:  trade - multiple not founds - only needs to detect one
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invten'	| Trading: 'CRY9'	| Amount of Units: 100
	Investor B: 'invnine'	| Trading: 'CRY8'	| Amount of Units: 100
Result: Failed, InvestorA and InvestorB not found
---------------------------------------------------------------------------------------------
Comment:  trade - insufficient funds for one investor - held
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 1000
	Investor B: 'invtwo'	| Trading: 'CRY5'	| Amount of Units: 10
Result: Failed, Investor 'invone' has insufficient funds
---------------------------------------------------------------------------------------------
Comment:  trade - insufficient funds for one investor - not held
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY4'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY5'	| Amount of Units: 10
Result: Failed, Investor 'invone's currency not found
---------------------------------------------------------------------------------------------
Comment:  trade - insufficient funds for both investors - held - only needs to detect one
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 1000
	Investor B: 'invtwo'	| Trading: 'CRY5'	| Amount of Units: 20
Result: Failed, Both Investors have insufficient funds
---------------------------------------------------------------------------------------------
Comment:  trade - insufficient funds for both investors - not held - only needs to detect one
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY4'	| Amount of Units: 1000
	Investor B: 'invthree'	| Trading: 'CRY5'	| Amount of Units: 1000
Result: Failed, Investor 'invone's currency not found
---------------------------------------------------------------------------------------------
Comment:  trade - insufficient CAD
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 499
	Investor B: 'invtwo'	| Trading: 'CAD'	| Amount of Units: 10000
Result: Failed, Investor 'invtwo' has insufficient funds
---------------------------------------------------------------------------------------------
Comment:  trade - same trader
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invone'	| Trading: 'CRY1'	| Amount of Units: 1
Result: Failed, Same trader
---------------------------------------------------------------------------------------------
Comment:  trade - multiple errors - only needs to report one
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invten'	| Trading: 'CRY9'	| Amount of Units: 1000
	Investor B: 'invone'	| Trading: 'CRY9'	| Amount of Units: 0
Result: Failed, InvestorA not found
---------------------------------------------------------------------------------------------
Comment:  successful trade - both cryptos, both new
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY2'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY3'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Comment:  successful trade - both cryptos, both existing
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY5'	| Amount of Units: 5
Result: Success
---------------------------------------------------------------------------------------------
Comment:  successful trade - one CAD - new currency
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 200
	Investor B: 'invthree'	| Trading: 'CAD'	| Amount of Units: 500
Result: Success
---------------------------------------------------------------------------------------------
Comment:  successful trade - one CAD - existing currency
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CAD'	| Amount of Units: 500
Result: Success
---------------------------------------------------------------------------------------------
Comment:  successful trade - both cryptos same
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Comment:  some .. uh .. more trades.
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 101
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 100
Result: Success
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invtwo'	| Trading: 'CRY1'	| Amount of Units: 101
Result: Success
---------------------------------------------------------------------------------------------
Comment:  successful trade - investor is traded out of a currency
---------------------------------------------------------------------------------------------
Processing new TRADE...
	Investor A: 'invone'	| Trading: 'CRY1'	| Amount of Units: 100
	Investor B: 'invthree'	| Trading: 'CRY3'	| Amount of Units: 300
Result: Success
---------------------------------------------------------------------------------------------
Comment:  nonexistent crypto report
---------------------------------------------------------------------------------------------
Processing 'CRY9's BlockChain report...
Result: Currency Not found
---------------------------------------------------------------------------------------------
Comment:  report all cryptos
---------------------------------------------------------------------------------------------
Processing 'CRY1's BlockChain report...
Result: Success
		 • Action: 'Mine'	 | Investor: 'invone'	| Amount: 500	| Hash: 0
		 • Action: 'Mine'	 | Investor: 'invtwo'	| Amount: 400	| Hash: 31
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 62
		 • Action: 'Sold'	 | Investor: 'invthree'	| Amount: 200	| Hash: 93
		 • Action: 'Sold'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 124
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 155
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 186
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 217
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 248
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 279
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 310
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 341
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 372
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 403
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 434
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 465
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 496
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 527
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 558
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 589
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 620
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 651
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 682
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 713
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 744
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 775
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 806
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 837
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 868
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 899
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 930
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 961
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 992
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 1023
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 1054
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 1085
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 1116
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 1147
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 1178
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 1209
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 1240
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 1271
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 1302
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 1333
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 1364
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 1395
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 1426
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 1457
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 1488
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 1519
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 1550
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 1581
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 1612
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 1643
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 1674
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 1705
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 1736
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 1767
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 1798
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 1829
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 1860
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 1891
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 1922
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 1953
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 1984
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 2015
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 2046
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 2077
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 2108
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 2139
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 2170
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 2201
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 2232
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 2263
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 2294
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 2325
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 2356
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 2387
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 2418
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 2449
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 2480
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 2511
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 2542
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 2573
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 2604
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 2635
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 2666
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 2697
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 2728
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 2759
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 2790
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 2821
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 2852
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 2883
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 2914
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 2945
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 2976
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 3007
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 3038
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 3069
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 3100
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 3131
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 3162
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 3193
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 3224
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 3255
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 3286
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 3317
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 3348
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 3379
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 3410
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 3441
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 3472
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 3503
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 3534
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 3565
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 3596
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 3627
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 3658
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 3689
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 3720
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 3751
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 3782
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 3813
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 3844
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 3875
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 3906
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 3937
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 3968
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 3999
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 4030
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 4061
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 4092
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 4123
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 4154
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 4185
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 4216
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 4247
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 4278
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 4309
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 4340
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 4371
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 4402
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 4433
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 4464
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 4495
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 4526
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 4557
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 4588
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 4619
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 4650
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 4681
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 4712
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 4743
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 4774
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 4805
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 4836
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 4867
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 4898
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 4929
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 4960
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 4991
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 5022
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 5053
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 5084
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 5115
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 5146
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 5177
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 5208
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 5239
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 5270
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 5301
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 5332
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 5363
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 5394
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 5425
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 5456
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 5487
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 5518
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 5549
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 5580
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 5611
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 5642
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 5673
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 5704
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 5735
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 5766
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 5797
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 5828
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 5859
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 5890
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 5921
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 5952
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 5983
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 6014
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 6045
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 6076
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 6107
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 6138
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 6169
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 6200
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 6231
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 6262
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 6293
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 6324
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 6355
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 6386
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 6417
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 6448
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 6479
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 6510
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 6541
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 6572
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 6603
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 6634
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 6665
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 6696
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 6727
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 6758
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 6789
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 6820
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 6851
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 6882
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 6913
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 6944
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 6975
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 7006
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 7037
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 7068
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 7099
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 7130
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 7161
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 7192
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 7223
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 7254
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 7285
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 7316
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 7347
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 7378
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 7409
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 7440
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 7471
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 7502
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 7533
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 7564
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 7595
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 7626
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 7657
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 7688
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 7719
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 7750
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 7781
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 7812
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 7843
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 7874
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 7905
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 7936
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 7967
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 7998
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 8029
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 8060
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 8091
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 8122
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 8153
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 8184
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 8215
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 8246
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 8277
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 8308
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 8339
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 8370
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 8401
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 8432
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 8463
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 8494
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 8525
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 8556
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 8587
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 8618
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 8649
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 8680
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 8711
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 8742
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 8773
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 8804
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 8835
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 8866
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 8897
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 8928
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 8959
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 8990
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 9021
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 9052
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 9083
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 9114
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 9145
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 9176
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 9207
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 9238
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 9269
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 9300
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 9331
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 9362
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 9393
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 9424
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 9455
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 9486
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 9517
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 9548
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 9579
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 9610
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 9641
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 9672
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 9703
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 9734
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 9765
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 9796
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 9827
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 9858
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 9889
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 9920
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 9951
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 9982
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 101	| Hash: 10013
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 10044
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 10075
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 101	| Hash: 10106
		 • Action: 'Trade'	 | Investor: 'invthree'	| Amount: 100	| Hash: 10137

---------------------------------------------------------------------------------------------
Processing 'CRY2's BlockChain report...
Result: Success
		 • Action: 'Mine'	 | Investor: 'invone'	| Amount: 1000	| Hash: 0
		 • Action: 'Trade'	 | Investor: 'invtwo'	| Amount: 100	| Hash: 31

---------------------------------------------------------------------------------------------
Processing 'CRY3's BlockChain report...
Result: Success
		 • Action: 'Mine'	 | Investor: 'invthree'	| Amount: 100	| Hash: 0
		 • Action: 'Mine'	 | Investor: 'invthree'	| Amount: 200	| Hash: 31
		 • Action: 'Mine'	 | Investor: 'invtwo'	| Amount: 200	| Hash: 62
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 100	| Hash: 93
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 300	| Hash: 124

---------------------------------------------------------------------------------------------
Processing 'CRY4's BlockChain report...
Result: BlockChain is empty 
---------------------------------------------------------------------------------------------
Processing 'CRY5's BlockChain report...
Result: Success
		 • Action: 'Mine'	 | Investor: 'invtwo'	| Amount: 10	| Hash: 0
		 • Action: 'Mine'	 | Investor: 'invone'	| Amount: 10	| Hash: 31
		 • Action: 'Trade'	 | Investor: 'invone'	| Amount: 5	| Hash: 62

---------------------------------------------------------------------------------------------
Comment:  nonexistent report investor
---------------------------------------------------------------------------------------------
Processing 'invteen's Portfolio report...
Result: Investor Not found
---------------------------------------------------------------------------------------------
Comment:  report all investors
---------------------------------------------------------------------------------------------
Processing 'invone's Portfolio report...
Result: Success
		 • Currency: 'CRY1'	| Amount: 1
		 • Currency: 'CRY2'	| Amount: 900
		 • Currency: 'CRY5'	| Amount: 15
		 • Currency: 'CRY3'	| Amount: 400

---------------------------------------------------------------------------------------------
Processing 'invtwo's Portfolio report...
Result: Success
		 • Currency: 'CRY1'	| Amount: 599
		 • Currency: 'CRY5'	| Amount: 5
		 • Currency: 'CRY3'	| Amount: 100
		 • Currency: 'CRY2'	| Amount: 100

---------------------------------------------------------------------------------------------
Processing 'invthree's Portfolio report...
Result: Success
		 • Currency: 'CRY3'	| Amount: 0
		 • Currency: 'CRY1'	| Amount: 300

---------------------------------------------------------------------------------------------
Processing 'invfour's Portfolio report...
Result: Portfolio is empty
---------------------------------------------------------------------------------------------
Comment:  END
---------------------------------------------------------------------------------------------
Program Completed
***********************************
Duplicate information at top if that makes it easier for you

 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1

How to compile: 
I have put all the java source files into one folder (as well as this text doc). All that has to be done is compile them.

Notes on output:
I tried to make the output as clean as possible, and as organized as possible. The only hurdle I found was when there was comments (#). All you have to know is when a comment is made, the corresponding output will be below it, that's the only thing I found that may mistake you for a few seconds. 

You will see "Action" in the report of the blockChain, this is just to specify what created this particular action. It made it easier to debug actually.

Everything is separated by "---------", this was to make it more readable, for myself at least.

Last notes about assignment itself: 
There is testing methods in my Main class, I left those in so you could see how I tested my code, if thats part of the marks or anything. It is all at the bottom and clearly denoted.
