package dessin.collaboratif.view.component.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;

import dessin.collaboratif.misc.DirectionEnum;
import dessin.collaboratif.view.component.button.MoveButton;

public class MoveDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1151343403574524760L;
	
	public static MoveDialog INSTANCE = null;
	
	private MoveButton upButton;
	private MoveButton downButton;
	private MoveButton leftButton;
	private MoveButton rightButton;

	private MoveDialog() {
		super();
		fill();
	}
	
	public static MoveDialog getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new MoveDialog();
		}
		return INSTANCE;
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
		setLocation(250,250);
		setVisible(true);
		setResizable(false);
//		this.setSize(new Dimension (250,250));
//		((JPanel)this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
