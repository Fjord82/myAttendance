package myattendance.BLL;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import myattendance.GUI.Controller.LoginViewController;

public class IPMatching
{

    private String EASVIP = "10.176.167.255";

    private InetAddress retrieveBroadcastingAddressOfIndividual()
    {

        try
        {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements())
            {

                NetworkInterface networkInterface = interfaces.nextElement();

                if (networkInterface.isLoopback())
                {
                    continue;    // Don't want to broadcast to the loopback interface
                }

                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses())
                {

                    InetAddress broadcastingAddress = interfaceAddress.getBroadcast();

                    if (broadcastingAddress == null)
                    {
                        continue;
                    }
                    return broadcastingAddress;

                }

            }
        } catch (SocketException ex)
        {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean matchingBroadcastingAddress()
    {

        try
        {
            InetAddress hBroadcast = InetAddress.getByName(EASVIP);

            if (hBroadcast.equals(retrieveBroadcastingAddressOfIndividual()))
            {

                return true;
            }

        } catch (UnknownHostException ex)
        {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
