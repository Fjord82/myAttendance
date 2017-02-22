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

    public Student(String name, String status)
    {
        this.name.set(name);
        this.status.set(status);
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
