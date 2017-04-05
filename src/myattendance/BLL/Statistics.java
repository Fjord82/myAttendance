package myattendance.BLL;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.DAL.DALFacade;
import org.joda.time.DateTime;

/**
 *
 * @author jeppe
 */
public class Statistics
{

    DALFacade dalFacade = DALFacade.getInstance();

    public ObservableList<PieChart.Data> getPieChartData(User user)
    {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        List<Day> absentDays = new ArrayList<>(user.getAbsentDays());
        List<Day> daysUpToToday = new ArrayList<>(dalFacade.getDaysBetweenDates(dalFacade.getStartDate(), new DateTime()));

        int absentDaysInt = absentDays.size();
        int daysUptoTodayInt = daysUpToToday.size();
        int presentDaysInt = daysUptoTodayInt - absentDaysInt;

        double percentageAbsence = (double) absentDaysInt / daysUptoTodayInt * 100;
        double percentagePresence = 100 - percentageAbsence;

        DecimalFormat df = new DecimalFormat("##");

        pieChartData.add(new PieChart.Data("Presence: " + df.format(percentagePresence) + "%", presentDaysInt));
        pieChartData.add(new PieChart.Data("Absence: " + df.format(percentageAbsence) + "%", absentDaysInt));

        return pieChartData;
    }

    public XYChart.Series<String, Number> getStackedChartData(User user)
    {
        XYChart.Series<String, Number> absenceSeries = new XYChart.Series<>();
        
        int mondays = 0;
        int tuesdays = 0;
        int wednesdays = 0;
        int thursdays = 0;
        int fridays = 0;
        
        List<String> dayStrings = new ArrayList<>();
        for (Day day : user.getAbsentDays())
        {
            if (day.getWeekdayName().equalsIgnoreCase("monday"))
            {
                mondays++;
            }
            if (day.getWeekdayName().equalsIgnoreCase("tuesday"))
            {
                tuesdays++;
            }
            if (day.getWeekdayName().equalsIgnoreCase("wednesday"))
            {
                wednesdays++;
            }
            if (day.getWeekdayName().equalsIgnoreCase("thursday"))
            {
                thursdays++;
            }
            if (day.getWeekdayName().equalsIgnoreCase("friday"))
            {
                fridays++;
            }
            
        }
        absenceSeries.getData().add(new XYChart.Data("Monday", mondays));
        absenceSeries.getData().add(new XYChart.Data("Tuesday", tuesdays));
        absenceSeries.getData().add(new XYChart.Data("Wednesday", wednesdays));
        absenceSeries.getData().add(new XYChart.Data("Thursday", thursdays));
        absenceSeries.getData().add(new XYChart.Data("Friday", fridays));

        return absenceSeries;
    }
    
    public XYChart.Series<String, Number> getLineChartData(User user)
    {
        XYChart.Series<String, Number> absenceSeries = new XYChart.Series<>();
        
        
        
        return absenceSeries;
    }
}
