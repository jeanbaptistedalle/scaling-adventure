package dessin.collaboratif.view.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dessin.collaboratif.controller.component.DrawPanelMouseListener;

public class DrawPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6311776541493011834L;
	private BufferedImage bufferedImage;

	public DrawPanel() {
		super();
		this.addMouseListener(new DrawPanelMouseListener());
	}

	public DrawPanel(final int width, final int height) {
		this();
		this.setSize(width, height);
	}

	public DrawPanel(final File image) {
		this();
		try {
			bufferedImage = ImageIO.read(image);
		} catch (final IOException e) {
			// TODO gerer erreur
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		if (bufferedImage != null) {
			this.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
			g.drawImage(bufferedImage, 0, 0, null);
		}
	}

	public void setImage(final File image) {
		if (image == null) {
			bufferedImage = null;
		} else {
			try {
				bufferedImage = ImageIO.read(image);
			} catch (final Exception e) {
				// TODO
				throw new RuntimeException(e);
			}
		}
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

}
