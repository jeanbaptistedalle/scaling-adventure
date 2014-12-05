/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau.server;

import reseau.common.Constant;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @class Server
 * @brief Va lancer les différentes rooms qui aura chacune un dessin SVG. Chaque
 * room aura sa propre liste de clients.
 */
public class Server {

    private Vector<Room> rooms;
    private Vector<ClientManager> clients;
    private ClientManager active;

    @SuppressWarnings({"Convert2Diamond", "CallToThreadStartDuringObjectConstruction"})
    public Server(int port) {
        try {
            rooms = new Vector<Room>();
            clients = new Vector<ClientManager>();
            ServerSocket sock = new ServerSocket(port);

            /**
             * On crée les rooms
             */
            for (int i = 0; i < Constant.NB_ROOM; i++) {
                Room room = new Room(i);
                rooms.add(room);
            }

            System.out.println("¤ Server ready... Listening @ " + sock.toString());

            /**
             * En attente de nouveaux clients.
             */
            while (true) {
                Socket client_sock = sock.accept();

                System.out.println("¤ New client tried to connect");
                ClientManager client = new ClientManager(client_sock, this);
                client.start();
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
    public Vector<ClientManager> getClients() {
        return (this.clients);
    }

    /**
     * @fn getRooms
     * @brief accesseur de la liste des Room
     * @return vector rooms
     */
    public Vector<Room> getRooms() {
        return (this.rooms);
    }

    /**
     * @fn addClient
     * @brief Ajoute un client à la liste des clients loggés
     * @param cli le nouveau client (ClientManager)
     */
    public void addClient(ClientManager cli) {
        this.clients.add(cli);
    }

    /**
     * @fn rmClient
     * @brief Supprime un client de la liste des clients loggés (déconnexion)
     * @param cli le client déconnecté
     */
    public void rmClient(ClientManager cli) {
        this.clients.remove(cli);
    }

    /**
     * @fn getRoomList
     * @brief Renvoie la liste de toutes les rooms disponibles sous la forme
     * room1,room2,roomn
     * @return la chaîne de caractère qui liste les rooms
     */
    public String getRoomList() {
        String res = "";
        for (int i = 0; i < rooms.size() - 1; i++) {
            res += rooms.get(i).toString() + Constant.SEPARATOR;
        }
        res += rooms.get(rooms.size() - 1).toString();

        return (res);
    }

    /**
     * @fn getRoomById
     * @brief Retourne la Room d'ID correspondant au String passé en argument
     * @param id l'ID de la Room demandée
     * @return la Room concernée, null si elle n'existe pas
     */
    public Room getRoomById(int id) {
        for (Room r : rooms) {
            if (r.equals(id)) {
                return (r);
            }
        }
        return (null);
    }
}
