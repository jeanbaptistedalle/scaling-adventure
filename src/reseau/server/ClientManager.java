package reseau.server;

//~--- non-JDK imports --------------------------------------------------------

import reseau.common.ClientRC;
import reseau.common.Constant;
import reseau.common.Message;

//~--- JDK imports ------------------------------------------------------------

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @class ClientManager
 * @brief Gestionnaire de client, qui communiquera avec un client spécifique
 */
class ClientManager extends Thread {
    private long             startTime = -1, endTime, duration;
    private final Server     server;
    private Room             room;
    private Socket           sock;
    private DataInputStream  in;
    private DataOutputStream out;
    private ClientRC         client;

    public ClientManager(Socket client_sock, Server server) {
        this.server = server;
        this.sock   = client_sock;
        this.client = new ClientRC(sock.getInetAddress());

        try {
            this.in  = new DataInputStream(sock.getInputStream());
            this.out = new DataOutputStream(sock.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("override")
    public void run() {
        boolean cont      = false;
        String  my_pseudo = null;
        int     my_room;

        /**
         * Quand un nouveau client arrive on lui demande un pseudo.
         * Lorsqu'il est connecté avec un pseudo il doit choisir une room pour dessiner.
         * Un fois connecté à une room il peut dessiner (Avec le protocole de dessin mis en place).
         */

        /* Le client choisit son pseudo */
        do {
            Message msg = recvMessage();

            switch (msg.getCmd()) {
            case DISCONNECT :
                server.rmClient(this);

                try {
                    in.close();
                    out.close();
                    sock.close();
                } catch (IOException ex) {
                    Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("¤ Client leaved before complete connection.");

                return;

            case CONNECT :
                my_pseudo = msg.getContent();
                cont      = false;

                // On teste que le pseudo soit unique
                for (ClientManager cli : server.getClients()) {
                    if (cli.getPseudo() != null) {
                        if (my_pseudo.equals(cli.getPseudo())) {
                            cont = true;
                        }
                    }
                }

                sendMessage((cont)
                            ? Constant.command.DENY
                            : Constant.command.ACCEPT);

                break;

            default :
                break;
            }
        } while (cont);

        this.client.setPseudo(my_pseudo);
        System.out.println("¤ Client " + this.getPseudo() + " joined");
        server.addClient(this);

        /* Puis il choisit sa Room */

        /*
         *  sendMessage(Constant.command.LIST_ROOMS, server.getRoomList());
         *
         * do{
         *   my_room = recvMessage().getContent();
         *   cont = false;
         *   // On vérifie que la Room existe
         *   for (Room r : server.getRooms()){
         *       if (my_room.equals(r.toString())){
         *           cont = false;
         *       }
         *   }
         *   sendMessage((cont)?Constant.command.DENY:Constant.command.ACCEPT);
         * }while (cont);
         */
        my_room   = 0;
        this.room = server.getRoomById(my_room);

        /* Laisse le temps à l'interface de charger correctement. */
        /* Voir pour faire envoyer un message à l'interface quand elle est prete. */
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.room.addClient(this);

        /*
         *  Envoi du SVG stocké sur le serveur uand le client se connecte.
         * Au départ de la room, SVG minimal.
         */
        this.sendMessage(Constant.command.UPDATE, this.room.getImage());
        cont = true;

        while (cont) {
            Message msg = recvMessage();

            switch (msg.getCmd()) {
            case GET_USERS :
                sendMessage(Constant.command.LIST_USERS, room.getClientList());

                break;

            case REQUEST_CTRL :
                room.joinWaitList(this);

                break;

            case LEAVE_CTRL :

                /* On ne se sert pas du LEAVE_CTRL envoyé par l'utilisateur. C'est le serveur qui s'occupe du controle. */
                break;

            case SUBMIT :
                if (room.getWaitList().isCurrent(this)) {

                    /* Mettre l'image dans la room */
                    room.setImage(msg.getContent());
                    room.broadcast(new Message(msg.getFrom(), Constant.command.UPDATE, msg.getContent()));
                }

                break;

            case DISCONNECT :
                cont = false;
                room.rmClient(this);
                server.rmClient(this);
                room.broadcast(new Message(msg.getFrom(), Constant.command.LIST_USERS, room.getClientList()));

                break;

            default :
                cont = false;
                room.rmClient(this);
                server.rmClient(this);
                room.broadcast(new Message(msg.getFrom(), Constant.command.LIST_USERS, room.getClientList()));

                break;
            }

            /* Le timer est dans une boucle avec une action bloquante. Il faut le mettre dans un Thread à part. */
            if (startTime != -1) {
                endTime  = System.nanoTime();
                duration = (endTime - startTime);

                // System.out.println(" :: " + (duration / 1000000000));
                if ((duration / 1000000000) > 30) {

                    /* Retirer le controle au bout de 30 secondes. */
                    this.startTime = -1;
                    this.sendMessage(Constant.command.LEAVE_CTRL);
                    room.leaveWaitList(this);
                }
            }
        }

        /* Lorsqu'on est ici, le client à demandé la déconnexion */
        try {
            in.close();
            out.close();
            sock.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("¤ Client " + this.getPseudo() + " leaved");
    }

    public void takeControl() {
        startTime = System.nanoTime();
    }

    /**
     * @fn toString
     * @brief Renvoie la chaîne de caractère correspondant au client (en l'occurence le pseudo)
     * @return le pseudo du client
     */
    @Override
    public String toString() {
        return (this.client.toString());
    }

    /**
     * @fn sendMessage
     * @brief Crée un Message et l'envoie au client
     * @param cmd la commande
     * @param content le contenu
     */
    @SuppressWarnings("ImplicitArrayToString")
    private void sendMessage(Constant.command cmd, String content) {
        try {
            out.write(new Message(Constant.SERVER_IP, cmd, content).toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @fn sendMessage
     * @brief Crée un Message et l'envoie au client
     * @param cmd la commande
     */
    @SuppressWarnings("ImplicitArrayToString")
    private void sendMessage(Constant.command cmd) {
        try {
            out.write(new Message(Constant.SERVER_IP, cmd).toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @fn sendMessage
     * @brief Envoie un Message au client
     * @param msg le Message à envoyer
     */
    public void sendMessage(Message msg) {
        try {
            out.write(msg.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @fn recvMessage
     * @brief lit un Message sur l'entrée "in" et le retourne
     * @return le Message lu
     */
    private Message recvMessage() {
        try {
            return (new Message(in));
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (null);
    }

    /**
     * @fn getPseudo
     * @brief Accesseur de client.pseudo
     * @return client.pseudo
     */
    public String getPseudo() {
        return (this.client.getPseudo());
    }

    /**
     * @fn getAddr
     * @brief Accesseur de client.addr
     * @return client.addr
     */
    public InetAddress getAddr() {
        return (this.client.getAddr());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
