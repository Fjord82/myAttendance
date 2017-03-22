/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.BE;

import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Fjord82
 */
public class User
{

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty sClass = new SimpleStringProperty();
    
    private final boolean isTeacher;
    
    private int absentClasses = 0;
    private int presentClasses = 0;
    Date date = Calendar.getInstance().getTime();
    
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

    public User(int id, String name, String sClass, boolean isTeacher)
    {
        this.id.set(id);
        this.name.set(name);
        this.sClass.set(sClass);
        this.isTeacher = isTeacher;
    }
    
        public User(String name,String status, int absentClasses, int presentClasses)
    {
        this.name.set(name);
        this.status.set(status);
        this.absentClasses = absentClasses;
        this.presentClasses = presentClasses;
        this.isTeacher = false;
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
    
}
