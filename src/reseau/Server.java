/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @class Server
 * @brief Va lancer les différentes rooms qui aura chacune un dessin SVG. Chaque room aura sa propre liste de clients.
 */
public class Server {
    private Vector <Room> rooms;
    private Vector <ClientManager> clients;
    
    public static void main(String[] args) {
        Server s = new Server();
    }
    
    public Server () {
        try {
            rooms = new Vector<Room>();
            ServerSocket sock = new ServerSocket(7030);
            
            /**
             * On démarre les rooms.d
             */
            for (int i = 0; i < Constant.NB_ROOM; i++) {
                Room room = new Room();
                rooms.add(room);
                room.start();
            }
            
            System.out.println("¤ Server ready... Listening");
            
            /**
             * En attente de nouveaux clients.
             */
            while (true) {
                Socket client_sock = sock.accept();

                System.out.println("¤ New client tried to connect");
                ClientManager client = new ClientManager(client_sock, this);
                client.start();
                clients.add(client);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @fn getClients
     * @brief accesseur du vector des clients
     * @return vector clients
     */
    public Vector <ClientManager> getClients(){
        return(this.clients);
    }
    
    /**
     * @fn addClient
     * @brief Ajoute un client à la liste des clients loggés
     * @param cli le nouveau client (ClientManager)
     */
    public void addClient(ClientManager cli){
        this.clients.add(cli);
    }
    
    /**
     * @fn getRoomList
     * @brief Renvoie la liste de toutes les rooms disponibles sous la forme room1,room2,roomn
     * @return la chaîne de caractère qui liste les rooms
     */
    public String getRoomList(){
        String res = "";
        for (int i=0; i<rooms.size() - 1; i++){
            res += rooms.get(i).toString() + ",";
        }
        res += rooms.get(rooms.size() - 1).toString();
        
        return(res);
    }
}
