package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;


/**
 * Listener du bouton Scale
 * 
 * Cette classe permet de passer en mode redimenssionnement de formes
 */public class ScaleButtonListener implements ActionListener {


		/**
		 * Méthode lancée à la détection d'un clic sur le bouton
		 * 
		 * @param e 
		 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		String act = e.getActionCommand();
		Client.getInstance().scale(act);
		Client.getInstance().getScaleDial().unpressAll();
		MainFrame.getInstance().repaintDrawPanel();
	}

}
