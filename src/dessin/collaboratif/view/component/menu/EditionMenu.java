package dessin.collaboratif.view.component.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.view.component.menu.item.DeleteMenuItem;
import dessin.collaboratif.view.component.menu.item.UndoMenuItem;

public class EditionMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8313338369807254353L;

	private UndoMenuItem undo;
	private DeleteMenuItem delete;

	public EditionMenu() {
		super(GeneralVariables.EDITION_MENU_TITLE);
		this.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('e'));
		undo = new UndoMenuItem();
		this.add(undo);
		delete = new DeleteMenuItem();
		this.add(delete);
	}

	public UndoMenuItem getUndo() {
		return undo;
	}

	public void setUndo(UndoMenuItem undo) {
		this.undo = undo;
	}

	public DeleteMenuItem getDelete() {
		return delete;
	}

	public void setDelete(DeleteMenuItem delete) {
		this.delete = delete;
	}

}
