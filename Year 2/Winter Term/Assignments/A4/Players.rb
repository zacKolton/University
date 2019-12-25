=begin
//CLASS:    Players (Abstract Parent class) 
//AUTHOR:   Zachary Kolton
//REMARKS:  Helps link InteractivePlayer and Player
=end



class Players
  
  attr_reader :card
  
 def Players.new(*args)
   if self == Players
     raise "Abstract Class Creation not Allowed"
   else
     super
   end
 end
 
 #-----------------------------------
 # Assignment Method(s) Implementation
 #-----------------------------------
 
=begin
  setup
   
   PURPOSE:      setup player with information
   PARAMETERS:   numP, indexCurr, susList, locList, wepList     
=end
 def setup(numP, indexCurr, susList, locList, wepList)
    @numPlayers = numP
    @index      = indexCurr
    @suspectList  = susList
    @locationList = locList
    @weaponList   = wepList
    @card         = nil
    @guess        = nil 
    @cardList      = Array.new()
  end
  
=begin
  setCard
  
  PURPOSE:     recieve a dealt card, add it to cardList 
  PARAMETERS:  c, a Card    
=end
  def setCard(c)
    @cardList.push(c)
  end
  
=begin
  canAnswer
  PURPOSE:      incomplete 
  PARAMETERS:   i, g (index and a guess)      
=end
  def canAnswer(i,g)
    @result = searchCard(g)
    if(@result != nil)
      puts("It worked: "+ @result.print())
    end
    @result
  end
  
=begin
  getGuess 
  
  PURPOSE:      gets a guess from user input (incomplete)     
=end
  def getGuess()
    @guess
  end
  
  #-----------------------------------
  # Helper Method(s) Implementation
  #-----------------------------------
  
=begin
  searchCard
  
  PURPOSE:    search for a card in the list    
  PARAMETERS:   g (guess)
  RETURN:      a card
=end
  def searchCard(g)
    @result = nil
    found = false
    i = 0
    while((i < @cardList.size) && !found)
      if(@cardList.at(i).isEqual(g.suspect,g.place,g.weapon))
        found = true
        result = @cardList.at(i)
      else
        i += 1
      end
    end
    @result
  end
  
=begin
  emptyList
  PURPOSE:   checks if a list is empty   
  PARAMETERS:   list
  RETURN:      Boolean
=end
 def emptyList(list)
   @result = false
   if((list != nil) && (list.size == 0))
     @result = true
   end
   @result
 end
  
  
  #-----------------------------------
  # Printing Method(s) Implementation
  #-----------------------------------
  def print()
    puts("Num players:   "+ @numPlayers.to_s + " Current index: " + @index.to_s)
    puts("Suspect List:  "+ printArray(@suspectList))
    puts("Location List: "+ printArray(@locationList))
    puts("Weapon List:   "+ printArray(@weaponList))
    puts("Dealt Cards:   "+ printArray(@cardList))
    puts("----------------------------------")
  end
  
  def printArray(a)
    result = "["
    (a.size).times{ |i| 
      if(i == (a.size - 1))
        result += a.at(i).print()
      else
        result += a.at(i).print() +", "
      end
    }
    result+="]"
  end
    
end