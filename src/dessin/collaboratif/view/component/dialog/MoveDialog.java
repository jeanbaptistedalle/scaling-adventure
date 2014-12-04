package dessin.collaboratif.view.component.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import dessin.collaboratif.misc.DirectionEnum;
import dessin.collaboratif.misc.ScaleEnum;
import dessin.collaboratif.view.component.button.MoveButton;
import dessin.collaboratif.view.component.button.ScaleButton;

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
	private ScaleButton largerButton;
	private ScaleButton smallerButton;

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
		
		JPanel p = new JPanel(new BorderLayout());
		
		upButton = new MoveButton(DirectionEnum.UP);
		downButton = new MoveButton(DirectionEnum.DOWN);
		leftButton = new MoveButton(DirectionEnum.LEFT);
		rightButton = new MoveButton(DirectionEnum.RIGHT);

		JPanel pan = new JPanel(new BorderLayout());
		
		pan.add(upButton, BorderLayout.NORTH);
		pan.add(downButton, BorderLayout.SOUTH);
		pan.add(leftButton, BorderLayout.WEST);
		pan.add(rightButton, BorderLayout.EAST);
		
		p.add(pan, BorderLayout.NORTH);
		
		largerButton = new ScaleButton(ScaleEnum.INCREASE);
		smallerButton = new ScaleButton(ScaleEnum.DECREASE);

		JPanel pan2 = new JPanel(new BorderLayout());
		
		pan2.add(largerButton, BorderLayout.EAST);
		pan2.add(smallerButton, BorderLayout.WEST);
		
		p.add(pan2, BorderLayout.SOUTH);
		
		add(p, BorderLayout.CENTER);
		
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(250,250);
		setVisible(true);
		setResizable(true);
//		this.setSize(new Dimension (250,250));
//		((JPanel)this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	public void unpressAll() {
		upButton.getModel().setPressed(false);
		downButton.getModel().setPressed(false);
		leftButton.getModel().setPressed(false);
		rightButton.getModel().setPressed(false);
		largerButton.getModel().setPressed(false);
		smallerButton.getModel().setPressed(false);
		
		upButton.setSelected(false);
		downButton.setSelected(false);
		leftButton.setSelected(false);
		rightButton.setSelected(false);
		largerButton.setSelected(false);
		smallerButton.setSelected(false);
	}

}
