package dessin.collaboratif.view.component.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JPanel;

import dessin.collaboratif.misc.DirectionEnum;
import dessin.collaboratif.view.component.button.MoveButton;

public class MoveDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1151343403574524760L;
	
	private MoveButton upButton;
	private MoveButton downButton;
	private MoveButton leftButton;
	private MoveButton rightButton;

	public MoveDialog() {
		super();
		fill();
		setLocation(250,250);
	}

	public MoveDialog(Frame owner) {
		super(owner);
		fill();
	}

	public MoveDialog(Dialog owner) {
		super(owner);
		fill();
	}

	public MoveDialog(Window owner) {
		super(owner);
		fill();
	}

	public MoveDialog(Frame owner, boolean modal) {
		super(owner, modal);
		fill();
	}

	public MoveDialog(Frame owner, String title) {
		super(owner, title);
		fill();
	}

	public MoveDialog(Dialog owner, boolean modal) {
		super(owner, modal);
		fill();
	}

	public MoveDialog(Dialog owner, String title) {
		super(owner, title);
		fill();
	}

	public MoveDialog(Window owner, ModalityType modalityType) {
		super(owner, modalityType);
		fill();
	}

	public MoveDialog(Window owner, String title) {
		super(owner, title);
		fill();
	}

	public MoveDialog(Frame arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		fill();
	}

	public MoveDialog(Dialog owner, String title, boolean modal) {
		super(owner, title, modal);
		fill();
	}

	public MoveDialog(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
		fill();
	}

	public MoveDialog(Frame arg0, String arg1, boolean arg2,
			GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
		fill();
	}

	public MoveDialog(Dialog owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
		fill();
	}

	public MoveDialog(Window owner, String title, ModalityType modalityType,
			GraphicsConfiguration gc) {
		super(owner, title, modalityType, gc);
		fill();
	}

	private void fill() {
		upButton = new MoveButton(DirectionEnum.UP);
		downButton = new MoveButton(DirectionEnum.DOWN);
		leftButton = new MoveButton(DirectionEnum.LEFT);
		rightButton = new MoveButton(DirectionEnum.RIGHT);

		JPanel pan = new JPanel(new BorderLayout());
		
		pan.add(upButton, BorderLayout.NORTH);
		pan.add(downButton, BorderLayout.SOUTH);
		pan.add(leftButton, BorderLayout.WEST);
		pan.add(rightButton, BorderLayout.EAST);
		
		add(pan);
		
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public void unpressAll() {
		upButton.getModel().setPressed(false);
		downButton.getModel().setPressed(false);
		leftButton.getModel().setPressed(false);
		rightButton.getModel().setPressed(false);
		
		upButton.setSelected(false);
		downButton.setSelected(false);
		leftButton.setSelected(false);
		rightButton.setSelected(false);
	}

}
