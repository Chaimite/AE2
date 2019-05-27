import java.util.InputMismatchException;

public class FileDoesNotExistInDirectory extends NullPointerException
{
public FileDoesNotExistInDirectory(){
   System.err.println("You must introduce a file name that exists in the directory.");
}
}
