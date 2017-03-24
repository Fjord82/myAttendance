package myattendance.GUI.Model;

import myattendance.BE.User;
import myattendance.BLL.BLLFacade;

/**
 *
 * @author meng
 */
public class StudentViewModel
        
{
    BLLFacade bllFacade = BLLFacade.getInstance();

     public void updateLastLogin(User user)
      {
          bllFacade.updateLastLogin(user);
      }
}
