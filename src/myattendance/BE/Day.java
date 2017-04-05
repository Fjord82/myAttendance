package myattendance.BE;

import java.text.SimpleDateFormat;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.joda.time.DateTime;


public class Day
{

    private final IntegerProperty DateID = new SimpleIntegerProperty();
    private final DateTime dateInTime;
    private final IntegerProperty weekdayNumber = new SimpleIntegerProperty();
    private final StringProperty weekdayName = new SimpleStringProperty();
    private boolean isSchoolDay;

    public Day(int DateID, DateTime dateInTime, int weekdayNumber, String weekdayName, boolean isSchoolDay)
    {
        this.DateID.set(DateID);
        this.dateInTime = dateInTime;
        this.weekdayNumber.set(weekdayNumber);
        this.weekdayName.set(weekdayName);
        this.isSchoolDay = isSchoolDay;
    }

    public int getDateID()
    {
        return DateID.get();
    }

    public DateTime getDateInTime()
    {
        return dateInTime;
    }

    public int getWeekdayNumber()
    {
        return weekdayNumber.get();
    }

    public String getWeekdayName()
    {
        return weekdayName.get();
    }

    public boolean isSchoolDay()
    {
        return isSchoolDay;
    }

    public void setIsSchoolDay(boolean isSchoolDay)
    {
        this.isSchoolDay = isSchoolDay;
    }
    
    public StringProperty WeekdayNameProperty()
    {
        return weekdayName;
    }
    
    public StringProperty toStringProperty()
    {
        StringProperty returnString = new SimpleStringProperty();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
        
        returnString.set(this.getWeekdayName() + " " + (this.getDateInTime().toLocalDate()));
        
        return returnString;
    }
    
    
}
