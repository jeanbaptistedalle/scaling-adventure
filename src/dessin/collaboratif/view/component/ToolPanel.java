package dessin.collaboratif.view.component;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.button.CircleButton;
import dessin.collaboratif.view.component.button.ColorPickerButton;
import dessin.collaboratif.view.component.button.CursorButton;
import dessin.collaboratif.view.component.button.EllipseButton;
import dessin.collaboratif.view.component.button.LineButton;
import dessin.collaboratif.view.component.button.SquareButton;

public class ToolPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7961403117980955928L;

	private ButtonGroup toolButtonGroup;

	private SquareButton squareButton;

	private CircleButton circleButton;

	private EllipseButton ellipseButton;

	private LineButton lineButton;

	private CursorButton cursorButton;

	private ColorPickerButton colorPickerButton;

	private JPanel selectedColor;

	public ToolPanel() {
		super(new GridLayout());
		toolButtonGroup = new ButtonGroup();

		cursorButton = new CursorButton();
		this.add(cursorButton);
		toolButtonGroup.add(cursorButton);
		cursorButton.getModel().setPressed(true);

		lineButton = new LineButton();
		toolButtonGroup.add(lineButton);
		this.add(lineButton);

		squareButton = new SquareButton();
		toolButtonGroup.add(squareButton);
		this.add(squareButton);

		circleButton = new CircleButton();
		toolButtonGroup.add(circleButton);
		this.add(circleButton);

		colorPickerButton = new ColorPickerButton();
		toolButtonGroup.add(colorPickerButton);
		this.add(colorPickerButton);
		
		ellipseButton = new EllipseButton();
		toolButtonGroup.add(ellipseButton);
		this.add(ellipseButton);

		selectedColor = new JPanel();
		selectedColor.setBackground(Client.getInstance().getSelectedColor());
		this.add(selectedColor);
	}

	public void press(final DrawModelEnum drawModelEnum) {
		unpressAll();
		if (drawModelEnum == null) {
			cursorButton.getModel().setPressed(true);
		} else {
			switch (drawModelEnum) {
			case SQUARE:
				squareButton.getModel().setPressed(true);
				break;
			case CIRCLE:
				circleButton.getModel().setPressed(true);
				break;
			case LINE:
				lineButton.getModel().setPressed(true);
				break;
			case ELLIPSE:
				ellipseButton.getModel().setPressed(true);
				break;
			default:
				throw new RuntimeException("Type de bouton non géré");
			}
		}
	}

	public boolean isPress(final DrawModelEnum drawModelEnum) {
		switch (drawModelEnum) {
		case SQUARE:
			if (squareButton.getModel().isPressed()) {
				return true;
			}
			return false;
		case CIRCLE:
			if (circleButton.getModel().isPressed()) {
				return true;
			}
			return false;
		case LINE:
			if (lineButton.getModel().isPressed()) {
				return true;
			}
			return false;
		case ELLIPSE:
			if (ellipseButton.getModel().isPressed()) {
				return true;
			}
			return false;
		default:
			break;
		}
		return false;
	}

	public void unpressAll() {
		squareButton.getModel().setPressed(false);
		circleButton.getModel().setPressed(false);
		lineButton.getModel().setPressed(false);
		cursorButton.getModel().setPressed(false);
	}

	public SquareButton getSquareButton() {
		return squareButton;
	}

	public void setSquareButton(SquareButton squareButton) {
		this.squareButton = squareButton;
	}

	public ButtonGroup getToolButtonGroup() {
		return toolButtonGroup;
	}

	public void setToolButtonGroup(ButtonGroup toolButtonGroup) {
		this.toolButtonGroup = toolButtonGroup;
	}

	public CircleButton getCircleButton() {
		return circleButton;
	}

	public void setCircleButton(CircleButton circleButton) {
		this.circleButton = circleButton;
	}

	public LineButton getLineButton() {
		return lineButton;
	}

	public void setLineButton(LineButton lineButton) {
		this.lineButton = lineButton;
	}

	public CursorButton getCursorButton() {
		return cursorButton;
	}

	public void setCursorButton(CursorButton cursorButton) {
		this.cursorButton = cursorButton;
	}

	public ColorPickerButton getColorPickerButton() {
		return colorPickerButton;
	}

	public void setColorPickerButton(ColorPickerButton colorPickerButton) {
		this.colorPickerButton = colorPickerButton;
	}

	public JPanel getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(JPanel selectedColor) {
		this.selectedColor = selectedColor;
	}
}