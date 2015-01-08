package dessin.collaboratif.view.component.menu;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.view.component.menu.item.CloseMenuItem;
import dessin.collaboratif.view.component.menu.item.ExportMenuItem;
import dessin.collaboratif.view.component.menu.item.NewMenuItem;
import dessin.collaboratif.view.component.menu.item.OpenMenuItem;
import dessin.collaboratif.view.component.menu.item.QuitMenuItem;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

public class FileMenu extends JMenu {

    /**
     *
     */
    private static final long serialVersionUID = -6807367852801088686L;
    private NewMenuItem       newI;
    private OpenMenuItem      open;
    private ExportMenuItem    export;
    private QuitMenuItem      quit;

    public FileMenu() {
        super(GeneralVariables.FILE_MENU_TITLE);
        this.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('f'));
        newI = new NewMenuItem();
        this.add(newI);
        open = new OpenMenuItem();
        this.add(open);
        export = new ExportMenuItem();
        this.add(export);
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

    public QuitMenuItem getQuit() {
        return quit;
    }

    public void setQuit(QuitMenuItem quit) {
        this.quit = quit;
    }

    public ExportMenuItem getExport() {
        return export;
    }

    public void setExport(ExportMenuItem export) {
        this.export = export;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
