package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class NewListener implements ActionListener {

	private JFileChooser fileChooser;

	public NewListener(final JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public void actionPerformed(final ActionEvent action) {
		// Allow user to choose a name for the new file
		final Client client = Client.getInstance();

		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(
					"svg")) {
				file = new File(file.getParentFile(),
						FilenameUtils.getBaseName(file.getName()) + ".svg");
			}
			try {
				file.createNewFile();
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}

			Document doc = client.getDomImpl().createDocument(
					client.getSvgNameSpace(), "svg", null);

			// Get the root element (the 'svg' element).
			Element svgRoot = doc.getDocumentElement();

			// Set the width and height attributes on the root 'svg' element.
			svgRoot.setAttributeNS(null, "width", "600");
			svgRoot.setAttributeNS(null, "height", "600");
			svgRoot.setAttributeNS(null, "fill", "white");

			// Save the file
			final DOMSource source = new DOMSource(doc);
			final StreamResult result = new StreamResult(file);
			try {
				client.getTransformer().transform(source, result);
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
			MainFrame.getInstance().repaintDrawPanel();
			client.setImage(doc);
		}
	}
}
