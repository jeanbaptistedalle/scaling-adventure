package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.dialog.ScaleDialog;
/**
 * Listener de l'item du menu Redimensionner
 * 
 * Permet de redimensionner une forme
 */
public class ScaleListener implements ActionListener{

	/**
	 * Méthode lancée à la détection d'un clic sur le bouton
	 * 
	 * @param arg0 
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(Client.getInstance().getImage() != null){
			Client.getInstance().setScaleDial(new ScaleDialog());
		}
	}
}
