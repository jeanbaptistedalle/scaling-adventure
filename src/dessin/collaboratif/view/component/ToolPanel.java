package dessin.collaboratif.view.component;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.view.component.button.CircleButton;
import dessin.collaboratif.view.component.button.ColorPickerButton;
import dessin.collaboratif.view.component.button.CursorButton;
import dessin.collaboratif.view.component.button.EllipseButton;
import dessin.collaboratif.view.component.button.LineButton;
import dessin.collaboratif.view.component.button.SquareButton;
import dessin.collaboratif.view.component.button.TextButton;
import dessin.collaboratif.view.component.field.TextInsertField;
import dessin.collaboratif.view.component.field.TextSizeField;

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

	private TextButton textButton;

	private CursorButton cursorButton;

	private ColorPickerButton colorPickerButton;

	private TextInsertField textInsertField;

	private TextSizeField textSizeField;

	public ToolPanel() {
		super(new GridLayout());
		toolButtonGroup = new ButtonGroup();

		cursorButton = new CursorButton();
		this.add(cursorButton);
		toolButtonGroup.add(cursorButton);
		cursorButton.getModel().setPressed(true);

		colorPickerButton = new ColorPickerButton();
		toolButtonGroup.add(colorPickerButton);
		this.add(colorPickerButton);

		lineButton = new LineButton();
		toolButtonGroup.add(lineButton);
		this.add(lineButton);

		squareButton = new SquareButton();
		toolButtonGroup.add(squareButton);
		this.add(squareButton);

		circleButton = new CircleButton();
		toolButtonGroup.add(circleButton);
		this.add(circleButton);

		ellipseButton = new EllipseButton();
		toolButtonGroup.add(ellipseButton);
		this.add(ellipseButton);

		textButton = new TextButton();
		toolButtonGroup.add(textButton);
		this.add(textButton);

		textInsertField = new TextInsertField();
		this.add(textInsertField);

		textSizeField = new TextSizeField();
		this.add(textSizeField);
	}

	public TextSizeField getTextSizeField() {
		return textSizeField;
	}

	public void setTextSizeField(TextSizeField textSizeField) {
		this.textSizeField = textSizeField;
	}

	public void press(final DrawModelEnum drawModelEnum) {
//		unpressAll();
//		if (drawModelEnum == null) {
//			cursorButton.getModel().setPressed(true);
//		} else {
//			switch (drawModelEnum) {
//			case SQUARE:
//				squareButton.getModel().setPressed(true);
//				break;
//			case CIRCLE:
//				circleButton.getModel().setPressed(true);
//				break;
//			case LINE:
//				lineButton.getModel().setPressed(true);
//				break;
//			case ELLIPSE:
//				ellipseButton.getModel().setPressed(true);
//				break;
//			case TEXT:
//				textButton.getModel().setPressed(true);
//				break;
//			default:
//				throw new RuntimeException("Type de bouton non géré");
//			}
//		}
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
		case TEXT:
			if (textButton.getModel().isPressed()) {
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
		ellipseButton.getModel().setPressed(false);
		textButton.getModel().setPressed(false);
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

	public EllipseButton getEllipseButton() {
		return ellipseButton;
	}

	public void setEllipseButton(EllipseButton ellipseButton) {
		this.ellipseButton = ellipseButton;
	}

	public TextButton getTextButton() {
		return textButton;
	}

	public void setTextButton(TextButton textButton) {
		this.textButton = textButton;
	}

	public TextInsertField getTextInsertField() {
		return textInsertField;
	}

	public void setTextInsertField(TextInsertField textInsertField) {
		this.textInsertField = textInsertField;
	}
}