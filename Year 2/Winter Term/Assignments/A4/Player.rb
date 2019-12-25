=begin
//CLASS:   Player (Subclass of Players) 
//AUTHOR:  Zachary Kolton
//REMARKS: Represents a computer player  
=end


require_relative "Players"

class Player < Players
  def initialize()
    @numPlayers = nil
    @index      = nil
    @suspectList  = nil
    @locationList = nil
    @weaponList   = nil
    @guess        = nil ##next step
    @cardList     = nil
  end
  
=begin
  setup
    
  PURPOSE:      setup player with information
  PARAMETERS:   numP, indexCurr, susList, locList, wepList    
=end
  
  def setup(numP, indexCurr, susList, locList, wepList)
    super(numP, indexCurr, susList, locList, wepList)
  end
  
=begin
  setCard
  
  PURPOSE:     recieve a dealt card, add it to cardList 
  PARAMETERS:  c, a Card     
=end
  
  def setCard(c)
    super(c)
  end
  
=begin
  canAnswer
  PURPOSE:      incomplete 
  PARAMETERS:   i, g (index and a guess)     
=end
  
  def canAnswer(i, g)
    super(i,g)
  end
  
=begin
  getGuess
  
  PURPOSE:      Make computer player guess 
  PARAMETERS:   
  RETURN:    Guess (incomplete)  
=end
  
  def getGuess()
    @person  = getValue(@suspectList)
    @place   = getValue(@locationtList)
    @weapon  = getValue(@weaponList)
  end
  
=begin
  getValue 
  
  PURPOSE:      Get a value from the list (excluding dealt cards)
  PARAMETERS:   list
  RETURN:      a value 
=end
  def getValue(list)
    i = 0
    @result = list.at(i)
    while((i < list.size) && inCardList(@result.value))
      @result = list.at(i) #Yes it checks again redundantly 
      i += 1               #Since speed doesnt matter in the course...
    end 
    @result
  end
  
=begin
  inCardList
  
  PURPOSE:      Checks if a value is in the dealt cards
  PARAMETERS:   value
  RETURN:      Boolean
=end
  def inCardList(value)
    @found = false
    i = 0
    while((i < @cardList.size) && !@found)
      if(@cardList.at(i).valueEqual(value))
        @found = true
      end
      i += 1
    end
    @found
  end
  
=begin
  recieveInfo
  PURPOSE:    Recieve info (incomplete)   
  PARAMETERS:   i, c (index and card)    
=end
  def receiveInfo(i, c)
    super(i, c)
  end
end

