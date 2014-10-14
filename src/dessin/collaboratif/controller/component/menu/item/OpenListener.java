package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class OpenListener implements ActionListener {

	private JFileChooser fileChooser;

	public OpenListener(final JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public void actionPerformed(final ActionEvent e) {
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			final Client client = Client.getInstance();
			final File file = fileChooser.getSelectedFile();
			try {
				String parser = XMLResourceDescriptor.getXMLParserClassName();
				SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(
						parser);
				Document doc = factory.createDocument(file.toURI().toString());
				client.setImage(doc);
				client.setFileImage(file);
			} catch (final Exception e1) {
				throw new RuntimeException(e1);
			}
			MainFrame.getInstance().repaintDrawPanel();
		}
	}
}