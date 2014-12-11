package dessin.collaboratif.controller.component.button;

//~--- non-JDK imports --------------------------------------------------------

import java.awt.event.ActionListener;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;
//~--- JDK imports ------------------------------------------------------------
import java.awt.event.ActionEvent;

public class CursorButtonListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param e
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        Client.getInstance().setCurrentDraw(null);
        MainFrame.getInstance().getToolPanel().press(null);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
