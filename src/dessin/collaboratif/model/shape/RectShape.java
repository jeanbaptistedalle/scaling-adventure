package dessin.collaboratif.model.shape;

import java.awt.Dimension;

public class RectShape extends Shape {

	protected Dimension size;

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public RectShape() {
		super();
		setSize(new Dimension(10, 10));
	}

	@Override
	public String toSVGEntity(String misc) {

		String tmp = misc;

		misc = "width=\"";
		misc += getSize().getWidth();

		misc += "\" height=\"";
		misc += getSize().getHeight();
		
		misc += "\" ";
		misc += tmp;
		
		return super.toSVGEntity(misc);
	}

}
