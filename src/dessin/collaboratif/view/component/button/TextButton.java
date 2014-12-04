package dessin.collaboratif.view.component.button;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.field.TextButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

public class TextButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680353709746079985L;

	public TextButton() {
		super("");
		setIcon(new ImageIcon(GeneralVariables.TEXT_ICON_PATH));
		setFocusable(false);
		setToolTipText("Ajouter du texte");
		this.addActionListener(new TextButtonListener());
	}

}
