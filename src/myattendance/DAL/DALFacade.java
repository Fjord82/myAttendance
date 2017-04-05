package myattendance.DAL;

import java.util.List;
import myattendance.BE.Course;
import myattendance.BE.Day;
import myattendance.BE.User;
import org.joda.time.DateTime;

public class DALFacade
{

    // Private field for the Facade singleton instance.
    private static DALFacade instance;

    /**
     * Publicly accessible method for acquiring the singleton instance of the
     * class. Returns the instance of the class, and creates one if there
     * currently isn't one. Either case, returns a the singleton instance of
     * this class.
     *
     * @return DALFacade
     */
    public static DALFacade getInstance()
    {
        if (instance == null)
        {
            instance = new DALFacade();
        }
        return instance;
    }

    /**
     * Private constructor to ensure there will only be a single instance of
     * this class, adhering to the singleton design.
     */
    private DALFacade()
    {

    }

    DatabaseAccess databaseAccess = new DatabaseAccess();

    public User loginQuery(String login, String pass)
    {
        return databaseAccess.loginQuery(login, pass);
    }

    public DateTime getStartDate()
    {
        return databaseAccess.getStartDate();
    }

    public List<Course> getCourses(int PID)
    {
        return databaseAccess.getCourses(PID);
    }

    public Course fillUsersInCourse(Course course)
    {
        return databaseAccess.fillUsersInCourse(course);
    }

    public DateTime getLastLoginDate(User user)
    {
        return databaseAccess.getLastLoginDate(user);
    }

    public Day getDay(DateTime dateTime)
    {
        return databaseAccess.getDay(dateTime);
    }

    public void updateLastLogin(User user)
    {
        databaseAccess.updateLastLogin(user);
    }

    public List<Day> getAbsentDays(User user)
    {
        return databaseAccess.getAbsentDays(user);
    }

    public List<Day> getDaysBetweenDates(DateTime startDate, DateTime endDate)
    {
        return databaseAccess.getDaysBetweenDates(startDate, endDate);
    }

    public void writeAbsencesIntoDB(User user, Day day)
    {
        databaseAccess.writeAbsencesIntoDB(user, day);
    }

    public boolean establishServerConnection()
    {
        return databaseAccess.establishServerConnection();
    }

    public Integer totalSchoolDays()
    {
        return databaseAccess.totalSchoolDays();
    }

    public List<Day> getDaysUptoToday()
    {
        return databaseAccess.getDaysUptoToday();
    }

    public void changeToNonSchoolDay(Day d, int c)
    {

        databaseAccess.changeToNonSchoolDay(d, c);
    }

    public List<Day> listNonSchoolDays()
    {
        return databaseAccess.listNonSchoolDays();
    }
}
