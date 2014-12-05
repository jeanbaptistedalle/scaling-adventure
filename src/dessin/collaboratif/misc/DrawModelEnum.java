package dessin.collaboratif.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum des formes dessinables  
 */
public enum DrawModelEnum {
	CIRCLE("circle"),

	RECTANGLE("rect"),

	LINE("line"),

	ELLIPSE("ellipse"),
	
	TEXT("text");

        @SuppressWarnings("FieldMayBeFinal")
	private String tagName;

	private DrawModelEnum(final String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	/**
	 * @return la liste des valeurs de l'enum
	 */
	public static List<DrawModelEnum> getAll() {
                @SuppressWarnings("Convert2Diamond")
		List<DrawModelEnum> list = new ArrayList<DrawModelEnum>();
		list.add(CIRCLE);
		list.add(ELLIPSE);
		list.add(LINE);
		list.add(RECTANGLE);
		list.add(TEXT);
		return list;
	}

	/**
	 * Methode retournant l'Enum correspondant à une chaine de caracteres
	 * 
	 * @param tagName la chaine de caracteres
	 * @return l'enum correspodnant, null sinon
	 */
	public static DrawModelEnum evaluate(final String tagName) {
		for (DrawModelEnum model : getAll()) {
			if (model.getTagName().equals(tagName)) {
				return model;
			}
		}
		return null;
	}
}
