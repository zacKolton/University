/**
 * Template
 * COMP 1020
 * Lab 5 Bronze Exercise
 */
import java.io.*; //Required for most I/O operations

public class TemplateLab5Bronze {

    static final String INPUT_FILE = "testLab5Bronze.txt";
    static final String OUTPUT_FILE = "outputLab5Bronze.txt";

    public static void main(String[] args) throws IOException{
        try{
            FileReader fileRdr = new FileReader(INPUT_FILE);
            BufferedReader inFile = new BufferedReader(fileRdr);
            String line = "";
            int lineNum = 0;
            do{
                line = inFile.readLine();
                lineNum++;
                if(line != null){
                    System.out.println(lineNum+": "+line);
                }
            }
            while(line != null);
            inFile.close();

        }catch(IOException e)
        {
            System.out.println("didnt work"+ e.getMessage());
        }

      
    }

}//main
//TemplateLab5Bronze
