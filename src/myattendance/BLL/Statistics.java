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

    public XYChart.Series<String, Number> getBarChartData(User user)
    {
        XYChart.Series<String, Number> absenceSeries = new XYChart.Series<>();
        for (Day day : user.getAbsentDays())
        {
            absenceSeries.getData().add(new XYChart.Data(day.getWeekdayName(), 1));
        }

        return absenceSeries;
    }
}
