package dessin.collaboratif.controller.component;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

public class ComponentListPanelListSelectionListener implements
		ListSelectionListener {
	
	protected JList<String> componentList = null;

	public ComponentListPanelListSelectionListener(JList<String> componentList) {
		super();
		this.componentList = componentList;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (Client.getInstance().getSelected() != componentList.getSelectedIndex()) {
			Client.getInstance().setSelected(componentList.getSelectedIndex());
			MainFrame.getInstance().repaintMenu();
		}
	}

}
