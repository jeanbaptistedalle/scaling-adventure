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
import reseau.common.Client;
import reseau.common.Message;

/**
 * @class Client
 * @brief Gère la partie "réseau du client
 */
public class ClientNetwork extends Thread{
    private Socket sock;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private InetAddress addr;
    private String pseudo;
    private Client me;
    private boolean have_control = false;
    
    /**
     * @fn ClientNetwork
     * @brief Constructeur de Client
     * @param server_ip l'ip du serveur
     * @param port le port du serveur
     */
    public ClientNetwork(InetAddress server_ip, int port){
        // connexion de la socket
        try {
            sock = new Socket(server_ip, port);
        } catch (IOException ex) {
            Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO choix du pseudo
        // TODO réception de la liste de rooms
        // TODO choix de la room
        // TODO choix du pseudo
        // TODO réception de la liste de rooms
        // TODO choix de la room
    }
    
    public void run(){
        // TODO écoute des messages serveur, réponse en conséquent
        while(true){
            try {
                Message msg = new Message(in);
                switch (msg.getCmd()){
                    case LIST_ROOMS :
                        // TODO update des rooms
                        break;
                    case LIST_USERS :
                        // TODO update des users
                        break;
                    case GIVE_CTRL :
                        // Si msg.getContent() est celui de client, alors have_content = true
                        // Sinon, on note qui a la main
                        break;
                    case UPDATE :
                        // Actualise le dessin coté client, à partir de msg.getContent
                        break;
                    case LEAVE_CTRL :
                        if (this.have_control){
                            this.have_control = false;
                        }
                }
            } catch (UnknownHostException ex) {
                Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
