package dessin.collaboratif.view.component;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.SwingUtilities;

import org.apache.batik.ext.awt.geom.Polygon2D;
import org.apache.batik.gvt.TextNode;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

import dessin.collaboratif.controller.component.SvgCanvasMouseAdapter;
import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.dialog.MoveDialog;
import dessin.collaboratif.view.component.dialog.RenameDialog;
import dessin.collaboratif.view.component.dialog.ScaleDialog;

public class SVGCanvas extends JSVGCanvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5846489199288523503L;

	private static long lastClickTime = 0;

	// private OverviewBoxListener overviewBox = new OverviewBoxListener();

	public SVGCanvas() {
		setVisible(false);
		// setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
		setSize(Integer.valueOf(GeneralVariables.DEFAULT_STROKE_WIDTH),
				Integer.valueOf(GeneralVariables.DEFAULT_HEIGHT));
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		SvgCanvasMouseAdapter mouseAdapter = new SvgCanvasMouseAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setDoubleBufferedRendering(true);
		// addMouseListener(overviewBox);
		// addMouseMotionListener(overviewBox);
	}

	@Override
	public void repaint() {
		// Graphics2D g2 = (Graphics2D) getGraphics();
		// if (overviewBox != null && g2 != null) {
		// Rectangle2D selectionBox = overviewBox.getSelectionBox();
		// if (selectionBox != null) {
		// g2.setComposite(AlphaComposite.getInstance(overviewBox.getRule(),
		// overviewBox.getAlpha()));
		// g2.draw(selectionBox);
		// paintComponent(g2);
		// }
		// }

		super.repaint();
	}

	public void setImage(Document image) {
		if (image != null) {
			setSVGDocument((SVGDocument) image);
			setVisible(true);
		} else {
			setSVGDocument(null);
			setVisible(false);
		}
	}

	public void click(MouseEvent event) {

		final int x = event.getX();
		final int y = event.getY();
		final int button = event.getButton();
		final int nbClick = event.getClickCount();

		final SVGDocument doc = getSVGDocument();
		final NodeList list = doc.getFirstChild().getChildNodes();
		Integer foundIndice = -1;
		boolean isText = false;
		isText = false;
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
						foundIndice = i;
						isText = false;
					}
					break;
				case LINE:
					/*
					 * Il n'est pas possible d'être contenu dans une ligne car
					 * c'est le parametre "stroke" qui donne la la ligne sa
					 * couleur. Pour detecter qu'un point est contenu dans une
					 * ligne, on crée donc un polygone ayant la taille de la
					 * propriété stroke
					 */
					x1 = Double.parseDouble(n.getAttributes().getNamedItem("x1").getNodeValue());
					y1 = Double.parseDouble(n.getAttributes().getNamedItem("y1").getNodeValue());
					x2 = Double.parseDouble(n.getAttributes().getNamedItem("x2").getNodeValue());
					y2 = Double.parseDouble(n.getAttributes().getNamedItem("y2").getNodeValue());
					Polygon2D p = new Polygon2D();
					final Integer strokeWidth = Integer
							.parseInt(GeneralVariables.DEFAULT_STROKE_WIDTH) / 2;
					p.addPoint(new Point2D.Double(x1 + strokeWidth, y1 + strokeWidth));
					p.addPoint(new Point2D.Double(x1 - strokeWidth, y1 - strokeWidth));
					p.addPoint(new Point2D.Double(x2 - strokeWidth, y2 - strokeWidth));
					p.addPoint(new Point2D.Double(x2 + strokeWidth, y2 + strokeWidth));
					if (p.contains(new Point2D.Double((double) x, (double) y))) {
						// Le point est contenu dans la ligne
						System.out.println("Le click correspond à une ligne");
						foundIndice = i;
						isText = false;
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
						foundIndice = i;
						isText = false;
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
						foundIndice = i;
						isText = false;
					}
					break;
				case TEXT:

					try {

						TextNode node = (TextNode) getCanvasGraphicsNode().getChildren().get(i);

						if (node.contains(new Point2D.Double((double) x, (double) y))) {
							System.out.println("Le click correspond à du texte");
							foundIndice = i;
							isText = true;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					break;
				default:
					break;
				}
			}
		}
		if (foundIndice == -1) {
			MainFrame.getInstance().getComponentListPanel().getComponentList().clearSelection();
		} else {
			MainFrame.getInstance().getComponentListPanel().getComponentList()
					.setSelectedIndex(foundIndice);
		}
		Client.getInstance().setSelected(foundIndice);
		MainFrame.getInstance().repaintDrawPanel();
		System.out.println("Indice trouvé : " + foundIndice);

		if (SwingUtilities.isLeftMouseButton(event)) {
			if (nbClick == 2 && foundIndice != -1) {
				System.out.println("Ouverture dialog");
				Client.getInstance().setMoveDial(new MoveDialog());
			} else if (nbClick == 3 && foundIndice != -1) {
				System.out.println("Ouverture dialog");
				Client.getInstance().setScaleDial(new ScaleDialog());
			}
		} else if (SwingUtilities.isRightMouseButton(event) && nbClick == 2 && isText) {
			Client.getInstance().setRenameDial(new RenameDialog());
		}
		lastClickTime = System.nanoTime();
	}
}
