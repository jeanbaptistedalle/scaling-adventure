package dessin.collaboratif.view.component.menu.item;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import dessin.collaboratif.controller.component.menu.item.OpenListener;
import dessin.collaboratif.misc.GeneralVariables;

public class OpenMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -840379100240751601L;

	private JFileChooser fileChooser;

	public OpenMenuItem() {
		super(GeneralVariables.FILE_MENU_OPEN);
		ImageIcon openIcon = new ImageIcon(
				GeneralVariables.FILE_MENU_OPEN_ICON_PATH);
		this.setIcon(openIcon);
		fileChooser = new JFileChooser();

		fileChooser.setAcceptAllFileFilterUsed(false);

		fileChooser.setFileFilter(new FileNameExtensionFilter(
				".svg", "svg"));

		this.addActionListener(new OpenListener(fileChooser));
	}
}
