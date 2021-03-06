package dessin.collaboratif.view.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.menu.item.TakeHandListener;
import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class TakeHandMenuItem extends JMenuItem {

    /**
     *
     */
    private static final long serialVersionUID = 7460592700478889054L;

    public TakeHandMenuItem() {
        super(GeneralVariables.COLLABORATION_MENU_TAKE_HAND);
        this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        this.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('p'));

        ImageIcon takeHandIcon = new ImageIcon(GeneralVariables.COLLABORATION_MENU_TAKE_HAND_ICON_PATH);

        this.setIcon(takeHandIcon);

        if (Client.getInstance().getImage() != null) {
            this.setEnabled(true);
        } else {
            this.setEnabled(false);
        }

        this.addActionListener(new TakeHandListener());
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
