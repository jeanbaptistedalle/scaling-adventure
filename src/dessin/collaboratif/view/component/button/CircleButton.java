package dessin.collaboratif.view.component.button;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.button.CircleButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

public class CircleButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680353709746079985L;

	public CircleButton() {
		super("");
		setIcon(new ImageIcon(GeneralVariables.CIRCLE_ICON_PATH));
		setToolTipText("Dessiner un cercle");
		setFocusable(false);
		this.addActionListener(new CircleButtonListener());
	}

}
