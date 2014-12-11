package dessin.collaboratif.view.component.menu.item;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import dessin.collaboratif.controller.component.menu.item.NewListener;
import dessin.collaboratif.misc.GeneralVariables;

public class NewMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5479721616821036217L;

	public NewMenuItem() {
		super(GeneralVariables.FILE_MENU_NEW);
		final ImageIcon fileIcon = new ImageIcon(
				GeneralVariables.FILE_MENU_NEW_ICON_PATH);
		this.setIcon(fileIcon);
		this.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('n'));
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
		        InputEvent.CTRL_MASK));

		this.addActionListener(new NewListener());
	}
}
