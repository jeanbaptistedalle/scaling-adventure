package dessin.collaboratif.view.component.menu;

import javax.swing.JMenu;

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.view.component.menu.item.CloseMenuItem;
import dessin.collaboratif.view.component.menu.item.NewMenuItem;
import dessin.collaboratif.view.component.menu.item.OpenMenuItem;
import dessin.collaboratif.view.component.menu.item.QuitMenuItem;

public class FileMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6807367852801088686L;

	private NewMenuItem newI;

	private OpenMenuItem open;

	private CloseMenuItem close;

	private QuitMenuItem quit;

	public FileMenu() {
		super(GeneralVariables.FILE_MENU_TITLE);
		newI = new NewMenuItem();
		this.add(newI);
		open = new OpenMenuItem();
		this.add(open);
		close = new CloseMenuItem();
		this.add(close);
		quit = new QuitMenuItem();
		this.add(quit);
	}

	public NewMenuItem getNewI() {
		return newI;
	}

	public void setNewI(NewMenuItem newI) {
		this.newI = newI;
	}

	public OpenMenuItem getOpen() {
		return open;
	}

	public void setOpen(OpenMenuItem open) {
		this.open = open;
	}

	public CloseMenuItem getClose() {
		return close;
	}

	public void setClose(CloseMenuItem close) {
		this.close = close;
	}

	public QuitMenuItem getQuit() {
		return quit;
	}

	public void setQuit(QuitMenuItem quit) {
		this.quit = quit;
	}
}
