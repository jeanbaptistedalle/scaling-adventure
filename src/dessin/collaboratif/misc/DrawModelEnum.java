package dessin.collaboratif.misc;

import java.util.ArrayList;
import java.util.List;

public enum DrawModelEnum {
	CIRCLE("circle"),

	SQUARE("rect"),

	LINE("line"),

	ELLIPSE("ellipse"),
	
	TEXT("text");

	private String tagName;

	private DrawModelEnum(final String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	public static List<DrawModelEnum> getAll() {
		List<DrawModelEnum> list = new ArrayList<DrawModelEnum>();
		list.add(CIRCLE);
		list.add(ELLIPSE);
		list.add(LINE);
		list.add(SQUARE);
		return list;
	}

	public static DrawModelEnum evaluate(final String tagName) {
		for (DrawModelEnum model : getAll()) {
			if (model.getTagName().equals(tagName)) {
				return model;
			}
		}
		return null;
	}
}
