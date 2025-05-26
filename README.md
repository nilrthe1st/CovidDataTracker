# COVID-19 Health Data Tracker 

This is a Java desktop application that visualizes COVID-19 case and death trends using an interactive GUI and real-world public health data.

##  Features
- Parses and displays real COVID-19 data from [Our World In Data](https://ourworldindata.org/)
- Interactive GUI (Java Swing)
- Dynamic line charts for total cases and deaths (JFreeChart)
- Country and metric selection
- Clean code architecture: `api`, `data`, `ui` layers

##  Structure
src/
- api/ # Handles data fetching
- data/ # Data model classes (CountryData, DailyData)
-  ui/ # GUI and chart rendering


## Why I Built This
I built this as a summer learning project after my first year studying Computer Science & Biology at UW-Madison. I wanted to challenge myself by working with real datasets and building a complete desktop app from start to finish. I used ChatGPT as a tutor to help me understand advanced topics like JSON parsing and chart visualization.

##  How to Run
1. Clone the repo
2. Download `owid-covid-data.json` (not tracked in the Git, have to download from their website)
3. Open the project in an IDE (I used IntelliJ but Eclipse also works)
4. Run `CovidTrackerApp.java` from the `ui` package

##  Next Steps
- Add filtering by date range
- Export chart as PNG
- Add support for additional metrics (e.g., new cases, vaccinations)

---

##  Technologies Used
- JDK 24
- Gson (for JSON)
- JFreeChart (for charting)
- Swing (GUI framework)

---

##  Author
Nil Roy  
Freshman @ UW–Madison  
Major: Computer Science & Biology
