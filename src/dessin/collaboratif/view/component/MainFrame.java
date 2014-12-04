package dessin.collaboratif.view.component;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.menu.Menu;

import reseau.client.ClientNetwork;

/**
 * 
 * The MainFrame of the application. it's implement the Singleton design pattern
 * 
 * @author JBD
 *
 */
public class MainFrame extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4328888022114581781L;

	public static MainFrame INSTANCE = null;

	private Menu menu;

	private ToolPanel toolPanel;

	private DrawPanel drawPanel;

	private ComponentListPanel componentListPanel;

	private JFrame frame;

        @SuppressWarnings("LeakingThisInConstructor")
	private MainFrame() {

		setDoubleBuffered(true);
		frame = new JFrame();
		frame.setTitle("Dessin colaboratif - Login : " + Client.getInstance().getLogin()
				+ ", Server : " + Client.getInstance().getServerAdress());
		frame.setSize(600, 680);
		frame.setLocationRelativeTo(null);
		
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        ClientNetwork.getInstance().disconnect();
                        System.exit(0);
                    }
                });
                
		menu = new Menu();
		frame.setJMenuBar(menu);
		menu.setVisible(true);

		frame.add(this);

		toolPanel = new ToolPanel();
		frame.add(toolPanel, BorderLayout.NORTH);

		drawPanel = new DrawPanel(600, 600);
		drawPanel.setBackground(Color.LIGHT_GRAY);
		frame.add(drawPanel, BorderLayout.CENTER);

		componentListPanel = new ComponentListPanel();
		frame.add(componentListPanel, BorderLayout.EAST);

		/* Test plein écran */
		// final Window[] windows = JFrame.getWindows();
		// final GraphicsDevice[] graphicsDevices =
		// GraphicsEnvironment.getLocalGraphicsEnvironment()
		// .getScreenDevices();
		// for (final GraphicsDevice graphicsDevise : graphicsDevices) {
		// for (final Window window : windows) {
		// graphicsDevise.setFullScreenWindow(window);
		// }
		// }

		this.setVisible(true);
		frame.setVisible(true);
	}

	/**
	 * Repaint the drawPanel and all the components link to it (like close and
	 * undo menu items)
	 */
	public void repaintDrawPanel() {
		if (Client.getInstance().getImage() != null) {
			drawPanel.getSvgCanvas().setImage(Client.getInstance().getImage());
		} else {
			drawPanel.getSvgCanvas().setImage(null);
		}
		repaintMenu();
		componentListPanel.repaint();
		drawPanel.repaint();
                
                /* Envoi du SVG au serveur */
                ClientNetwork.getInstance().submitPicture(Client.getInstance().imageToString());
	}
	
	/**
	 * Repaint the component of the menu which can have stat (disabled, enabled)
	 */
	public void repaintMenu(){
		menu.getFileMenu().getClose().repaint();
		menu.getFileMenu().getExport().repaint();
		menu.getEditionMenu().getUndo().repaint();
		menu.getEditionMenu().getDelete().repaint();
		menu.getEditionMenu().getMove().repaint();
		menu.getEditionMenu().getRename().repaint();
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

	public ComponentListPanel getComponentListPanel() {
		return componentListPanel;
	}

	public void setComponentListPanel(ComponentListPanel componentListPanel) {
		this.componentListPanel = componentListPanel;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}