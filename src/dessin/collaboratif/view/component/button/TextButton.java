package dessin.collaboratif.view.component.button;

import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.field.TextButtonListener;

public class TextButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680353709746079985L;

	public TextButton() {
		super("text");
		setFocusable(false);
		setToolTipText("Ajouter du texte");
		this.addActionListener(new TextButtonListener());
	}

}
