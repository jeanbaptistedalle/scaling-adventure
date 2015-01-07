package dessin.collaboratif.controller.component.button;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener du bouton Ellipse
 *
 * Cette classe permet de passer en mode dessin d'ellipses
 */
public class EllipseButtonListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Client.getInstance().setCurrentDraw(DrawModelEnum.ELLIPSE);
        MainFrame.getInstance().getToolPanel().press(DrawModelEnum.ELLIPSE);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
