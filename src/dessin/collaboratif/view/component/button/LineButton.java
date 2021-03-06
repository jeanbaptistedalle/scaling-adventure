package dessin.collaboratif.view.component.button;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.button.LineButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class LineButton extends JToggleButton {

    /**
     *
     */
    private static final long serialVersionUID = 4835567050955071395L;

    public LineButton() {
        super("");
        setIcon(new ImageIcon(GeneralVariables.LINE_ICON_PATH));
        setToolTipText("Dessiner une ligne");
        setFocusable(false);
        this.addActionListener(new LineButtonListener());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
