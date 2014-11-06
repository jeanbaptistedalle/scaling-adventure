package dessin.collaboratif.view.component.button;

import java.awt.Dimension;

import javax.swing.JToggleButton;

import dessin.collaboratif.controller.component.ScaleDialMouseAdapter;
import dessin.collaboratif.controller.component.button.ScaleButtonListener;
import dessin.collaboratif.misc.ScaleEnum;

public class ScaleButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 167490277775938940L;

	private ScaleEnum scale;
	
	public ScaleButton(ScaleEnum dir) {
		super(dir.toString());
		this.setFocusable(false);
		this.setScale(dir);
		this.setActionCommand(dir.toString());
		this.addActionListener(new ScaleButtonListener());
		this.setVisible(true);
		this.addMouseListener(new ScaleDialMouseAdapter());
		this.addMouseMotionListener(new ScaleDialMouseAdapter());
//		this.setPreferredSize(new Dimension (40,50));
	}

	public ScaleEnum getScale() {
		return scale;
	}

	public void setScale(ScaleEnum scale) {
		this.scale = scale;
	}

}
