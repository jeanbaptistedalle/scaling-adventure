package dessin.collaboratif.view.component.button;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.button.SquareButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class SquareButton extends JToggleButton {

    /**
     *
     */
    private static final long serialVersionUID = 1680353709746079985L;

    public SquareButton() {
        super("");
        setIcon(new ImageIcon(GeneralVariables.SQUARE_ICON_PATH));
        setToolTipText("Dessiner un rectangle");
        setFocusable(false);
        this.addActionListener(new SquareButtonListener());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
