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

public class SVGCanvas extends JSVGCanvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5846489199288523503L;

	public SVGCanvas() {
		setVisible(false);
		setSize(Integer.valueOf(GeneralVariables.DEFAULT_STROKE_WIDTH),
				Integer.valueOf(GeneralVariables.DEFAULT_HEIGHT));
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		SvgCanvasMouseAdapter mouseAdapter = new SvgCanvasMouseAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setDoubleBufferedRendering(true);
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
}
