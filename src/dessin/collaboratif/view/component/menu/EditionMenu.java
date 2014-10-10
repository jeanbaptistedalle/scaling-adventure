package dessin.collaboratif.view.component.menu;

import javax.swing.JMenu;

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.view.component.menu.item.UndoMenuItem;

public class EditionMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8313338369807254353L;

	private UndoMenuItem undo;

	public EditionMenu() {
		super(GeneralVariables.EDITION_MENU_TITLE);
		undo = new UndoMenuItem();
		this.add(undo);
	}

	public UndoMenuItem getUndo() {
		return undo;
	}

	public void setUndo(UndoMenuItem undo) {
		this.undo = undo;
	}

}
