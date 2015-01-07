package dessin.collaboratif.controller.component.button;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.view.component.button.ValidateLoginButton;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ValidateLoginButtonKeyListener implements KeyListener {
    private ValidateLoginButton button;

    public ValidateLoginButtonKeyListener(ValidateLoginButton from) {
        button = from;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {

        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
            button.doClick();
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

        // TODO Auto-generated method stub
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
