package reseau.server;

import java.util.Vector;

/**
 * @class WaitList
 * @brief Liste d'attente de client
 */
public class WaitList <T>{
    private Vector<T> list;
    
    /**
     * @fn WaitList
     * @brief Constructeur de WaitList
     */
    public WaitList(){
    }
    
    /**
     * @fn push
     * @brief Ajoute un nouvel élément T à la liste d'attente si il n'y est pas déja
     * @param elem le nouvel élément
     */
    public void push(T elem){
        if (!list.contains(elem)){
            list.add(elem);
        }
    }
    
    /**
     * @fn unshift
     * @brief Retire le premier élément de la liste et le retourne
     * @return le premier élément de list, null si list est vide
     */
    public T unshift(){
        if (list.size() == 0){
            return(null);
        }
        T res = list.get(0);
        list.remove(res);
        return(res);
    }
    
    /**
     * @fn remove
     * @brief Enlève un élément de la liste, quelle que soit sa position
     * @param elem l'élement à retirer
     */
    public void remove(T elem){
        list.remove(elem);
    }
}
