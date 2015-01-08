package dessin.collaboratif.view.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.menu.item.CloseListener;
import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class CloseMenuItem extends JMenuItem {

    /**
     *
     */
    private static final long serialVersionUID = -1925657456085063018L;

    public CloseMenuItem() {
        super(GeneralVariables.FILE_MENU_CLOSE);

        final ImageIcon closeIcon = new ImageIcon(GeneralVariables.FILE_MENU_CLOSE_ICON_PATH);

        this.setIcon(closeIcon);

        if (Client.getInstance().getImage() != null) {
            this.setEnabled(true);
        } else {
            this.setEnabled(false);
        }

        this.addActionListener(new CloseListener());
    }

    @Override
    public void repaint() {
        if (Client.getInstance().getImage() != null) {
            this.setEnabled(true);
        } else {
            this.setEnabled(false);
        }

        super.repaint();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
