/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau.server;

import reseau.common.Message;
import reseau.common.Constant;
import com.sun.xml.internal.ws.handler.HandlerChainsModel;
import java.util.Vector;

/**
 * @class Room
 * @brief Correspond à un groupe de clients travaillant sur le même dessin
 */
class Room{
    private Vector <ClientManager> clients;
    private  final int id;
    private WaitList wait;

    
    /**
     * @fn Room
     * @srief Constructeur de Room
     * @param number 
     */
    public Room(int number){
        this.id = number;
        wait = new WaitList();
    }
    
    /**
     * @fn addClient
     * @brief Ajoute un client dans la liste de la room
     * @param cli le nouveau client
     */
    public void addClient(ClientManager cli){
        clients.add(cli);
    }
    
    /**
     * @fn rmClient
     * @brief Supprime un client de la liste de la room
     * @param cli le client à supprimer
     */
    public void rmClient(ClientManager cli){
        clients.remove(cli);
    }
    
    /**
     * @fn joinWaitList
     * @brief Ajoute un client à la file d'attente (lui donne la main si il est le premier)
     * @param c le client à ajouter
     */
    public synchronized void joinWaitList(ClientManager c){
        if (wait.join(this)){       // Si il rejoint une file d'attente vide, il a la main
            broadcast(new Message(Constant.SERVER_IP, Constant.command.GIVE_CTRL, c.toString()));
        }
    }
    
    /**
     * @fn leaveWaitList
     * @brief Fait quitter la liste d'attente au client c, donne la main au suivant si besoin
     * @param c le client
     */
    public synchronized void leaveWaitList(ClientManager c){
        if (wait.leave(this)){  // Si le client avait la main
            broadcast(new Message(Constant.SERVER_IP, Constant.command.LEAVE_CTRL));
            if (!wait.isEmpty()){   // Si un nouveau client reçoit la main
                broadcast(new Message(Constant.SERVER_IP, Constant.command.GIVE_CTRL, wait.getCurrent().toString()));
            }
        }
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
    
        
    /**
     * @fn getWaitList
     * @brief Accesseur de la liste d'attente des clients
     * @return liste d'attente des clients
     */
    public WaitList <ClientManager> getWaitList(){
        return(this.wait);
    }
    
    /**
     * @fn broadcast
     * @brief diffusion d'un Message à tous les client de cette room
     * @param msg le Message à difuser
     */
    public void broadcast(Message msg){
        for (ClientManager cli : clients){
            cli.sendMessage(msg);
        }
    }
    
    /**
     * @fn toString
     * @brief renvoie la chaîne de caractère décrivant cette room (ici son ID)
     * @return la chaîne de caractères correspondante
     */
    public String toString(){
        return(Integer.toString(this.id));
    }
    
    public boolean equals(int i){
        return(this.id == i);
    }
}
