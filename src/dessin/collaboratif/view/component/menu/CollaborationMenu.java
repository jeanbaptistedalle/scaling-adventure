package dessin.collaboratif.view.component.menu;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.view.component.menu.item.TakeHandMenuItem;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

public class CollaborationMenu extends JMenu {

    /**
     *
     */
    private static final long serialVersionUID = -5394492329818948094L;
    private TakeHandMenuItem  takeHand;

    public CollaborationMenu() {
        super(GeneralVariables.COLLABORATION_MENU_TITLE);
        this.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('c'));
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


//~ Formatted by Jindent --- http://www.jindent.com
