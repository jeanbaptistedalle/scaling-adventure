package reseau.client;

import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import reseau.common.Constant;
import reseau.server.Server;

/**
 *
 * @author kevin
 */
public class ClientNetworkTest {

    private Server s;

    @Before
    public void setUp() {
        new Thread() {
            public void run() {
                s = new Server(Constant.PORT);
            }
        }.start();
    }

    /**
     * Test si le client prend et perd bien le contrôle du dessin après 30 secondes.
     */
    @Test
    public void testControl() {
        try {
            boolean cont;
            long startTime, endTime, duration;
            String serverText = "0.0.0.0";
            String loginText = "azerty";
            
            ClientNetwork.getInstance().initIp(serverText);
            ClientNetwork.getInstance().initPseudo(loginText);
            ClientNetwork.getInstance().start();
            MainFrame.getInstance();
            
            /* On attend une petite secondes que les messages soient bien envoyés et reçus.
             * Dan l'application il y'a un systeme de poignée de main pour éviter d'avoir à attendre de cette façon.
             */
            startTime = System.nanoTime();
            cont = true;
            while (cont) {
                endTime = System.nanoTime();
                duration = (endTime - startTime);

                if ((duration / 1000000000) > 1) {
                    cont = false;
                }
            }
            ClientNetwork.getInstance().requestControl();
            
            /*
             * Test que le client à bien pris le contrôle.
             */
            assertEquals(true, ClientNetwork.getInstance().haveControl());
            
            startTime = System.nanoTime();
            cont = true;
            while (cont) {
                endTime = System.nanoTime();
                duration = (endTime - startTime);

                if ((duration / 1000000000) > 30) {
                    cont = false;
                }
            }

            /* 
             * Test que le serveur à bien indiquer au client qu'il n'avait plus le controle.
             */
            assertEquals(false, ClientNetwork.getInstance().haveControl());
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientNetworkTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
