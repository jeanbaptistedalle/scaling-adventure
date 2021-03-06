package dessin.collaboratif.view.component;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.misc.GeneralVariables;

//~--- JDK imports ------------------------------------------------------------

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 2023568065097190397L;
    private static HelpFrame  INSTANCE         = null;
    private JPanel            helpContent;
    private JLabel            helpText;

    private HelpFrame() {
        this.setTitle(GeneralVariables.HELP_FRAME_TITLE);

        final ImageIcon helpIcon = new ImageIcon(GeneralVariables.HELP_MENU_HELP_ICON_PATH);

        this.setIconImage(helpIcon.getImage());

        // this.getContentPane().setPreferredSize(new Dimension(300, 300));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        helpContent = new JPanel();
        this.add(helpContent);
        helpText = new JLabel();

        Path p = Paths.get(GeneralVariables.HELP_CONTENT_PATH);

        try {
            String helpStr = new String(Files.readAllBytes(p));

            helpText.setText(helpStr);
        } catch (Exception e) {
            helpText.setText("Erreur dans le chargement du fichier d'aide");
        }

        helpContent.add(helpText);
        pack();
        validate();
    }

    public static synchronized HelpFrame getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HelpFrame();
        }

        return INSTANCE;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
