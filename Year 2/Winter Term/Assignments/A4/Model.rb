=begin
//-----------------------------------------
// NAME             : Zachary Kolton
// STUDENT NUMBER   : 7838513
// COURSE           : COMP 2150
// INSTRUCTOR       : Mr. Boyer
// ASSIGNMENT       : 4
// QUESTION         : Model
//
// REMARKS: Maintain a list of cards representing Suspects, Locations, and Weapons
//          Maintain a list of players (1 interactive player, x number of Computer players)
//          Play the game, the method play is incomplete/not written 
//-----------------------------------------

=end



require_relative "InteractivePlayer"
require_relative "Player"
require_relative "Card"

=begin
//CLASS:    Model
//AUTHOR:   Zachary Kolton
//REMARKS:  Maintain a list of cards representing Suspects, Locations, and Weapons
//          Maintain a list of players (1 interactive player, x number of Computer players)
//          Play the game, the method play is incomplete/not written 
=end
class Model
  
  def initialize(susList, locList, wepList)
    @suspectList  = susList
    @locationList = locList
    @weaponList   = wepList
    @playerList   = nil
    @suspectWin   = nil
    @locationWin  = nil
    @weaponWin    = nil
    
  end
  
=begin
  setPlayers
  
  PURPOSE:     players are initailized in this method 
  PARAMETERS:  array of players
=end
  
  def setPlayers(array)
    @playerList = array
    n = @playerList.size
    n.times{ |i| @playerList[i].setup(n,i,@suspectList,@locationList,@weaponList)}
  end
  
=begin
  setupCards
  
PURPOSE:      Sets up the cards for the game    
=end
  
  def setupCards()
    randSus = rand(@suspectList.size)
    randLoc = rand(@locationList.size)
    randWep = rand(@weaponList.size)
    
    @suspectWin   = @suspectList.at(randSus)
    @locationWin  = @locationList.at(randLoc)
    @weaponWin    = @weaponList.at(randWep)
    
    @arrAllCards  = (@suspectList + @locationList + @weaponList).shuffle()
    dealCards()
  end
  
=begin
  dealCards
  
PURPOSE:    Helper for setup cards     
=end
  
  def dealCards()
    (@arrAllCards.size).times{ |i| 
      if(!(@arrAllCards.at(i).isEqual(@suspectWin,@locationWin,@weaponWin)))
      @playerList[i%(@playerList.size)].setCard(@arrAllCards.at(i))
    end
  }
  end
  
=begin
  play
  
PURPOSE:      Play the game (incomplete)  
=end
  
def play()
  puts("Incomplete")
end

  
end



