public class LetterFrequencies
{

  public double[] avgCounts = { 8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0,
         6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8,
         1.0, 2.4, 0.2, 2.0, 0.1 };

   // counts the number of times a capital letter appears in a given text
   public int[] letterFrequency(char[] plaintext)
   {
      int[] storeFreq = new int[26];
      for (int i = 0; i < plaintext.length; i++)
      {
         for (int j = 65; j <= 90; j++)
         {
            if (plaintext[i] == (char) j)
            {
               storeFreq[j - 65]++;
            }
         }
      }
      return storeFreq;
   }

   // Method to return the percentage of a letter frequency
   public  double[] frequencyPercent(char[] plaintext)
   {
      int totalLetters = plaintext.length;
      double[] freqPerc = new double[26];
      int[] letFreq = letterFrequency(plaintext);
      for (int i = 0; i <= letFreq.length - 1; i++)
      {
         freqPerc[i] = (letFreq[i] / (double) totalLetters) * 100;
      }
      return freqPerc;
   }

   // Method to return difference in percentages
   public double[] diff(char[] text)
   {
      double[] diff = new double[26];
      double[] freqPerc = frequencyPercent(text);
      for (int i = 0; i <= diff.length - 1; i++)
      {
         diff[i] = freqPerc[i] - avgCounts[i];
      }
      return diff;
   }

   // Method that returns the most frequent char
   public char mostFrequentCharacter(int[] array)
   {
      char letter = ' ';
      int max = 0;
      for (int i = 0; i < array.length - 1; i++)
      {
         max = Math.max(array[i], max);
         if (array[i] == max)
         {
            max = array[i];
            letter = (char) (i + 65);
         }
      }
      return letter;
   }

   // Method that returns the value for the most frequent character
   public String freqPercMostFreqChar(double[] array)
   {
      double max = 0;
      for (int i = 0; i < array.length - 1; i++)
      {
         max = Math.max(array[i], max);
         if (array[i] == max)
         {
            max = array[i];
         }
      }
      return (String.format("%4.1f", max));
   } 
}
