package dessin.collaboratif.view.component.button;

import javax.swing.JButton;

import dessin.collaboratif.controller.component.button.LineButtonListener;

public class LineButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4835567050955071395L;
	

	public LineButton() {
		super("line");
		this.addActionListener(new LineButtonListener());
	}

}
