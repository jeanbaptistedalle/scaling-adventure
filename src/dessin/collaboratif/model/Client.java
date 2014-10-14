package dessin.collaboratif.model;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dessin.collaboratif.misc.DrawModelEnum;

public class Client {

	private static Client INSTANCE = null;

	private DOMImplementation domImpl;
	private String svgNameSpace;
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private TransformerFactory transformerFactory;
	private Transformer transformer;

	private Document image;
	private File fileImage;

	private DrawModelEnum currentDraw = null;

	private Client() {
		try {
			domImpl = SVGDOMImplementation.getDOMImplementation();
			svgNameSpace = SVGDOMImplementation.SVG_NAMESPACE_URI;
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method return true if there is a currentDraw selected and if the
	 * points are legal, false elsewhere
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	private boolean isLegal(final Integer x1, final Integer y1,
			final Integer x2, final Integer y2) {
		return x1 != null && x1 >= 0 && y1 != null && y1 >= 0 && x2 != null
				&& x2 >= 0 && y2 != null && y2 >= 0;
	}

	/**
	 * Draw the selected form on the .svg if the dimension are legal.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void draw(final Integer x1, final Integer y1, final Integer x2,
			final Integer y2) {
		if (isLegal(x1, x2, y1, y2)) {
			if (currentDraw != null) {
				switch (currentDraw) {
				case CIRCLE:
					// TODO
					break;
				case LINE:
					// TODO
					break;
				case SQUARE:
					drawSquare(x1, y1, x2, y2);
				default:
					break;
				}
			} else {
				/*
				 * TODO si aucune forme à dessiner n'est selectionnée, on doit
				 * pouvoir déplacer une forme
				 */
			}
		}
	}

	/**
	 * This method draw a square on the selected .svg
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void drawSquare(final Integer x1, final Integer y1,
			final Integer x2, final Integer y2) {
		/*
		 * On réorganise les valeurs afin que le point d'origine soit toujours
		 * le plus petit afin que les largeurs et hauteurs soient positives.
		 */
		Integer nx1;
		Integer ny1;
		Integer nx2;
		Integer ny2;
		if (x1 < x2) {
			nx1 = x1;
			nx2 = x2;
		} else {
			nx2 = x1;
			nx1 = x2;
		}
		if (y1 < y2) {
			ny1 = y1;
			ny2 = y2;
		} else {
			ny2 = y1;
			ny1 = y2;
		}

		final Document doc = getImage();
		final Element svgRoot = doc.getDocumentElement();
		Element rectangle = doc.createElementNS(Client.getInstance()
				.getSvgNameSpace(), "rect");
		rectangle.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE,
				nx1.toString());
		rectangle.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE,
				ny1.toString());
		Integer height = ny2 - ny1;
		Integer width = nx2 - nx1;
		rectangle.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE,
				height.toString());
		rectangle.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE,
				width.toString());
		rectangle
				.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, "black");
		svgRoot.appendChild(rectangle);
		saveSVG();
	}

	/**
	 * This method undo the last form of the selected .svg
	 */
	public void undo() {
		final Document doc = getImage();
		if (doc != null && doc.getFirstChild().getFirstChild() != null) {
			doc.getFirstChild().removeChild(
					doc.getDocumentElement().getLastChild());
			saveSVG();
		}
	}

	/**
	 * This method save the .svg currently selected by the client
	 */
	public void saveSVG() {
		final Document doc = getImage();
		final File file = getFileImage();
		if (doc != null && file != null) {
			final DOMSource source = new DOMSource(doc);
			final StreamResult result = new StreamResult(file.getPath());
			try {
				getTransformer().transform(source, result);
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Return the current instance of the Client if exist, or create a new one
	 * if there isn't
	 * 
	 * @return
	 */
	public static Client getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Client();
		}
		return INSTANCE;
	}

	public DrawModelEnum getCurrentDraw() {
		return currentDraw;
	}

	public void setCurrentDraw(DrawModelEnum currentDraw) {
		this.currentDraw = currentDraw;
	}

	public Document getImage() {
		return image;
	}

	public void setImage(Document image) {
		this.image = image;
	}

	public DOMImplementation getDomImpl() {
		return domImpl;
	}

	public void setDomImpl(DOMImplementation domImpl) {
		this.domImpl = domImpl;
	}

	public String getSvgNameSpace() {
		return svgNameSpace;
	}

	public void setSvgNameSpace(String svgNameSpace) {
		this.svgNameSpace = svgNameSpace;
	}

	public DocumentBuilderFactory getDocFactory() {
		return docFactory;
	}

	public void setDocFactory(DocumentBuilderFactory docFactory) {
		this.docFactory = docFactory;
	}

	public DocumentBuilder getDocBuilder() {
		return docBuilder;
	}

	public void setDocBuilder(DocumentBuilder docBuilder) {
		this.docBuilder = docBuilder;
	}

	public TransformerFactory getTransformerFactory() {
		return transformerFactory;
	}

	public void setTransformerFactory(TransformerFactory transformerFactory) {
		this.transformerFactory = transformerFactory;
	}

	public Transformer getTransformer() {
		return transformer;
	}

	public void setTransformer(Transformer transformer) {
		this.transformer = transformer;
	}

	public File getFileImage() {
		return fileImage;
	}

	public void setFileImage(File file) {
		this.fileImage = file;
	}
}
