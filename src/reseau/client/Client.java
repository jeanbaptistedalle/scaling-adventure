package reseau.client;

import reseau.common.Constant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import reseau.common.Message;

/**
 * @class Client
 * @brief Gère la partie "réseau du client
 */
public class Client extends Thread{
    private Socket sock;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private InetAddress addr;
    
    /**
     * @fn Client
     * @brief Constructeur de Client
     * @param server_ip l'ip du serveur
     * @param port le port du serveur
     */
    public Client(InetAddress server_ip, int port){
        // connexion de la socket
        try {
            sock = new Socket(server_ip, port);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO choix du pseudo
        // TODO réception de la liste de rooms
        // TODO choix de la room
    }
    
    public void run(){
        // TODO écoute des messages serveur, réponse en conséquent
    }
    
    /**
     * @fn sendMessage
     * @brief Envoie un Message au serveur
     * @param cmd la commande de ce message
     * @param content la chaîne de caractères contenue dans ce message
     */
    public void sendMessage(Constant.command cmd, String content){
        out.print(new Message(addr, cmd, content).toByteArray());
    }
    
    /**
     * @fn sendMessage
     * @brief Envoie un Message au serveur
     * @param cmd la commande de ce message
     */
    public void sendMessage(Constant.command cmd){
        out.print(new Message(addr, cmd).toByteArray());
    }    
}
