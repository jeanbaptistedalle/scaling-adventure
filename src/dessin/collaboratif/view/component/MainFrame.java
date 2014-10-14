package dessin.collaboratif.view.component;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.menu.Menu;

/**
 * 
 * The MainFrame of the application. it's implement the Singleton design pattern
 * 
 * @author JBD
 *
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4328888022114581781L;

	public static MainFrame INSTANCE = null;

	private Menu menu;

	private ToolPanel toolPanel;

	private DrawPanel drawPanel;

	private MainFrame() {
		this.setTitle("Dessin colaboratif");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		menu = new Menu();
		this.setJMenuBar(menu);
		menu.setVisible(true);

		toolPanel = new ToolPanel();
		this.add(toolPanel, BorderLayout.NORTH);

		drawPanel = new DrawPanel(600, 600);
		drawPanel.setBackground(Color.LIGHT_GRAY);
		this.add(drawPanel, BorderLayout.CENTER);
		validate();
	}

	public void repaintDrawPanel() {
		if (Client.getInstance().getImage() != null) {
			drawPanel.setImage(Client.getInstance().getImage());
		} else {
			drawPanel.setImage(null);
		}
		menu.getFileMenu().getClose().repaint();
		drawPanel.repaint();
		repaint();
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public ToolPanel getToolPanel() {
		return toolPanel;
	}

	public void setToolPanel(ToolPanel toolPanel) {
		this.toolPanel = toolPanel;
	}

	public DrawPanel getDrawPanel() {
		return drawPanel;
	}

	public void setDrawPanel(DrawPanel drawPanel) {
		this.drawPanel = drawPanel;
	}

	/**
	 * Get instance of the MainFrame (or create one if not exists)
	 * 
	 * @return
	 */
	public static MainFrame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MainFrame();
		}
		return INSTANCE;
	}
}