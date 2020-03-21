package i.met.you.service.view;

import java.util.List;

import i.met.you.model.view.IViewModel;

/**
 * Nezet tabla service oldali interface specifikacioja
 * 
 * @author AppGen
 *
 * @param <T> View entitas tipus
 */
public interface IViewService<T extends IViewModel> {
	/**
	 * A paraméterben átadott query elemszámával tér vissza
	 * 
	 * nem sql injection biztos
	 * 
	 * @param queryStr
	 * @return
	 */

	Long queryCount(String queryStr);

	Long queryCount(String queryStr, List<Object> params);

	/**
	 * Az Entitás típus összes elemét visszaadja
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * Az Entitás típus összes elemét visszaadja, törölt állapot vizsgálata nélkül
	 * 
	 * @return
	 */
	List<T> findAllWithoutDeleteParam();

	/**
	 * Egy a lekérdezésnek megfelelő entitás listát ad vissza a megadott számtól
	 * elemszámig
	 * 
	 * @param query
	 * @param offset
	 * @param limit  nem sql injection biztos
	 * @return
	 */

	List<T> findAll(String query, Integer offset, Integer limit);

	List<T> findAll(String query, List<Object> params, Integer offset, Integer limit);

	List<T> seek(String query, Integer limit);

	T read(String id);
}
