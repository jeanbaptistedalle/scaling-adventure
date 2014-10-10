package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class SquareButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(Client.getInstance().getCurrentDraw() == DrawModelEnum.SQUARE){
			Client.getInstance().setCurrentDraw(null);
			MainFrame.getInstance().getToolPanel().press(null);
		}else{
			Client.getInstance().setCurrentDraw(DrawModelEnum.SQUARE);
			MainFrame.getInstance().getToolPanel().press(DrawModelEnum.SQUARE);
		}
	}

}
