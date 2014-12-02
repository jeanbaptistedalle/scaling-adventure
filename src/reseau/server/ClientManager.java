/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau.server;

import reseau.common.Message;
import reseau.common.Constant;
import com.sun.corba.se.pept.transport.InboundConnectionCache;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import reseau.common.Client;

/**
 * @class ClientManager
 * @brief Gestionnaire de client, qui communiquera avec un client spécifique
 */
class ClientManager extends Thread {
    private final Server server;
    private Room room;
    private Socket sock;
    private BufferedReader in;
    private PrintWriter out;
    private Client client;

    public ClientManager(Socket client_sock, Server server) {
        this.server = server;
        this.sock = client_sock;
        this.client = new Client(sock.getInetAddress());
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
            System.out.println("...");
            my_pseudo = recvMessage().getContent();
            System.out.println("...");
            cont = false;
            // On teste que le pseudo soit unique
            for (ClientManager cli : server.getClients()){
                if (my_pseudo.equals(cli.getPseudo())){
                    cont = true;
                }
            }
            sendMessage((cont) ? Constant.command.DENY : Constant.command.ACCEPT);
        }while (cont);
        
        this.client.setPseudo(my_pseudo);
        
        System.out.println("¤ Client " + this.getPseudo() + " joined");
        
        server.addClient(this);
        
        /* Puis il choisit sa Room */
        /*
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
        */
        my_room = "0";
        
        this.room = server.getRoomById(my_room);
        this.room.addClient(this);
        
        cont = true;
        while (cont){
            Message msg = recvMessage();
            
            switch (msg.getCmd()){
                case GET_USERS :
                    sendMessage(Constant.command.LIST_USERS, room.getClientList());
                    break;
                case REQUEST_CTRL :
                    room.joinWaitList(this);
                    break;
                case LEAVE_CTRL :
                    room.leaveWaitList(this);
                case SUBMIT :
                    if (room.getWaitList().isCurrent(this)){
                        room.broadcast(new Message(msg.getFrom(), Constant.command.UPDATE, msg.getContent()));
                    }
                    break;
                case DISCONNECT :
                    cont = false;
                    break;
            }
        }
        
        /* Lorsqu'on est ici, le client à demandé la déconnexion */
        room.rmClient(this);
        server.rmClient(this);
        try {
            in.close();
            out.close();
            sock.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("¤ Client " + this.getPseudo() + "leaved");
    }
    
    /**
     * @fn toString
     * @brief Renvoie la chaîne de caractère correspondant au client (en l'occurence le pseudo)
     * @return le pseudo du client
     */
    public String toString(){
        return(this.client.toString());
    }
    
    /**
     * @fn sendMessage
     * @brief Crée un Message et l'envoie au client
     * @param cmd la commande
     * @param content le contenu
     */
    @SuppressWarnings("ImplicitArrayToString")
    private void sendMessage(Constant.command cmd, String content){
        out.print(new Message(Constant.SERVER_IP, cmd, content).toByteArray());
    }
   
    /**
     * @fn sendMessage
     * @brief Crée un Message et l'envoie au client
     * @param cmd la commande
     */
    @SuppressWarnings("ImplicitArrayToString")
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
            return(new Message(in));
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return(null);
    }
    
    /**
     * @fn getPseudo
     * @brief Accesseur de client.pseudo
     * @return client.pseudo
     */
    public String getPseudo(){
        return(this.client.getPseudo());
    }
    
    /**
     * @fn getAddr
     * @brief Accesseur de client.addr
     * @return client.addr
     */
    public InetAddress getAddr(){
        return(this.client.getAddr());
    }
}
