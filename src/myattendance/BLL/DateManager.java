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

    public int daysBetween()

    {
        Date startDate = dalFacade.getStartDate();
        DateTime startDateTime = new DateTime(startDate);
        DateTime todaysDateTime = new DateTime();

        int daysBetween = Days.daysBetween(startDateTime, todaysDateTime).getDays();
        return daysBetween;
        
    }

}
