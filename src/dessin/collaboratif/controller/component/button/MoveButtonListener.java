package dessin.collaboratif.controller.component.button;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;
import dessin.collaboratif.view.component.dialog.MoveDialog;

import reseau.client.ClientNetwork;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener du bouton Move
 *
 * Cette classe permet de passer en mode déplacement de formes
 */
public class MoveButtonListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param e
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (ClientNetwork.getInstance().haveControl()) {
            String act = e.getActionCommand();

            Client.getInstance().move(act);
            MoveDialog.getInstance().unpressAll();
            MainFrame.getInstance().repaintDrawPanel();
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
