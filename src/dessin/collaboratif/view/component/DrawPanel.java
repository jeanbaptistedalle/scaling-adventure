package dessin.collaboratif.view.component;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

import dessin.collaboratif.controller.component.SvgCanvasMouseAdapter;
import dessin.collaboratif.misc.DrawModelEnum;

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
		for (int i = 0; i < list.getLength(); i++) {
			final Node n = list.item(i);
			final DrawModelEnum model = DrawModelEnum.evaluate(n.getNodeName());
			Double cx, rx, x1, x2, cy, ry, y1, y2, r, width, height;
			if (model != null) {
				switch (model) {
				case CIRCLE:
					cx = Double.parseDouble(n.getAttributes().getNamedItem("cx").getNodeValue());
					cy = Double.parseDouble(n.getAttributes().getNamedItem("cy").getNodeValue());
					r = Double.parseDouble(n.getAttributes().getNamedItem("r").getNodeValue());
					Ellipse2D.Double circle = new Ellipse2D.Double(cx - r, cy - r, r * 2, r * 2);
					if (circle.contains(new Point2D.Double((double) x, (double) y))) {
						// Le point est contenu dans l'ellipse
						System.out.println("Le click correspond à un cercle");
					}
					break;
				case LINE:
					x1 = Double.parseDouble(n.getAttributes().getNamedItem("x1").getNodeValue());
					y1 = Double.parseDouble(n.getAttributes().getNamedItem("y1").getNodeValue());
					x2 = Double.parseDouble(n.getAttributes().getNamedItem("x2").getNodeValue());
					y2 = Double.parseDouble(n.getAttributes().getNamedItem("y2").getNodeValue());
					Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
					//FIXME non selectionnable a cause du stroke non pris en compte
					if (line.contains(new Point2D.Double((double) x, (double) y))) {
						// Le point est contenu dans la ligne
						System.out.println("Le click correspond à une ligne");
					}
					break;
				case SQUARE:
					x1 = Double.parseDouble(n.getAttributes().getNamedItem("x").getNodeValue());
					y1 = Double.parseDouble(n.getAttributes().getNamedItem("y").getNodeValue());
					width = Double.parseDouble(n.getAttributes().getNamedItem("width")
							.getNodeValue());
					height = Double.parseDouble(n.getAttributes().getNamedItem("height")
							.getNodeValue());
					Rectangle2D.Double rect = new Rectangle2D.Double(x1, y1, width, height);
					if (rect.contains(new Point2D.Double((double) x, (double) y))) {
						// Le point est contenu dans le rectangle
						System.out.println("Le click correspond à un rectangle");
					}
					break;
				case ELLIPSE:
					cx = Double.parseDouble(n.getAttributes().getNamedItem("cx").getNodeValue());
					cy = Double.parseDouble(n.getAttributes().getNamedItem("cy").getNodeValue());
					rx = Double.parseDouble(n.getAttributes().getNamedItem("rx").getNodeValue());
					ry = Double.parseDouble(n.getAttributes().getNamedItem("ry").getNodeValue());
					Ellipse2D.Double ellipse = new Ellipse2D.Double(cx - rx, cy - ry, rx * 2,
							ry * 2);
					if (ellipse.contains(new Point2D.Double((double) x, (double) y))) {
						// Le point est contenu dans l'ellipse
						System.out.println("Le click correspond à une ellipse");
					}
					break;
				default:
					break;
				}
			}
		}
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
