public class TestA1Phase3 {
  
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
   
    // Try borrowing a book and see if the operation is successful
    System.out.println("Borrowing " + lewis + " from " + pembinaTrails.getName());
    int borrowedID1 = pembinaTrails.borrowBook(lewis);
    if(borrowedID1 != Library.NOT_FOUND)
      System.out.println("Got it!");
   
    // See how many copies of this book are now available at Pembina Trails:
    System.out.println("Available copies of " + lewis + " at " + pembinaTrails.getName() + ":  " +  pembinaTrails.getNumberAvailable(lewis));
    System.out.println();
   
    // Print the list of books at Pembina Trails
    System.out.println(pembinaTrails);
    
    // Try another copy of book and see if the operation is successful
    System.out.println("Borrowing " + lewis + " from " + pembinaTrails.getName());
    int borrowedID2 = pembinaTrails.borrowBook(lewis);
    if(borrowedID2 != Library.NOT_FOUND)
      System.out.println("Got it!");
    
    // See how many copies of this book are now available at Pembina Trails:
    System.out.println("Available copies of " + lewis + " at " + pembinaTrails.getName() + ":  " +  pembinaTrails.getNumberAvailable(lewis));
    System.out.println();
    
    // Print the list of books at Pembina Trails
    System.out.println(pembinaTrails);
   
   // return one of the borrowed books
   System.out.println("Returning " + lewis + " to " + pembinaTrails.getName());
   if(pembinaTrails.returnBook(borrowedID1))
     System.out.println("Returned!");
   System.out.println();
   
   //Print the list of books at Pembina Trails
   System.out.println(pembinaTrails);
    
  } // main
  
}