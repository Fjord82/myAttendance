package myattendance.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myattendance.BE.Student;
import myattendance.DAL.DatabaseAccess;

/**
 *
 * @author jeppe
 */
public class LoginCheckManager
{
    DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
    
    public boolean CheckValidLogin(Student student)
    {
        
        
        if (student != null)
        {
            return true;
        }
        else
        return false;
    }
    
    public Student getStudent(String login, String pass)
    {
        return databaseAccess.getStudent(login, pass);
    }
    
    
}
