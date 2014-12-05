package dessin.collaboratif.view.component.button;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.button.EllipseButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

public class EllipseButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680353709746079985L;

	public EllipseButton() {
		super("");
		setIcon(new ImageIcon(GeneralVariables.ELLIPSE_ICON_PATH));
		setToolTipText("Dessiner une ellipse");
		setFocusable(false);
		this.addActionListener(new EllipseButtonListener());
	}

}
