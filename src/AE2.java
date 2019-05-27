import java.util.Scanner;

public class AE2
{
   // Method to ask a file name from the user
   private static String askForFileName() 
   {
      String fileName = "";
      Scanner fn = new Scanner(System.in);
      boolean flag = true;
      while (flag)
      {
         System.out.println(
               "Please introduce a file name(the last character of the filename must be either 'C' or 'P'): ");
         try
         {
            fileName = fn.nextLine();
            if (fileName.charAt(fileName.length() - 1) == 'C'
                  || fileName.charAt(fileName.length() - 1) == 'P')
            {
               
               flag = false;
               
            }
            else if (fileName.isEmpty())
            {// checks if the file name is empty, if it is an exception is
             // thrown
               throw new FileDoesNotExistInDirectory();
            }
            else
            {
               throw new FileDoesNotExistInDirectory();
            }
         }
         catch ( NullPointerException e)
         {
            System.out.println("The name of the file does not exist.");
         }
         catch (StringIndexOutOfBoundsException e)
         {
            throw new FileDoesNotExistInDirectory();
         }
         // fn.close(); Can't be closed, since if the last character is != C ||
         // P the scanner needs to be there
      }
      return fileName;
   }

   // Method to ask for key from the user
   private static String askForKey()
   {
      boolean flag = true;
      String key = "";
      Scanner fn = new Scanner(System.in);
      while (flag)
      {
         System.out.println(
               "Please insert a key(do not give repeated characters and use only capital letters): ");
         key = fn.nextLine();
         if (isStringOnlyCapitalLetter(key) == true// checks if there are only
                                                   // capital letters
               && isCharacterRepeated(key) == true)// checks if there is a
                                                   // repeated character
         {
            flag = false;
         }
         // fn.close();Can't be closed, since if the last character is != C || P
         // the scanner needs to be there
      }
      return key;
   }

   // checks if a string as repeated letters
   private static boolean isCharacterRepeated(String filename)
   {
      boolean flag = true;
      if (filename.length() == 1)
      {
         flag = true;
      }
      for (int i = 0; i < filename.length() - 1; i++)
      {
         for (int j = i + 1; j < filename.length(); j++)
         {
            if (filename.charAt(i) == filename.charAt(j))
            {
               flag = false;
               break;
            }
         }
      }
      return flag;
   }

   // Checks if a character is capital or not
   private static boolean isCapitalLetter(char letter)
   {
      int asciiCode = (int) letter;
      return (asciiCode >= 65 && asciiCode <= 90) ? true : false;
   }

   // Method to check if a string only has capital letters
   private static boolean isStringOnlyCapitalLetter(String key)
   {
      char[] test = key.toCharArray();
      boolean result = true;
      for (int i = 0; i < key.length(); i++)
      {
         if (isCapitalLetter(test[i]) == false)
         {
            result = false;
         }
      }
      return result;
   }

   // Method to write letter frequencies document
   private static void letterFrequency(String newFileName)
   {
      FileIO io = new FileIO();
      LetterFrequencies m = new LetterFrequencies();
      // Read the file with the new name
      String frequencyFileName = newFileName.substring(0,
            newFileName.length() - 5) + "F.txt";
      char[] newplaintext = io.readFromFile(newFileName);
      // Letter frequency
      int[] lf = m.letterFrequency(newplaintext);
      // Letter frequency percentage
      double[] lfp = m.frequencyPercent(newplaintext);
      // Difference between percentages
      double[] diffPer = m.diff(newplaintext);
      // Most frequent char
      char mFreqChar = m.mostFrequentCharacter(lf);
      // Percentage of the most frequent character
      String perMChar = m.freqPercMostFreqChar(lfp);
      // Writing the content to a file
      String toWriteF = "LETTER ANALYSES\r\n" + "\r\n"
            + " Letter     Freq    Freq%  AvgFreq%  diff" + "\r\n";
      for (int i = 0; i < 26; i++)
      {
         toWriteF += String.format("\t%s \t\t%4d  \t%4.1f \t%4.1f \t%4.1f %n",
               (char) ('A' + i), lf[i], lfp[i], m.avgCounts[i], diffPer[i]);
      }

      toWriteF += "\r\n" + "The most frequent letter is " + mFreqChar + " at "
            + perMChar + "%.";
      io.writeToFile(frequencyFileName, toWriteF);
   }

   // runs the program
   public static void run()
   {
      String name = askForFileName();
      String keyword = askForKey();
      FileIO io = new FileIO();

      MonoAlphabetic s = new MonoAlphabetic(keyword);
      // Vignere s = new Vignere(keyword);

      if (name.charAt(name.length() - 1) == 'P')
      {
         // Name of file where text to be encoded is
         String readfile = name + ".txt";
         // New file name
         String newFileName = name.substring(0, name.length() - 1) + "C.txt";
         // Reading the file with text to be encoded
         char[] plaintext = io.readFromFile(readfile);
         // Encoding process
         char[] encode = s.encode(plaintext);
         // Writing encode text to new file
         io.writeToFile(newFileName, encode);
         // Method to obtain the letter frequencies file
         letterFrequency(newFileName);

      }
      else if (name.charAt(name.length() - 1) == 'C')
      {
         // Name of file where text to be encoded is
         String readfile = name + ".txt";
         // New file name
         String newFileName = name.substring(0, name.length() - 1) + "D.txt";
         // Reading the file with text to be encoded
         char[] plaintext = io.readFromFile(readfile);
         // Decoding process
         char[] decode = s.decode(plaintext);
         // Writing decoded text to new file
         io.writeToFile(newFileName, decode);
         // Method to obtain the letter frequencies file
         letterFrequency(newFileName);

      }

   }

   public static void main(String[] args)
   {
      run();
   }

}
