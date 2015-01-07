package dessin.collaboratif.view.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.menu.item.OpenListener;
import dessin.collaboratif.misc.GeneralVariables;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenMenuItem extends JMenuItem {

    /**
     *
     */
    private static final long serialVersionUID = -840379100240751601L;
    @SuppressWarnings("FieldMayBeFinal")
    private JFileChooser      fileChooser;

    public OpenMenuItem() {
        super(GeneralVariables.FILE_MENU_OPEN);

        ImageIcon openIcon = new ImageIcon(GeneralVariables.FILE_MENU_OPEN_ICON_PATH);

        this.setIcon(openIcon);
        this.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('o'));
        this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        fileChooser = new JFileChooser() {
            @Override
            public void approveSelection() {
                if (!getSelectedFile().exists()) {
                    JOptionPane.showMessageDialog(null, GeneralVariables.FILE_NOT_EXISTS);

                    return;
                }

                super.approveSelection();
            }
        };
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter(".svg", "svg"));
        this.addActionListener(new OpenListener(fileChooser));
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
