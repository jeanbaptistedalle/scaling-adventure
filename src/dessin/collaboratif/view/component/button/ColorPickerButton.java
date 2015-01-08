package dessin.collaboratif.view.component.button;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.button.ColorPickerButtonListener;
import dessin.collaboratif.model.Client;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JButton;

public class ColorPickerButton extends JButton {

    /**
     *
     */
    private static final long serialVersionUID = -718703034383032397L;

    public ColorPickerButton() {
        super();
        setToolTipText("Changer la couleur");
        setFocusable(false);
        this.setBackground(Client.getInstance().getSelectedColor());
        this.addActionListener(new ColorPickerButtonListener());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
