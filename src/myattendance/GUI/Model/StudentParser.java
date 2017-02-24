/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.GUI.Model;

import java.util.List;
import myattendance.BE.Student;
import myattendance.BLL.StudentManager;

/**
 *
 * @author Fjord82
 */
public class StudentParser
{
    
    private static StudentParser instance;
    
    public static StudentParser getInstance()
    {
        if (instance == null)
        {
            instance = new StudentParser();
        }
        return instance;
    }
    
    private StudentParser()
    {
        
    }
    
    StudentManager studentManager = StudentManager.getInstance();
    
    public List<Student> getDanishClassList()
    {
        return studentManager.getDanishClassList();
    }
    
    public List<Student> getInternationalClassList()
    {
        return studentManager.getInternationalClassList();
    }
    
    public Student getRasmus()
    {
        return studentManager.getRasmus();
    }
    
}
