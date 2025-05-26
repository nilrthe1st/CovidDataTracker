package ui;

import data.DailyData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JPanel;
import java.util.List;

public class CovidChartPanel {

  public static ChartPanel createCasesChart(List<DailyData> dataList) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (DailyData day : dataList) {
      // Skip days without valid data
      if (day.total_cases != null && day.date != null) {
        dataset.addValue(day.total_cases, "Total Cases", day.date);
      }
    }

    JFreeChart lineChart = ChartFactory.createLineChart(
        "COVID-19 Total Cases Over Time", // Chart title
        "Date",                            // X-axis label
        "Total Cases",                     // Y-axis label
        dataset                            // The data to chart
    );

    return new ChartPanel(lineChart);


  }

  public static ChartPanel createDeathsChart(List<DailyData> dataList) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (DailyData day : dataList) {
      if (day.total_deaths != null && day.date != null) {
        dataset.addValue(day.total_deaths, "Total Deaths", day.date);
      }
    }

    JFreeChart lineChart = ChartFactory.createLineChart(
        "COVID-19 Total Deaths Over Time",
        "Date",
        "Total Deaths",
        dataset
    );

    return new ChartPanel(lineChart);
  }

}
