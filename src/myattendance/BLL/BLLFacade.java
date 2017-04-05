package myattendance.BLL;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import myattendance.BE.Course;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.DAL.DALFacade;
import org.joda.time.DateTime;

public class BLLFacade
{

    // Private field for the Facade singleton instance.
    private static BLLFacade instance;

    /**
     * Publicly accessible method for acquiring the singleton instance of the
     * class. Returns the instance of the class, and creates one if there
     * currently isn't one. Either case, returns the singleton instance of this
     * class.
     *
     * @return BLLFacade
     */
    public static BLLFacade getInstance()
    {
        if (instance == null)
        {
            instance = new BLLFacade();
        }
        return instance;
    }

    /**
     * Private constructor to ensure there will only be a single instance of
     * this class, adhering to the singleton design.
     */
    private BLLFacade()
    {
    }

    DALFacade dalFacade = DALFacade.getInstance();
    DateManager dateManager = new DateManager();
    Statistics statistics = new Statistics();

    public User getUser(String login, String pass)
    {
        return dalFacade.loginQuery(login, pass);
    }

    public List<Course> getCourses(User teacher)
    {
        return dalFacade.getCourses(teacher);
    }

    public Course fillUsersInCourse(Course course)
    {
        return dalFacade.fillUsersInCourse(course);
    }

    public DateTime getStartDate()
    {
        return dalFacade.getStartDate();
    }

    public Day getDay(DateTime dateTime)
    {
        return dalFacade.getDay(dateTime);
    }

    public void updateLastLogin(User user)
    {
        dalFacade.updateLastLogin(user);
    }

    public List<Day> getAbsentDays(User user)
    {
        return dalFacade.getAbsentDays(user);
    }

    public List<Day> getDaysBetweenDates(DateTime startDate, DateTime endDate)
    {
        return dalFacade.getDaysBetweenDates(startDate, endDate);
    }

    public void writeAbsencesIntoDB(User user, Day day)
    {
        dalFacade.writeAbsencesIntoDB(user, day);
    }

    public void recordAbsence(User user, Day today)
    {
        dateManager.recordAbsence(user, today);
    }

    public boolean establishServerConnection()
    {
        return dalFacade.establishServerConnection();
    }

    public ObservableList<PieChart.Data> getPieChartData(User user)
    {
        return statistics.getPieChartData(user);
    }

    public XYChart.Series<String, Number> getBarChartData(User user)
    {
        return statistics.getStackedChartData(user);
    }

    public Integer totalSchoolDays()
    {
        return dalFacade.totalSchoolDays();
    }

    public List<Day> getDaysUptoToday()
    {
        return dalFacade.getDaysUptoToday();
    }

    public void changeToNonSchoolDay(Day d, int c)
    {

        dalFacade.changeToNonSchoolDay(d, c);
    }
}
