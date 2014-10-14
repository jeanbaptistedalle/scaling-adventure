package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class CloseListener implements ActionListener{
	public void actionPerformed(ActionEvent arg0) {
		if(Client.getInstance().getImage() != null){
			Client.getInstance().setImage(null);
			MainFrame.getInstance().repaintDrawPanel();
		}
	}
}
