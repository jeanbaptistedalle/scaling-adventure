package dessin.collaboratif.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dessin.collaboratif.controller.component.button.ValidateLoginButtonListener;
import dessin.collaboratif.view.component.button.ValidateLoginButton;
import dessin.collaboratif.view.component.field.LoginField;
import dessin.collaboratif.view.component.field.ServerField;
import reseau.client.ClientNetwork;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6064044484594722272L;

	private static LoginFrame INSTANCE = null;
	private JLabel loginLabel;
	private JPanel loginPanel;
	private LoginField loginField;
	private JLabel serverTextLabel;
	private ServerField serverField;
	private ValidateLoginButton validateLoginButton;
	private JLabel errorLabel;

	private LoginFrame() {
		loginPanel = new JPanel();
		this.add(loginPanel, BorderLayout.CENTER);

		loginLabel = new JLabel("Login : ");
		loginPanel.add(loginLabel);

		loginField = new LoginField();
		loginPanel.add(loginField);

		serverTextLabel = new JLabel("Adresse du serveur : ");
		loginPanel.add(serverTextLabel);

		serverField = new ServerField();
		loginPanel.add(serverField);

		validateLoginButton = new ValidateLoginButton();
		validateLoginButton.addActionListener(new ValidateLoginButtonListener());
		loginPanel.add(validateLoginButton);

		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.red);
		loginPanel.add(errorLabel);

		this.setSize(new Dimension(350, 200));
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        ClientNetwork.getInstance().disconnect();
                        System.exit(0);
                    }
                });
		this.setVisible(true);
	}

	public static LoginFrame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LoginFrame();
		}
		return INSTANCE;
	}

	public LoginField getLoginField() {
		return loginField;
	}

	public void setLoginField(LoginField loginField) {
		this.loginField = loginField;
	}

	public ValidateLoginButton getValidateLoginButton() {
		return validateLoginButton;
	}

	public void setValidateLoginButton(ValidateLoginButton validateLoginButton) {
		this.validateLoginButton = validateLoginButton;
	}

	public JLabel getLoginLabel() {
		return loginLabel;
	}

	public void setLoginLabel(JLabel loginLabel) {
		this.loginLabel = loginLabel;
	}

	public JPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(JPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	public JLabel getServerTextLabel() {
		return serverTextLabel;
	}

	public void setServerTextLabel(JLabel serverTextLabel) {
		this.serverTextLabel = serverTextLabel;
	}

	public ServerField getServerField() {
		return serverField;
	}

	public void setServerField(ServerField serverField) {
		this.serverField = serverField;
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

	public void setErrorLabel(JLabel errorLabel) {
		this.errorLabel = errorLabel;
	}

}
