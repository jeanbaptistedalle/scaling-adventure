package reseau.common;

/**
 * @class Constant
 *
 * regroupe les différentes constantes employées dans la partie réseau du projet
 */
public class Constant {
    public static final int  NB_ROOM     = 10;
    public static final byte SERVER_IP[] = { 0, 0, 0, 0 };    // L'adresse IP lorsque server est l'expéditeur
    public static final char SEPARATOR   = ',';
    public static final int  PORT        = 6666;

    /**
     * Les différents types de messages
     * Une valeur fixe leur est donnée afin d'éviter tout changement à la compilation
     */
    public enum command {
        CONNECT(0),                                           // Connexion
        DISCONNECT(1),                                        // Déconnexion
        ACCEPT(2),                                            // Validation
        DENY(3),                                              // Refus
        REQUEST_CTRL(4),                                      // Demander de prendre la main
        GIVE_CTRL(5),                                         // Donner la main
        LEAVE_CTRL(6),                                        // Laisser la main
        SUBMIT(7),                                            // Envoi de modifications
        GET_USERS(8),                                         // Demande de la liste des utilisateurs connectés
        LIST_USERS(9),                                        // Envoi de la liste des utilisateurs connectés
        LIST_ROOMS(10),                                       // Envoi de la liste des rooms disponibles
        JOIN_ROOM(11),                                        // Rejoindre une room
        UPDATE(12);                                           // Envoi de la nouvelle image

        private final int value;

        private command(int value) {
            this.value = value;
        }

        public int getValue() {
            return (value);
        }
    }

    /**
     * @fn getCommand
     * @brief Retourne la commande correspondant à l'entier passé en argument
     * @param val
     * @return Constant.command correspondant
     */
    public static command getCommand(int val) {
        for (command i : Constant.command.values()) {
            if (i.getValue() == val) {
                return (i);
            }
        }

        return (null);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
