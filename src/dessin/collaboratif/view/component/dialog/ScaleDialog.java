package dessin.collaboratif.view.component.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JPanel;

import dessin.collaboratif.misc.ScaleEnum;
import dessin.collaboratif.view.component.button.ScaleButton;

public class ScaleDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1151343403574524760L;
	
	private ScaleButton upButton;
	private ScaleButton downButton;

	public ScaleDialog() {
		super();
		fill();
	}

	public ScaleDialog(Frame owner) {
		super(owner);
		fill();
	}

	public ScaleDialog(Dialog owner) {
		super(owner);
		fill();
	}

	public ScaleDialog(Window owner) {
		super(owner);
		fill();
	}

	public ScaleDialog(Frame owner, boolean modal) {
		super(owner, modal);
		fill();
	}

	public ScaleDialog(Frame owner, String title) {
		super(owner, title);
		fill();
	}

	public ScaleDialog(Dialog owner, boolean modal) {
		super(owner, modal);
		fill();
	}

	public ScaleDialog(Dialog owner, String title) {
		super(owner, title);
		fill();
	}

	public ScaleDialog(Window owner, ModalityType modalityType) {
		super(owner, modalityType);
		fill();
	}

	public ScaleDialog(Window owner, String title) {
		super(owner, title);
		fill();
	}

	public ScaleDialog(Frame arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		fill();
	}

	public ScaleDialog(Dialog owner, String title, boolean modal) {
		super(owner, title, modal);
		fill();
	}

	public ScaleDialog(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
		fill();
	}

	public ScaleDialog(Frame arg0, String arg1, boolean arg2,
			GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
		fill();
	}

	public ScaleDialog(Dialog owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
		fill();
	}

	public ScaleDialog(Window owner, String title, ModalityType modalityType,
			GraphicsConfiguration gc) {
		super(owner, title, modalityType, gc);
		fill();
	}

	private void fill() {
		upButton = new ScaleButton(ScaleEnum.INCREASE);
		downButton = new ScaleButton(ScaleEnum.DECREASE);

		JPanel pan = new JPanel(new BorderLayout());
		
		pan.add(upButton, BorderLayout.EAST);
		pan.add(downButton, BorderLayout.WEST);
		
		add(pan);
		
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(250,250);
		setVisible(true);
		setResizable(false);
	}

	public void unpressAll() {
		upButton.getModel().setPressed(false);
		downButton.getModel().setPressed(false);
		
		upButton.setSelected(false);
		downButton.setSelected(false);
	}

}
