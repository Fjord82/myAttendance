package myattendance.GUI.Model;

import myattendance.BLL.BLLFacade;

public class IPParser {

    private static IPParser instance;
    
    BLLFacade bllFacade = BLLFacade.getInstance();

    public static IPParser getInstance()
    {

        if (instance == null)
        {
            instance = new IPParser();
        }
        return instance;
    }
    
    private IPParser() {
    }

    public boolean MatchingBroadcasting() {
        return bllFacade.matchingBroadcastingAddress();
    }

    
    
    
}
