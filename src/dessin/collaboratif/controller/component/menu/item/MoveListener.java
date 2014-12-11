package dessin.collaboratif.controller.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import java.awt.event.ActionListener;

import reseau.client.ClientNetwork;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.dialog.MoveDialog;
//~--- JDK imports ------------------------------------------------------------
import java.awt.event.ActionEvent;

/**
 * Listener de l'item de déplacement
 *
 * Permet de déplacer la forme sélectionnée
 */
public class MoveListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param arg0
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (ClientNetwork.getInstance().haveControl()) {
            if (Client.getInstance().getImage() != null) {
                MoveDialog.getInstance().setVisible(true);
            }
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
