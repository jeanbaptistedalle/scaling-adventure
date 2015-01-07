package dessin.collaboratif.view.component.field;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Dimension;

import javax.swing.JTextField;

/**
 * @author JBD
 *
 */
public class LoginField extends JTextField {

    /**
     *
     */
    private static final long serialVersionUID = 7577321535367978392L;

    public LoginField() {
        this.setText("");
        this.setToolTipText("Login");
        this.setPreferredSize(new Dimension(150, 20));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
