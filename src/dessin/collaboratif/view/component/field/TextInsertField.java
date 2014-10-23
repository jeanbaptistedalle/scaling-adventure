package dessin.collaboratif.view.component.field;

import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import dessin.collaboratif.controller.component.field.TextButtonListener;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

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
			public void changedUpdate(DocumentEvent e) {
				modification();
			}

			public void removeUpdate(DocumentEvent e) {
				modification();
			}

			public void insertUpdate(DocumentEvent e) {
				modification();
			}

			public void modification() {
				Client.getInstance().setTextToInsert(getText());
			}
		});

	}

}
