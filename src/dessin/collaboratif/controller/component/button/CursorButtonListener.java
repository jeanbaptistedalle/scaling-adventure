package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class CursorButtonListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
		Client.getInstance().setCurrentDraw(null);
		MainFrame.getInstance().getToolPanel().press(null);
	}
}