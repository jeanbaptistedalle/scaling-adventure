package dessin.collaboratif.view.component.menu;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.view.component.menu.item.HelpMenuItem;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

public class HelpMenu extends JMenu {

    /**
     *
     */
    private static final long serialVersionUID = -5394492329818948094L;
    private HelpMenuItem      help;

    public HelpMenu() {
        super(GeneralVariables.HELP_MENU_TITLE);
        this.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('a'));
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


//~ Formatted by Jindent --- http://www.jindent.com
