package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.view.component.TextInputFrame;

public class ValidateButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		TextInputFrame.getInstance().getTextField().getText();
	}

}
