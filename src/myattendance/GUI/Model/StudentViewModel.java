package myattendance.GUI.Model;

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
        int daysUptoTodayInt = daysUpToToday.size();
        int presentDaysInt = daysUptoTodayInt - absentDaysInt;

        System.out.println(absentDaysInt + "+" + presentDaysInt + "=" + daysUptoTodayInt);

        double percentageAbsence = (double) absentDaysInt / daysUptoTodayInt * 100;
        double percentagePresence = 100 - percentageAbsence;

        System.out.println(percentageAbsence);
        System.out.println(percentagePresence);

        pieChartData.add(new PieChart.Data("Presence: " + percentagePresence + "%", presentDaysInt));
        pieChartData.add(new PieChart.Data("Absence: " + percentageAbsence + "%", absentDaysInt));

        return pieChartData;
    }

}
