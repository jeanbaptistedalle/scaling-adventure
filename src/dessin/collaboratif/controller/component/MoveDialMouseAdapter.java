package dessin.collaboratif.controller.component;

//~--- non-JDK imports --------------------------------------------------------

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JToggleButton;

import dessin.collaboratif.misc.ScaleEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;
//~--- JDK imports ------------------------------------------------------------
import java.awt.event.MouseEvent;

/**
 * Adapteur des boutons de la fenetre de deplacement/redimensionnement
 */
public class MoveDialMouseAdapter implements MouseListener, MouseMotionListener {
    private boolean mouseDown = false;
    private String  dir       = null;
    private boolean isRunning = false;

    /**
     * Permet de répéter les actions en restant appuyé sur le bouton
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
        dir       = ((JToggleButton) e.getSource()).getActionCommand();
        System.out.println("##" + dir);

        try {
            Thread.sleep(100);
            initThread();
        } catch (InterruptedException ex) {
            Logger.getLogger(MoveDialMouseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }

    private synchronized boolean checkAndMark() {
        if (isRunning) {
            return false;
        }

        isRunning = true;

        return true;
    }

    /**
     * Thread permettant la répétition de l'action
     */
    private void initThread() {
        if (checkAndMark()) {
            new Thread() {
                @Override
                @SuppressWarnings("SleepWhileInLoop")
                public void run() {
                    do {
                        if (!dir.equals(ScaleEnum.INCREASE.toString()) &&!dir.equals(ScaleEnum.DECREASE.toString())) {
                            Client.getInstance().move(dir);
                        } else {
                            Client.getInstance().scale(dir);
                        }

                        System.out.println("Pressed");
                        MainFrame.getInstance().repaintDrawPanel();

                        try {
                            sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MoveDialMouseAdapter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } while (mouseDown);

                    isRunning = false;
                }
            }.start();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent arg0) {}

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}
}


//~ Formatted by Jindent --- http://www.jindent.com
