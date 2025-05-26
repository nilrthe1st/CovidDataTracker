package api;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;

public class CovidApiFetcher {
  public String fetchData(String filePath) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      StringBuilder jsonBuilder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        jsonBuilder.append(line);
      }
      reader.close();

      return jsonBuilder.toString();
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
