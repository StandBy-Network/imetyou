package i.met.you.dao;

import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.BasicRowProcessor;

import i.met.you.ds.ApplicationDataSource;

/**
 * Csak olvashato DAO interface specifikacioja
 * 
 * @author AppGen
 *
 * @param <T>
 */
public interface IReadOnlyDao<T> {

	BasicRowProcessor getRowProcessor();

	String getTableName();

	BasicDataSource getDataSource();

	/**
	 * Entitás olvasása id alapján
	 * 
	 * @param id
	 * @return
	 */
	T read(String id);

	/**
	 * A paraméterben átadott query elemszámával tér vissza
	 * 
	 * Nem biztonsagos, SQL injection tamadast lehet vegrehajtani
	 *             vele
	 * @param queryStr
	 * @return
	 */
	
	Long queryCount(String queryStr);

	/**
	 * A paraméterben átadott query elemszámával tér vissza
	 * 
	 * @param query
	 *            PreparedStatement-nek megfelelo sql lekerdezes
	 * @param params
	 *            behelyettesitendo parameterek
	 * @return
	 */
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
	 * Egy entitás listát ad vissza a megadott számtól elemszámig
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<T> findAll(Integer offset, Integer limit);

	/**
	 * Egy a lekérdezésnek megfelelő entitás listát ad vissza a megadott számtól
	 * elemszámig
	 * 
	 *Nem biztonsagos, SQL injection tamadast lehet vegrehajtani
	 *             vele
	 * @param query
	 * @param offset
	 * @param limit
	 * @return
	 */
	
	List<T> findAll(String query, Integer offset, Integer limit);

	/**
	 * Egy a lekérdezésnek megfelelő entitás listát ad vissza a megadott számtól
	 * elemszámig
	 * 
	 * @param query
	 *            PreparedStatement-nek megfelelo sql lekerdezes
	 * @param params
	 *            behelyettesitendo parameterek
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<T> findAll(String query, List<Object> params, Integer offset, Integer limit);

	List<T> seek(Integer limit);

	List<T> seek(String query, Integer limit);

	void delete(String id);

	ApplicationDataSource getApplicationDataSource();

}
