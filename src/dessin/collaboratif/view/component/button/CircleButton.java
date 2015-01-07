package dessin.collaboratif.view.component.button;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.button.CircleButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class CircleButton extends JToggleButton {

    /**
     *
     */
    private static final long serialVersionUID = 1680353709746079985L;

    public CircleButton() {
        super("");
        setIcon(new ImageIcon(GeneralVariables.CIRCLE_ICON_PATH));
        setToolTipText("Dessiner un cercle");
        setFocusable(false);
        this.addActionListener(new CircleButtonListener());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
