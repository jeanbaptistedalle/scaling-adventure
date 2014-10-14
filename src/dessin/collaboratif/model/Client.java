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
import dessin.collaboratif.view.component.MainFrame;

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

	public void draw(final Integer x1, final Integer y1, final Integer x2,
			final Integer y2) {
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
		}
	}

	public void drawSquare(final Integer x1, final Integer y1,
			final Integer x2, final Integer y2) {
		final Document doc = getImage();
		final Element svgRoot = doc.getDocumentElement();
		Element rectangle = doc.createElementNS(Client.getInstance()
				.getSvgNameSpace(), "rect");
		rectangle.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE,
				x1.toString());
		rectangle.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE,
				y1.toString());
		Integer height = Math.abs(y1 - y2);
		Integer width = Math.abs(x1 - x2);
		rectangle.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE,
				height.toString());
		rectangle.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE,
				width.toString());
		rectangle
				.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, "black");
		svgRoot.appendChild(rectangle);
		saveSVG(doc, Client.getInstance().getFileImage());
	}

	public void saveSVG(final Document doc, final File file) {
		// Save the file
		final DOMSource source = new DOMSource(doc);
		final StreamResult result = new StreamResult(file.getPath());
		try {
			getTransformer().transform(source, result);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
		MainFrame.getInstance().repaintDrawPanel();
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

	public File getFileImage() {
		return fileImage;
	}

	public void setFileImage(File file) {
		this.fileImage = file;
	}
}
