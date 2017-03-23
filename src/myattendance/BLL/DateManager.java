package myattendance.BLL;

import java.sql.Date;
import myattendance.DAL.DALFacade;
import org.joda.time.Days;
import org.joda.time.DateTime;

/**
 *
 * @author meng
 */
public class DateManager
{

    DALFacade dalFacade = DALFacade.getInstance();

    public int daysBetween(DateTime startDateTime, DateTime endDateTime)
    {

        int daysBetween = Days.daysBetween(startDateTime, endDateTime).getDays();
        return daysBetween;

    }

    public DateTime getTodaysDate()
    {
        DateTime todaysDateTime = new DateTime();
        return todaysDateTime;
    }

    //This method needs tidying up - combine with the one below
    public int daysBetweenStartAndToday()
    {
        DateTime startDate = dalFacade.getStartDate();
        DateTime startDateTime = new DateTime(startDate);
        int daysBetweenStartAndToday = daysBetween(startDateTime, getTodaysDate());
        return daysBetweenStartAndToday;
    }

    public int daysBetweenSpecificDateAndToday(DateTime specificDate)
    {

        DateTime today = getTodaysDate();
        int daysBetweenLastLoginAndToday = daysBetween(specificDate, today);
        return daysBetweenLastLoginAndToday;
    }

    public boolean isAbsent(int PID)
    {
        //Number of days between today and last login
        int lLAndToday = daysBetweenSpecificDateAndToday(dalFacade.getLastLoginDate(PID));

        if (lLAndToday == 1)
        {
            return false;
        } else
        {
            return true;
        }

    }
}
