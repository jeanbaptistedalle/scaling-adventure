package dessin.collaboratif.view.component.menu.item;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import dessin.collaboratif.controller.component.menu.item.QuitListener;
import dessin.collaboratif.misc.GeneralVariables;

public class QuitMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1330530720171433708L;

	public QuitMenuItem() {
		super(GeneralVariables.FILE_MENU_QUIT);
		ImageIcon quitIcon = new ImageIcon(
				GeneralVariables.FILE_MENU_QUIT_ICON_PATH);
		this.setIcon(quitIcon);
		this.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('q'));
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
		        InputEvent.CTRL_MASK));
		this.addActionListener(new QuitListener());
	}
}
