package dessin.collaboratif.view.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import dessin.collaboratif.misc.GeneralVariables;

public class UndoMenuItem extends JMenuItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 807794640134507783L;
	
	public UndoMenuItem(){
		super(GeneralVariables.EDITION_MENU_UNDO);
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
	}
}
