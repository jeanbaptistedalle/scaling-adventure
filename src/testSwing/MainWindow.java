package testSwing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6748325253307146260L;

	private JPanel content;
	private JMenuBar menuBar;

	private JMenu fichier;
	private JMenuItem quitter;

	private JMenu edition;
	private JMenuItem annuler;

	public MainWindow() {
		this.setTitle("Un super test de Swing");
		this.setVisible(true);
		this.setSize(600, 600);
		// Pour centrer, sinon relatif à un autre élément
		this.setLocationRelativeTo(null);
		content = new JPanel();
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		fichier = new JMenu("Fichier");
		menuBar.add(fichier);
		quitter = new JMenuItem("Quitter");
		// On ajoute un listener sur le bouton quitter qui va quitter le
		// programme.
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		fichier.add(quitter);
		edition = new JMenu("Edition");
		menuBar.add(edition);
		annuler = new JMenuItem("Annuler");
		edition.add(annuler);
	}
}
