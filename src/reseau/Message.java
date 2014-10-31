package reseau;

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @class Message
 * @brief Message qui sera transmis entre clients et serveur
 */
public class Message {
    private InetAddress from;
    private Constant.command cmd;
    private String content;
    
    /**
     * @fn Message
     * @brief Constructeur de Message d'après son contenu
     * @param a_from l'expéditeur
     * @param a_cmd la commande
     * @param a_content le contenu
     */
    public Message(InetAddress a_from, Constant.command a_cmd, String a_content){
        from = a_from;
        cmd = a_cmd;
        content = a_content;
    }
    
    /**
     * @fn Message
     * @brief Constructeur de Message d'après une commande
     * @param a_from l'expéditeur
     * @param a_cmd la commande
     */
    public Message(InetAddress a_from, Constant.command a_cmd){
        this(a_from, a_cmd, "");
    }
        
    /**
     * @fn Message
     * @brief Constructeur de Message d'après son contenu
     * @param a_from l'IP de l'expéditeur (tabeau d'octets)
     * @param a_cmd la commande
     * @param a_content le contenu
     */
    public Message(byte[] a_from, Constant.command a_cmd, String a_content){
        try {
            from = InetAddress.getByAddress(a_from);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmd = a_cmd;
        content = a_content;
    }
    
    /**
     * @fn Message
     * @brief Constructeur de Message d'après une commande
     * @param a_from l'IP de l'expéditeur (tabeau d'octets)
     * @param a_cmd la commande
     */
    public Message(byte[] a_from, Constant.command a_cmd){
        this(a_from, a_cmd, "");
    }
    
    /**
     * @fn Message
     * @brief constructeur de Message d'après un tableau d'octets
     * @param b_Array le tableau d'octets
     */
    public Message(byte[] b_Array) throws UnknownHostException{
        byte[] b_addr = new byte[4];
        byte[] b_cmd = new byte[4];
        byte[] b_content_size = new byte[4];
        
        System.arraycopy(b_Array, 0, b_addr, 0, 4);
        System.arraycopy(b_Array, 4, b_cmd, 0, 4);
        System.arraycopy(b_Array, 8, b_content_size, 0, 4);
        
        from = InetAddress.getByAddress(b_addr);
        cmd = Constant.getCommand(byteArrayToInt(b_cmd));
        int content_size = byteArrayToInt(b_content_size);
        char[] c_content = new char[content_size];
        System.arraycopy(b_Array, 12, c_content, 0, content_size);
        content = String.copyValueOf(c_content);
    }
    
    /**
     * @fn toByteArray
     * @brief exporte le message sous la forme d'un tableau de bytes, pouvant être lu depuis n'importe quel langage
     * @return tableau de bytes
     */
    public byte[] toByteArray(){
        byte[] result = new byte[getContent().length() + 12];
        byte[] b_addr = from.getAddress();                              // (octets 0-3)     : l'adresse de l'expéditeur
        byte[] b_cmd = intToByteArray(getCmd().getValue());                  // (octets 4-7)     : le type de commande
        byte[] b_content = getContent().getBytes();                          // (octets 12-fin)  : le contenu
        byte[] b_content_size = intToByteArray(b_content.length);       // (octets 8-11)    : la taille du contenu
        System.arraycopy(b_addr, 0, result, 0, 4);
        System.arraycopy(b_cmd, 0, result, 4, 4);
        System.arraycopy(b_content_size, 0, result, 8, 4);
        System.arraycopy(b_content, 0, result, 12, b_content.length);
        
        return(result);
    }
    
    /**
     * @fn intToByteArray
     * @brief transforme un int en tableau de byte
     * @param input l'entier à transformer
     * @return tableau de byte
     */
    public byte[] intToByteArray(int input){
        return(new byte[] {
            (byte)(input >>> 24),
            (byte)(input >>> 16),
            (byte)(input >>> 8),
            (byte)(input)
        });
    }
    
    /**
     * @fn byteArrayToInt
     * @brief transforme un tableau de 4 octets en int
     * @param input le tableau d'octets à convertir
     * @return l'entier correspondant
     */
    public final int byteArrayToInt(byte[] input){
        // on préfère multiplier qu'agir directement sur les bits
        // En effet, on ne peut pas appliquer un décalage suffisant sur les octets (overflow)
        return(input[0] * 16777216 + input[1] * 65535 + input[2] * 255 + input[3]);
    }

    /**
     * @fn getCmd
     * @brief getter de cmd
     * @return the cmd
     */
    public Constant.command getCmd() {
        return cmd;
    }

    /**
     * @fn getContent
     * @brief getter de content
     * @return the content
     */
    public String getContent() {
        return content;
    }
}
