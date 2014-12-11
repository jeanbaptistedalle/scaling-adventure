package dessin.collaboratif.controller.component.button;

//~--- non-JDK imports --------------------------------------------------------

import java.awt.event.ActionListener;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;
//~--- JDK imports ------------------------------------------------------------
import java.awt.event.ActionEvent;

/**
 * Listener du bouton Circle
 *
 * Cette classe permet de passer en mode dessin de cercles
 */
public class CircleButtonListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param e
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        Client.getInstance().setCurrentDraw(DrawModelEnum.CIRCLE);
        MainFrame.getInstance().getToolPanel().press(DrawModelEnum.CIRCLE);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
