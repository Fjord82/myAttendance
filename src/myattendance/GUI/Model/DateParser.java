package myattendance.GUI.Model;

import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.BLL.BLLFacade;
import org.joda.time.DateTime;

/**
 *
 * @author meng
 */
public class DateParser
{

    BLLFacade bllFacade = BLLFacade.getInstance();

    private static DateParser instance;

    public static DateParser getInstance()
    {
        if (instance == null)
        {
            instance = new DateParser();
        }
        return instance;
    }

    private DateParser()
    {

    }

    public DateTime getStartDate()
    {
        return bllFacade.getStartDate();
    }

    public Day getDay(DateTime dateTime)
    {
        return bllFacade.getDay(dateTime);
    }

    public void recordAbsence(User user, Day today)
    {
        bllFacade.recordAbsence(user, today);
    }
}
