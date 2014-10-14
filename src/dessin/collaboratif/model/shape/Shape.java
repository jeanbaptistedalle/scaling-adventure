package dessin.collaboratif.model.shape;

import java.awt.Color;
import java.awt.Dimension;

import dessin.collaboratif.misc.DrawModelEnum;

public abstract class Shape {

	protected Dimension position;
	protected Color color;
	protected DrawModelEnum type;

	public DrawModelEnum getType() {
		return type;
	}

	public void setType(DrawModelEnum type) {
		this.type = type;
	}

	public Dimension getPosition() {
		return position;
	}

	public void setPosition(Dimension position) {
		this.position = position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Shape() {
		setColor(Color.BLACK);
		setPosition(new Dimension(0, 0));
	}

	public String toSVGEntity(String misc) {
		String baliseName;
		String entity;

		baliseName = getBaliseName();

		entity = "<";
		entity += baliseName;
		entity += " x=\"";
		entity += getPosition().getWidth();
		entity += "\" y=\"";
		entity += getPosition().getHeight();
		entity += "\" ";
		entity += misc;
		entity += " />";

		return entity;
	}

	protected String getBaliseName() {
		String baliseName;
		switch (this.getType()) {

		case CIRCLE:
			baliseName = "circ";
			break;

		case SQUARE:
			baliseName = "rect";
			break;

		case LINE:
			baliseName = "line";
			break;

		default:
			throw new RuntimeException("Forme non gérée");
		}
		return baliseName;
	}

	public String toSVGEntity() {
		return toSVGEntity("");
	}

}
