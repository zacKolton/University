/**
 * Template
 * Lab 1 Bronze exercise
 * COMP 1020
 */
import java.util.Scanner; //Allows the use of Scanner input

public class TemplateLab1Bronze {
    
  public static void main(String[] args) {
    //Call the readData() method to test it,
    int numberRead = readData();
    //and print out the result that was returned.
    System.out.println(numberRead+" valid entries were read in.");
  }//main
  
  static int readData() {
    /* Prompt the user to enter integer values, one at a time.
     * Values between 1 and 100 are valid. They will be accepted,
     * and echoed in a suitable message. A value of 0 will
     * cause the method to terminate. All other values will
     * cause an error message to be printed. The number of valid
     * values entered (1-100, but not 0) will be returned.
     */
     Scanner keyboard = new Scanner(System.in);
    //**********YOUR CODE HERE******************
    int num = keyboard.nextInt();
    int values = 0;
    System.out.println("Enter an int from 1 - 100 (0 to quit): " + num);
    while(num != 0){
    if(num >= 1 && num <= 100){
        System.out.println("Entry " + num + " accepted");
        values++;
    }
    else{
            System.out.println("Invalid int");
        } 
   }
   return values;
  }
}

    


