/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau;

import java.util.Vector;

/**
 * @class Server
 * @brief Va lancer les différentes rooms qui aura chacune un dessin SVG. Chaque room aura ça propre liste de cleint.
 */
public class Server {
    private Vector <Room> rooms;
    
    public static void main(String[] args) {
        Server s = new Server();
    }
    
    public Server () {
        /**
         * Un server par room (Donc ouvrir un socket par Room) ?? 
         * Ou bien un socket ouvert sur le serveur mais chaque client doit donné le numéro de ca room
         *  dans les messages échangés.
         */
        rooms = new Vector<Room>();
        for (int i = 0; i < Constant.NB_ROOM; i++) {
            
        }
    }
}
