/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Fjord82
 */
public class Student
{

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty sClass = new SimpleStringProperty();
    private int absentClasses = 0;
    private int presentClasses = 0;

    public Student(String name, String sClass)
    {
        this.name.set(name);
        this.status.set(sClass);
    }

    public Student(String name, String status, int absence, int presence)
    {
        this.name.set(name);
        this.status.set(status);
        this.absentClasses = absence;
        this.presentClasses = presence;
    }

    public int getAbsentClasses()
    {
        return absentClasses;
    }

    public void setAbsentClasses(int absentClasses)
    {
        this.absentClasses = absentClasses;
    }

    public int getPresentClasses()
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
}
