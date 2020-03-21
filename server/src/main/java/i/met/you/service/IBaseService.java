package i.met.you.service;

import java.util.List;

import i.met.you.model.IBaseModel;



/**
 * CRUD szolgaltatasok interface specifikacioja
 * 
 * @author AppGen
 *
 * @param <T>
 *            Entitas tipusa
 */
public interface IBaseService<T extends IBaseModel> {

	/**
	 * Entitás létrehozása
	 * 
	 * @param entity
	 * @return
	 */
	T create(T entity);

	/**
	 * Entitás olvasása id alapján
	 * 
	 * @param id
	 * @return
	 */
	T read(String id);

	/**
	 * Entitás frissítése
	 * 
	 * @param entity
	 * @return
	 */
	T update(T entity);

	/**
	 * Entitás logikai törlése id alapján
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * Entitás logikai törlése entitás alapján
	 * 
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * Entitás logikai törlésének visszaállítása id alapján
	 * 
	 * @param id
	 */
	void undelete(String id);

	/**
	 * Entitás logikai törlésének visszaállítása entitás alapján
	 * 
	 * @param entity
	 */
	void undelete(T entity);

	/**
	 * Entitás fizikai törlése id alapján
	 * 
	 * @param id
	 */
	void remove(String id);

	/**
	 * Entitás fizikai törlése entitás alapján
	 * 
	 * @param entity
	 */
	void remove(T entity);

	/**
	 * Entitás mentése. Ha nem rendelkezik id-val akkor create ellenkező esetben
	 * update metódus fut rá
	 * 
	 * @param entity
	 * @return
	 */
	T save(T entity);

	/**
	 * A paraméterben átadott query elemszámával tér vissza
	 * 
	 * @param queryStr
	 * @return
	 */
	Long queryCount(String queryStr);

	/**
	 * Az Entitás típus összes elemét visszaadja
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * Egy a lekérdezésnek megfelelő entitás listát ad vissza a megadott számtól
	 * elemszámig
	 * 
	 * @param query
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<T> findAll(String query, Integer offset, Integer limit);

	List<T> seek(String query, Integer limit);



}
