import api.CovidApiFetcher;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.CountryData;
import data.DailyData;
import java.lang.reflect.Type;
import java.util.Map;

public class TestCovidParsing {
  public static void main (String [] args) {
    CovidApiFetcher fetcher = new CovidApiFetcher();
    String json = fetcher.fetchData("owid-covid-data.json");

    Type type = new TypeToken<Map<String, CountryData>>() {}.getType();
    Gson gson = new Gson();
    Map<String, CountryData> allCountries = gson.fromJson(json, type);

    CountryData usa = allCountries.get("USA");
    System.out.println("Location " + usa.location);
    for (int i = 0; i < Math.min(usa.data.size(), 5); i++) {
      DailyData day = usa.data.get(i);
      System.out.println(day.date + ": " + day.total_cases + " total cases");
    }

  }
}
