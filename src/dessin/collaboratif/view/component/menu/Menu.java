package dessin.collaboratif.view.component.menu;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.misc.GeneralVariables;

import reseau.client.ClientNetwork;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

public class Menu extends JMenuBar {

    /**
     *
     */
    private static final long serialVersionUID = -2815773923677308498L;
    private FileMenu          fileMenu;
    private EditionMenu       editionMenu;
    private CollaborationMenu collaborationMenu;
    private HelpMenu          helpMenu;
    private JLabel            info;

    public Menu() {
        super();
        fileMenu = new FileMenu();
        this.add(fileMenu);
        editionMenu = new EditionMenu();
        this.add(editionMenu);
        collaborationMenu = new CollaborationMenu();
        this.add(collaborationMenu);
        helpMenu = new HelpMenu();
        this.add(helpMenu);
        info = new JLabel(GeneralVariables.INFO_MENU_HAVENT_HAND);
        this.add(info);

        final ImageIcon fileIcon = new ImageIcon(GeneralVariables.RED_LIGHT_ICON_PATH);

        info.setIcon(fileIcon);
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

    @Override
    public void repaint() {
        if (info != null) {
            if (ClientNetwork.getInstance().haveControl()) {
                final ImageIcon fileIcon = new ImageIcon(GeneralVariables.GREEN_LIGHT_ICON_PATH);

                info.setText(GeneralVariables.INFO_MENU_HAVE_HAND);
                info.setIcon(fileIcon);
            } else {
                if (ClientNetwork.getInstance().hasRqstdCtrl()) {
                    final ImageIcon fileIcon = new ImageIcon(GeneralVariables.ORANGE_LIGHT_ICON_PATH);

                    info.setText(GeneralVariables.INFO_MENU_REQUEST_HAND);
                    info.setIcon(fileIcon);
                } else {
                    final ImageIcon fileIcon = new ImageIcon(GeneralVariables.RED_LIGHT_ICON_PATH);

                    info.setText(GeneralVariables.INFO_MENU_HAVENT_HAND);
                    info.setIcon(fileIcon);
                }
            }
        }

        if (fileMenu != null) {
            getFileMenu().getExport().repaint();
        }

        if (editionMenu != null) {
            editionMenu.getUndo().repaint();
            editionMenu.getDelete().repaint();
            editionMenu.getMove().repaint();
            editionMenu.getRename().repaint();
        }

        super.repaint();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
