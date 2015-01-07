package dessin.collaboratif.view.component.button;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.button.CursorButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class CursorButton extends JToggleButton {

    /**
     *
     */
    private static final long serialVersionUID = -5254659661703089570L;

    public CursorButton() {
        super("");
        setIcon(new ImageIcon(GeneralVariables.CURSOR_ICON_PATH));
        setToolTipText("Selection d'élément");
        setFocusable(false);
        this.addActionListener(new CursorButtonListener());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
