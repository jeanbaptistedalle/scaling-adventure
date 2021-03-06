package reseau.server;

//~--- JDK imports ------------------------------------------------------------

import java.util.Vector;

/**
 * @class WaitList
 * @brief Liste d'attente de client
 */
public class WaitList {
    @SuppressWarnings("FieldMayBeFinal")
    private Vector<ClientManager> list;

    /**
     * @fn WaitList
     * @brief Constructeur de WaitList
     */
    public WaitList() {
        list = new Vector<>();
    }

    /**
     * @fn join
     * @brief Ajoute un nouvel élément T à la liste d'attente si il n'y est pas déja
     * @param elem le nouvel élément
     * @return true si l'élément est ajouté à une liste vide (donc élément courant actualisé), false sinon
     */
    public boolean join(ClientManager elem) {
        boolean res = list.isEmpty();

        list.add(elem);

        return (res);
    }

    /**
     * @fn leave
     * @brief Lorqu'un client à rendu la main (ou bien il la rend, ou bien il annule sa demande)
     * @param elem l'élément à retirer
     * @return true si l'élément qui a la main est actualisé, false sinon
     */
    public boolean leave(ClientManager elem) {
        if (!list.isEmpty() && (elem == getCurrent())) {
            unshift();

            return (true);
        } else {
            remove(elem);

            return (false);
        }
    }

    /**
     * @fn unshift
     * @brief Retire le premier élément de la liste
     */
    private void unshift() {
        if (!list.isEmpty()) {
            ClientManager first = getCurrent();

            list.remove(first);
        }
    }

    /**
     * @fn remove
     * @brief Enlève un élément de la liste, quelle que soit sa position
     * @param elem l'élement à retirer
     */
    private void remove(ClientManager elem) {
        list.remove(elem);
    }

    /**
     * @fn getCurrent
     * @brief Accesseur de l'élément courant, c'est-à-dire le premier de la liste (ici, client qui a la main)
     * @return list.get(0)
     */
    public ClientManager getCurrent() {
        if (!list.isEmpty()) {
            return (list.firstElement());
        } else {
            return (null);
        }
    }

    /**
     * @fn isEmpty
     * @brief Indique si un client a la main ou non
     * @return true si un clien a la main, false sinon
     */
    public boolean isEmpty() {
        return (list.isEmpty());
    }

    /**
     * @fn isCurrent
     * @brief Indique si un élément est l'élément courant ou non
     * @param elem l'élement à tester
     * @return true si c'est l'élément courant, false sinon
     */
    public boolean isCurrent(ClientManager elem) {
        if (list.isEmpty()) {
            return (false);
        }

        return (elem == getCurrent());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
