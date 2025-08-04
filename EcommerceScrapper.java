import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class EcommerceScrapper {
    public static void main(String[] args) {
        String url = "https://example.com/products";
        String csvFile = "products.csv";

        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write CSV header
            writer.append("Name,Price,Rating\n");

            // Fetch and parse the webpage
            Document doc = Jsoup.connect(url).get();
            Elements products = doc.select(".product"); // adapt selector to target site

            for (Element product : products) {
                String name = product.select(".name").text();     // adapt selector
                String price = product.select(".price").text();   // adapt selector
                String rating = product.select(".rating").text(); // adapt selector

                // Escape commas if necessary
                writer.append('"').append(name).append('"').append(',');
                writer.append('"').append(price).append('"').append(',');
                writer.append('"').append(rating).append('"').append('\n');
            }

            System.out.println("Data written to " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}