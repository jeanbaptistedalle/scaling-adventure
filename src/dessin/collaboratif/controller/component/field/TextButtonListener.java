package dessin.collaboratif.controller.component.field;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener du champs de saisie de la forme TEXT
 *
 * Cette classe permet de passer en mode TEXT tout en changeant la valeur du texte à insérer
 */
public class TextButtonListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Client.getInstance().setCurrentDraw(DrawModelEnum.TEXT);
        MainFrame.getInstance().getToolPanel().press(DrawModelEnum.TEXT);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
