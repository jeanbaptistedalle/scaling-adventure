package dessin.collaboratif.controller.component;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class DrawPanelMouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (Client.getInstance().getCurrentDraw() != null) {
			MainFrame.getInstance().getDrawPanel()
					.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		MainFrame.getInstance().getDrawPanel()
				.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO
	}

}
