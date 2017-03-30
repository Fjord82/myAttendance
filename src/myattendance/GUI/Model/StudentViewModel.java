package myattendance.GUI.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.BLL.BLLFacade;
import org.joda.time.DateTime;

/**
 *
 * @author meng
 */
public class StudentViewModel

{

    BLLFacade bllFacade = BLLFacade.getInstance();

    public void updateLastLogin(User user)
    {
        bllFacade.updateLastLogin(user);
    }

    public ObservableList<PieChart.Data> getPieChartData(User user)
    {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        List<Day> absentDays = new ArrayList<>(bllFacade.getAbsentDays(user));
        List<Day> daysUpToToday = new ArrayList<>(bllFacade.getDaysBetweenDates(bllFacade.getStartDate(), new DateTime()));

        int absentDaysInt = absentDays.size();
        System.out.println(absentDaysInt);
        int daysUptoTodayInt = daysUpToToday.size();
        System.out.println(daysUptoTodayInt);
        int presentDaysInt = daysUptoTodayInt - absentDaysInt;

        double percentageAbsence = (double) absentDaysInt / daysUptoTodayInt * 100;
        double percentagePresence = 100 - percentageAbsence;

        DecimalFormat df = new DecimalFormat("##");

        pieChartData.add(new PieChart.Data("Presence: " + df.format(percentagePresence) + "%", presentDaysInt));
        pieChartData.add(new PieChart.Data("Absence: " + df.format(percentageAbsence) + "%", absentDaysInt));

        return pieChartData;
    }

}
