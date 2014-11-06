package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class ScaleButtonListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
		String act = e.getActionCommand();
		Client.getInstance().scale(act);
		Client.getInstance().getScaleDial().unpressAll();
		MainFrame.getInstance().repaintDrawPanel();
	}

}
