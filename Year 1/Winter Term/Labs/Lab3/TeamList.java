/**
 * A class that implements a list of HockeyTeam objects.
 */
import java.util.Arrays; //To access the Arrays.sort method

public class TeamList {
  
  //Class variables
  private final int MAX_TEAMS = 64; //The maximum capacity of one of these lists
  private int numTeams;       //The number of teams in the list
  private HockeyTeam[] teams; //A list of all of them
      
  //The constructor
  public TeamList() { 
    numTeams = 0;
    teams = new HockeyTeam[MAX_TEAMS];
  }//constructor
  
  public void addTeam(HockeyTeam newTeam){
    teams[numTeams++]=newTeam;
  }//addTeam
  
  public String toString(){
    String result = "";
    for(int i=0; i<numTeams; i++)
      result += teams[i]+"\n";
    return result;
  }//toString
      
  //The method that sorts all of the teams
  //This relies on the fact that HockeyTeam has a compareTo() method
  public void sortTeams(){
    Arrays.sort(teams,0,numTeams);
  }//sortTeams
  
  //Find a team in the list by searching for its name.
  //Return null if the team could not be found.
  public HockeyTeam findTeam(String name){
    for(int i=0; i<numTeams; i++)
      if(teams[i].getName().equals(name))
        return teams[i];
    return null;
  }//findTeam
    
}//HockeyTeam class
