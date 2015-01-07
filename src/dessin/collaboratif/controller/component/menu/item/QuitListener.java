package dessin.collaboratif.controller.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import reseau.client.ClientNetwork;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener de l'item du menu Quitter
 *
 * Permet d'arrêter le logiciel
 */
public class QuitListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param arg0
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        ClientNetwork.getInstance().disconnect();
        System.exit(0);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
