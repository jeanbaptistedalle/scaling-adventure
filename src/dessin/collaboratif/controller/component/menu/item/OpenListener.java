package dessin.collaboratif.controller.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;

import reseau.client.ClientNetwork;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;
//~--- JDK imports ------------------------------------------------------------
import java.awt.event.ActionEvent;

/**
 * Listener de l'item de menu d'ouverture
 *
 * Permet d'ouvrir un fichier
 */
public class OpenListener implements ActionListener {
    @SuppressWarnings("FieldMayBeFinal")
    private JFileChooser fileChooser;

    public OpenListener(final JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    /**
     * Méthode lancée à la détection d'un clic sur le bouton
     *
     * @param e
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (ClientNetwork.getInstance().haveControl()) {
            int returnVal = fileChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                final Client client = Client.getInstance();

                // Récupère le fichier choisi
                final File file = fileChooser.getSelectedFile();

                try {

                    // Parse le svg
                    String                parser  = XMLResourceDescriptor.getXMLParserClassName();
                    SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
                    Document              doc     = factory.createDocument(file.toURI().toString());

                    // Afficher le tableau
                    client.setImage(doc);
                    client.setFileImage(file);
                } catch (final Exception e1) {
                    throw new RuntimeException(e1);
                }

                MainFrame.getInstance().repaintDrawPanel();

                /* Envoi du SVG au serveur */
                ClientNetwork.getInstance().submitPicture(Client.getInstance().imageToString());
            }
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
