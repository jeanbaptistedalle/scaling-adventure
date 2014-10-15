package dessin.collaboratif.view.component;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dessin.collaboratif.misc.GeneralVariables;

public class HelpFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2023568065097190397L;

	private static HelpFrame INSTANCE = null;

	private JPanel helpContent;
	private JLabel helpText;

	private HelpFrame() {
		this.setTitle("Dessin colaboratif - Aide");
		final ImageIcon helpIcon = new ImageIcon(
				GeneralVariables.HELP_MENU_HELP_ICON_PATH);
		this.setIconImage(helpIcon.getImage());
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		helpContent = new JPanel();
		this.add(helpContent);

		helpText = new JLabel();
		helpText.setText("En construction ...");
		helpContent.add(helpText);

		validate();
	}

	public static HelpFrame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HelpFrame();
		}
		return INSTANCE;
	}
}
