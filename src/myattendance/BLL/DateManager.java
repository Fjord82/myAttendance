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

    public int daysBetweenStartAndToday()
    {
        Date startDate = dalFacade.getStartDate();
        DateTime startDateTime = new DateTime(startDate);
        int daysBetweenStartAndToday = daysBetween(startDateTime,getTodaysDate());
        return daysBetweenStartAndToday;
    }
    
    public int daysBetweenLastLoginAndToday(){
        int bla =0;
        return bla;
    }

}
