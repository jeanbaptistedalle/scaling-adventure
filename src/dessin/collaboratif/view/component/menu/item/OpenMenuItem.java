package dessin.collaboratif.view.component.menu.item;

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
		fileChooser = new JFileChooser();

		fileChooser.setAcceptAllFileFilterUsed(false);

		fileChooser.setFileFilter(new FileNameExtensionFilter(
				".svg", "svg"));

		this.addActionListener(new OpenListener(fileChooser));
	}
}
