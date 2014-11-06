package dessin.collaboratif.misc;


public enum DirectionEnum {
	UP("up"),

	DOWN("down"),

	LEFT("left"),

	RIGHT("right");

	private String tagName;
	
	private DirectionEnum(final String name){
		tagName = name;
	}
	
	@Override
	public String toString() {
		return tagName;
	}
}
