package dessin.collaboratif.view.component.menu.item;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import dessin.collaboratif.controller.component.menu.item.UndoListener;
import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;

public class UndoMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 807794640134507783L;

	public UndoMenuItem() {
		super(GeneralVariables.EDITION_MENU_UNDO);
		ImageIcon undoIcon = new ImageIcon(
				GeneralVariables.EDITION_MENU_UNDO_ICON_PATH);
		this.setIcon(undoIcon);
		if (Client.getInstance().getImage() != null && Client.getInstance().getImage().getDocumentElement().getFirstChild() != null) {
			this.setEnabled(true);
		} else {
			this.setEnabled(false);
		}
		this.addActionListener(new UndoListener());
	}

	@Override
	public void repaint() {
		if (Client.getInstance().getImage() != null && Client.getInstance().getImage().getDocumentElement().getFirstChild() != null) {
			this.setEnabled(true);
		} else {
			this.setEnabled(false);
		}
		super.repaint();
	}
}
