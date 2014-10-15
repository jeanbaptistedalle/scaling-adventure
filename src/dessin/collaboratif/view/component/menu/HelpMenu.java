package dessin.collaboratif.view.component.menu;

import javax.swing.JMenu;

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.view.component.menu.item.HelpMenuItem;

public class HelpMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5394492329818948094L;

	private HelpMenuItem help;

	public HelpMenu() {
		super(GeneralVariables.HELP_MENU_TITLE);
		help = new HelpMenuItem();
		this.add(help);
	}

	public HelpMenuItem getHelp() {
		return help;
	}

	public void setHelp(HelpMenuItem help) {
		this.help = help;
	}

}
