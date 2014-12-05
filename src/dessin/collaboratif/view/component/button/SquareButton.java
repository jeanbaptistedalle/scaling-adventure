package dessin.collaboratif.view.component.button;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.button.SquareButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

public class SquareButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680353709746079985L;

	public SquareButton() {
		super("");
		setIcon(new ImageIcon(GeneralVariables.SQUARE_ICON_PATH));
		setToolTipText("Dessiner un rectangle");
		setFocusable(false);
		this.addActionListener(new SquareButtonListener());
	}

}
