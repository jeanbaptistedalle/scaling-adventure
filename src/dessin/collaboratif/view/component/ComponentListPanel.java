package dessin.collaboratif.view.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dessin.collaboratif.controller.component.ComponentListPanelListSelectionListener;
import dessin.collaboratif.controller.component.ComponentListPanelMouseAdapter;
import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;

public class ComponentListPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 29596208033922048L;

	/* Only work with java 7 jdk */
	private JList<String> componentList;
	private JScrollPane scrollPane;

	public ComponentListPanel() {
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(GeneralVariables.DEFAULT_LIST_DIMENSION);
		componentList = new JList<String>();
		scrollPane.setViewportView(componentList);
		this.add(scrollPane);
		populateList();
		componentList.addListSelectionListener(new ComponentListPanelListSelectionListener(componentList));
		componentList.addMouseListener(new ComponentListPanelMouseAdapter());
	}

	private void populateList() {
		if (componentList != null) {
			final Document doc = Client.getInstance().getImage();
			if (doc != null) {
				final List<String> formList = new ArrayList<String>();
				if (doc != null) {
					final Node root = doc.getFirstChild();
					final NodeList nodeList = root.getChildNodes();
					for (int i = 0; i < nodeList.getLength(); i++) {
						formList.add(nodeList.item(i).getNodeName());
					}
				}
				String[] array = new String[formList.size()];
				array = formList.toArray(array);
				componentList.setListData(array);
			} else {
				componentList.setListData(new String[0]);
			}

		}
	}

	public JList<String> getComponentList() {
		return componentList;
	}

	public void setComponentList(JList<String> componentList) {
		this.componentList = componentList;
	}

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		if (Client.getInstance().getImage() != null) {
			int index = componentList.getSelectedIndex();
			populateList();
			componentList.setSelectedIndex(index);
			this.setVisible(true);
		} else {
			if (componentList != null) {
				componentList.setListData(new String[0]);
			}
			this.setVisible(false);
		}
		super.repaint();
	}
}
