package myattendance.BLL;

import java.util.List;
import myattendance.BE.User;
import myattendance.DAL.DALFacade;

/**
 *
 * @author jeppe
 */
public class BLLFacade
{
    // Private field for the Facade singleton instance.

    private static BLLFacade instance;

    /**
     * Publicly accessible method for acquiring the singleton instance of the
     * class. Returns the instance of the class, and creates one if there
     * currently isn't one. Either case, returns a the singleton instance of
     * this class.
     *
     * @return BLLFacade
     */
    public static BLLFacade getInstance()
    {
        if (instance == null)
        {
            instance = new BLLFacade();
        }
        return instance;
    }

    /**
     * Private constructor to ensure there will only be a single instance of
     * this class, adhering to the singleton design.
     */
    private BLLFacade()
    {
    }

    DALFacade dalFacade = DALFacade.getInstance();
    IPMatching ipMatching = new IPMatching();
    LoginCheckManager loginCheckManager = new LoginCheckManager();

    public boolean matchingBroadcastingAddress()
    {
        return ipMatching.matchingBroadcastingAddress();
    }

    public List<User> getDanishClassList()
    {
        return dalFacade.getDanishClassList();
    }

    public List<User> getInternationalClassList()
    {
        return dalFacade.getInternationalClassList();
    }

    public User getRasmus()
    {
        return dalFacade.getRasmus();
    }

    public User getUser(String login, String pass)
    {
        return dalFacade.loginQuery(login, pass);
    }

}
