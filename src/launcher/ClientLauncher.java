package launcher;

import dessin.collaboratif.view.component.LoginFrame;

/**
 * This class allows user to launch only the client of the app. Even if the
 * server is not online, he can launch it but he can't succeed to login
 *
 * @author JBD
 *
 */
public class ClientLauncher {
    public static void main(String[] args) {
        LoginFrame.getInstance();
    }
}
