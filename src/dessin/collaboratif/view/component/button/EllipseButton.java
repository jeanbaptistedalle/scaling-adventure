package dessin.collaboratif.view.component.button;

import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.button.EllipseButtonListener;

public class EllipseButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680353709746079985L;

	public EllipseButton() {
		super("ellipse");
		setToolTipText("Dessiner une ellipse");
		this.addActionListener(new EllipseButtonListener());
	}

}
