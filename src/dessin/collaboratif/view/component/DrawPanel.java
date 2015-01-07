package dessin.collaboratif.view.component;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -6311776541493011834L;
    private SVGCanvas         svgCanvas        = new SVGCanvas();
    private JPanel            selectionBox     = new JPanel();

    public DrawPanel() {
        super(new BorderLayout());
        this.add(svgCanvas);
        setIgnoreRepaint(true);
        setDoubleBuffered(true);
    }

    public DrawPanel(final int width, final int height) {
        this();
        this.setSize(width, height);
    }

    @Override
    public void repaint() {

        // super.repaint();
        if (svgCanvas != null) {
            svgCanvas.repaint();
        }
    }

    public JPanel getSelectionBox() {
        return selectionBox;
    }

    public void setSelectionBox(JPanel selectionBox) {
        this.selectionBox = selectionBox;
    }

    public SVGCanvas getSvgCanvas() {
        return svgCanvas;
    }

    public void setSvgCanvas(SVGCanvas svgCanvas) {
        this.svgCanvas = svgCanvas;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
