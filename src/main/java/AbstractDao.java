

import java.util.ArrayList;

/**
 * 
 * @author Melissa
 *
 * @param <T> type de forme
 */
public abstract class AbstractDao<T> {
	/**
	 * ajoouter un élement au dao
	 * @param object element a ajouter
	 * @return création
	 */
	public abstract T create (T object);
	/**
	 * trouver l'element 
	 * @param id identifiant de l'element
	 * @return l'element rechrché
	 */
	public abstract T find (String id);
	/**
	 * recuperer tousles lement
	 * @return tous les element
	 */
	public abstract ArrayList<T> findAll();
	/**
	 * modifier l'element du dao
	 * @param object element a modifier
	 * @return la modification
	 */
	public abstract T update(T object);
	/**
	 * supprimer un élément du dao
	 * @param object element a supprimer
	 */
	public abstract void delete(T object);
	

}
