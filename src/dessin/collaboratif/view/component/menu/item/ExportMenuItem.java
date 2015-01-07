package dessin.collaboratif.view.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.menu.item.ExportListener;
import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportMenuItem extends JMenuItem {

    /**
     *
     */
    private static final long serialVersionUID = 8034532338930762358L;
    @SuppressWarnings("FieldMayBeFinal")
    private JFileChooser      fileChooser;

    public ExportMenuItem() {
        super(GeneralVariables.FILE_MENU_EXPORT);

        final ImageIcon exportIcon = new ImageIcon(GeneralVariables.FILE_MENU_EXPORT_ICON_PATH);

        setIcon(exportIcon);
        this.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('e'));
        this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));

        if (Client.getInstance().getImage() != null) {
            this.setEnabled(true);
        } else {
            this.setEnabled(false);
        }

        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter(".jpeg", "jpeg", "jpg"));
        this.addActionListener(new ExportListener(fileChooser));
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
