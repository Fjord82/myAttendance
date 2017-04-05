package myattendance.GUI.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import myattendance.BE.Course;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.BLL.BLLFacade;
import org.joda.time.DateTime;

/**
 *
 * @author jeppe
 */
public class TeacherViewModel
{

    BLLFacade bllFacade = BLLFacade.getInstance();

    List<Course> courseList = new ArrayList<>();

    ObservableList<Course> comboItems;

    public ObservableList<Course> comboBoxContentGet(User teacher)
    {
        courseList = bllFacade.getCourses(teacher);
        getClassList();

        comboItems = FXCollections.observableArrayList(courseList);

        return comboItems;
    }

    public void getClassList()
    {

        for (Course course : courseList)
        {
            course.clearUserList();
            int index = courseList.indexOf(course);
            Course filledCourse = bllFacade.fillUsersInCourse(course);
            for (Course c : courseList)
            {
                for (User u : c.getUserList())
                {
                    u.setAbsencePercentage(calculateAbsencePercentage(u));
                }
            }
            courseList.set(index, filledCourse);

        }

    }

    public ObservableList<User> filterList(String filter, Course course)
    {
        List<User> unfilteredList = new ArrayList<>(course.getUserList());
        List<User> filteredList = new ArrayList<>();
        ObservableList<User> returnList = FXCollections.observableArrayList();

        for (User u : unfilteredList)
        {
            if (u.getName().toLowerCase().contains(filter.toLowerCase()))
            {
                filteredList.add(u);
            }
        }
        returnList.addAll(filteredList);

        return returnList;
    }

    public ObservableList<User> updateList(String filter, Course course)
    {
        getClassList();
        ObservableList<User> returnList = FXCollections.observableArrayList();
        {
            for (User u : course.getUserList())
            {
                //u.setAbsencePercentage(calculateAbsencePercentage(u));
                returnList.add(u);

            }
        }
        return returnList;
    }

    public ObservableList<PieChart.Data> getPieChartData(User user)
    {
        return bllFacade.getPieChartData(user);
    }

    public XYChart.Series<String, Number> getStackedChartData(User user)
    {
        return bllFacade.getStackedChartData(user);
    }

    public XYChart.Series<String, Number> getLineChartData(User user)
    {
        return bllFacade.getLineChartData(user);
    }

    public String calculateAbsencePercentage(User user)
    {
        List<Day> absentDays = new ArrayList<>(user.getAbsentDays());
        List<Day> daysUpToToday = new ArrayList<>(bllFacade.getDaysBetweenDates(bllFacade.getStartDate(), new DateTime()));

        int absentDaysInt = absentDays.size();
        int daysUptoTodayInt = daysUpToToday.size();
        int presentDaysInt = daysUptoTodayInt - absentDaysInt;

        double percentageAbsence = (double) absentDaysInt / daysUptoTodayInt * 100;

        DecimalFormat df = new DecimalFormat("0.0");

        return df.format(percentageAbsence);
    }

}
