package i.met.you.service;

import java.util.List;

import i.met.you.dao.IRandomAccessDao;
import i.met.you.model.AbstractBaseModel;


/**
 * Alapertelmezett service osztaly szolgaltatasok implementalasara
 * 
 * @author AppGen
 *
 * @param <T>
 *            entitas tipus
 * @param <DAO_TYPE>
 *            Entitas tipus DAO osztaly tipusa
 */
public abstract class AbstractBaseService<T extends AbstractBaseModel, DAO_TYPE extends IRandomAccessDao<T>>
		implements IBaseService<T> {

	protected DAO_TYPE dao = null;

	protected AbstractBaseService(DAO_TYPE dao) {
		this.dao = dao;
	}

	@Override
	public T create(T entity) {
		return dao.create(entity);
	}

	@Override
	public T read(String id) {
		return dao.read(id);
	}

	@Override
	public T update(T entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public void delete(T entity) {
		dao.delete(entity);
	}

	@Override
	public void undelete(String id) {
		dao.undelete(id);
	}

	@Override
	public void undelete(T entity) {
		dao.undelete(entity);
	}

	@Override
	public void remove(String id) {
		dao.remove(id);
	}

	@Override
	public void remove(T entity) {
		dao.remove(entity);
	}

	@Override
	public T save(T entity) {
		return dao.save(entity);
	}

	@Override
	public Long queryCount(String queryStr) {
		return dao.queryCount(queryStr);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	@Override
	public List<T> findAll(String query, Integer offset, Integer limit) {
		return dao.findAll(query, offset, limit);
	}

	@Override
	public List<T> seek(String query, Integer limit) {
		return dao.seek(query, limit);
	}

	protected DAO_TYPE getDao() {
		return dao;
	}
}
