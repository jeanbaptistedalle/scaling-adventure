package dessin.collaboratif.controller.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.LoginFrame;
import dessin.collaboratif.view.component.MainFrame;

public class ValidateLoginButtonListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent arg0) {
		final String loginText = LoginFrame.getInstance().getLoginField().getText();
		final String serverText = LoginFrame.getInstance().getServerField().getText();
		if (loginText == null || loginText.length() <= 0) {
			LoginFrame.getInstance().getErrorLabel()
					.setText(GeneralVariables.LOGIN_ERROR_MISSING_LOGIN);
			return;
		}
		if (serverText == null || serverText.length() <= 0) {
			LoginFrame.getInstance().getErrorLabel()
					.setText(GeneralVariables.LOGIN_ERROR_MISSING_SERVER);
			return;
		}
		// TODO vérifier validité adresse serveur
		// if () {
		// LoginFrame.getInstance().getErrorLabel()
		// .setText(GeneralVariables.LOGIN_ERROR_INVALID_SERVER);
		// return;
		// }

		// TODO vérifier unicité login
		// if () {
		// LoginFrame.getInstance().getErrorLabel()
		// .setText(GeneralVariables.LOGIN_ERROR_INVALID_LOGIN);
		// return;
		// }
		Client.getInstance().setLogin(loginText);
		Client.getInstance().setServerAdress(serverText);
		LoginFrame.getInstance().setVisible(false);
		MainFrame.getInstance();
	}

}
