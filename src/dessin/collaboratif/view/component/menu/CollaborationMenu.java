package dessin.collaboratif.view.component.menu;

import javax.swing.JMenu;

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.view.component.menu.item.TakeHandMenuItem;

public class CollaborationMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5394492329818948094L;

	private TakeHandMenuItem takeHand;

	public CollaborationMenu() {
		super(GeneralVariables.COLLABORATION_MENU_TITLE);
		takeHand = new TakeHandMenuItem();
		this.add(takeHand);
	}

	public TakeHandMenuItem getTakeHand() {
		return takeHand;
	}

	public void setTakeHand(TakeHandMenuItem takeHand) {
		this.takeHand = takeHand;
	}

}
