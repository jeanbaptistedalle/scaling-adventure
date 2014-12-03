package dessin.collaboratif.misc;


public enum ScaleEnum {
	
	INCREASE("+"),

	DECREASE("-");

        @SuppressWarnings("FieldMayBeFinal")
	private String tagName;
	
	private ScaleEnum(final String name){
		tagName = name;
	}
	
	@Override
	public String toString() {
		return tagName;
	}
}
