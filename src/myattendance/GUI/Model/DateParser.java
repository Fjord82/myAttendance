package myattendance.GUI.Model;

import myattendance.BE.Day;
import myattendance.BLL.BLLFacade;
import org.joda.time.DateTime;

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
        if (instance == null)
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

    public DateTime getLastLoginDate(int PID)
    {
        return bllFacade.getLastLoginDate(PID);
    }

    public int daysBetweenSpecificDateAndToday(DateTime specificDate)
    {
        return bllFacade.daysBetweenSpecificDateAndToday(specificDate);
    }
    
    public DateTime getStartDate(){
        return bllFacade.getStartDate();
    }
    
    public boolean isAbsent(int PID, Day day){
        return bllFacade.isAbsent(PID, day);
    }

  public Day getDay(DateTime dateTime){
      return bllFacade.getDay(dateTime);
  }
    


}
