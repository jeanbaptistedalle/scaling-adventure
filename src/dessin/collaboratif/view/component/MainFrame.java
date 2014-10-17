package dessin.collaboratif.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
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
public class MainFrame extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4328888022114581781L;

	public static MainFrame INSTANCE = null;

	private BufferStrategy bufferStrategy;

	private Menu menu;

	private ToolPanel toolPanel;

	private DrawPanel drawPanel;

	private JFrame frame;

	private MainFrame() {
		frame = new JFrame();
		frame.setTitle("Dessin colaboratif");
		frame.setSize(600, 680);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menu = new Menu();
		frame.setJMenuBar(menu);
		menu.setVisible(true);

		frame.add(this);

		toolPanel = new ToolPanel();
		frame.add(toolPanel, BorderLayout.NORTH);

		drawPanel = new DrawPanel(600, 600);
		drawPanel.setBackground(Color.LIGHT_GRAY);
		this.add(drawPanel, BorderLayout.CENTER);
		this.setVisible(true);
		
		frame.setVisible(true);

//		setIgnoreRepaint(true);
//		frame.createBufferStrategy(2);
//		bufferStrategy = frame.getBufferStrategy();
//
//		new Renderer().start();
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
		menu.getFileMenu().getClose().repaint();
		menu.getFileMenu().getExport().repaint();
		menu.getEditionMenu().getUndo().repaint();
		drawPanel.repaint();
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

	private class Renderer extends Thread {
		private BufferedImage image;

		public Renderer() {
			// NOTE: image size is fixed now, but better to bind image size to
			// the size of viewport
			image = new BufferedImage(600, 680, BufferedImage.TYPE_INT_ARGB);
		}

		public void run() {
			while (true) {
				Graphics g = null;
				try {
					g = bufferStrategy.getDrawGraphics();
					drawSprites(g);
				} finally {
					g.dispose();
				}
				bufferStrategy.show();
				Toolkit.getDefaultToolkit().sync();

				try {
					Thread.sleep(1000 / 24);
				} catch (InterruptedException ie) {
				}
			}
		}

		private void drawSprites(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;

			Graphics graph = image.createGraphics();
			graph.drawImage(frame.createImage(getWidth(), getHeight()), 0, 0, null);
			g2d.drawImage(image, 0, 0, null);
			graph.dispose();
		}
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

}