package dessin.collaboratif.model.shape;

import dessin.collaboratif.misc.DrawModelEnum;

/**
 * Représentation d'un cercle
 */
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

	/**
	 * Renvoie l'entité SVG du cercle avec ses paramètres et eventuellement un
	 * ou des paramètre supplémentaires
	 * @param misc les paramètres supplémentaires
	 */
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
