package launcher;

//~--- non-JDK imports --------------------------------------------------------

import reseau.common.Constant;

import reseau.server.Server;

/**
 * This class allows the user to launch the server
 *
 * @author JBD
 *
 */
public class ServerLauncher {
    public static void main(String[] args) {
        Server s = new Server(Constant.PORT);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
