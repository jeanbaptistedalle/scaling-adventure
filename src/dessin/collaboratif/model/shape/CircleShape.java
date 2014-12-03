package dessin.collaboratif.model.shape;

import dessin.collaboratif.misc.DrawModelEnum;

public class CircleShape extends Shape {

	protected int rayon;

	public int getRayon() {
		return rayon;
	}

	public void setRayon(int rayon) {
		this.rayon = rayon;
	}

        @SuppressWarnings("OverridableMethodCallInConstructor")
	public CircleShape() {
		super();
		super.setType(DrawModelEnum.CIRCLE);
		setRayon(10);
	}
	
	@Override
	public String toSVGEntity(String misc) {
		String baliseName;
		String entity;

		baliseName = super.getBaliseName();

		entity = "<";
		entity += baliseName;
		entity += " cx=\"";
		entity += getPosition().getWidth();
		entity += "\" cy=\"";
		entity += getPosition().getHeight();
		entity += "\" r=\"";
		entity += getRayon();
		entity += "\" ";
		entity += misc;
		entity += " />";

		return entity;
	}

}
