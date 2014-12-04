package dessin.collaboratif.controller.component;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import dessin.collaboratif.view.component.MainFrame;
import dessin.collaboratif.view.component.SVGCanvas;

public class OverviewBoxListener implements MouseMotionListener, MouseListener {

	private Integer x;
	private Integer y;
	private int rule = AlphaComposite.SRC_OVER;
	private float alpha = 0.85F;
	private Rectangle2D selectionBox;

	public OverviewBoxListener() {
		super();
	}

        @Override
	public void mouseDragged(MouseEvent e) {
		if (x == null || y == null) {
			x = e.getX();
			y = e.getY();
		}
		SVGCanvas svgCanvas = MainFrame.getInstance().getDrawPanel().getSvgCanvas();
		selectionBox = new Rectangle2D.Double();
		Graphics2D g2 = (Graphics2D) svgCanvas.getGraphics();
		selectionBox.setFrameFromDiagonal(x, y, e.getX(), e.getY());
		g2.setComposite(AlphaComposite.getInstance(rule, alpha));
		g2.draw(selectionBox);
		svgCanvas.paintComponent(g2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (x == null || y == null) {
			x = e.getX();
			y = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		MainFrame.getInstance().getDrawPanel().getSvgCanvas().repaint();
		selectionBox = null;
		x = null;
		y = null;
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public Rectangle2D getSelectionBox() {
		return selectionBox;
	}

	public void setSelectionBox(Rectangle2D selectionBox) {
		this.selectionBox = selectionBox;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

}
