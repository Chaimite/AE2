import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIO
{  //reads files
   public char[] readFromFile(String filename)

   {
      FileReader s = null;
      String line = "";
      Scanner rf = null;

      try
      {
         s = new FileReader(filename);
         rf = new Scanner(s);
         while (rf.hasNextLine())
         {
            line += rf.nextLine() + "\n";
         }
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File not found, or could not be opened");
      }

      rf.close();
      return line.toCharArray();

   }
   //writes files
   public void writeToFile(String fileName, char[] sequence)
   {
      PrintWriter write = null;
      try
      {
         FileOutputStream fileOut = new FileOutputStream(fileName, true);
         write = new PrintWriter(fileOut);
      }
      catch (FileNotFoundException e)
      {

         System.err.println("No file was found");
      }

      write.println(sequence);
      write.close();

   }
   //same as previous, but with an overloaded constructor
   public void writeToFile(String fileName, String sequence)
   {
      PrintWriter write = null;
      try
      {
         FileOutputStream fileOut = new FileOutputStream(fileName, true);
         write = new PrintWriter(fileOut);
      }
      catch (FileNotFoundException e)
      {

         System.err.println("No file was found");
      }

      write.println(sequence);
      write.close();

   }
  
  
}
