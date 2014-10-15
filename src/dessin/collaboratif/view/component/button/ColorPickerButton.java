package dessin.collaboratif.view.component.button;

import javax.swing.JButton;

import dessin.collaboratif.controller.component.button.ColorPickerButtonListener;

public class ColorPickerButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -718703034383032397L;

	public ColorPickerButton(){
		super("ColorPicker");
		this.addActionListener(new ColorPickerButtonListener());
	}
}
