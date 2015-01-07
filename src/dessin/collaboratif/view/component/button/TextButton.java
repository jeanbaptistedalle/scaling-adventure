package dessin.collaboratif.view.component.button;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.field.TextButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class TextButton extends JToggleButton {

    /**
     *
     */
    private static final long serialVersionUID = 1680353709746079985L;

    public TextButton() {
        super("");
        setIcon(new ImageIcon(GeneralVariables.TEXT_ICON_PATH));
        setFocusable(false);
        setToolTipText("Ajouter du texte");
        this.addActionListener(new TextButtonListener());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
