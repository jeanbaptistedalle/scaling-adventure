package dessin.collaboratif.view.component.menu;

import javax.swing.JMenuBar;

public class Menu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2815773923677308498L;

	private FileMenu fileMenu;

	private EditionMenu editionMenu;

	private CollaborationMenu collaborationMenu;

	public Menu() {
		super();
		fileMenu = new FileMenu();
		this.add(fileMenu);
		editionMenu = new EditionMenu();
		this.add(editionMenu);
		collaborationMenu = new CollaborationMenu();
		this.add(collaborationMenu);
	}

	public FileMenu getFileMenu() {
		return fileMenu;
	}

	public void setFileMenu(FileMenu fileMenu) {
		this.fileMenu = fileMenu;
	}

	public EditionMenu getEditionMenu() {
		return editionMenu;
	}

	public void setEditionMenu(EditionMenu editionMenu) {
		this.editionMenu = editionMenu;
	}

	public CollaborationMenu getCollaborationMenu() {
		return collaborationMenu;
	}

	public void setCollaborationMenu(CollaborationMenu collaborationMenu) {
		this.collaborationMenu = collaborationMenu;
	}
}
