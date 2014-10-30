package reseau;

/**
 * @class Constant
 * 
 * regroupe les différentes constantes employées dans la partie réseau du projet
 */
public class Constant {
    public static int NB_ROOM = 10; 
    /**
     * Les différents types de messages
     * Une valeur fixe leur est donnée afin d'éviter tout changement à la compilation
     */
    public enum command{
        CONNECT(0),
        DISCONNECT(1),
        ACCEPT(2),
        DENY(3),
        REQUIRE_CTRL(4),
        GIVE_CTRL(5),
        LEAVE_CTRL(6),
        SUBMIT(7),
        GET_USERS(8),
        LIST_USERS(9);
        
        private final int value;
        
        private command(int value){
            this.value = value;
        }
        
        public int getValue(){
            return(value);
        }
    }
    
    /**
     * @fn getCommand
     * @brief Retourne la commande correspondant à l'entier passé en argument
     * @param val
     * @return Constant.command correspondant
     */
    public static command getCommand(int val){
        for (command i : Constant.command.values())
            if (i.getValue() == val){
                return(i);
            }
        return(null);
    }
}