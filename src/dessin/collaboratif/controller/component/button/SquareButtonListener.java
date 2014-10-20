package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class SquareButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		Client.getInstance().setCurrentDraw(DrawModelEnum.SQUARE);
		MainFrame.getInstance().getToolPanel().press(DrawModelEnum.SQUARE);

	}

}
