package myattendance.BLL;

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

    public boolean isAbsent(User user, Day day)
    {
        //Number of days between today and last login
        int lLAndToday = daysBetweenSpecificDateAndToday(dalFacade.getLastLoginDate(user));

        if (lLAndToday == 1)
        {
            return false;
        } else
        {
            return true;
            //Further checks for weekends and public holidays
        }

    }

    public void recordAbsence(User user, Day today)
    {
        if (isAbsent(user, today) == true)
        {
            daysBetweenSpecificDateAndToday(user.getLastLogin());
            dalFacade.writeAbsencesIntoDB(user, user.getLastLogin(), today.getDateInTime());
        }
    }

}
