import java.util.Arrays;
public class TestQuestion1 {
 public static void main(String [] args) {
  
  int []testData = {1,2,3,4,5};
  IntLinkedList list = new IntLinkedList(testData);
  
  System.out.println("These two lists should be in the same order:");
  System.out.println(list.toString());
  System.out.println(Arrays.toString(testData));
  System.out.println("Is the above list empty? " + list.empty());

  IntLinkedList list2 = new IntLinkedList();
  System.out.println("\nList 2: "+list2);
  System.out.println("Is List 2 empty? " + list2.empty());
  
  
  list.remove(4);
  list.remove(0);
  System.out.println("\nShould contain 2,3,4:");
  System.out.println(list);
  
  // will the code crash?
  list.remove(0);
  list.remove(0);
  
  // Next test
  int []testData2 = {7,4,5,15,0,1};
  IntLinkedList list3 = new IntLinkedList(testData2);
  System.out.println("\nThe new list: " + list3);
  //System.out.println("\nThe new list reversed: " + list2.printReverse());
  
  // clone some data
  IntLinkedList cloned = list3.clone();
  System.out.println("\nOld list and new list should be identical:");
  System.out.println("Old: " + list3);
  System.out.println("New: " + cloned);
  
  cloned.add(88);
  cloned.add(99);
  System.out.println("\nThe old list should be unchanged:");
  System.out.println("Old: " + list3);
  System.out.println("New: " + cloned);
  
  
  
  ///remove elements that don't exist
  cloned.remove(-1);
  cloned.remove(100);
  System.out.println("\nThe new list should be unchanged:");
  System.out.println("New: " + cloned);
  
  

 
 }
}
