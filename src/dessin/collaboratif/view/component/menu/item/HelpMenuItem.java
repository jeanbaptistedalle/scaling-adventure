package dessin.collaboratif.view.component.menu.item;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import dessin.collaboratif.controller.component.menu.item.HelpListener;
import dessin.collaboratif.misc.GeneralVariables;

public class HelpMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6173068911474770587L;

	public HelpMenuItem() {
		super(GeneralVariables.HELP_MENU_HELP);
		final ImageIcon helpIcon = new ImageIcon(
				GeneralVariables.HELP_MENU_HELP_ICON_PATH);
		this.setIcon(helpIcon);
		this.addActionListener(new HelpListener());
	}
}
