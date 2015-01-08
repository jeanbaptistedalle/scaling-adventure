package dessin.collaboratif.controller.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.misc.GeneralVariables;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;

import org.apache.batik.util.SVGConstants;
import org.apache.commons.io.FilenameUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import reseau.client.ClientNetwork;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JFileChooser;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Listener de l'item pour creer nouveau tableau
 *
 * Permet de créer un nouveau tableau
 */
public class NewListener implements ActionListener {
    @SuppressWarnings("FieldMayBeFinal")
    public NewListener() {}

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param action
     */
    @Override
    public void actionPerformed(final ActionEvent action) {
        if (ClientNetwork.getInstance().haveControl()) {

            // Allow user to choose a name for the new file
            final Client client = Client.getInstance();

            // Initialisation du DOM svg du fichier
            Document doc = client.getDomImpl().createDocument(client.getSvgNameSpace(), "svg", null);

            // Get the root element (the 'svg' element).
            Element svgRoot = doc.getDocumentElement();

            // Set the width and height attributes on the root 'svg'
            // element.
            svgRoot.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, GeneralVariables.DEFAULT_WIDTH);
            svgRoot.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, GeneralVariables.DEFAULT_HEIGHT);

            Text text = doc.createTextNode("Scaling Adventure by JB/K/A/R");

            // Create the rectangle.
            Element rectangle = doc.createElementNS(client.getSvgNameSpace(), "text");

            rectangle.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, "20");
            rectangle.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, "20");
            rectangle.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, "silver");
            rectangle.setAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, "12");
            rectangle.appendChild(text);
            svgRoot.appendChild(rectangle);
            client.setImage(doc);
            MainFrame.getInstance().repaintDrawPanel();

            /* Envoi du SVG au serveur */
            ClientNetwork.getInstance().submitPicture(Client.getInstance().imageToString());
        }
    }
}

// ~ Formatted by Jindent --- http://www.jindent.com


//~ Formatted by Jindent --- http://www.jindent.com
