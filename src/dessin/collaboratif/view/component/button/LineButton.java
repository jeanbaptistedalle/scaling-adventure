package dessin.collaboratif.view.component.button;

import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.button.LineButtonListener;

public class LineButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4835567050955071395L;

	public LineButton() {
		super("line");
		setToolTipText("Dessiner une ligne");
		this.addActionListener(new LineButtonListener());
	}

}
