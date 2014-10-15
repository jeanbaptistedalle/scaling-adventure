package dessin.collaboratif.view.component.menu.item;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import dessin.collaboratif.controller.component.menu.item.NewListener;
import dessin.collaboratif.misc.GeneralVariables;

public class NewMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5479721616821036217L;

	private JFileChooser fileChooser;

	public NewMenuItem() {
		super(GeneralVariables.FILE_MENU_NEW);
		final ImageIcon fileIcon = new ImageIcon(
				GeneralVariables.FILE_MENU_NEW_ICON_PATH);
		this.setIcon(fileIcon);

		fileChooser = new JFileChooser();

		fileChooser.setAcceptAllFileFilterUsed(false);

		fileChooser.setFileFilter(new FileNameExtensionFilter(".svg", "svg"));
		this.addActionListener(new NewListener(fileChooser));
	}
}
