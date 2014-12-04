package dessin.collaboratif.view.component.button;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.button.CursorButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

public class CursorButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5254659661703089570L;

	public CursorButton() {
		super("");
		setIcon(new ImageIcon(GeneralVariables.CURSOR_ICON_PATH));
		setToolTipText("Selection d'élément");
		setFocusable(false);
		this.addActionListener(new CursorButtonListener());
	}
}
