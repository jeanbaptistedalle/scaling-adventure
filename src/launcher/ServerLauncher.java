package launcher;

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
		new Server(Constant.PORT);
	}
}
