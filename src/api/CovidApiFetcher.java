package api;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CovidApiFetcher {
  public String fetchData(String urlString) {
    try {
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      reader.close();

      return response.toString();
    } catch (IOException e) {
      System.out.println("IOException caught");
      e.printStackTrace();
      return null;
    } catch (Exception e) {
      System.out.println("Exception caught");
      e.printStackTrace();
      return null;
    }
  }
}
