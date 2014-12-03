package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import org.apache.commons.io.FilenameUtils;

import dessin.collaboratif.model.Client;

/**
 * Listener de l'item d'export
 * 
 * Enregistre l'image sous format JPEG sur le client 
 */
public class ExportListener implements ActionListener {

	private JFileChooser fileChooser;

	public ExportListener(final JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}


	/**
	 * Méthode lancée à la détection d'un clic sur le bouton
	 * 
	 * @param e 
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {

		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(
					"jpg") && !FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(
							"jpeg")) {
				file = new File(file.getParentFile(),
						FilenameUtils.getBaseName(file.getName()) + ".jpeg");
			}
			Client.getInstance().exportToJPG(file);
		}
	}

}
