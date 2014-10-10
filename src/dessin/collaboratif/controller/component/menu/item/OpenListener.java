package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class OpenListener implements ActionListener {

	private JFileChooser fileChooser;

	public OpenListener(final JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public void actionPerformed(final ActionEvent e) {
		// TODO
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Client.getInstance()
					.setImageToModify(fileChooser.getSelectedFile());
			MainFrame.getInstance().repaintDrawPanel();
		}
	}
}
