/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau;

import java.net.Socket;

/**
 * @class ClientManager
 * @brief 
 */
class ClientManager extends Thread {

    public ClientManager(Socket client_sock, Server server) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void run () {
        /**
         * Quand un nouveau client arrive on lui demande un pseudo.
         * Lorsqu'il est connecté avec un pseudo il doit choisir une room pour dessiner.
         * Un fois connecté à une room il peut dessiner (Avec le protocole de dessin mis en place).
         */
    }
    
}
