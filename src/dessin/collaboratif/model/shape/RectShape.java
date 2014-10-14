package dessin.collaboratif.model.shape;

import java.awt.Dimension;

public class RectShape extends Shape {

	protected Dimension size;
	
	public RectShape() {
		super();
		setSize(new Dimension(10, 10));
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
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
