package reseau.server;

import java.util.Vector;

import reseau.common.Constant;
import reseau.common.Message;
/**
 * @class Room
 * @brief Correspond à un groupe de clients travaillant sur le même dessin
 */
class Room{
    @SuppressWarnings("FieldMayBeFinal")
    private Vector <ClientManager> clients;
    private  final int id;
    @SuppressWarnings("FieldMayBeFinal")
    private WaitList wait;

    /* Document SVG sous forme d'une chaine de caractere. */
    private String image;


    /**
     * @fn Room
     * @srief Constructeur de Room
     * @param number
     */
    @SuppressWarnings("Convert2Diamond")
    public Room(int number){
        this.id = number;
        wait = new WaitList();
        clients = new Vector<ClientManager>();

        image = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<svg xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns=\"http://www.w3.org/2000/svg\" contentScriptType=\"text/ecmascript\" width=\"470\" zoomAndPan=\"magnify\" contentStyleType=\"text/css\" height=\"416\" preserveAspectRatio=\"xMidYMid meet\" version=\"1.0\">" +
                "<text x=\"20\" fill=\"silver\" y=\"20\" font-size=\"12\">Scaling Adventure by JB/K/A/R</text>" +
                "</svg>";
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
        this.leaveWaitList(cli);
        clients.remove(cli);
    }

    /**
     * @fn joinWaitList
     * @brief Ajoute un client à la file d'attente (lui donne la main si il est le premier)
     * @param c le client à ajouter
     */
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public synchronized void joinWaitList(ClientManager c){
        if (wait.join(c)){       // Si il rejoint une file d'attente vide, il a la main
            broadcast(new Message(Constant.SERVER_IP, Constant.command.GIVE_CTRL, c.toString()));
            new SchedulerLeaveCtrl(this, c).start();
        }
    }

    /**
     * @fn leaveWaitList
     * @brief Fait quitter la liste d'attente au client c, donne la main au suivant si besoin
     * @param c le client
     */
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public synchronized void leaveWaitList(ClientManager c){
        if (wait.leave(c)){  // Si le client avait la main
            c.sendMessage(new Message(c.getAddr(), Constant.command.UPDATE, this.image));
            if (!wait.isEmpty()){   // Si un nouveau client reçoit la main
                new SchedulerLeaveCtrl(this, c).start();
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
            res += clients.get(i).toString() + Constant.SEPARATOR;
        }
        if (!clients.isEmpty())
            res += clients.get(clients.size() - 1).toString();
        
        return(res);
    }


    /**
     * @fn getWaitList
     * @brief Accesseur de la liste d'attente des clients
     * @return liste d'attente des clients
     */
    public WaitList getWaitList(){
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
    @Override
    public String toString(){
        return(Integer.toString(this.id));
    }

    public boolean equals(int i){
        return(this.id == i);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

}
