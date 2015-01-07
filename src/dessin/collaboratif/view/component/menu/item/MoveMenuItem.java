package dessin.collaboratif.view.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.menu.item.MoveListener;
import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MoveMenuItem extends JMenuItem {

    /**
     *
     */
    private static final long serialVersionUID = 807794640134507783L;

    public MoveMenuItem() {
        super(GeneralVariables.EDITION_MENU_MOVE);
        this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));

        ImageIcon moveIcon = new ImageIcon(GeneralVariables.EDITION_MENU_MOVE_ICON_PATH);

        this.setIcon(moveIcon);

        if ((Client.getInstance().getImage() != null)
                && (Client.getInstance().getImage().getDocumentElement().getFirstChild() != null)
                && (Client.getInstance().getSelected() != -1)) {
            this.setEnabled(true);
        } else {
            this.setEnabled(false);
        }

        this.addActionListener(new MoveListener());
    }

    @Override
    public void repaint() {
        if ((Client.getInstance().getImage() != null)
                && (Client.getInstance().getImage().getDocumentElement().getFirstChild() != null)
                && (Client.getInstance().getSelected() != -1)) {
            this.setEnabled(true);
        } else {
            this.setEnabled(false);
        }

        super.repaint();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
