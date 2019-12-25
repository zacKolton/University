/**
 * Test Program
 * Lab 6 Bronze
 * COMP 1020
 */
public class TestLab6Bronze {
  
  public static void main(String[] args) { 
    System.out.print("A Real number r (should print \"123.46\"): ");
    Real r = new Real(123.4567);
    System.out.println(r);

    System.out.print("A Complex number c1 (should print \"3.20+6.70i\"): ");
    Complex c1 = new Complex(3.2,6.7);
    System.out.println(c1);

    System.out.print("A Complex number c2 (should print \"3.20-6.70i\"): ");
    Complex c2 = new Complex(3.2,-6.7);
    System.out.println(c2);

    System.out.printf("Magnitude of r (should be 123.45670 ): %8.5f%n",r.magnitude());
    System.out.printf("Magnitude of c1 (should be 7.42496 ): %8.5f%n",c1.magnitude());
    System.out.printf("Magnitude of c2 (should be 7.42496 ): %8.5f%n",c2.magnitude());
    
  }
  
}
