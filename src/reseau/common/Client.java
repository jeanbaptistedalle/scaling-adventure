package reseau.common;

import java.net.InetAddress;

/**
 * @fn Client
 * @brief Classe décrivant un client
 */
/* Cette classe n'est pas indispensable pour l'instant, mais elle pourra être
utile si on ajoute des éléments descriptifs de chaque client (statut, ...)
*/
public class Client {
    private final InetAddress addr;
    private String pseudo;
    
    /**
     * @fn Client
     * @brief Constructeur de Client
     * @param a_addr adresse du client
     * @param a_pseudo pseudo du client
     */
    public Client(InetAddress a_addr, String a_pseudo){
        this.addr = a_addr;
        this.pseudo = a_pseudo;
    }
    
    /**
     * @fn Client
     * @brief Constructeur de Client
     * @param a_addr l'adresse du Client
     */
    public Client(InetAddress a_addr){
        this.addr = a_addr;
    }
    
    /**
     * @fn Client
     * @brief Constructeur de Client sans adresse
     * @param a_pseudo le pseudo du client
     */
    public Client(String a_pseudo){
        this.addr = null;
        this.pseudo = a_pseudo;
    }
    
    /**
     * @fn toString
     * @brief Exportation du client en chaîne de caractères
     * @return this.client.pseudo
     */
    public String toString(){
        return(this.getPseudo());
    }
        
    /**
     * @fn setPseudo
     * @brief mutateur de this.pseudo
     * @param a_pseudo le nouveau pseudo
     */
    public void setPseudo(String a_pseudo){
        this.pseudo = a_pseudo;
    }
    
    /**
     * @fn getPseudo
     * @brief Accesseur de pseudo 
     * @return this.pseudo
     */
    public String getPseudo(){
        return(this.pseudo);
    }
    
    /**
     * @fn getAddr
     * @brief Accesseur de addr
     * @return this.addr
     */
    public InetAddress getAddr(){
        return(this.addr);
    }
}
