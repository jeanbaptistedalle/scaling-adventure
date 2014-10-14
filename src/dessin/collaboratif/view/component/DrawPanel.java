package dessin.collaboratif.view.component;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JPanel;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.Document;
import org.w3c.dom.svg.SVGDocument;

import dessin.collaboratif.controller.component.SvgCanvasMouseAdapter;

public class DrawPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6311776541493011834L;
	private JSVGCanvas svgCanvas = new JSVGCanvas();

	public DrawPanel() {
		super(new BorderLayout());
		svgCanvas.setVisible(false);
		svgCanvas.setSize(600, 600);
		svgCanvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		SvgCanvasMouseAdapter mouseAdapter = new SvgCanvasMouseAdapter();
		svgCanvas.addMouseListener(mouseAdapter);
		svgCanvas.addMouseMotionListener(mouseAdapter);
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

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
		if (svgCanvas != null) {
			svgCanvas.repaint();
		}
	}
}
