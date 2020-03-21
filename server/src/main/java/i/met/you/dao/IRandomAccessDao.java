package i.met.you.dao;

import i.met.you.model.IBaseModel;



/**
 * Irhato-olvashato DAO interface specifikacioja
 * @author Csaba
 *
 * @param <T>
 */
public interface IRandomAccessDao<T extends IBaseModel> extends IReadOnlyDao<T> {

	/**
	 * Entitás létrehozása
	 * @param entity
	 * @return
	 */
	T create(T entity);

	/**
	 * Entitás frissítése
	 * @param entity
	 * @return
	 */
	T update(T entity);

	/**
	 * Entitás logikai törlése id alapján
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * Entitás logikai törlése entitás alapján
	 * @param entity
	 */
	void delete(T entity);
	
	
	/**
	 * Entitás logikai törlésének visszaállítása id alapján
	 * @param id
	 */
	void undelete(String id);
	
	/**
	 * Entitás logikai törlésének visszaállítása entitás alapján
	 * @param entity
	 */
	void undelete(T entity);
	
	/**
	 * Entitás fizikai törlése id alapján
	 * @param id
	 */
	void remove(String id);
	
	
	/**
	 * Entitás fizikai törlése entitás alapján
	 * @param entity
	 */
	void remove(T entity);

	/**
	 * Entitás mentése. Ha nem rendelkezik id-val akkor create ellenkező esetben update metódus fut rá
	 * @param entity
	 * @return
	 */
	T save(T entity);
}
