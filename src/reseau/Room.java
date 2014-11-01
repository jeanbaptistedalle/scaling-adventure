/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau;

import java.util.Vector;

/**
 * @class Room
 * @brief Correspond à un groupe de clients travaillant sur le même dessin
 */
class Room{
    private Vector <ClientManager> clients;
    
    /**
     * @fn addClient
     * @brief Ajoute un client dans la liste de la room
     * @param cli le nouveau client
     */
    public void addClient(ClientManager cli){
        clients.add(cli);
    }
    
    /**
     * @fn getClientList
     * @brief Retourne la liste des clients présents sur cette room sous la forme client1,client2,clientn
     * @return la chaîne de caractères qui liste les clients
     */
    public String getClientList(){
        String res = "";
        for (int i=0; i<clients.size()-1; i++){
            res += clients.get(i).toString() + ",";
        }
        res += clients.get(clients.size() - 1).toString();
        
        return(res);
    }
    
    public void broadcast(Message msg){
        for (ClientManager cli : clients){
            cli.sendMessage(msg);
        }
    }
}
