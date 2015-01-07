package dessin.collaboratif.view.component.field;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Dimension;

import javax.swing.JTextField;

public class ServerField extends JTextField {

    /**
     *
     */
    private static final long serialVersionUID = -1190851341076332271L;

    public ServerField() {
        this.setText("");
        this.setToolTipText("Ip du serveur");
        this.setPreferredSize(new Dimension(150, 20));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
