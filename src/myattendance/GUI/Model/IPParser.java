package myattendance.GUI.Model;

import myattendance.BLL.IPMatching;

public class IPParser {

    private static IPParser instance;
    
    IPMatching iPMatching = IPMatching.getInstance();

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

    public boolean getIsMatchingBroadcasting() {
        return iPMatching.isMatchingBroadcasting();
    }

    
    
    
}
