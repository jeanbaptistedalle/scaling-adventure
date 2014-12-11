package dessin.collaboratif.controller.component.button;

//~--- non-JDK imports --------------------------------------------------------

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;
//~--- JDK imports ------------------------------------------------------------
import java.awt.Color;

/**
 * Listener du bouton ColorChooser
 *
 * Cette classe permet de changer la couleur du pinceau
 */
public class ColorPickerButtonListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param arg0
     */
    @Override
    public void actionPerformed(final ActionEvent arg0) {
        Color chosenColor = JColorChooser.showDialog(MainFrame.getInstance(), GeneralVariables.CHOOSE_COLOR_MESSAGE,
                                Color.white);

        if (chosenColor != null) {
            Client.getInstance().setSelectedColor(chosenColor);
            MainFrame.getInstance().getToolPanel().getColorPickerButton().setBackground(chosenColor);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
