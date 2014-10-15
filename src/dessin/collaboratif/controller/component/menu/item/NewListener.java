package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.util.SVGConstants;
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
			Document doc = client.getDomImpl().createDocument(
					client.getSvgNameSpace(), "svg", null);

			// Get the root element (the 'svg' element).
			Element svgRoot = doc.getDocumentElement();

			// Set the width and height attributes on the root 'svg' element.
			svgRoot.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, "600");
			svgRoot.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, "600");

			// Create the rectangle.
			Element rectangle = doc.createElementNS(client.getSvgNameSpace(),
					"rect");
			rectangle.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, "10");
			rectangle.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, "20");
			rectangle.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, "100");
			rectangle.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, "50");
			rectangle.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, "red");
			svgRoot.appendChild(rectangle);

			Element rectangle2 = doc.createElementNS(client.getSvgNameSpace(),
					"rect");
			rectangle2.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, "110");
			rectangle2.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, "70");
			rectangle2.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, "100");
			rectangle2.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, "50");
			rectangle2.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, "blue");
			svgRoot.appendChild(rectangle2);

			// Save the file
			final DOMSource source = new DOMSource(doc);
			final StreamResult result = new StreamResult(file.getPath());
			try {
				client.getTransformer().transform(source, result);
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
			client.setImage(doc);
			client.setFileImage(file);
			MainFrame.getInstance().repaintDrawPanel();
		}
	}
}
