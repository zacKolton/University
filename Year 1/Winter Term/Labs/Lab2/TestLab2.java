/**
 * Lab 2 Bronze test program
 * 
 * This class provides a main method to test the
 * HockeyTeam class of Objects
 */
public class TestLab2Bronze {
  
  public static void main(String[] args) {
    
    //Create the teams in the Central division of the NHL.
    //This is intentionally very simple brute-force code.
    HockeyTeam team1 = new HockeyTeam("Winnipeg",22,14,8);
    HockeyTeam team2 = new HockeyTeam("Chicago",28,13,2);
    HockeyTeam team3 = new HockeyTeam("Colorado",18,17,8);
    HockeyTeam team4 = new HockeyTeam("St. Louis",27,13,3);
    HockeyTeam team5 = new HockeyTeam("Dallas",19,16,7);
    HockeyTeam team6 = new HockeyTeam("Minnesota",18,19,5);
    HockeyTeam team7 = new HockeyTeam("Nashville",29,9,4);

    //Print out all 7 objects, again with simple code.
    System.out.println("Initial teams:\n" +
              team1 + "\n" + team2 + "\n" + team3 + "\n" + team4 + "\n" +
              team5 + "\n" + team6 + "\n" + team7 + "\n");
    
    //Record the results of some fictional games
    team1.won(); team2.lost();          //Winnipeg beat Chicago
    team1.won(); team3.lostOvertime();  //Winnipeg beat Colorado
    team1.won(); team4.lostOvertime();  //Winnipeg beat St. Louis
    team1.won(); team5.lost();          //Winnipeg beat Dallas
    team1.won(); team6.lost();          //Winnipeg beat Minnesota
    team1.won(); team7.lostOvertime();  //Winnipeg beat Nashville
    //OK. So the Jets are really hot right now.
    team4.won(); team2.lost();          //St. Louis beat Chicago
    team3.won(); team5.lost();          //Colorado beat Dallas   
    
    //Print out the 7 objects again, to see the changes.
    System.out.println("Final teams:\n" +
              team1 + "\n" + team2 + "\n" + team3 + "\n" + team4 + "\n" +
              team5 + "\n" + team6 + "\n" + team7 + "\n");
    
  }//main
  
}//TestLab2Bronze
