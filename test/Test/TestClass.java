package Test;

import myattendance.DAL.DatabaseAccess;
import org.joda.time.DateTime;

/**
 *
 * @author meng
 */
public class TestClass

{

    public static void main(String[] args)
    {
        DatabaseAccess db = new DatabaseAccess();
        DateTime endDate = new DateTime();
        DateTime startDate = new DateTime("2017-03-17T23:43:12.254+01:00");
        db.writeAbsencesIntoDB(23, startDate, endDate);
        db.writeAbsencesIntoDB(24, startDate, endDate);
        
    }

}
