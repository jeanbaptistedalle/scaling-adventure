package dessin.collaboratif.view.component;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.button.ValidateButtonListener;
import dessin.collaboratif.misc.GeneralVariables;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextInputFrame extends JFrame {

    /**
     *
     */
    private static final long     serialVersionUID = 2023568065097190397L;
    private static TextInputFrame INSTANCE         = null;
    private JPanel                textContent;
    private JLabel                textText;
    private JTextField            textField;
    private JButton               val;

    private TextInputFrame() {
        this.setTitle("Dessin colaboratif - Aide");

        final ImageIcon textIcon = new ImageIcon(GeneralVariables.HELP_MENU_TEXT_ICON_PATH);

        this.setIconImage(textIcon.getImage());
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        textContent = new JPanel();
        this.add(textContent);
        textText = new JLabel();
        textText.setText("Saisissez le texte à ajouter");
        textContent.add(textText);
        textField = new JTextField();
        this.add(textField);
        val = new JButton(GeneralVariables.TEXT_INPUT_FRAME_VALIDATE_BUTTON);
        val.addActionListener(new ValidateButtonListener());
        validate();
    }

    public static TextInputFrame getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TextInputFrame();
        }

        return INSTANCE;
    }

    public JPanel getTextContent() {
        return textContent;
    }

    public void setTextContent(JPanel textContent) {
        this.textContent = textContent;
    }

    public JLabel getTextText() {
        return textText;
    }

    public void setTextText(JLabel textText) {
        this.textText = textText;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField tf) {
        this.textField = tf;
    }

    public JButton getVal() {
        return val;
    }

    public void setVal(JButton val) {
        this.val = val;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
