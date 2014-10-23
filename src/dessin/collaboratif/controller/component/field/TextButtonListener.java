package dessin.collaboratif.controller.component.field;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class TextButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		Client.getInstance().setCurrentDraw(DrawModelEnum.TEXT);
		MainFrame.getInstance().getToolPanel().press(DrawModelEnum.TEXT);
	}

}
