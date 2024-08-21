package Hashmap;
import java.util.HashMap;
import java.util.Map;
public class PriceDictionary 
{
  public String movie;
  public double price;
  public double getPrice(String S)
  {
    HashMap<String, Double> moviePrices = new HashMap<>();

        // Add movie names and their prices to the HashMap
        moviePrices.put("The Shawshank Redemption", 9.99);
        moviePrices.put("The Godfather", 8.99);
        moviePrices.put("The Dark Knight", 10.50);
        moviePrices.put("Schindler's List", 7.99);
        moviePrices.put("The Lord of the Rings: The Return of the King", 11.25);
        moviePrices.put("Pulp Fiction", 8.50);
        moviePrices.put("Fight Club", 9.00);
        moviePrices.put("Forrest Gump", 8.75);
        moviePrices.put("Inception", 10.25);
        moviePrices.put("The Matrix", 9.50);
        moviePrices.put("The Silence of the Lambs", 7.75);
        moviePrices.put("The Green Mile", 9.25);
        moviePrices.put("Goodfellas", 8.00);
        moviePrices.put("The Godfather: Part II", 8.99);
        moviePrices.put("The Lord of the Rings: The Fellowship of the Ring", 10.75);
        moviePrices.put("The Lion King", 7.50);
        moviePrices.put("Gladiator", 9.25);
        moviePrices.put("Saving Private Ryan", 9.75);
        moviePrices.put("The Departed", 10.00);
        moviePrices.put("The Prestige", 8.75);
        moviePrices.put("Interstellar", 11.50);
        moviePrices.put("The Usual Suspects", 8.25);
        moviePrices.put("Se7en", 8.50);
        moviePrices.put("The Sixth Sense", 8.25);
        moviePrices.put("The Truman Show", 7.99);
      for (Map.Entry<String, Double> entry : moviePrices.entrySet()) {
          if(S.toLowerCase().equals(entry.getKey().toLowerCase()))
          {
            movie = entry.getKey();
            price = entry.getValue();
            System.out.println(movie + ": $" + price);
          }
      }

    return price;
  }
  
}
