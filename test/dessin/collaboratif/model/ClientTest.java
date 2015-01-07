package dessin.collaboratif.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import dessin.collaboratif.misc.DrawModelEnum;
import dessin.collaboratif.misc.GeneralVariables;

public class ClientTest {

	private Client c;

	@Before
	public void setUp() throws Exception {
		c = Client.getInstance();
		File file = new File(GeneralVariables.PROJECT_PATH
				+ "ressources/test/test.svg");

		// Parse le svg
		String parser = XMLResourceDescriptor.getXMLParserClassName();
		SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
		Document doc;
		try {
			doc = factory.createDocument(file.toURI().toString());

			// Afficher le tableau
			c.setImage(doc);
			c.setFileImage(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		c = null;
	}

	@Test
	public void isLegal() {

		Random rand = new Random();

		Integer x1 = rand.nextInt(), y1 = rand.nextInt(), x2 = rand.nextInt(), y2 = rand
				.nextInt();

		Integer count = 0;

		for (; count < 1000; count++) {
			boolean b = c.isLegal(x1, y1, x2, y2);

			if (x1 < 0 || y1 < 0 || x2 <= x1 + 1 || y2 <= y1 + 1) {
				assertFalse(b);
			} else {
				assertTrue(b);
			}
		}

	}

	@Test
	public void draw() {
		Integer count = 0;

		// Test for circle

		c.setCurrentDraw(DrawModelEnum.CIRCLE);
		count = c.getImage().getFirstChild().getChildNodes().getLength();
		c.draw(1, 1, 10, 10);
		assertEquals(count + 1, c.getImage().getFirstChild().getChildNodes()
				.getLength());
		assertEquals(DrawModelEnum.CIRCLE.getTagName(), c.getImage()
				.getFirstChild().getLastChild().getNodeName());

		// Test for rectangle

		c.setCurrentDraw(DrawModelEnum.RECTANGLE);
		count = c.getImage().getFirstChild().getChildNodes().getLength();
		c.draw(1, 1, 10, 10);
		assertEquals(count + 1, c.getImage().getFirstChild().getChildNodes()
				.getLength());
		assertEquals(DrawModelEnum.RECTANGLE.getTagName(), c.getImage()
				.getFirstChild().getLastChild().getNodeName());

		// Test for line

		c.setCurrentDraw(DrawModelEnum.LINE);
		count = c.getImage().getFirstChild().getChildNodes().getLength();
		c.draw(1, 1, 10, 10);
		assertEquals(count + 1, c.getImage().getFirstChild().getChildNodes()
				.getLength());
		assertEquals(DrawModelEnum.LINE.getTagName(), c.getImage()
				.getFirstChild().getLastChild().getNodeName());

		// Test for ellipse

		c.setCurrentDraw(DrawModelEnum.ELLIPSE);
		count = c.getImage().getFirstChild().getChildNodes().getLength();
		c.draw(1, 1, 10, 10);
		assertEquals(count + 1, c.getImage().getFirstChild().getChildNodes()
				.getLength());
		assertEquals(DrawModelEnum.ELLIPSE.getTagName(), c.getImage()
				.getFirstChild().getLastChild().getNodeName());

		// Test for text

		c.setCurrentDraw(DrawModelEnum.TEXT);
		c.setTextToInsert("Test");
		count = c.getImage().getFirstChild().getChildNodes().getLength();
		c.draw(1, 1, 10, 10);
		assertEquals(count + 1, c.getImage().getFirstChild().getChildNodes()
				.getLength());
		assertEquals(DrawModelEnum.TEXT.getTagName(), c.getImage()
				.getFirstChild().getLastChild().getNodeName());
	}

	@Test
	public void delete() {
		Integer countBefore, countBetween, countAfter;

		countBefore = c.getImage().getFirstChild().getChildNodes().getLength();

		c.setSelected(0);
		c.setCurrentDraw(DrawModelEnum.ELLIPSE);
		c.draw(1, 1, 10, 10);

		countBetween = c.getImage().getFirstChild().getChildNodes().getLength();

		c.delete();

		countAfter = c.getImage().getFirstChild().getChildNodes().getLength();

		assertEquals(countBefore + 1, countBetween + 0);
		assertEquals(countBetween + 0, countAfter + 1);

	}

	@Test
	public void undo() {
		Integer countBefore, countBetween, countAfter;

		countBefore = c.getImage().getFirstChild().getChildNodes().getLength();

		c.setCurrentDraw(DrawModelEnum.ELLIPSE);
		c.draw(1, 1, 10, 10);

		countBetween = c.getImage().getFirstChild().getChildNodes().getLength();

		c.undo();

		countAfter = c.getImage().getFirstChild().getChildNodes().getLength();

		assertEquals(countBefore + 1, countBetween + 0);
		assertEquals(countBetween + 0, countAfter + 1);
	}

	@Test
	public void saveSVG() {
		String expected = "";

		c.setCurrentDraw(DrawModelEnum.ELLIPSE);
		c.draw(1, 1, 10, 10);

		Document doc = c.getImage();
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			writer.flush();
			expected = writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
		}

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(c.getFileImage().getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			StringBuilder sb = new StringBuilder();
			String line;
			line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();

			assertEquals(expected + "\n", everything);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Test
	public void colorToRGB() {
		Color expectedColor = c.getSelectedColor();

		String translatedColor = c.colorToRGB(expectedColor);

		String triplet = translatedColor.substring(
				translatedColor.indexOf("(") + 1, translatedColor.indexOf(")"));

		String[] colors = triplet.split(",");

		Color selectedColor = new Color(Integer.valueOf(colors[0]),
				Integer.valueOf(colors[1]), Integer.valueOf(colors[2]));

		assertEquals(expectedColor, selectedColor);

	}

	@Test
	public void imageToString() {
		String expected = "";
		Document doc = c.getImage();
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			writer.flush();
			expected = writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
		}

		String actual = c.imageToString();

		assertEquals(expected, actual);
	}
}
