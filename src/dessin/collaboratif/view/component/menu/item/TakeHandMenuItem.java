package dessin.collaboratif.view.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import dessin.collaboratif.misc.GeneralVariables;

public class TakeHandMenuItem extends JMenuItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7460592700478889054L;
	
	public TakeHandMenuItem(){
		super(GeneralVariables.COLLABORATION_MENU_TAKE_HAND);
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
		        InputEvent.CTRL_MASK));
		this.setMnemonic(KeyEvent.getExtendedKeyCodeForChar('p'));
		ImageIcon takeHandIcon = new ImageIcon(
				GeneralVariables.COLLABORATION_MENU_TAKE_HAND_ICON_PATH);
		this.setIcon(takeHandIcon);
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
	}
}
