/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.GUI.Model;

import java.util.List;
import myattendance.BE.User;
import myattendance.BLL.BLLFacade;

/**
 *
 * @author Fjord82
 */
public class StudentParser
{

    private static StudentParser instance;

    public static StudentParser getInstance()
    {
        if (instance == null)
        {
            instance = new StudentParser();
        }
        return instance;
    }

    private StudentParser()
    {
    }
    
    BLLFacade bllFacade = BLLFacade.getInstance();

//    public List<User> getDanishClassList()
//    {
//        return bllFacade.getDanishClassList();
//    }
//
//    public List<User> getInternationalClassList()
//    {
//        return bllFacade.getInternationalClassList();
//    }
//
//    public User getRasmus()
//    {
//        return bllFacade.getRasmus();
//    }

}
