package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class LineButtonListener implements ActionListener{

	@Override
	public void actionPerformed(final ActionEvent e) {

			Client.getInstance().setCurrentDraw(DrawModelEnum.LINE);
			MainFrame.getInstance().getToolPanel().press(DrawModelEnum.LINE);
		
	}

}
