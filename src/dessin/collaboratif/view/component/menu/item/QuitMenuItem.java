package dessin.collaboratif.view.component.menu.item;

import javax.swing.JMenuItem;

import dessin.collaboratif.controller.component.menu.item.QuitListener;
import dessin.collaboratif.misc.GeneralVariables;

public class QuitMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1330530720171433708L;

	public QuitMenuItem() {
		super(GeneralVariables.FILE_MENU_QUIT);
		this.addActionListener(new QuitListener());
	}
}
