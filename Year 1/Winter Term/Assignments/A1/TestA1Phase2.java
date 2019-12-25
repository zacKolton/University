
// Code to test Phase 2 of Assignment 1
public class TestA1Phase2 {
  
 
  public static void main(String[] args) {
    
 // Create two Book instances
    Book austen = new Book("To Kill a Mockingbird", "Jane Austen", 1990);
    Book lewis = new Book("The Lion, the Witch and the Wardrobe", "C.S. Lewis", 1950);


    // Create three holding instances (2 of the Austen book and 1 of the Lewis book)
    Holding holdingAusten1 = new Holding(austen);
    Holding holdingAusten2 = new Holding(austen);    
    Holding holdingLewis = new Holding (lewis);
    
    // Display the new holdings
    System.out.println("New holdings: ");   
    System.out.println(holdingAusten1);
    System.out.println(holdingAusten2);
    System.out.println(holdingLewis);
    System.out.println();
    
    // Check out one of the Austen holdings
    System.out.println("Checking out: " + holdingAusten2);
    holdingAusten2.checkOut();
    System.out.println("Is holding now available? " + holdingAusten2.isAvailable());
    System.out.println();
   
    // Display the holdings after the checkout operation
    System.out.println("Current holdings");
    System.out.println(holdingAusten1);
    System.out.println(holdingAusten2);
    System.out.println(holdingLewis);    
    System.out.println();
    
    // Check the Austen holding back in and display the holdings again
    System.out.println("Checking in: "  + holdingAusten2);
    holdingAusten2.checkIn();
    System.out.println();
    
    System.out.println("Current holdings");
    System.out.println(holdingAusten1);
    System.out.println(holdingAusten2);
    System.out.println(holdingLewis);
    System.out.println();
    
    // Trying the matches method:
    System.out.println("Is the ID for holdingAusten2 20180001? " + holdingAusten2.matches(20180001));
    System.out.println("Is the ID for holdingAusten2 20180002? " + holdingAusten2.matches(20180002));     
    
  }//main
}