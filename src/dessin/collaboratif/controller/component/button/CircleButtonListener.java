package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class CircleButtonListener implements ActionListener{

	@Override
	public void actionPerformed(final ActionEvent e) {
		if(Client.getInstance().getCurrentDraw() == DrawModelEnum.CIRCLE){
			Client.getInstance().setCurrentDraw(null);
			MainFrame.getInstance().getToolPanel().press(null);
		}else{
			Client.getInstance().setCurrentDraw(DrawModelEnum.CIRCLE);
			MainFrame.getInstance().getToolPanel().press(DrawModelEnum.CIRCLE);
		}
	}

}
