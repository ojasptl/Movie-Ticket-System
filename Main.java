import java.util.Scanner;
import Total.total;
import Updation.Updation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Hashmap.PriceDictionary;

public class Main
{
  Scanner S1 = new Scanner(System.in);
  public static int n, type;
  public double price;
  public static String Movie_name, Customer_name;
  public static void main(String[] args)
  {
    Main.print();
    Main M1 = new Main();
    M1.loop();

  }

  public static void print()
  {
    printLine("*Welcome*");
    printLine("*To*");
    printLine("*PCET's Theatre*");
    System.out.println();
  }

  public static void printLine(String message) 
  {
    int totalLength = 42; // Total length of the pattern
    int messageLength = message.length();

    int starsCount = (totalLength - messageLength) / 2;
    int remainingSpaces = totalLength - messageLength - starsCount * 2;

    // Print stars
    for (int i = 0; i < starsCount; i++) {
        System.out.print("*");
    }

    // Print spaces before the message
    for (int i = 0; i < remainingSpaces / 2; i++) {
        System.out.print(" ");
    }

    // Print the message
    System.out.print(message);

    // Prints spaces after the message
    for (int i = 0; i < remainingSpaces / 2; i++) {
        System.out.print(" ");
    }

    // Prints stars
    for (int i = 0; i < starsCount; i++) {
        System.out.print("*");
    }

    // Move to the next line
    System.out.println();
  }

  public void loop()
  {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Do you want to enter");
    n = scanner.nextInt();
    while(n == 1)
    {
      System.out.println("1 for Customer");
      System.out.println("2 for Owner");
      System.out.println("3 for exit");
      type = scanner.nextInt();
      if(type == 1)
      {
        System.out.println("These are the movies");
        Main.movie_reader();
        scanner.nextLine();
        System.out.println("Enter the movie you want to watch\t");
        Movie_name = scanner.nextLine();
        System.out.println("Enter your First Name\t");
        Customer_name = scanner.next();
        PriceDictionary H1 = new PriceDictionary();
        price = H1.getPrice(Movie_name);
        System.out.println(Movie_name);
        System.out.println(Customer_name + price);
        Updation.getter(Customer_name + " " + Movie_name.replace(" ", "") + " " + Double.toString(price));
        System.out.println("Ticket Booked");
        System.out.println("Please pay" + price);
        System.out.println("Do you want to continue\t");
        String choice = S1.nextLine();
        if(choice.toLowerCase().equals("no"))
        {
          System.out.println("Exiting");
          try
          {
            Thread.sleep(10000);
          }
          catch (InterruptedException e) {
            // Handle interrupted exception
            e.printStackTrace();
        }
        break;
        }
        if(choice.toLowerCase().equals("0"))
        {
          System.out.println("Exiting");
          try
          {
            Thread.sleep(10000);
          }
          catch (InterruptedException e) {
            // Handle interrupted exception
            e.printStackTrace();
        }
        break;
        }
        else
        continue;
      }
      if(type == 2)
      {
        System.out.println("Enter the password\t");
        
        int i = 1;
        String pass = scanner.nextLine();
        while(!pass.toLowerCase().equals("12345678"))
        {
          scanner.nextLine();
          System.out.println("Enter the password\t");
          pass = scanner.nextLine();
          if(!pass.toLowerCase().equals("12345678"))
          {
            System.out.println("Do you want to exit");
            int choice = scanner.nextInt();
            if(choice == 1)
            break;
          }
          if(i > 1)
          {
            System.out.println("Maximum try reached...............");
            System.out.println("Exiting.................");
            System.exit(1);
          }
          i+=1;
        }
        if(pass.toLowerCase().equals("12345678"))
        {
          System.out.println("Welcome Boss");
          System.out.println("Do you want to see the records or the total sales");
          System.out.println("1 for total sales and 2 for checking record");
          int choice = scanner.nextInt();
          if(choice == 1)
          {
            System.out.println("The total sales are: " + total.summation());
          }
          else{
              Main.total_sales_records();
          }
          System.out.println("1 for exit");
          choice = S1.nextInt();
          if(choice == 1)
          {
            System.out.println("Exiting");
          try
          {
            Thread.sleep(10000);
          }
          catch (InterruptedException e) {
            // Handle interrupted exception
            e.printStackTrace();
        }
        break;
          }
          else{
            System.out.println("Returning to Main Menu");
            try
            {
              Thread.sleep(10000);
            }
            catch (InterruptedException e) {
              // Handle interrupted exception
              e.printStackTrace();
          }
          }

        }
        else{
          System.out.println("Returning to Main Menu");
          try
          {
            Thread.sleep(10000);
          }
          catch (InterruptedException e) {
            // Handle interrupted exception
            e.printStackTrace();
        }
        }
      }
      if(type == 3)
      {
        System.out.println("Exiting");
          try
          {
            Thread.sleep(10000);
          }
          catch (InterruptedException e) {
            // Handle interrupted exception
            e.printStackTrace();
        }
        
        
        break;
      }
    }
    scanner.close();
  }

  public static void movie_reader()
  {
    String filename = "Movies.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
  }

  public static void total_sales_records() {
    String filename = "History.txt";
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }
}

}