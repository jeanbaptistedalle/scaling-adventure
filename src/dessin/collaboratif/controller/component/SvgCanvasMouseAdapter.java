package dessin.collaboratif.controller.component;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;
import dessin.collaboratif.view.component.SVGCanvas;
import dessin.collaboratif.view.component.dialog.MoveDialog;
import dessin.collaboratif.view.component.dialog.RenameDialog;

import org.apache.batik.ext.awt.geom.Polygon2D;
import org.apache.batik.gvt.TextNode;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

import reseau.client.ClientNetwork;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.SwingUtilities;

/**
 * Adapter du tableau
 *
 * Permet la selection/le dessin d'elements en cliquant sur leur image sur le
 * tableau de dessin
 */

/**
 * @author JBD
 *
 */
public class SvgCanvasMouseAdapter implements MouseListener, MouseMotionListener {
    private Integer x1     = null;
    private Integer y1     = null;
    private Integer x2     = null;
    private Integer y2     = null;
    private Boolean resize = false;
    private long    timeDB;

    /**
     * Récupère la position du curseur au début du clic
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        x1     = e.getX();
        y1     = e.getY();
        x2     = null;
        y2     = null;
        timeDB = System.nanoTime();
    }

    /**
     * A chaque mouvement de la souris, on redessine la derniere forme dessinée
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();

        if ((x1 != null) && (x2 != null) && (x2 >= 0) && (y1 != null) && (y2 != null) && (y2 >= 0)) {
            if (!resize) {
                if (Client.getInstance().draw(x1, y1, x2, y2, resize)) {
                    MainFrame.getInstance().repaintDrawPanel();
                    resize = true;
                }
            } else if (((System.nanoTime() - timeDB) / 1000000) > 25) {
                if (Client.getInstance().draw(x1, y1, x2, y2, resize)) {
                    MainFrame.getInstance().repaintDrawPanel();
                    timeDB = System.nanoTime();
                }
            }
        }
    }

    /**
     * Lorsqu'on lache la souris, on finalise le dessin de la forme
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (ClientNetwork.getInstance().haveControl()) {
            x2 = e.getX();
            y2 = e.getY();

            if ((x1 != null) && (x2 != null) && (y1 != null) && (y2 != null)) {
                if (Client.getInstance().draw(x1, y1, x2, y2, resize)) {
                    MainFrame.getInstance().repaintDrawPanel();
                }
            }

            x1     = null;
            y1     = null;
            x2     = null;
            y2     = null;
            resize = false;

            /* Envoi du SVG au serveur */
            ClientNetwork.getInstance().submitPicture(Client.getInstance().imageToString());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        click(e);
    }

    /**
     * This method is use in order to determinate which model has been click. If
     * a model is found, the selected model is change. If the model was
     * double-clicked, this method launch the move and rescale dialog. If the
     * model was right-clicked and if it's a text, this method launch the rename
     * dialog.
     *
     *
     * @param e
     */
    public void click(MouseEvent e) {
        final SVGCanvas svgCanvas = MainFrame.getInstance().getDrawPanel().getSvgCanvas();
        final int       x         = e.getX();
        final int       y         = e.getY();
        final int       nbClick   = e.getClickCount();

        /* Si le click est en dehors du canvas, il n'est pas géré */
        if ((x < 0) || (y < 0)) {
            return;
        }

        final SVGDocument doc         = svgCanvas.getSVGDocument();
        final NodeList    list        = doc.getFirstChild().getChildNodes();
        Integer           foundIndice = -1;
        boolean           isText      = false;

        /*
         * On verifie pour chaque forme du dessin si le click se trouve dans
         * celui-ci
         */
        for (int i = 0; i < list.getLength(); i++) {
            final Node          n     = list.item(i);
            final DrawModelEnum model = DrawModelEnum.evaluate(n.getNodeName());

            if (model != null) {
                switch (model) {
                case CIRCLE :
                    if (clickOnCircle(x, y, n)) {
                        foundIndice = i;
                        isText      = false;
                    }

                    break;

                case LINE :
                    if (clickOnLine(x, y, n)) {
                        foundIndice = i;
                        isText      = false;
                    }

                    break;

                case RECTANGLE :
                    if (clickOnRectangle(x, y, n)) {
                        foundIndice = i;
                        isText      = false;
                    }

                    break;

                case ELLIPSE :
                    if (clickOnEllipse(x, y, n)) {
                        foundIndice = i;
                        isText      = false;
                    }

                    break;

                case TEXT :
                    if (clickOnText(x, y, n)) {
                        foundIndice = i;
                        isText      = true;
                    }

                    break;

                default :
                    break;
                }
            }
        }

        /*
         * Si le click ne correspond à rien, la selection actuelle est
         * supprimée. Sinon, on met à jour la selection actuelle.
         *
         * Dans le cas où la cible aurait été double cliqué ou cliqué droit, on
         * lance le dialog qui convient.
         */
        if (foundIndice == -1) {
            MainFrame.getInstance().getComponentListPanel().getComponentList().clearSelection();
        } else {
            MainFrame.getInstance().getComponentListPanel().getComponentList().setSelectedIndex(foundIndice);
        }

        Client.getInstance().setSelected(foundIndice);
        MainFrame.getInstance().repaintDrawPanel();
        System.out.println("Indice trouvé : " + foundIndice);

        if (SwingUtilities.isLeftMouseButton(e)) {
            if ((nbClick == 2) && (foundIndice != -1)) {
                System.out.println("Ouverture dialog");
                MoveDialog.getInstance().setVisible(true);
            }
        } else if (SwingUtilities.isRightMouseButton(e) && (nbClick == 1) && isText) {
            RenameDialog.getInstance().setVisible(true);
        }
    }

    /**
     *
     * This method test if the click is in the node. The node HAS to be a circle
     *
     * @param x
     * @param y
     * @param n
     * @return
     */
    public boolean clickOnCircle(final int x, final int y, final Node n) {
        final double           cx     = Double.parseDouble(n.getAttributes().getNamedItem("cx").getNodeValue());
        final double           cy     = Double.parseDouble(n.getAttributes().getNamedItem("cy").getNodeValue());
        final double           r      = Double.parseDouble(n.getAttributes().getNamedItem("r").getNodeValue());
        final Ellipse2D.Double circle = new Ellipse2D.Double(cx - r, cy - r, r * 2, r * 2);

        if (circle.contains(new Point2D.Double((double) x, (double) y))) {
            System.out.println("Le click correspond à un cercle");

            return true;
        }

        return false;
    }

    /**
     * This method test if the click is in the node. The node HAS to be a line
     *
     * @param x
     * @param y
     * @param n
     * @return
     */
    public boolean clickOnLine(final int x, final int y, final Node n) {

        /*
         * Il n'est pas possible d'être contenu dans une ligne car c'est le
         * parametre "stroke" qui donne a la ligne sa couleur. Pour detecter
         * qu'un point est contenu dans une ligne, on utilise donc un polygone
         * ayant la taille de la propriété stroke
         */
        final double    x1          = Double.parseDouble(n.getAttributes().getNamedItem("x1").getNodeValue());
        final double    y1          = Double.parseDouble(n.getAttributes().getNamedItem("y1").getNodeValue());
        final double    x2          = Double.parseDouble(n.getAttributes().getNamedItem("x2").getNodeValue());
        final double    y2          = Double.parseDouble(n.getAttributes().getNamedItem("y2").getNodeValue());
        final Polygon2D p           = new Polygon2D();
        final Integer   strokeWidth = Integer.parseInt(GeneralVariables.DEFAULT_STROKE_WIDTH) / 2;

        p.addPoint(new Point2D.Double(x1 + strokeWidth, y1 + strokeWidth));
        p.addPoint(new Point2D.Double(x1 - strokeWidth, y1 - strokeWidth));
        p.addPoint(new Point2D.Double(x2 - strokeWidth, y2 - strokeWidth));
        p.addPoint(new Point2D.Double(x2 + strokeWidth, y2 + strokeWidth));

        if (p.contains(new Point2D.Double((double) x, (double) y))) {
            System.out.println("Le click correspond à une ligne");

            return true;
        }

        return false;
    }

    /**
     * This method test if the click is in the node. The node HAS to be a
     * rectangle
     *
     * @param x
     * @param y
     * @param n
     * @return
     */
    public boolean clickOnRectangle(final int x, final int y, final Node n) {
        final double             x1     = Double.parseDouble(n.getAttributes().getNamedItem("x").getNodeValue());
        final double             y1     = Double.parseDouble(n.getAttributes().getNamedItem("y").getNodeValue());
        final double             width  = Double.parseDouble(n.getAttributes().getNamedItem("width").getNodeValue());
        final double             height = Double.parseDouble(n.getAttributes().getNamedItem("height").getNodeValue());
        final Rectangle2D.Double rect   = new Rectangle2D.Double(x1, y1, width, height);

        if (rect.contains(new Point2D.Double((double) x, (double) y))) {
            System.out.println("Le click correspond à un rectangle");

            return true;
        }

        return false;
    }

    /**
     * This method test if the click is in the node. The node HAS to be an
     * ellipse
     *
     * @param x
     * @param y
     * @param n
     * @return
     */
    public boolean clickOnEllipse(final int x, final int y, final Node n) {
        final double           cx      = Double.parseDouble(n.getAttributes().getNamedItem("cx").getNodeValue());
        final double           cy      = Double.parseDouble(n.getAttributes().getNamedItem("cy").getNodeValue());
        final double           rx      = Double.parseDouble(n.getAttributes().getNamedItem("rx").getNodeValue());
        final double           ry      = Double.parseDouble(n.getAttributes().getNamedItem("ry").getNodeValue());
        final Ellipse2D.Double ellipse = new Ellipse2D.Double(cx - rx, cy - ry, rx * 2, ry * 2);

        if (ellipse.contains(new Point2D.Double((double) x, (double) y))) {
            System.out.println("Le click correspond à une ellipse");

            return true;
        }

        return false;
    }

    /**
     * This method test if the click is in the node. The node HAS to be a text
     *
     * @param x
     * @param y
     * @param n
     * @return
     */
    public boolean clickOnText(final int x, final int y, final Node n) {
        final TextNode node;

        if (n instanceof TextNode) {
            node = (TextNode) n;
        } else {
            return false;
        }

        if (node.contains(new Point2D.Double((double) x, (double) y))) {
            System.out.println("Le click correspond à du texte");

            return true;
        }

        return false;
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}
}


//~ Formatted by Jindent --- http://www.jindent.com
