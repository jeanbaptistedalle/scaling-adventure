package dessin.collaboratif.view.component;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.FrameListener;
import dessin.collaboratif.controller.component.button.ValidateLoginButtonKeyListener;
import dessin.collaboratif.controller.component.button.ValidateLoginButtonListener;
import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.view.component.button.ValidateLoginButton;
import dessin.collaboratif.view.component.field.LoginField;
import dessin.collaboratif.view.component.field.ServerField;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginFrame extends JFrame {

    /**
     *
     */
    private static final long   serialVersionUID = 6064044484594722272L;
    private static LoginFrame   INSTANCE         = null;
    private JLabel              loginLabel;
    private JPanel              loginPanel;
    private LoginField          loginField;
    private JLabel              serverTextLabel;
    private ServerField         serverField;
    private ValidateLoginButton validateLoginButton;
    private JLabel              errorLabel;

    private LoginFrame() {
        this.setTitle(GeneralVariables.LOGIN_FRAME_TITLE);
        loginPanel = new JPanel();
        this.add(loginPanel, BorderLayout.CENTER);
        loginLabel = new JLabel(GeneralVariables.LOGIN_FRAME_LOGIN_LABEL);
        loginPanel.add(loginLabel);
        loginField = new LoginField();
        loginPanel.add(loginField);
        serverTextLabel = new JLabel(GeneralVariables.LOGIN_FRAME_SERVER_ADRESS_LABEL);
        loginPanel.add(serverTextLabel);
        serverField = new ServerField();
        loginPanel.add(serverField);
        validateLoginButton = new ValidateLoginButton();
        validateLoginButton.addActionListener(new ValidateLoginButtonListener());
        loginPanel.add(validateLoginButton);
        serverField.addKeyListener(new ValidateLoginButtonKeyListener(validateLoginButton));
        validateLoginButton.addKeyListener(new ValidateLoginButtonKeyListener(validateLoginButton));
        loginField.addKeyListener(new ValidateLoginButtonKeyListener(validateLoginButton));
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setHorizontalAlignment(0);
        errorLabel.setPreferredSize(new Dimension(50, 50));
        this.add(errorLabel, BorderLayout.SOUTH);
        this.setSize(new Dimension(350, 200));
        this.setLocationRelativeTo(null);
        this.addWindowListener(new FrameListener());
        this.setVisible(true);
    }

    public static synchronized LoginFrame getInstance() {
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


//~ Formatted by Jindent --- http://www.jindent.com
