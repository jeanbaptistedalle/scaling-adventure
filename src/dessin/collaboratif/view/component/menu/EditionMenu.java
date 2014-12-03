package dessin.collaboratif.view.component.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.view.component.menu.item.DeleteMenuItem;
import dessin.collaboratif.view.component.menu.item.MoveMenuItem;
import dessin.collaboratif.view.component.menu.item.RenameMenuItem;
import dessin.collaboratif.view.component.menu.item.UndoMenuItem;

public class EditionMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8313338369807254353L;

	private UndoMenuItem undo;
	private DeleteMenuItem delete;
	private MoveMenuItem move;
	private RenameMenuItem rename;

	public EditionMenu() {
		super(GeneralVariables.EDITION_MENU_TITLE);
		this.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('e'));
		undo = new UndoMenuItem();
		this.add(undo);
		delete = new DeleteMenuItem();
		this.add(delete);
		move = new MoveMenuItem();
		this.add(move);
		rename = new RenameMenuItem();
		this.add(rename);
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

	public MoveMenuItem getMove() {
		return move;
	}

	public void setMove(MoveMenuItem move) {
		this.move = move;
	}

	public RenameMenuItem getRename() {
		return rename;
	}

	public void setRename(RenameMenuItem rename) {
		this.rename = rename;
	}

}
