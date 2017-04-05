package myattendance.GUI.Model;

import java.util.Date;
import java.util.List;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.BLL.BLLFacade;
import org.joda.time.DateTime;
import java.time.Instant;

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

    public void changeSchoolDay(Day d, int c)
    {
        bllFacade.changeToNonSchoolDay(d, c);
    }

    public List<Day> listNonSchoolDays()
    {
        return bllFacade.listNonSchoolDays();
    }

    public boolean checkNonSchoolDay(Instant day)
    {
        Date date = Date.from(day);

        Day d = getDay(new DateTime(date));

        for (Day nonSchoolDay : listNonSchoolDays())
        {
            if (d == nonSchoolDay)
            {
                return true;
            }

        }
        return false;
    }
}
