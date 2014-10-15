package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.view.component.HelpFrame;

public class HelpListener implements ActionListener {
	public void actionPerformed(final ActionEvent arg0) {
		HelpFrame.getInstance().setVisible(true);
	}
}
