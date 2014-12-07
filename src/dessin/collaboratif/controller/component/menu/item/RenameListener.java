package dessin.collaboratif.controller.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.dialog.RenameDialog;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener de l'item du menu Renommer
 *
 *  Permet de modifier le texte d'une forme TEXT
 */
public class RenameListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param arg0
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (Client.getInstance().getImage() != null) {
            RenameDialog.getInstance().setVisible(true);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
