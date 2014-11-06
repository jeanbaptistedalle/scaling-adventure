package reseau.server;

import java.util.Vector;

/**
 * @class WaitList
 * @brief Liste d'attente de client
 */
public class WaitList <T>{
    private Vector<T> list;
    private T current;
    private Server serv;
    
    /**
     * @fn WaitList
     * @brief Constructeur de WaitList
     * @param s le serveur qui contient cette liste
     */
    public WaitList(Server s){
        serv = s;
    }
    
    /**
     * @fn join
     * @brief Ajoute un nouvel élément T à la liste d'attente si il n'y est pas déja
     * @param elem le nouvel élément
     */
    public void join(T elem){
        if (list.isEmpty() && current == null){
            current = elem;
        }
        else if (!list.contains(elem)){
            list.add(elem);
        }
    }
   
    /**
     * @fn leave
     * @brief Lorqu'un client à rendu la main (ou bien il la rend, ou bien il annule sa demande);
     * @param elem 
     */
    public void leave(T elem){
        if (elem == this.current){
            unshift();
        }
        else{
            remove(elem);
        }
    }
    /**
     * @fn unshift
     * @brief Retire le premier élément de la liste et le place dans current
     */
    private void unshift(){
        if (!list.isEmpty()){
            current = list.get(0);
            list.remove(current);
        }
        else{
            current = null;
        }
    }
    
    /**
     * @fn remove
     * @brief Enlève un élément de la liste, quelle que soit sa position
     * @param elem l'élement à retirer 
     */
    private void remove(T elem){
        list.remove(elem);
    }
    
    /**
     * @fn getCurrent
     * @brief Accesseur de current
     * @return this.current
     */
    public T getCurrent(){
        return(this.current);
    }
}
