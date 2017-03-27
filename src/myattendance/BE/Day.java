package myattendance.BE;

import org.joda.time.DateTime;


public class Day
{

    private final int DateID;
    private final DateTime dateInTime;
    private final int weekdayNumber;
    private final String weekdayName;
    private boolean isSchoolDay;

    public Day(int DateID, DateTime dateInTime, int weekdayNumber, String weekdayName, boolean isSchoolDay)
    {
        this.DateID = DateID;
        this.dateInTime = dateInTime;
        this.weekdayNumber = weekdayNumber;
        this.weekdayName = weekdayName;
        this.isSchoolDay = isSchoolDay;
    }

    public int getDateID()
    {
        return DateID;
    }

    public DateTime getDateInTime()
    {
        return dateInTime;
    }

    public int getWeekdayNumber()
    {
        return weekdayNumber;
    }

    public String getWeekdayName()
    {
        return weekdayName;
    }

    public boolean isIsSchoolDay()
    {
        return isSchoolDay;
    }

    public void setIsSchoolDay(boolean isSchoolDay)
    {
        this.isSchoolDay = isSchoolDay;
    }

}
