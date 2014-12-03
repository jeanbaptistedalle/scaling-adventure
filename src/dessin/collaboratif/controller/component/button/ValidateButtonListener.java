package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.view.component.TextInputFrame;

/**
 * Listener du bouton Validate
 * 
 * Cette classe permet de valider la saisie du texte de TextInputFrame
 * 
 * @see TextInputFrame
 */
public class ValidateButtonListener implements ActionListener{


	/**
	 * Méthode lancée à la détection d'un clic sur le bouton
	 * 
	 * @param e 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		TextInputFrame.getInstance().getTextField().getText();
	}

}
