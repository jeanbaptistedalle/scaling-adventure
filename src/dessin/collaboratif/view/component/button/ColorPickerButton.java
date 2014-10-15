package dessin.collaboratif.view.component.button;

import javax.swing.JButton;

import dessin.collaboratif.controller.component.button.ColorPickerButtonListener;
import dessin.collaboratif.model.Client;

public class ColorPickerButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -718703034383032397L;

	public ColorPickerButton(){
		super();
		this.setBackground(Client.getInstance().getSelectedColor());
		this.addActionListener(new ColorPickerButtonListener());
	}
}
