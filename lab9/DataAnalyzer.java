import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

/**
   This program reads a file containing numbers and analyzes its contents.
   If the file doesn't exist or contains strings that are not numbers, an
   error message is displayed.
*/
public class DataAnalyzer
{
   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      DataSetReader reader = new DataSetReader();
      
      boolean done = false;
      while (!done) 
      {
         try 
         {
            System.out.println("Please enter the file name: ");
            String filename = in.next();
            
            double[] data = reader.readFile(filename);
            double sum = 0;
            for (double d : data) { sum = sum + d; }
            System.out.println("The sum is " + sum);
            done = true;
         }
         catch (FileNotFoundException exception)
         {
	     String message = exception.getMessage();
	     Pattern pattern = Pattern.compile("\\(No such file or directory\\)");
	     Matcher matcher = pattern.matcher(message);
	     matcher.find();
	     message = message.substring(0, matcher.start());
	     System.out.println("File not found: " + message);
         }
         catch (BadDataException exception)
         {
            System.out.println("Bad data: " + exception.getMessage());
	    return;
         }
         catch (IOException exception)
         {
            exception.printStackTrace();
	    return;
         }
      }
   }
}
