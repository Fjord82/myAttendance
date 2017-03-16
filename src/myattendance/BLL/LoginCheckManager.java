package myattendance.BLL;

import myattendance.BE.Student;
import myattendance.DAL.DALFacade;

/**
 *
 * @author jeppe
 */
public class LoginCheckManager
{
    

    DALFacade dalFacade = DALFacade.getInstance();

    public boolean CheckValidLogin(Student student)
    {

        if (student != null)
        {
            return true;
        } else
        {
            return false;
        }
    }



}
