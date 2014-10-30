/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @class ClientManager
 * @brief 
 */
class ClientManager extends Thread {
    private final Server server;
    private final Socket sock;
    private String pseudo;
    private BufferedReader in;
    private PrintWriter out;

    public ClientManager(Socket client_sock, Server server) {
        this.server = server;
        this.sock = client_sock;
    }
    
    public void run () {
        boolean cont;
        String my_pseudo = "Anon";
        /**
         * Quand un nouveau client arrive on lui demande un pseudo.
         * Lorsqu'il est connecté avec un pseudo il doit choisir une room pour dessiner.
         * Un fois connecté à une room il peut dessiner (Avec le protocole de dessin mis en place).
         */
        // TODO : ouverture des streams
        do{
            // TODO : lecture du pseudo
            try {
                my_pseudo = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            cont = false;
            for (ClientManager cli : server.getClients()){
                if (my_pseudo.equals(cli.pseudo)){
                    cont = true;
                }
            }
            // On envoie un message ACCEPT si le pseudo est valide, DENY sinon
        }while (cont);
    }
    
}
