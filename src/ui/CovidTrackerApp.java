package ui;

import api.CovidApiFetcher;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.CountryData;
import data.DailyData;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class CovidTrackerApp {

  public static void main(String[] args) {
    // Step 1: Fetch and parse the data
    CovidApiFetcher fetcher = new CovidApiFetcher();
    String json = fetcher.fetchData("owid-covid-data.json");
    Gson gson = new Gson();
    Type type = new TypeToken<Map<String, CountryData>>() {}.getType();
    Map<String, CountryData> allCountries = gson.fromJson(json, type);

    // Step 2: Build the sorted list of country options
    String[] countryOptions = allCountries.entrySet().stream()
        .map(entry -> entry.getKey() + " — " + entry.getValue().location)
        .sorted()
        .toArray(String[]::new);

    // Step 3: Set up the frame
    JFrame frame = new JFrame("COVID-19 Data Tracker");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 600);
    frame.setLayout(new BorderLayout());

    // Step 4: Top panel with inputs
    JPanel inputPanel = new JPanel();

    JComboBox<String> countryDropdown = new JComboBox<>(countryOptions);
    String[] metricOptions = {"Total Cases", "Total Deaths"};
    JComboBox<String> metricDropdown = new JComboBox<>(metricOptions);

    JButton updateButton = new JButton("Show Data");

    inputPanel.add(new JLabel("Country:"));
    inputPanel.add(countryDropdown);
    inputPanel.add(new JLabel("Metric:"));
    inputPanel.add(metricDropdown);
    inputPanel.add(updateButton);

    frame.add(inputPanel, BorderLayout.NORTH);

    // Step 5: Chart panel using a wrapper array
    final ChartPanel[] chartPanel = new ChartPanel[1];
    CountryData initial = allCountries.get("USA");
    chartPanel[0] = CovidChartPanel.createCasesChart(initial.data);
    frame.add(chartPanel[0], BorderLayout.CENTER);

    // Step 6: Button listener
    updateButton.addActionListener(e -> {
      String selection = (String) countryDropdown.getSelectedItem();
      String code = selection.split(" — ")[0];
      String metric = (String) metricDropdown.getSelectedItem();

      if (allCountries.containsKey(code)) {
        CountryData selected = allCountries.get(code);

        ChartPanel newChart;
        if (metric.equals("Total Cases")) {
          newChart = CovidChartPanel.createCasesChart(selected.data);
        } else {
          newChart = CovidChartPanel.createDeathsChart(selected.data);
        }

        frame.remove(chartPanel[0]);
        chartPanel[0] = newChart;
        frame.add(chartPanel[0], BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
      } else {
        JOptionPane.showMessageDialog(frame, "Country not found: " + code);
      }
    });

    // Step 7: Show GUI
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
