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
import org.w3c.dom.Text;

import dessin.collaboratif.misc.GeneralVariables;
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
			svgRoot.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, GeneralVariables.DEFAULT_WIDTH);
			svgRoot.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, GeneralVariables.DEFAULT_HEIGHT);

			Text text = doc.createTextNode("Scaling Adventure by JB/K/A/R");
			
			// Create the rectangle.
			Element rectangle = doc.createElementNS(client.getSvgNameSpace(),
					"text");
			rectangle.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, "20");
			rectangle.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, "20");
			rectangle.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, "silver");
			rectangle.setAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, "12");
			rectangle.appendChild(text);
			svgRoot.appendChild(rectangle);

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
