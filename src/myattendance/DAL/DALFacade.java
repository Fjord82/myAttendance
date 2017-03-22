package myattendance.DAL;

import java.sql.Date;
import java.util.List;
import myattendance.BE.User;

/**
 *
 * @author jeppe
 */
public class DALFacade
{

    // Private field for the Facade singleton instance.
    private static DALFacade instance;

    /**
     * Publicly accessible method for acquiring the singleton instance of the
     * class. Returns the instance of the class, and creates one if there
     * currently isn't one. Either case, returns a the singleton instance of
     * this class.
     *
     * @return DALFacade
     */
    public static DALFacade getInstance()
    {
        if (instance == null)
        {
            instance = new DALFacade();
        }
        return instance;
    }

    /**
     * Private constructor to ensure there will only be a single instance of
     * this class, adhering to the singleton design.
     */
    private DALFacade()
    {

    }

    FileManager fileManager = new FileManager();
    DatabaseAccess databaseAccess = new DatabaseAccess();

    public User getRasmus()
    {
        return fileManager.getRasmus();
    }

    public List<User> getInternationalClassList()
    {
        return fileManager.getInternationalClassList();
    }
    
    public List<User> getDanishClassList()
    {
        return fileManager.getDanishClassList();
    }
    
    public User loginQuery(String login, String pass)
    {
        return databaseAccess.loginQuery(login, pass);
    }
    
    public Date getStartDate(){
        return databaseAccess.getStartDate();
    }

}
