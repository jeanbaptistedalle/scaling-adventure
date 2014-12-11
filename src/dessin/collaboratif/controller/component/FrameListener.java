package dessin.collaboratif.controller.component;

//~--- non-JDK imports --------------------------------------------------------

import reseau.client.ClientNetwork;
//~--- JDK imports ------------------------------------------------------------
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        ClientNetwork.getInstance().disconnect();
        System.exit(0);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
