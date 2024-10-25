package portfoliomanagement.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * A class to fetch stock news from a REST API.
 * Author: Mr.Aditya Hakani
 */
public class FetchApi {
    
    /**
     * Fetches stock news for specified stock names.
     * @return JSON response from the API or null if an error occurs.
     */
    public String fetchStockNews(String[] stock_name) {
        try {
            // Use HTTP, not HTTPS
            URL url = new URL("http://localhost:5000/fetch_stock_price");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // JSON input to send
              // Assuming stock_name is a single element array
            String jsonInputString = "{\"stock_names\": [\"" + stock_name[0] + "\"]}"; // Adjusted for proper JSON structure

            
            // Send JSON data
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code
            int code = connection.getResponseCode();
            System.out.println("Response code: " + code);

            // Read the response from the input stream
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line.trim());
                }
            }

            // Print the response
            System.out.println("Response: " + response.toString());
            if (code == HttpURLConnection.HTTP_OK) {
                
                return response.toString();
            }

        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace for debugging
        }
        return null; // Return null if an error occurs
    }
}
