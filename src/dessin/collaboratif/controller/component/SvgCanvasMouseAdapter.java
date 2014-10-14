package dessin.collaboratif.controller.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dessin.collaboratif.model.Client;

public class SvgCanvasMouseAdapter extends MouseAdapter {
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
	}

	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		if (x1 != null && x2 != null && y1 != null && y2 != null) {
			Client.getInstance().draw(x1, y1, x2, y2);
		}
		x1 = null;
		y1 = null;
		x2 = null;
		y2 = null;
	}
}
