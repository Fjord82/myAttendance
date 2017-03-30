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
        System.out.println(daysBetween);
        return daysBetween;

    }

    public boolean isAbsent(User user)
    {
        //Number of days between today and last login
        int lLAndToday = daysBetween(user.getLastLogin(), new DateTime());

        if (lLAndToday <= 1)
        {
            System.out.println("Is absent = false");
            return false;
        } else
        {
            System.out.println("Is absent = true");
            return true;
        }
        

    }

    public void recordAbsence(User user, Day today)
    {
        System.out.println("Started record absence");
        if (isAbsent(user))
        {
            System.out.println("Getting list");
            List<Day> absentDays = new ArrayList(dalFacade.getDaysBetweenDates(user.getLastLogin(), today.getDateInTime()));
            System.out.println("Got list at size " + absentDays.size());
            
            for (Day day : absentDays)
            {
                System.out.println(day.getWeekdayName());
                
                if(day.isSchoolDay())
                {
                    dalFacade.writeAbsencesIntoDB(user, day);
                    System.out.println("Recorded absence");
                }
            }
        }
    }

}
