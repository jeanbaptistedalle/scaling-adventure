package dessin.collaboratif.model;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

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

	public static Client getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Client();
		}
		return INSTANCE;
	}
}
