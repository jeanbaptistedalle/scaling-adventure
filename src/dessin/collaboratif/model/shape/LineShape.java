package dessin.collaboratif.model.shape;

import java.awt.Dimension;

public class LineShape extends Shape {

	protected Dimension endPosition;
	
	public Dimension getEndPosition() {
		return endPosition;
	}



	public void setEndPosition(Dimension endPosition) {
		this.endPosition = endPosition;
	}



	public LineShape() {
		super();
		setEndPosition(new Dimension(10,10));
	}
	
	@Override
	public String toSVGEntity(String misc) {
		String baliseName;
		String entity;

		baliseName = super.getBaliseName();

		entity  = "<";
		entity += baliseName;
		entity += " x1=\"";
		entity += getPosition().getWidth();
		entity += "\" y1=\"";
		entity += getPosition().getHeight();
		entity += "\" x2=\"";
		entity += getEndPosition().getWidth();
		entity += "\" y2=\"";
		entity += getEndPosition().getHeight();
		entity += "\" ";
		entity += misc;
		entity += " />";

		return entity;
	}

}
