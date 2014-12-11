package dessin.collaboratif.controller.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import java.awt.event.ActionListener;

import reseau.client.ClientNetwork;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;
//~--- JDK imports ------------------------------------------------------------
import java.awt.event.ActionEvent;

/**
 * Listener de l'item de suppression de forme
 *
 * Supprime la forme selectionnée
 */
public class DeleteListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param arg0
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (ClientNetwork.getInstance().haveControl()) {
            if (Client.getInstance().getImage() != null) {
                Client.getInstance().delete();
                MainFrame.getInstance().repaintDrawPanel();

                /* Envoi du SVG au serveur */
                ClientNetwork.getInstance().submitPicture(Client.getInstance().imageToString());
            }
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
