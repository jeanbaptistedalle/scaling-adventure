package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class MoveButtonListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
		String act = e.getActionCommand();
		Client.getInstance().move(act);
		Client.getInstance().getMoveDial().unpressAll();
		MainFrame.getInstance().repaintDrawPanel();
	}

}
