package reseau.client;

import dessin.collaboratif.view.component.MainFrame;
import reseau.common.Constant;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
 * @brief Gère la partie "réseau" du client
 */
public class ClientNetwork extends Thread{
    
    @SuppressWarnings("FieldMayBeFinal")
    static private ClientNetwork INSTANCE = null;
    
    private Socket sock = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private InetAddress addr = null;
    private String pseudo;
    private Client me;
    private boolean have_control = false;
    private Vector <Client> clients;
    
    /**
     * @fn getInstance
     * @brief retourne l'instance du singleton. Fait appel au constructeur s'il n'y a pas encore d'intance.
     * @return ClientNetwork.INSTANCE
     */
    static public synchronized ClientNetwork getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientNetwork();
        }
        return INSTANCE;
    }
    
    /**
     * @fn ClientNetwork
     * @brief Constructeur de Client
     * @param server_ip l'ip du serveur
     * @param port le port du serveur
     */
    private ClientNetwork(){}
    
    public boolean initIp (String serverIpS) {
        if (sock == null || !sock.isConnected()) {
            try {
                InetAddress serverIp;
                serverIp = InetAddress.getByName(serverIpS);

                sock = new Socket(serverIp, Constant.PORT);
                in = new DataInputStream(sock.getInputStream());
                out = new DataOutputStream(sock.getOutputStream());
                addr = sock.getInetAddress();

                System.out.println("My address : " + addr.toString());
                System.out.println("Socket : " + sock.toString());
                System.out.println("in : " + in.toString());
                System.out.println("out : " + out.toString());
                
                return true;
            } catch (UnknownHostException ex) {
                // Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (IOException ex) {
                // Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }
    
    public boolean initPseudo (String pseudo) throws InterruptedException {
        try {
            sendMessage(Constant.command.CONNECT, pseudo);
            Constant.command c = new Message(in).getCmd();
            boolean isValid = (c == Constant.command.ACCEPT);
            
            if (isValid)
                me = new Client(pseudo);
            
            return isValid;
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean initRoom (String roomNum) {
        try {
            // Message msg = new Message(in);
            // Vector <String> rooms = new Vector <String>();
            // String my_room = "";
            // for (int start = 0, end = msg.getContent().indexOf(Constant.SEPARATOR, 0); end != -1; start = end + 1, end = msg.getContent().indexOf(Constant.SEPARATOR, start)){
            //    rooms.add(msg.getContent().substring(start, end));
            // }
            sendMessage(Constant.command.JOIN_ROOM, roomNum);
            Message msg = new Message(in);
            return (msg.getCmd() == Constant.command.ACCEPT);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * @fn run
     */
    @Override
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
                        if (msg.getContent().equals(me.toString())){
                            this.have_control = true;
                        }
                        else{
                            this.have_control = false;
                        }
                    break;
                    case UPDATE :
                        /* Update du SVG. */
                        
                        MainFrame.getInstance().repaintDrawPanel();
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
        try {
            byte message[] = new Message(addr, cmd, content).toByteArray();
            
            System.out.print("Mesage to send : ");
            for(int i = 0; i < message.length; i++)
                System.out.print(message[i] + ".");
            System.out.println();
            
            out.write(message);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private char[] byteArrayToCharArray(byte [] input){
        char res[] = new char[input.length];
        for (int i = 0; i < input.length; i++){
            res[i] = (char) input[i];
        }
        return(res);
    }

    
    /**
     * @fn sendMessage
     * @brief Envoie un Message au serveur
     * @param cmd la commande de ce message
     */
    private void sendMessage(Constant.command cmd){
        try {
            if (sock != null && !sock.isClosed()) {
                out.write(new Message(addr, cmd).toByteArray());
                out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @fn updateUsers
     * @brief Actualise la liste des utilisateurs à partir d'une chaîne de caractères
     * @param users la chaîne de tous les utilisateurs
     */
    @SuppressWarnings({"UseOfObsoleteCollectionType", "Convert2Diamond"})
    private void updateUsers(String users){
       clients = new Vector<Client>();
        for (int start=0, end = users.indexOf(Constant.SEPARATOR); end != -1; start = end + 1, end = users.indexOf(Constant.SEPARATOR, start)){
            clients.add(new Client(users.substring(start, end)));
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
