package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import org.apache.commons.io.FilenameUtils;

import dessin.collaboratif.model.Client;

public class ExportListener implements ActionListener {

	private JFileChooser fileChooser;

	public ExportListener(final JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(
					"jpg") && !FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(
							"jepg")) {
				file = new File(file.getParentFile(),
						FilenameUtils.getBaseName(file.getName()) + ".jpeg");
			}
			Client.getInstance().exportToJPG(file);
		}
	}

}
