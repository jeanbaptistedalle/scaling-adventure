package dessin.collaboratif.misc;

/**
 * Enum des directions possibles d'un déplacement
 */
public enum DirectionEnum {
	UP("up"),

	DOWN("down"),

	LEFT("left"),

	RIGHT("right");

        @SuppressWarnings("FieldMayBeFinal")
	private String tagName;
	
	private DirectionEnum(final String name){
		tagName = name;
	}
	
	@Override
	public String toString() {
		return tagName;
	}
}
