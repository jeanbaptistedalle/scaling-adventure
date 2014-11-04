package dessin.collaboratif.controller.component;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class SvgCanvasMouseAdapter implements MouseListener,
		MouseMotionListener {
	private Integer x1 = null;
	private Integer y1 = null;
	private Integer x2 = null;
	private Integer y2 = null;

	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		x2 = null;
		y2 = null;
	}

	public void mouseDragged(MouseEvent e) {
//		x2 = e.getX();
//		y2 = e.getY();
//		if (x1 != null && x2 != null && y1 != null && y2 != null) {
//			if (Client.getInstance().draw(x1, y1, x2, y2)) {
//				MainFrame.getInstance().repaintDrawPanel(false);
//			}
//		}
	}

	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		if (x1 != null && x2 != null && y1 != null && y2 != null) {
			if (Client.getInstance().draw(x1, y1, x2, y2)) {
				MainFrame.getInstance().repaintDrawPanel();
			}
		}
		x1 = null;
		y1 = null;
		x2 = null;
		y2 = null;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		MainFrame.getInstance().getDrawPanel().getSvgCanvas().click(arg0.getX(), arg0.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}
}
