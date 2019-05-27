
public class MonoAlphabetic
{
   protected String keyword;
   char[] alphabet;

   public MonoAlphabetic(String keyword)
   {
      this.keyword = keyword;
      alphabet = getNewAlphabet(keyword);
   }

   // Encodes a message
   public char[] encode(char[] plaintext)
   {
      //creates an array to store the encoded text
      char[] ciphertext = new char[plaintext.length];
      //loops the text, checks if the letter is capital or not
      for (int i = 0; i < plaintext.length; i++)
      {
         if (isCapitalLetter(plaintext[i]))
         {  //finds the corresponding character and replaces it
            int characterToReplaceWith = (((int) plaintext[i]) - 64);
            ciphertext[i] = alphabet[characterToReplaceWith - 1];
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
      //Creates an array where the new code will be stored
      char[] plaintext = new char[ciphertext.length];
      //compares the letters in the cipher with the ones in the ciphered alphabet
      for (int i = 0; i < ciphertext.length; i++)
      {
         for (int j = 0; j < alphabet.length; j++)
         {
            //checks if letter is capital and then decodes it
            if (isCapitalLetter(ciphertext[i]))
            {
               if (ciphertext[i] == alphabet[j])
               {
                  plaintext[i] = (char) (j + 65);
               }
            }
            else
            {
               plaintext[i] = ciphertext[i];
            }
         }
      }
      return plaintext;
   }

   // Receives a key from the user and originates a new alphabet
   private static char[] getNewAlphabet(String keyword)
   {
      //creates two char arrays, one for the keyword and another with an alphabet
      char[] key = keyword.toCharArray();
      char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
      //creates an array to store the new alphabet
      char[] newAlphabet = new char[key.length + alphabet.length];
      //stores the key  and the alphabet in the encrypted alphabet 
      for (int i = 0; i < key.length; i++)
      {
         newAlphabet[i] = key[i];
      }

      for (int j = 0; j < alphabet.length; j++)
      {
         newAlphabet[key.length + j] = alphabet[j];
      }
      //replaces the repeated values of the key with empty spaces
      for (int s = 0; s < key.length; s++)
      {
         for (int z = key.length; z < newAlphabet.length; z++)
         {
            if (key[s] == newAlphabet[z])
            {
               newAlphabet[z] = ' ';
            }
         }
      }
      //converts the array to a string, then takes out the blank spaces and turns it back to an array
      String alph = String.valueOf(newAlphabet);
      String withoutSpaces = alph.replaceAll(" ", "");
      char[] charNewAlphabet = withoutSpaces.toCharArray();

      return charNewAlphabet;
   }
   //checks if a char is a capital letter
   private static boolean isCapitalLetter(char letter)
   {
      int asciiCode = (int) letter;
      return (asciiCode >= 65 && asciiCode <= 90) ? true : false;
   }
}
