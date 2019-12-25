import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

/*
 * A few examples of how to pull data in from a file. 
 * Does a sum of number on each line
 * Expected file has 0 or more space-delimited numbers per line.
 */
public class ReadFile 
{
	public static void readWithScanner() {
		Scanner in = null;

		try {
			in = new Scanner((new FileReader("data.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// read line-by line
		while (in.hasNextLine()) {
			String line = in.nextLine();
			Scanner row = new Scanner (line);
			// use a for loop to grab the data
			int sum = 0;
			while(row.hasNextInt()) {
				sum += row.nextInt();
			}
			System.out.println(sum);
			row.close();
		}
		in.close();
	}	

	public static void scannerAndSplit() {
		Scanner in = null;

		try {
			in = new Scanner((new FileReader("data.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// read line-by line
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] parts = line.split("\\s+");
			// use a for loop to grab the data
			int sum = 0;
			for (int i = 0; i < parts.length; i++) {
				sum += Integer.parseInt(parts[i]);
			}
			System.out.println(sum);
		}
		in.close();
	}
	
	public static void bufferedReader() 
	{
		BufferedReader in = null;
		try {
			in = new BufferedReader((new FileReader("data.txt")));
			// pre-load loop
			String line = in.readLine();
			while (line != null) {
				String[] parts = line.split(" ");
				// use a for loop to grab the data
				int sum = 0;
				for (int i = 0; i < parts.length; i++) {
					sum += Integer.parseInt(parts[i]);
				}
				System.out.println(sum);
				
				// get next line
				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		//readWithScanner();
		scannerAndSplit();
		//bufferedReader();
	}
}
