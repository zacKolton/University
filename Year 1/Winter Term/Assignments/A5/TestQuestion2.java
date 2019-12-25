public class TestQuestion2 {
     
   public static void main(String[] args) { 
      IntLinkedList test = new IntLinkedList(new int[]{3,5,4,6,8,2,1,4,3,2,5,3,6,3});
      System.out.println("Test list: "+test);
      IntLinkedList oddOnes = new IntLinkedList(), evenOnes = new IntLinkedList();
      test.split(oddOnes,evenOnes);
      System.out.println("Test list should be empty: "+test);
      System.out.println("Odds: "+oddOnes);
      System.out.println("Evens: "+evenOnes);

      test = new IntLinkedList(new int[]{3,5,4,6,8,2,1,4,3,2,5,3,6,3});
      System.out.println("Test list restored: "+test);
      System.out.println("Frequency of 3's: "+test.frequency(3));
      System.out.println("Frequency of 1's: "+test.frequency(1));
      System.out.println("Frequency of 7's: "+test.frequency(7));
      
   }
   
}
