package dessin.collaboratif.controller.component.button;

//~--- non-JDK imports --------------------------------------------------------

import java.awt.event.ActionListener;

import reseau.client.ClientNetwork;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;
import dessin.collaboratif.view.component.dialog.MoveDialog;
//~--- JDK imports ------------------------------------------------------------
import java.awt.event.ActionEvent;

/**
 * Listener du bouton Scale
 *
 * Cette classe permet de passer en mode redimensionnement de formes
 */
public class ScaleButtonListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param e
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (ClientNetwork.getInstance().haveControl()) {
            String act = e.getActionCommand();

            Client.getInstance().scale(act);
            MoveDialog.getInstance().unpressAll();
            MainFrame.getInstance().repaintDrawPanel();

            /* Envoi du SVG au serveur */
            ClientNetwork.getInstance().submitPicture(Client.getInstance().imageToString());
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
