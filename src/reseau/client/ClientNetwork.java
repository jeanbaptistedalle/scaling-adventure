package reseau.client;

import reseau.common.Constant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
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
    private Vector <Client> clients;
    
    /**
     * @fn ClientNetwork
     * @brief Constructeur de Client
     * @param server_ip l'ip du serveur
     * @param port le port du serveur
     */
    public ClientNetwork(InetAddress server_ip, int port){
        boolean cont = true;
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
        
        /* Choix du pseudo */
        String my_pseudo = "";
        do{
            try {
                // TODO proposirion de pseudo
                sendMessage(Constant.command.CONNECT, my_pseudo);
                cont = (new Message(in).getCmd() != Constant.command.ACCEPT);
            } catch (UnknownHostException ex) {
                Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while (cont);
        
        me = new Client(my_pseudo);
        
        /* Choix de la room */
        try {
            Message msg = new Message(in);
            Vector <String> rooms = new Vector <String>();
            String my_room = "";
            for (int start = 0, end = msg.getContent().indexOf(Constant.SEPARATOR, 0); end != -1; start = end + 1, end = msg.getContent().indexOf(Constant.SEPARATOR, start)){
                rooms.add(msg.getContent().substring(start, end));
            }
            do{
                // TDOD Choix de la room
                sendMessage(Constant.command.JOIN_ROOM, my_room);
                msg = new Message(in);
                cont = (msg.getCmd() != Constant.command.ACCEPT);
            }while (cont);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    public void run(){
        // TODO écoute des messages serveur, réponse en conséquent
        while(true){
            try {
                Message msg = new Message(in);
                switch (msg.getCmd()){
                    case LIST_USERS :
                        updateUsers(msg.getContent());
                        break;
                    case GIVE_CTRL :
                        if (msg.getContent() == me.toString()){
                            this.have_control = true;
                        }
                        else{
                            this.have_control = false;
                        }
                        break;
                    case UPDATE :
                        // TODO Actualise le dessin coté client, à partir de msg.getContent
                        break;
                    case LEAVE_CTRL :
                        if (this.have_control){
                            this.have_control = false;
                        }
                        break;
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
    private void sendMessage(Constant.command cmd, String content){
        out.print(new Message(addr, cmd, content).toByteArray());
    }
    
    /**
     * @fn sendMessage
     * @brief Envoie un Message au serveur
     * @param cmd la commande de ce message
     */
    private void sendMessage(Constant.command cmd){
        out.print(new Message(addr, cmd).toByteArray());
    }
    
    /**
     * @fn updateUsers
     * @brief Actualise la liste des utilisateurs à partir d'une chaîne de caractères
     * @param users la chaîne de tous les utilisateurs
     */
    private void updateUsers(String users){
        Vector <Client> new_clients = new Vector<Client>();
        for (int start=0, end = users.indexOf(Constant.SEPARATOR); end != -1; start = end + 1, end = users.indexOf(Constant.SEPARATOR, start)){
            new_clients.add(new Client(users.substring(start, end)));
        }
    }
    
    /**
     * @fn requestControl
     * @brief Demande la main pour ce client
     */
    public void requestControl(){
        sendMessage(Constant.command.REQUEST_CTRL);
    }
    
    /**
     * @fn leaveControl
     * @brief Action de rendre la main
     */
    public void leaveControl(){
        sendMessage(Constant.command.LEAVE_CTRL);
    }
    
    /**
     * @fn submitPicture
     * @brief envoie au serveur une mise à jour de l'image si le client a la main
     * @param modif la chaîne de caractères qui représente la nouvelle image
     */
    public void submitPicture(String modif){
        if(this.have_control){
            sendMessage(Constant.command.SUBMIT, modif);
        }
    }
    
    /**
     * @fn getUsers
     * @brief Accesseur de la liste de clients connectés à la même Room que ce client
     * @return this.clients
     */
    public Vector <Client> getUsers(){
        return(this.clients);
    }
    
    /**
     * @fn disconnect
     * @brief déconnection du client
     */
    public void disconnect(){
        sendMessage(Constant.command.DISCONNECT);
    }
    
    /**
     * @fn haveControl
     * @brief Indique si ce client a le controle ou non
     * @return this.have_control
     */
    public boolean haveControl(){
        return(this.have_control);
    }
}
