package dessin.collaboratif.view.component;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JPanel;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

import dessin.collaboratif.controller.component.SvgCanvasMouseAdapter;

public class DrawPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6311776541493011834L;
	private JSVGCanvas svgCanvas = new JSVGCanvas();
	private JPanel selectionBox = new JPanel();

	public DrawPanel() {
		super(new BorderLayout());
		svgCanvas.setVisible(false);
		svgCanvas.setSize(600, 600);
		svgCanvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		SvgCanvasMouseAdapter mouseAdapter = new SvgCanvasMouseAdapter();
		svgCanvas.addMouseListener(mouseAdapter);
		svgCanvas.addMouseMotionListener(mouseAdapter);
		this.add(svgCanvas);
		/* Test Select box */
		// this.add(new OverviewBoxListener());
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

	public void click(final int x, final int y) {
		final SVGDocument doc = svgCanvas.getSVGDocument();
		final NodeList list = doc.getFirstChild().getChildNodes();
		for(int i=0; i<list.getLength(); i++){
			final Node n = list.item(i);
			
		}
		System.out.println(svgCanvas.getComponentAt(x, y));
	}

	@Override
	public void repaint() {
		super.repaint();
		if (svgCanvas != null) {
			svgCanvas.repaint();
		}
	}

	public JSVGCanvas getSvgCanvas() {
		return svgCanvas;
	}

	public void setSvgCanvas(final JSVGCanvas svgCanvas) {
		this.svgCanvas = svgCanvas;
	}

	public JPanel getSelectionBox() {
		return selectionBox;
	}

	public void setSelectionBox(JPanel selectionBox) {
		this.selectionBox = selectionBox;
	}

}
