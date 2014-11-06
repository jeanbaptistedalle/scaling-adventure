package dessin.collaboratif.model;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import dessin.collaboratif.misc.DirectionEnum;
import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.view.component.dialog.MoveDialog;

public class Client {

	private static Client INSTANCE = null;
	
	private MoveDialog moveDial = null;

	private DOMImplementation domImpl;
	private String svgNameSpace;
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private TransformerFactory transformerFactory;
	private Transformer transformer;

	private Document image;
	private File fileImage;

	private Color selectedColor = Color.black;
	private DrawModelEnum currentDraw = null;
	
	private String textToInsert ="";
	private String sizeTextToInsert = "12";

	private Integer svgSizeX = Integer.valueOf( GeneralVariables.DEFAULT_WIDTH );
	private Integer svgSizeY = Integer.valueOf( GeneralVariables.DEFAULT_HEIGHT );
	
	private Integer selected=-1;
	private Integer decalage = 5;
	private DirectionEnum lastMove = null;
	
	
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
		if(x1 != null && x2 != null && y1 != null && y2 != null && Math.abs(x2-x1) < 1 
				&& Math.abs(y2-y1) < 1)
			return false;
		else
			return x1 != null && x1 >= 0 && y1 != null && y1 >= 0 && x2 != null
				&& x2 >= 0 && y2 != null && y2 >= 0;
	}

	/**
	 * Draw the selected form on the .svg if the dimension are legal. return
	 * true if something was draw
	 * 
	 * @return
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public boolean draw(final Integer x1, final Integer y1, final Integer x2,
			final Integer y2) {
		if (isLegal(x1, y1, x2, y2)) {
			if (currentDraw != null) {
				
				// On redimensionne le svg en fonction de l'emplacement et la taille de la forme 
				
				reshapeSVG(x1,y1,x2,y2);
				
				// On dessine la forme
				switch (currentDraw) {
				case CIRCLE:
					drawCircle(x1, y1, x2, y2);
					break;
				case LINE:
					drawLine(x1, y1, x2, y2);
					break;
				case SQUARE:
					drawSquare(x1, y1, x2, y2);
					break;
				case ELLIPSE:
					drawEllipse(x1, y1, x2, y2);
					break;
				case TEXT:
					drawText(x1, y1);
					break;
				default:
					break;
				}
				return true;
			} else {
				/*
				 * TODO si aucune forme à dessiner n'est selectionnée, on doit
				 * pouvoir déplacer une forme
				 */
				
				
				return false;
			}
		}
		return false;
	}
	
	public void move(String dir)
	{
		System.out.println(getSelected());
		
		if(getSelected() == -1)
			return;
		
		System.out.println("Move");
		
		if(dir == DirectionEnum.UP.toString())
			setLastMove(DirectionEnum.UP);
		else if(dir == DirectionEnum.DOWN.toString())
			setLastMove(DirectionEnum.DOWN);
		else if(dir == DirectionEnum.LEFT.toString())
			setLastMove(DirectionEnum.LEFT);
		else
			setLastMove(DirectionEnum.RIGHT);
		
		System.out.println(dir);
		
		final Document doc = getImage();
		final Element svgRoot = doc.getDocumentElement();
		
		Node eltNode = svgRoot.getChildNodes().item(getSelected());
		
		if(eltNode == null)
			return;
		
		Element elt = (Element) eltNode;
		
		if(elt.getNodeName() == DrawModelEnum.SQUARE.getTagName())
			moveRect(elt);
		else if(elt.getNodeName() == DrawModelEnum.ELLIPSE.getTagName())
			moveEllipse(elt);
		else if(elt.getNodeName() == DrawModelEnum.CIRCLE.getTagName())
			moveCircle(elt);
		else if(elt.getNodeName() == DrawModelEnum.LINE.getTagName())
			moveLine(elt);
		
		saveSVG();
	}

	private void moveRect(Element elt) {
		String attX = elt.getAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE);
		String attY = elt.getAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE);
		String attW = elt.getAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE);
		String attH = elt.getAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE);

		Integer valueX = Integer.valueOf(attX);
		Integer valueY = Integer.valueOf(attY);
		Integer valueW = Integer.valueOf(attW);
		Integer valueH = Integer.valueOf(attH);
		
		if(getLastMove() == DirectionEnum.UP)
			valueY = Integer.valueOf(attY) - decalage;
		else if(getLastMove() == DirectionEnum.DOWN)
			valueY = Integer.valueOf(attY) + decalage;
		else if(getLastMove() == DirectionEnum.LEFT)
			valueX = Integer.valueOf(attX) - decalage;
		else
			valueX = Integer.valueOf(attX) + decalage;

		valueX = (valueX>0) ? valueX : 0;
		valueY = (valueY>0) ? valueY : 0;

		elt.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, ""+valueX);
		elt.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, ""+valueY);
		
		reshapeSVG(valueX, valueY, valueW + valueX, valueH + valueY);
		
	}
	
	
	private void moveCircle(Element elt) {
		String attX = elt.getAttributeNS(null, SVGConstants.SVG_CX_ATTRIBUTE);
		String attY = elt.getAttributeNS(null, SVGConstants.SVG_CY_ATTRIBUTE);
		String attW = elt.getAttributeNS(null, SVGConstants.SVG_R_ATTRIBUTE);
		String attH = elt.getAttributeNS(null, SVGConstants.SVG_R_ATTRIBUTE);

		Integer valueX = Integer.valueOf(attX);
		Integer valueY = Integer.valueOf(attY);
		Integer valueW = Integer.valueOf(attW);
		Integer valueH = Integer.valueOf(attH);
		
		if(getLastMove() == DirectionEnum.UP)
			valueY = Integer.valueOf(attY) - decalage;
		else if(getLastMove() == DirectionEnum.DOWN)
			valueY = Integer.valueOf(attY) + decalage;
		else if(getLastMove() == DirectionEnum.LEFT)
			valueX = Integer.valueOf(attX) - decalage;
		else
			valueX = Integer.valueOf(attX) + decalage;

		valueX = (valueX>valueW) ? valueX : valueW;
		valueY = (valueY>valueH) ? valueY : valueH;

		elt.setAttributeNS(null, SVGConstants.SVG_CX_ATTRIBUTE, ""+valueX);
		elt.setAttributeNS(null, SVGConstants.SVG_CY_ATTRIBUTE, ""+valueY);
		
		reshapeSVG(valueX, valueY, valueW + valueX, valueH + valueY);
		
	}
	private void moveEllipse(Element elt) {
		String attX = elt.getAttributeNS(null, SVGConstants.SVG_CX_ATTRIBUTE);
		String attY = elt.getAttributeNS(null, SVGConstants.SVG_CY_ATTRIBUTE);
		String attW = elt.getAttributeNS(null, SVGConstants.SVG_RX_ATTRIBUTE);
		String attH = elt.getAttributeNS(null, SVGConstants.SVG_RY_ATTRIBUTE);

		Integer valueX = Integer.valueOf(attX);
		Integer valueY = Integer.valueOf(attY);
		Integer valueW = Integer.valueOf(attW);
		Integer valueH = Integer.valueOf(attH);
		
		if(getLastMove() == DirectionEnum.UP)
			valueY = Integer.valueOf(attY) - decalage;
		else if(getLastMove() == DirectionEnum.DOWN)
			valueY = Integer.valueOf(attY) + decalage;
		else if(getLastMove() == DirectionEnum.LEFT)
			valueX = Integer.valueOf(attX) - decalage;
		else
			valueX = Integer.valueOf(attX) + decalage;

		valueX = (valueX>valueW) ? valueX : valueW;
		valueY = (valueY>valueH) ? valueY : valueH;

		elt.setAttributeNS(null, SVGConstants.SVG_CX_ATTRIBUTE, ""+valueX);
		elt.setAttributeNS(null, SVGConstants.SVG_CY_ATTRIBUTE, ""+valueY);
		
		reshapeSVG(valueX, valueY, valueW + valueX, valueH + valueY);
		
	}
	private void moveLine(Element elt) {
		String attX1 = elt.getAttributeNS(null, SVGConstants.SVG_X1_ATTRIBUTE);
		String attY1 = elt.getAttributeNS(null, SVGConstants.SVG_Y1_ATTRIBUTE);
		String attX2 = elt.getAttributeNS(null, SVGConstants.SVG_X2_ATTRIBUTE);
		String attY2 = elt.getAttributeNS(null, SVGConstants.SVG_Y2_ATTRIBUTE);

		Integer valueX1 = Integer.valueOf(attX1);
		Integer valueY1 = Integer.valueOf(attY1);
		Integer valueX2 = Integer.valueOf(attX2);
		Integer valueY2 = Integer.valueOf(attY2);

		Integer tailleX = valueX2-valueX1;
		Integer tailleY = valueY2-valueY1;
		
		if(getLastMove() == DirectionEnum.UP)
		{
			valueY1 = Integer.valueOf(attY1) - decalage;
			valueY2 = Integer.valueOf(attY2) - decalage;
		}
		else if(getLastMove() == DirectionEnum.DOWN)
		{
			valueY1 = Integer.valueOf(attY1) + decalage;
			valueY2 = Integer.valueOf(attY2) + decalage;
		}
		else if(getLastMove() == DirectionEnum.LEFT)
		{
			valueX1 = Integer.valueOf(attX1) - decalage;
			valueX2 = Integer.valueOf(attX2) - decalage;
		}
		else
		{
			valueX1 = Integer.valueOf(attX1) + decalage;
			valueX2 = Integer.valueOf(attX2) + decalage;
		}

		valueX1 = (valueX1>0) ? valueX1 : 0;
		valueY1 = (valueY1>0) ? valueY1 : 0;
		valueX2 = (valueX1>0) ? valueX2 : tailleX;
		valueY2 = (valueY1>0) ? valueY2 : tailleY;

		elt.setAttributeNS(null, SVGConstants.SVG_X1_ATTRIBUTE, ""+valueX1);
		elt.setAttributeNS(null, SVGConstants.SVG_Y1_ATTRIBUTE, ""+valueY1);
		elt.setAttributeNS(null, SVGConstants.SVG_X2_ATTRIBUTE, ""+valueX2);
		elt.setAttributeNS(null, SVGConstants.SVG_Y2_ATTRIBUTE, ""+valueY2);
		
		reshapeSVG(valueX1, valueY1, valueX2, valueY2);
		
	}

	public boolean draw(Integer x1, Integer y1, Integer x2, Integer y2,
			Boolean resize) {
		
		if(resize)
			undo();
		
		return draw(x1, y1, x2, y2);
	}

	private void reshapeSVG(final Integer x1, final Integer y1, final Integer x2, final Integer y2) {
		Integer maxX = (x1 < x2) ? x2 : x1;
		Integer maxY = (y1 < y2) ? y2 : y1;
		
		final Document doc = getImage();
		final Element svgRoot = doc.getDocumentElement();

		maxX = (maxX < svgSizeX) ? svgSizeX : maxX;
		maxY = (maxY < svgSizeY) ? svgSizeY : maxY;

		maxX += 10;
		maxY += 10;
		
		svgRoot.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, ""+ maxX);
		svgRoot.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, ""+ maxY);

		setSvgSizeX(maxX);
		setSvgSizeY(maxY);
		
	}

	private void drawText(final Integer x1, final Integer y1) {
		
		System.out.println(">" + textToInsert);
		
		final Document doc = getImage();
		final Element svgRoot = doc.getDocumentElement();
		
		Text text = doc.createTextNode(textToInsert);
		
		// Create the rectangle.
		Element rectangle = doc.createElementNS(Client.getInstance().getSvgNameSpace(),
				"text");
		rectangle.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, x1.toString());
		rectangle.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, y1.toString());
		String rgbString = colorToRGB(selectedColor);
		rectangle.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, rgbString);
		rectangle.setAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, sizeTextToInsert);
		rectangle.appendChild(text);
		svgRoot.appendChild(rectangle);
		saveSVG();
		
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
		String rgbString = colorToRGB(selectedColor);
		rectangle.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE,
				rgbString);
		svgRoot.appendChild(rectangle);
		saveSVG();
	}

	/**
	 * This method draw an ellipse on the selected .svg
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void drawEllipse(final Integer x1, final Integer y1,
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
		Element ell = doc.createElementNS(Client.getInstance()
				.getSvgNameSpace(), "ellipse");

		Integer cx, cy, rx, ry;

		cx = ((nx2 - nx1) / 2) + nx1;
		cy = ((ny2 - ny1) / 2) + ny1;
		rx = ((nx2 - nx1) / 2);
		ry = ((ny2 - ny1) / 2);

		ell.setAttributeNS(null, SVGConstants.SVG_CX_ATTRIBUTE, cx.toString());
		ell.setAttributeNS(null, SVGConstants.SVG_CY_ATTRIBUTE, cy.toString());
		ell.setAttributeNS(null, SVGConstants.SVG_RX_ATTRIBUTE, rx.toString());
		ell.setAttributeNS(null, SVGConstants.SVG_RY_ATTRIBUTE, ry.toString());

		String rgbString = colorToRGB(selectedColor);
		ell.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, rgbString);
		svgRoot.appendChild(ell);
		saveSVG();
	}

	/**
	 * This method draw a circle on the selected .svg
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void drawCircle(final Integer x1, final Integer y1,
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
		Element cercle = doc.createElementNS(Client.getInstance()
				.getSvgNameSpace(), "circle");

		Integer cx, cy;
		Integer rayon;

		if (nx2 - nx1 > ny2 - ny1) {
			ny2 = nx2 - nx1 + ny1;
		} else {
			nx2 = ny2 - ny1 + nx1;
		}

		cx = ((nx2 - nx1) / 2) + nx1;
		cy = ((ny2 - ny1) / 2) + ny1;

		rayon = nx2 - cx;

		cercle.setAttributeNS(null, SVGConstants.SVG_CX_ATTRIBUTE,
				cx.toString());
		cercle.setAttributeNS(null, SVGConstants.SVG_CY_ATTRIBUTE,
				cy.toString());
		cercle.setAttributeNS(null, SVGConstants.SVG_R_ATTRIBUTE,
				rayon.toString());
		String rgbString = colorToRGB(selectedColor);
		cercle.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, rgbString);
		svgRoot.appendChild(cercle);
		saveSVG();
	}

	/**
	 * This method draw a line on the selected .svg
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void drawLine(final Integer x1, final Integer y1, final Integer x2,
			final Integer y2) {

		final Document doc = getImage();
		final Element svgRoot = doc.getDocumentElement();
		Element ligne = doc.createElementNS(Client.getInstance()
				.getSvgNameSpace(), "line");
		ligne.setAttributeNS(null, SVGConstants.SVG_X1_ATTRIBUTE, x1.toString());
		ligne.setAttributeNS(null, SVGConstants.SVG_Y1_ATTRIBUTE, y1.toString());
		ligne.setAttributeNS(null, SVGConstants.SVG_X2_ATTRIBUTE, x2.toString());
		ligne.setAttributeNS(null, SVGConstants.SVG_Y2_ATTRIBUTE, y2.toString());
		String rgbString = colorToRGB(selectedColor);
		ligne.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, rgbString);
		ligne.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, GeneralVariables.DEFAULT_STROKE_WIDTH);
		svgRoot.appendChild(ligne);
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

	public void exportToJPG(final File outputFile) {
		try {
			// Create a JPEGTranscoder and set its quality hint.
			JPEGTranscoder t = new JPEGTranscoder();
			t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));

			// Set the transcoder input and output.
			TranscoderInput input = new TranscoderInput(image);
			OutputStream ostream = new FileOutputStream(outputFile);
			TranscoderOutput output = new TranscoderOutput(ostream);

			// Perform the transcoding.
			t.transcode(input, output);
			ostream.flush();
			ostream.close();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Return a String which follow the syntax : 'rgb(x,y,z)'<br/>
	 * This method is use to generate the rgb code for generate the tag in a
	 * .svg
	 * 
	 * @param color
	 * @return
	 */
	public String colorToRGB(final Color color) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("rgb(");
		stringBuilder.append(selectedColor.getRed());
		stringBuilder.append(",");
		stringBuilder.append(selectedColor.getGreen());
		stringBuilder.append(",");
		stringBuilder.append(selectedColor.getBlue());
		stringBuilder.append(")");
		return stringBuilder.toString();
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

	public Color getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}

	public String getTextToInsert() {
		return textToInsert;
	}

	public void setTextToInsert(String textToInsert) {
		this.textToInsert = textToInsert;
	}

	public String getSizeTextToInsert() {
		return sizeTextToInsert;
	}

	public void setSizeTextToInsert(String sizeTextToInsert) {
		this.sizeTextToInsert = sizeTextToInsert;
	}

	public Integer getSvgSizeX() {
		return svgSizeX;
	}

	public void setSvgSizeX(Integer svgSizeX) {
		this.svgSizeX = svgSizeX;
	}

	public Integer getSvgSizeY() {
		return svgSizeY;
	}

	public void setSvgSizeY(Integer svgSizeY) {
		this.svgSizeY = svgSizeY;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public MoveDialog getMoveDial() {
		return moveDial;
	}

	public void setMoveDial(MoveDialog moveDial) {
		this.moveDial = moveDial;
	}

	public DirectionEnum getLastMove() {
		return lastMove;
	}

	public void setLastMove(DirectionEnum lastMove) {
		this.lastMove = lastMove;
	}

}
