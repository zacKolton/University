public class TestA1Phase1 {
 
 
  public static void main(String[] args) {
  
    // Create three book instances  
    Book book1 = new Book("To Kill a Mockingbird", "Harper Lee", 1960);
    Book book2 = new Book("The Lion, the Witch and the Wardrobe", "C.S. Lewis", 1950);
    Book book3 = new Book("To Kill a Mockingbird", "Harper Lee", 1960);
   
    // display the information for each instance using the .toString method
    System.out.println("Created three book instances: " );
    System.out.println("Book1: " + book1);                      
    System.out.println("Book2: " + book2);    
    System.out.println("Book3: " + book3);
    System.out.println();
    
    //Checking for equality
    System.out.println("Are book1 and book2 equal? " + book1.equals(book2));
    System.out.println("Are book1 and book3 equal? " + book1.equals(book3));
  } //main
  
}