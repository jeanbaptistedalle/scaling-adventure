package dessin.collaboratif.view.component.button;

import javax.swing.JButton;

import dessin.collaboratif.controller.component.button.CircleButtonListener;

public class CircleButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680353709746079985L;

	public CircleButton() {
		super("circle");
		this.addActionListener(new CircleButtonListener());
	}

}
