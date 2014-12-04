package dessin.collaboratif.controller.component;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.SwingUtilities;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.dialog.MoveDialog;
import dessin.collaboratif.view.component.dialog.RenameDialog;

public class ComponentListPanelMouseAdapter implements MouseListener {

	@SuppressWarnings("unchecked")
        @Override
	public void mouseClicked(MouseEvent evt) {
		JList<String> list = (JList<String>) evt.getSource();
		if (evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {
			int index = list.locationToIndex(evt.getPoint());
			Client.getInstance().setSelected(index);
			System.out.println("Ouverture dialog");
			MoveDialog.getInstance().setVisible(true);
		} else if (evt.getClickCount() == 2 && SwingUtilities.isRightMouseButton(evt) && 
				list.getModel().getElementAt(list.locationToIndex(evt.getPoint())).startsWith(DrawModelEnum.TEXT.getTagName()) ) {
			int index = list.locationToIndex(evt.getPoint());
			Client.getInstance().setSelected(index);
			System.out.println("Ouverture dialog");
			RenameDialog.getInstance().setVisible(true);
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
