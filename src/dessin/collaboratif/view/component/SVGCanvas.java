package dessin.collaboratif.view.component;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.SvgCanvasMouseAdapter;
import dessin.collaboratif.misc.GeneralVariables;

import org.apache.batik.swing.JSVGCanvas;

import org.w3c.dom.Document;
import org.w3c.dom.svg.SVGDocument;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Cursor;

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


//~ Formatted by Jindent --- http://www.jindent.com
