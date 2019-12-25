/**
 * A class that implements data for a hockey team.
 * The name of the team, and the number of wins, losses,
 * and overtime losses, will be stored for the team.
 */
public class HockeyTeam implements Comparable {
  
  //Instance variables
  private String name;          //The team name
  private int wins;             //How many wins the team has
  private int losses;           //How many losses the team has
  private int overtimeLosses;   //How many overtime losses the team has
    
  //The constructor for a HockeyTeam. All four data items are required.
  public HockeyTeam(String newName, int w, int l, int ot) { 
    name = newName;
    wins = w;
    losses = l;
    overtimeLosses = ot;
  }//constructor
  
  //One method to record each of wins, losses, and overtime losses.
  public void won(){
    wins++;
  }//won
  
  public void lost(){
    losses++;
  }//lost
  
  public void lostOvertime(){
    overtimeLosses++;
  }//lostOvertime
  
  //Calculate and return the number of points in the standings for the team.
  public int points(){
    return 2*wins + 1*overtimeLosses;
  }//points
  
  //Create a suitable String representing the data in the HockeyTeam object
  public String toString(){
    return name + "(" + wins + "," + losses + "," + overtimeLosses + 
                  "=" + points() + ")";
  }//toString
  
  //Accessor method for the name of the team
  public String getName(){
    return name;
  }//getName
  
  //The compareTo method which ranks the teams
  public int compareTo(Object otherObject){
    HockeyTeam otherTeam = (HockeyTeam)otherObject; //It had better be a HockeyTeam!
    int difference = otherTeam.points()-this.points();
    return difference; //This will correctly be +, 0, -. The exact number doesn't matter.
  }//compareTo

}//HockeyTeam
