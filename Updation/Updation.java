package Updation;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Updation 
{
  public static void getter(String SS)
  {
    String file = "History.txt";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) 
    {

      writer.write(SS); // Writes a line of text
      writer.newLine(); // Writes a new line


      System.out.println("History Updated Successfully.");
    } 
    catch (IOException e) 
    {
      System.err.println("Error writing to the file: " + e.getMessage());
    }
  
  }
}
