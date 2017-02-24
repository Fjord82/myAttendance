/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.BLL;

import java.util.List;
import myattendance.BE.Student;
import myattendance.DAL.FileManager;

/**
 *
 * @author Fjord82
 */
public class StudentManager
{
    
    private static StudentManager instance; 
    
    public static StudentManager getInstance()
    {
        if (instance == null)
        {
            instance = new StudentManager();
        }
        return instance;
    }

    private StudentManager()
    {
    }
    
    FileManager fileManager = FileManager.getInstance();
    
    
    public List<Student> getDanishClassList()
    {
        return fileManager.getDanishClassList();
    }
    
    public List<Student> getInternationalClassList()
    {
        return fileManager.getInternaionalClassList();
    }
    
    public Student getRasmus()
    {
        return fileManager.getRasmus();
    }
    
}
