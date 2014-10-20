package dessin.collaboratif.view.component.button;

import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.button.CircleButtonListener;

public class CircleButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680353709746079985L;

	public CircleButton() {
		super("circle");
		setToolTipText("Dessiner un cercle");
		setFocusable(false);
		this.addActionListener(new CircleButtonListener());
	}

}
