=begin
//CLASS:    Card
//AUTHOR:   Zachary Kolton
//REMARKS:  Holds a symbol and a value to represent a card
=end



class Card

  @@person  = :person
  @@place   = :place
  @@weapon  = :weapon
  
  attr_reader :type, :value
  
  def initialize(t, v)
    @value = v
    @type = getType(t)
  end
  
=begin
  getType
  
PURPOSE:     Gets the correct type of the symbol 
PARAMETERS:  Symbol t
RETURN:      Symbol

=end
  
  def getType(t)
    result = nil
    if(t == @@person)
      result = @@person
    elsif(t == @@place)
      result = @@place
    elsif(t == @@weapon)
      result = @@weapon
    else
      puts(".getType(t) is broken or type '"+t.to_s+"' does not exist")
    end
    result 
  end
  
=begin
  isEqual
  
  PURPOSE:    Checks if a card is equal to this card  
  PARAMETERS: suspect, place, weapon (sus,loc,wep)
  RETURN:     Boolean
=end
  
  def isEqual(sus,loc,wep)
    @result = false
    if((@value == sus.value) || (@value == loc.value) || (@value == wep.value))
      @result = true
    end
    @result 
  end
  
=begin
  valueEqual
  
  PURPOSE:     Checks if the value is equal to this value  
  PARAMETERS:  value v
  RETURN:      Boolean
=end
  
  def valueEqual(v)
    @result = false
    if(@value == v)
      @result = true
    end
    @result
  end
  
=begin
  print
  
  PURPOSE:      print the card out
=end
  
  def print()
    "'"+@value.to_s+"'"
  end
end


