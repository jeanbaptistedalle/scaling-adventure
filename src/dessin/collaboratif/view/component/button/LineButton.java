package dessin.collaboratif.view.component.button;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.button.LineButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

public class LineButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4835567050955071395L;

	public LineButton() {
		super("");
		setIcon(new ImageIcon(GeneralVariables.LINE_ICON_PATH));
		setToolTipText("Dessiner une ligne");
		setFocusable(false);
		this.addActionListener(new LineButtonListener());
	}

}
