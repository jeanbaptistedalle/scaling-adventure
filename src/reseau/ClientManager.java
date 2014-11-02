/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau;

import com.sun.corba.se.pept.transport.InboundConnectionCache;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @class ClientManager
 * @brief Gestionnaire de client, qui communiquera avec un client spécifique
 */
class ClientManager extends Thread {
    private final Server server;
    private Room room;
    private final Socket sock;
    private String pseudo;
    private BufferedReader in;
    private PrintWriter out;

    public ClientManager(Socket client_sock, Server server) {
            this.server = server;
            this.sock = client_sock;
        try{
            this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            this.out = new PrintWriter(sock.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run () {
        boolean cont;
        String my_pseudo;
        String my_room;
        /**
         * Quand un nouveau client arrive on lui demande un pseudo.
         * Lorsqu'il est connecté avec un pseudo il doit choisir une room pour dessiner.
         * Un fois connecté à une room il peut dessiner (Avec le protocole de dessin mis en place).
         */
        
        /* Le client choisit son pseudo */
        do{
            my_pseudo = recvMessage().getContent();
            cont = false;
            // On teste que le pseudo soit unique
            for (ClientManager cli : server.getClients()){
                if (my_pseudo.equals(cli.pseudo)){
                    cont = true;
                }
            }
            sendMessage((cont)?Constant.command.DENY:Constant.command.ACCEPT);            
        }while (cont);
        
        server.addClient(this);
        
        /* Puis il choisit sa Room */
        sendMessage(Constant.command.LIST_ROOMS, server.getRoomList());
        
        do{
            my_room = recvMessage().getContent();
            cont = false;
            // On vérifie que la Room existe
            for (Room r : server.getRooms()){
                if (my_room.equals(r.toString())){
                    cont = false;
                }
            }
            sendMessage((cont)?Constant.command.DENY:Constant.command.ACCEPT);            
        }while (cont);
        
        this.room = server.getRoomById(my_room);
        this.room.addClient(this);
        
        // TODO écoute des messages en provenance du client et transmission de ceux-ci au gestionnaire (?) de la room correpondante
    }
    
    /**
     * @fn toString
     * @brief Renvoie la chaîne de caractère correspondant au client (en l'occurence le pseudo)
     * @return le pseudo du client
     */
    public String toString(){
        return(this.pseudo);
    }
    
    /**
     * @fn sendMessage
     * @brief Crée un Message et l'envoie au client
     * @param cmd la commande
     * @param content le contenu
     */
    private void sendMessage(Constant.command cmd, String content){
        out.print(new Message(Constant.SERVER_IP, cmd, content).toByteArray());
    }
   
    /**
     * @fn sendMessage
     * @brief Crée un Message et l'envoie au client
     * @param cmd la commande
     */
    private void sendMessage(Constant.command cmd){
        out.print(new Message(Constant.SERVER_IP, cmd).toByteArray());
    }
    
    /**
     * @fn sendMessage
     * @brief Envoie un Message au client
     * @param msg le Message à envoyer
     */
    public void sendMessage(Message msg){
        out.print(msg.toByteArray());
    }
    
    /**
     * @fn recvMessage
     * @brief lit un Message sur l'entrée "in" et le retourne
     * @return le Message lu
     */
    private Message recvMessage(){
        try {
            return(new Message(in.readLine().getBytes()));
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return(null);
    }
}
