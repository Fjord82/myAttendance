package Test;

import myattendance.DAL.DatabaseAccess;

/**
 *
 * @author meng
 */
public class TestClass

{

    public static void main(String[] args)
    {
        DatabaseAccess db = new DatabaseAccess();
        System.out.println(db.totalSchoolDays());
    }

}
