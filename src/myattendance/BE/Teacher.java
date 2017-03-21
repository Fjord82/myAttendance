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
public class Teacher
{
    
    private final StringProperty name = new SimpleStringProperty();

    public StringProperty getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name.set(name);
    }
    
}
