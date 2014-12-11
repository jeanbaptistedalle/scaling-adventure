package dessin.collaboratif.view.component.button;

import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.MoveDialMouseAdapter;
import dessin.collaboratif.controller.component.button.MoveButtonListener;
import dessin.collaboratif.misc.DirectionEnum;

public class MoveButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 167490277775938940L;

	private DirectionEnum direction;
	
	public MoveButton(DirectionEnum dir) {
		super(dir.toString());
		this.setFocusable(false);
		this.setDirection(dir);
		this.setActionCommand(dir.toString());
		this.addActionListener(new MoveButtonListener());
		this.setVisible(true);
		this.addMouseListener(new MoveDialMouseAdapter());
		this.addMouseMotionListener(new MoveDialMouseAdapter());
//		this.setPreferredSize(new Dimension (50,50));
	}

	public DirectionEnum getDirection() {
		return direction;
	}

	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
	}

}
