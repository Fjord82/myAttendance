package Test;

import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.BLL.BLLFacade;
import org.joda.time.DateTime;

/**
 *
 * @author meng
 */
public class TestClass

{

    public static void main(String[] args)
    {
        BLLFacade f = BLLFacade.getInstance();
        User adam = new User(1, null, false);

        System.out.println("absent days");
        for (Day day : f.getAbsentDays(adam))
        {
            System.out.println(day.getDateInTime());
        }

        System.out.println(f.getStartDate());

        System.out.println("between days");
        for (Day day :f.getDaysBetweenDates(f.getStartDate(), new DateTime()))
        {
            System.out.println(day.getDateInTime());
        }
 
    }

}
