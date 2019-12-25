=begin
//CLASS:    Guess
//AUTHOR:   Zachary Kolton
//REMARKS:  Represents a suggestion/accusation with all three symbols (suspect,place,weapon)
=end

class Guess
  
  attr_reader :suspect, :place, :weapon
  
  def initialize(person, loc, w, b)
    @suspect  = person
    @place    = loc
    @weapon   = w
    @isAccusation  = b                     #for "Boolean"
  end
  
=begin
  isAccusation
  PURPOSE:      return if it is an accusation or not based on boolean      
=end
  def isAccusation()
    @isAccusation
  end
end