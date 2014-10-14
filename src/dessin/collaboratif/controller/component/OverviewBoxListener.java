package dessin.collaboratif.controller.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class OverviewBoxListener extends JPanel implements MouseMotionListener,
		MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6621279712781222550L;

	private int x;
	private int y;
	private int rule = AlphaComposite.SRC_OVER;
	private float alpha = 0.85F;

	public OverviewBoxListener() {
		super();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public void mouseDragged(MouseEvent e) {
		setOpaque(true);

		Graphics2D g2 = (Graphics2D) getGraphics();
		Rectangle2D selectionBox = new Rectangle2D.Double();

		selectionBox.setFrameFromDiagonal(e.getX(), e.getY(), x, y);
		g2.setComposite(AlphaComposite.getInstance(rule, alpha));
		g2.draw(selectionBox);
		g2.setColor(Color.BLUE);
		g2.fill(selectionBox);
		paintComponent(g2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
