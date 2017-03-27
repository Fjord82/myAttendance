package myattendance.BLL;

import java.util.List;
import myattendance.BE.Course;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.DAL.DALFacade;
import org.joda.time.DateTime;

/**
 *
 * @author jeppe
 */
public class BLLFacade
{
    // Private field for the Facade singleton instance.

    private static BLLFacade instance;

    /**
     * Publicly accessible method for acquiring the singleton instance of the
     * class. Returns the instance of the class, and creates one if there
     * currently isn't one. Either case, returns a the singleton instance of
     * this class.
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
    IPMatching ipMatching = new IPMatching();
    DateManager dateManager = new DateManager();

    public boolean matchingBroadcastingAddress()
    {
        return ipMatching.matchingBroadcastingAddress();
    }

    public User getUser(String login, String pass)
    {
        return dalFacade.loginQuery(login, pass);
    }

    public List<Course> getCourses(int PID)
    {
        return dalFacade.getCourses(PID);
    }

    public Course fillUsersInCourse(Course course)
    {
        return dalFacade.fillUsersInCourse(course);
    }

    public DateTime getTodaysDate()
    {
        return dateManager.getTodaysDate();
    }

    public DateTime getLastLoginDate(int PID)
    {
        return dalFacade.getLastLoginDate(PID);
    }

    public int daysBetweenSpecificDateAndToday(DateTime specificDate)
    {
        return dateManager.daysBetweenSpecificDateAndToday(specificDate);
    }

    public DateTime getStartDate()
    {
        return dalFacade.getStartDate();
    }

    public boolean isAbsent(int PID, Day day)
    {
        return dateManager.isAbsent(PID, day);
    }

    public Day getDay(DateTime dateTime)
    {
        return dalFacade.getDay(dateTime);
    }
<<<<<<< HEAD

    
     public void updateLastLogin(User user)
      {
          dalFacade.updateLastLogin(user);
      }

     public List<Day> getAbsentDays(User user){
         return dalFacade.getAbsentDays(user);
     }
     
      public List<Day> getDaysBetweenDates(DateTime startDate, DateTime endDate)
    {
        return dalFacade.getDaysBetweenDates(startDate, endDate);
    }

=======
>>>>>>> origin/Meng

}
