package myattendance.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.joda.time.DateTime;

public class User
{

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty sClass = new SimpleStringProperty();

    private final boolean isTeacher;

    private int absentClasses = 0;
    private int presentClasses = 0;
    DateTime today = new DateTime();
    DateTime lastLogin;

    public User()
    {
        isTeacher = false;
    }

    public User(int id, String name, boolean isTeacher)
    {
        this.id.set(id);
        this.name.set(name);
        this.isTeacher = isTeacher;
    }

    public User(int id, String name, String sClass, DateTime lastLogin, boolean isTeacher)
    {
        this.id.set(id);
        this.name.set(name);
        this.sClass.set(sClass);
        this.lastLogin = lastLogin;
        
        this.isTeacher = isTeacher;
    }

    public int getId()
    {
        return id.get();
    }

    public int getAbsentDates()
    {
        return absentClasses;
    }

    public void setAbsentClasses(int absentClasses)
    {
        this.absentClasses = absentClasses;
    }

    public int getPresentDates()
    {
        return presentClasses;
    }

    public void setPresentClasses(int presentClasses)
    {
        this.presentClasses = presentClasses;
    }

    public String getStatus()
    {
        return status.get();
    }

    public void setStatus(String value)
    {
        status.set(value);
    }

    public StringProperty statusProperty()
    {
        return status;
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String value)
    {
        name.set(value);
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public String getsClass()
    {
        return sClass.get();
    }

    public boolean IsTeacher()
    {
        return isTeacher;
    }

    public DateTime getLastLogin()
    {
        return lastLogin;
    }

    public void setLastLogin(DateTime lastLogin)
    {
        this.lastLogin = lastLogin;
        if (lastLogin.dayOfYear().equals(today.dayOfYear()))
        {
            setStatus("Online");
            
        } else
        {
            setStatus("Offline");
        }
    }

}
