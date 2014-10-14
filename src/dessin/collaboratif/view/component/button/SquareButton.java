package dessin.collaboratif.view.component.button;

import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.button.SquareButtonListener;

public class SquareButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680353709746079985L;

	public SquareButton() {
		super("square");
		this.addActionListener(new SquareButtonListener());
	}

}
