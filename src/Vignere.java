public class Vignere
{
   protected String keyword;
   char[][] alphabet;

   public Vignere(String keyword)
   {
      this.keyword = keyword;
      alphabet = getNewAlphabet(keyword);
   }

   // Encodes a message
   public char[] encode(char[] plaintext)
   {
      int counter = 0;
      //creates a array for the cipher text
      char[] ciphertext = new char[plaintext.length];
      for (int i = 0; i < plaintext.length; i++)
      {
         //checks if a letter is capital
         if (isCapitalLetter(plaintext[i]))
         {  //finds the character that has to be placed, based in the ciphered alphabet
            int characterToReplaceWith = (((int) plaintext[i]) - 64);
            ciphertext[i] = alphabet[counter++][characterToReplaceWith - 1];
            if (counter == keyword.length())
            {  //this is for the i part in the array to start at position 0 and advance with the number of iterations
               counter = 0;
            }
         }
         else
         {
            ciphertext[i] = plaintext[i];
         }
      }
      return ciphertext;
   }

   // Decodes a message
   public char[] decode(char[] ciphertext)
   {
      //creates a array for the cipher text
      char[] plaintext = new char[ciphertext.length];
      int index = 0;
      //Loops the ciphered text and the ciphered alphabet
      for (int i = 0; i < ciphertext.length; i++)
      {

         for (int j = 0; j < 26; j++)
         {

            if (isCapitalLetter(ciphertext[i]))
            {
               
               if (ciphertext[i] == alphabet[index][j])
               {
                  //when it finds the position of the replacement letter it replaces with decoded character
                  plaintext[i] = (char) (j + 65);
                  index++;
                  break;
               }
            }
            else
            {
               plaintext[i] = ciphertext[i];
            }
         }//if it hasn't found the correct character and needs to start the loop from the beggining
         if (index == keyword.length())
         {
            index = 0;
         }
      }
      return plaintext;
   }

   public static char[][] getNewAlphabet(String keyword)
   {
      char[] key = keyword.toCharArray();

      char[][] newAlphabet = new char[keyword.length()][26];
      for (int i = 0; i <= key.length - 1; i++)
      {  //passes the key to the rows of the new alphabet
         newAlphabet[i][0] = key[i];
         for (int j = 1; j <= 25; j++)
         {  //gets the elements of the colum elements
            newAlphabet[i][j] = (char) ((int) newAlphabet[i][j - 1] + 1);
            if (newAlphabet[i][j] == 91)
            {
               newAlphabet[i][j] = 'A';
            }
         }
      }
      return newAlphabet;
   }

   // Checks if a character is capital or not
   private static boolean isCapitalLetter(char letter)
   {
      int asciiCode = (int) letter;
      return (asciiCode >= 65 && asciiCode <= 90) ? true : false;
   }

}
