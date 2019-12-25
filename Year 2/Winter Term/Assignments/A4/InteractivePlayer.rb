=begin
//CLASS:    InteractivePlayer (Subclass of Players)
//AUTHOR:   Zachary Kolton
//REMARKS:  Represents the users player 
=end

require_relative "Players"

class InteractivePlayer < Players
                  
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
  
  PURPOSE:      gets a guess from user input (incomplete)  
=end
  def getGuess()
    super
  end
  
=begin
  recieveInfo
  PURPOSE:    Recieve info (incomplete)   
  PARAMETERS:   i, c (index and card)
=end
  def receiveInfo(i, c)
    super(i,c)
  end
  
  ##------------------#
  ##----- testing ----#
  ##------------------#
  
  def print()
    super()
  end
end
