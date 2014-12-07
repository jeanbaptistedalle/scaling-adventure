package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;
import reseau.client.ClientNetwork;

/**
 * Listener de l'item de suppression de forme
 * 
 * Supprime la forme selectionnée
 */
public class DeleteListener implements ActionListener{


	/**
	 * Méthode lancée à la détection d'un clic sur le bouton
	 * 
	 * @param arg0 
	 */
        @Override
	public void actionPerformed(ActionEvent arg0) {
		if(Client.getInstance().getImage() != null){
			Client.getInstance().delete();
			MainFrame.getInstance().repaintDrawPanel();
                        
                        /* Envoi du SVG au serveur */
                        if(ClientNetwork.getInstance().haveControl())
                            ClientNetwork.getInstance().submitPicture(Client.getInstance().imageToString());
		}
	}
}
