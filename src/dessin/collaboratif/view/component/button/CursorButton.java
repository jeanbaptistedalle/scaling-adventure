package dessin.collaboratif.view.component.button;

import javax.swing.JButton;

import dessin.collaboratif.controller.component.button.CursorButtonListener;

public class CursorButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5254659661703089570L;

	public CursorButton() {
		super("cursor");
		this.addActionListener(new CursorButtonListener());
	}
}
