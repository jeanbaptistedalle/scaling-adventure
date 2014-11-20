package dessin.collaboratif.view.component.menu.item;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import dessin.collaboratif.controller.component.menu.item.ScaleListener;
import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;

public class ScaleMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 807794640134507783L;

	public ScaleMenuItem() {
		super(GeneralVariables.EDITION_MENU_SCALE);
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		ImageIcon scaleIcon = new ImageIcon(GeneralVariables.EDITION_MENU_SCALE_ICON_PATH);
		this.setIcon(scaleIcon);
		if (Client.getInstance().getImage() != null
				&& Client.getInstance().getImage().getDocumentElement().getFirstChild() != null
				&& Client.getInstance().getSelected() != -1) {
			this.setEnabled(true);
		} else {
			this.setEnabled(false);
		}
		this.addActionListener(new ScaleListener());
	}

	@Override
	public void repaint() {
		if (Client.getInstance().getImage() != null
				&& Client.getInstance().getImage().getDocumentElement().getFirstChild() != null
				&& Client.getInstance().getSelected() != -1) {
			this.setEnabled(true);
		} else {
			this.setEnabled(false);
		}
		super.repaint();
	}
}
