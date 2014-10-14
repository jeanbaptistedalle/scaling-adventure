package dessin.collaboratif.view.component;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JPanel;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.Document;
import org.w3c.dom.svg.SVGDocument;

public class DrawPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6311776541493011834L;
	private JSVGCanvas svgCanvas = new JSVGCanvas();

	public DrawPanel() {
		super(new BorderLayout());
		svgCanvas.setVisible(false);
		this.setSize(600, 600);
		svgCanvas.setSize(600, 600);
		svgCanvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		this.add(svgCanvas);
	}

	public DrawPanel(final int width, final int height) {
		this();
		this.setSize(width, height);
	}

	public void setImage(Document image) {
		if (image != null) {
			svgCanvas.setDocument(image);
			svgCanvas.setSVGDocument((SVGDocument) image);
			svgCanvas.setVisible(true);
		} else {
			svgCanvas.setSVGDocument(null);
			svgCanvas.setVisible(false);
		}
	}
}
