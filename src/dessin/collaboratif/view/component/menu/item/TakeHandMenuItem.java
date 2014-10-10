package dessin.collaboratif.view.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import dessin.collaboratif.misc.GeneralVariables;

public class TakeHandMenuItem extends JMenuItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7460592700478889054L;
	
	public TakeHandMenuItem(){
		super(GeneralVariables.COLLABORATION_MENU_TAKE_HAND);
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
	}
}
