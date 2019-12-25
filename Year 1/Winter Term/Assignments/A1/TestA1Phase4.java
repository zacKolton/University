public class TestA1Phase4 {
  
  public static void main(String[] args) {
   
    // Create two Library instances
    Library pembinaTrails = new Library("Pembina Trails", 100);
    Library  millennium = new Library("Millennium", 80);
    
    // Create 4 different books
    Book lee = new Book("To Kill a Mockingbird", "Harper Lee", 1960);
    Book lewis = new Book("The Lion, the Witch and the Wardrobe", "C.S. Lewis", 1950);
    Book moriartyLies = new Book("Big Littles Lies", "Liane Moriarty", 2014);
    Book moriartyGuilty = new Book("Truly Madly Guilty", "Liane Moriarty", 2016);
    
    // Use the 4 books to add a number of acquisitions to each library 
    pembinaTrails.newAcquisition(lee);
    pembinaTrails.newAcquisition(lewis);
    pembinaTrails.newAcquisition(lewis);
    pembinaTrails.newAcquisition(moriartyLies);
    pembinaTrails.newAcquisition(lewis);
   
    millennium.newAcquisition(lee);   
    millennium.newAcquisition(lewis);
    millennium.newAcquisition(moriartyGuilty);
   
    // Call the .toString methods for each library
    System.out.println(pembinaTrails);
    System.out.println(millennium);
   
    // See which libraries have two of the books
    lewis.findCopies();
    System.out.println();
   
    moriartyGuilty.findCopies();
    System.out.println();
   
    //Borrow a book and see if the operation was successful
    System.out.println("Borrowing " + lewis + " from " + pembinaTrails.getName());
    int lewisBookID = pembinaTrails.borrowBook(lewis);
    if(lewisBookID != Library.NOT_FOUND)
      System.out.println("Got it!");
    System.out.println();
   
    //See which libraries now have this newly borrowed book
    lewis.findCopies();
    System.out.println();
   
   
    //Borrow a second book
    System.out.println("Borrowing " + moriartyGuilty + " from " + millennium.getName());
    int guiltyBookID = millennium.borrowBook(moriartyGuilty);
    if(guiltyBookID != Library.NOT_FOUND)
      System.out.println("Got it!");
    System.out.println();
   
    //See which libraries now have this newly borrowed book
    moriartyGuilty.findCopies();
    System.out.println();
  
    // Display the available holding from the millennium library
    System.out.println("Available at: " + millennium.getName());
    //System.out.println(millennium.availableHoldings());
   
    // Return one of the books
    System.out.println("Returning " + moriartyGuilty + " to " + millennium.getName());
    if(millennium.returnBook(guiltyBookID))
      System.out.println("Returned!");
    System.out.println();
   
    //See where this newly returned book is now available
    moriartyGuilty.findCopies();
    System.out.println();
   
    // Finish off by displaying the holdings at both libraries
    System.out.println(pembinaTrails);
    System.out.println(millennium);  
  } // main
  
}