package dessin.collaboratif.view.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.dialog.MoveDialog;
import dessin.collaboratif.view.component.dialog.RenameDialog;
import dessin.collaboratif.view.component.dialog.ScaleDialog;

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
		componentList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getSource() == MainFrame.getInstance().getComponentListPanel())
				{
					System.out.println("index du component: " + componentList.getSelectedIndex());
					Client.getInstance().setSelected(componentList.getSelectedIndex());
					System.out.println("index du getSelected : " + Client.getInstance().getSelected());
				}
			} 
		});
		componentList.addMouseListener(new MouseAdapter() {
		    @SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>) evt.getSource();
		        if (evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {
		            int index = list.locationToIndex(evt.getPoint());
		            Client.getInstance().setSelected(index);
					System.out.println("Ouverture dialog");
					Client.getInstance().setMoveDial(new MoveDialog());
					Client.getInstance().setScaleDial(new ScaleDialog());
		        }
		        else if(evt.getClickCount() == 2 && SwingUtilities.isRightMouseButton(evt))
		        {
		            int index = list.locationToIndex(evt.getPoint());
		            Client.getInstance().setSelected(index);
					System.out.println("Ouverture dialog");
					Client.getInstance().setRenameDial(new RenameDialog());	
		        }
		    }
		});
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
			populateList();
			this.setVisible(true);
		} else {
			this.setVisible(false);
		}
		super.repaint();
	}
}
