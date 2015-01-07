package dessin.collaboratif.view.component.field;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.model.Client;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class TextInsertField extends JTextField {

    /**
     *
     */
    private static final long serialVersionUID = -23958304697115492L;

    public TextInsertField() {
        this.setText("");
        this.setToolTipText("Texte à insérer");

        Document doc = getDocument();

        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                modification();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                modification();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                modification();
            }
            public void modification() {
                Client.getInstance().setTextToInsert(getText());
            }
        });
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
