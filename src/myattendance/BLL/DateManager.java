package myattendance.BLL;

import myattendance.BE.Day;
import myattendance.BE.User;
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

    public boolean isAbsent(User user, Day day)
    {
        //Number of days between today and last login
        int lLAndToday = daysBetweenSpecificDateAndToday(user.getLastLogin());

        //Checks if today is a school day and whether it is a Monday
        if (day.isIsSchoolDay() == true && day.getWeekdayNumber() != 2)
        {
            //Does not make the student if the difference between last login and today is 1
            if (lLAndToday == 1)
            {
                return false;
            } else
            {
                return true;
            }
        } else if (day.isIsSchoolDay() == true && day.getWeekdayNumber() == 2)
        {
            if (lLAndToday <= 3)
            {
                return false;
            } else
            {
                return true;
            }

        } else
        {
            return false;
        }
    }

    public void recordAbsence(User user, Day today)
    {
        if (isAbsent(user, today) == true)
        {
            daysBetweenSpecificDateAndToday(user.getLastLogin());
            dalFacade.writeAbsencesIntoDB(user.getId(), user.getLastLogin(), today.getDateInTime());
        } 
    }

}
