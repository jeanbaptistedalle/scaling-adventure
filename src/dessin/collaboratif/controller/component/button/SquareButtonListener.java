package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

/**
 * Listener du bouton Square
 * 
 * Cette classe permet de passer en mode dessin de carrés
 */
public class SquareButtonListener implements ActionListener {


	/**
	 * Méthode lancée à la détection d'un clic sur le bouton
	 * 
	 * @param e 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Client.getInstance().setCurrentDraw(DrawModelEnum.RECTANGLE);
		MainFrame.getInstance().getToolPanel().press(DrawModelEnum.RECTANGLE);

	}

}
