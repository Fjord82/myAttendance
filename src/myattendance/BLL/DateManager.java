package myattendance.BLL;

import java.util.ArrayList;
import java.util.List;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.DAL.DALFacade;
import org.joda.time.Days;
import org.joda.time.DateTime;

public class DateManager
{

    DALFacade dalFacade = DALFacade.getInstance();

    public int daysBetween(DateTime startDateTime, DateTime endDateTime)
    {
        int daysBetween = 0;
        
        if (startDateTime != null && endDateTime != null)
        {
        daysBetween = Days.daysBetween(startDateTime, endDateTime).getDays();
        }
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

    public int daysBetweenSpecificDateAndToday(DateTime lastLogin)
    {

        DateTime today = new DateTime();
        int daysBetweenLastLoginAndToday = daysBetween(lastLogin, today);
        return daysBetweenLastLoginAndToday;
    }

    public boolean isAbsent(User user)
    {
        //Number of days between today and last login
        int lLAndToday = daysBetween(user.getLastLogin(), new DateTime());

        if (lLAndToday == 1)
        {
            return false;
        } else
        {
            return true;
        }

    }

    public void recordAbsence(User user, Day today)
    {
        if (isAbsent(user))
        {
            List<Day> absentDays = new ArrayList<>(dalFacade.getDaysBetweenDates(user.getLastLogin(), today.getDateInTime()));
            
            for (Day day : absentDays)
            {
                if(day.isIsSchoolDay())
                    dalFacade.writeAbsencesIntoDB(user, day);
            }
        }
    }

}
