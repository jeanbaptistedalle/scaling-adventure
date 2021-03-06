package dessin.collaboratif.controller.component.button;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.LoginFrame;
import dessin.collaboratif.view.component.MainFrame;

import reseau.client.ClientNetwork;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Listener du bouton Validate
 *
 * Cette classe permet de valider la saisie des données de LoginFrame
 *
 * @see LoginFrame
 */
public class ValidateLoginButtonListener implements ActionListener {

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param arg0
     */
    @Override
    public void actionPerformed(final ActionEvent arg0) {
        final String loginText  = LoginFrame.getInstance().getLoginField().getText();
        final String serverText = LoginFrame.getInstance().getServerField().getText();

        if ((loginText == null) || (loginText.length() <= 0)) {
            LoginFrame.getInstance().getErrorLabel().setText(GeneralVariables.LOGIN_ERROR_MISSING_LOGIN);

            return;
        }

        if ((serverText == null) || (serverText.length() <= 0)) {
            LoginFrame.getInstance().getErrorLabel().setText(GeneralVariables.LOGIN_ERROR_MISSING_SERVER);

            return;
        }

        if (!ClientNetwork.getInstance().initIp(serverText)) {
            LoginFrame.getInstance().getErrorLabel().setText(GeneralVariables.LOGIN_ERROR_INVALID_SERVER);

            return;
        }

        try {
            if (!ClientNetwork.getInstance().initPseudo(loginText)) {
                LoginFrame.getInstance().getErrorLabel().setText(GeneralVariables.LOGIN_ERROR_INVALID_LOGIN);

                return;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ValidateLoginButtonListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        ClientNetwork.getInstance().start();
        Client.getInstance().setLogin(loginText);
        Client.getInstance().setServerAdress(serverText);
        LoginFrame.getInstance().setVisible(false);
        MainFrame.getInstance();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
