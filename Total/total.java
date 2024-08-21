package Total;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class total {
  public static double sum = 0;
  File file = new File("movie-ticket-booking\\History.txt");
  public static double summation()
  {
    String filename = "movie-ticket-booking\\History.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+"); // Split the line by whitespace
                if (parts.length >= 3) {
                    try {
                        double num = Double.parseDouble(parts[2]); // Parse the third column as integer
                        sum += num; // Add the number to the sum
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format: " + parts[2]);
                    }
                }
            }
            // System.out.println("Total sum: " + sum);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    return sum;

   
  }
  public static void main(String[] args)
  {
    System.out.println(summation());
  }
  
}
