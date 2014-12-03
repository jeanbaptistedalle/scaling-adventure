package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.view.component.HelpFrame;

/**
 * Listener de l'item d'aide
 * 
 * Affiche un panneau d'aide
 */
public class HelpListener implements ActionListener {


	/**
	 * Méthode lancée à la détection d'un clic sur le bouton
	 * 
	 * @param arg0 
	 */
	public void actionPerformed(final ActionEvent arg0) {
		HelpFrame.getInstance().setVisible(true);
	}
}
