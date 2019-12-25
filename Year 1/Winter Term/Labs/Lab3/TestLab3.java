/**
 * This program will test the Bronze exercise
 * (the Game class) for Lab 3 of COMP 1020.
 */
public class TestLab3Bronze  {
  
  public static void main(String[] args) {
    
    //Create 4 HockeyTeam objects
    HockeyTeam team1 = new HockeyTeam("Winnipeg",22,14,8);
    HockeyTeam team2 = new HockeyTeam("Chicago",28,13,2);
    HockeyTeam team3 = new HockeyTeam("Colorado",18,17,8);
    HockeyTeam team4 = new HockeyTeam("St. Louis",27,13,3);
    
    //Create 2 Game objects, and print suitable output
    System.out.println("Creating Chicago at Winnipeg game.");
    Game game1 = new Game(team1,team2);
    System.out.println(game1);

    System.out.println("Creating Colorado at St. Louis game.");
    Game game2 = new Game(team3,team4);
    System.out.println(game2);

    //Use the goal() method in all ways, with suitable output
    System.out.println(team2.getName()+" scores");
    game1.goal(team2);
    System.out.println(game1);

    System.out.println(team1.getName()+" scores");
    game1.goal(team1);
    System.out.println(game1);

    System.out.println(team4.getName()+" scores");
    game2.goal(team4);
    System.out.println(game2);

    System.out.println(team3.getName()+" scores");
    game2.goal(team3);
    System.out.println(game2);

    System.out.println(team2.getName()+" scores in the wrong game:");
    game2.goal(team2);

    System.out.println(team4.getName()+" scores in the wrong game:");
    game1.goal(team4);
   
    System.out.println(team1.getName()+" scores");
    game1.goal(team1);
    System.out.println(game1);

  }//main
  
}//TestLab3Bronze
