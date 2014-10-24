package dessin.collaboratif.view.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dessin.collaboratif.model.Client;

public class ComponentListPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 29596208033922048L;

	private JList componentList;

	public ComponentListPanel() {
		componentList = new JList();
		populateList();
		componentList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println("change");
			}
		});
		this.add(componentList);
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
				componentList.setListData(formList.toArray());
			} else {
				componentList.setListData(new Object[0]);
			}
		}
	}

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		populateList();
		super.repaint();
	}
}
