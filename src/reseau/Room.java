/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau;

import java.util.Vector;

/**
 * @class Room
 * @brief Server pour un dessin SVG.
 */
class Room extends Thread {
    private Vector <ClientManager> clients;
    
    public Room() {
        
    }
    
    public void run() {
        
    }
    
    /**
     * @fn addClient
     * @brief Ajoute un client dans la liste de la room
     * @param cli le nouveau client
     */
    public void addClient(ClientManager cli){
        clients.add(cli);
    }
}
