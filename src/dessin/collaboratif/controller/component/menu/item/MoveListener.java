package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.dialog.MoveDialog;

/**
 * Listener de l'item de déplacement
 * 
 * Permet de déplacer la forme sélectionnée
 */
public class MoveListener implements ActionListener{


	/**
	 * Méthode lancée à la détection d'un clic sur le bouton
	 * 
	 * @param arg0 
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(Client.getInstance().getImage() != null){
			Client.getInstance().setMoveDial(new MoveDialog());
		}
	}
}
