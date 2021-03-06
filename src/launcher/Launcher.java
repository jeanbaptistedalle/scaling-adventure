package launcher;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.view.component.LoginFrame;

import reseau.common.Constant;

import reseau.server.Server;

/**
 * This Class contains only a main which launch the server and then the app.
 *
 * @author JBD
 *
 */
public class Launcher {
    public static void main(String[] args) {
        new Thread() {
            public void run() {
                Server s = new Server(Constant.PORT);
            }
        }.start();
        LoginFrame.getInstance();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
