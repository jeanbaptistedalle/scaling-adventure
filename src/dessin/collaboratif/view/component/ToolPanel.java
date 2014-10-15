package dessin.collaboratif.view.component;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.view.component.button.CircleButton;
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

	public ToolPanel() {
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

		ellipseButton = new EllipseButton();
		toolButtonGroup.add(ellipseButton);
		this.add(ellipseButton);
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
}
