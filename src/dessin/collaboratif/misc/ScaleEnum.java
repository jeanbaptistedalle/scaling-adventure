package dessin.collaboratif.misc;


public enum ScaleEnum {
	
	INCREASE("+"),

	DECREASE("-");

	private String tagName;
	
	private ScaleEnum(final String name){
		tagName = name;
	}
	
	@Override
	public String toString() {
		return tagName;
	}
}
