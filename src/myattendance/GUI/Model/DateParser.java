package myattendance.GUI.Model;

import myattendance.BLL.BLLFacade;

/**
 *
 * @author meng
 */
public class DateParser
{
    BLLFacade bllFacade = BLLFacade.getInstance();
    
    private static DateParser instance;
    
    public static DateParser getInstance()
    {
        if(instance == null)
        {
            instance = new DateParser();     
        }
        return instance;
    }
    
    private DateParser()
    {
        
    }

    public void getTodaysDate()
    {
        bllFacade.getTodaysDate();
    }

    public void getLastLoginDate(int PID)
    {
        bllFacade.getLastLoginDate(PID);
    }
    
    
}
