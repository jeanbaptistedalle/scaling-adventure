package dessin.collaboratif.controller.component;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Listener de la liste d'elements
 *
 * Permet de sélectionner la forme dans le svg à partir du listing sur le coté de la fenetre principale
 */
public class ComponentListPanelListSelectionListener implements ListSelectionListener {
    protected JList<String> componentList = null;

    public ComponentListPanelListSelectionListener(JList<String> componentList) {
        super();
        this.componentList = componentList;
    }

    /**
     * Sélectionne l'element choisi dans la liste
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (Client.getInstance().getSelected() != componentList.getSelectedIndex()) {
            Client.getInstance().setSelected(componentList.getSelectedIndex());
            MainFrame.getInstance().repaintMenu();
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
