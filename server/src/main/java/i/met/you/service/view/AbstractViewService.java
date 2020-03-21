package i.met.you.service.view;

import java.util.List;

import i.met.you.dao.IReadOnlyDao;
import i.met.you.model.view.IViewModel;

public class AbstractViewService<T extends IViewModel, DAO_TYPE extends IReadOnlyDao<T>> implements IViewService<T> {

	private DAO_TYPE dao = null;

	/**
	 * Konstruktor
	 * 
	 * @param dao Dao osztalypeldany
	 */
	protected AbstractViewService(DAO_TYPE dao) {
		this.dao = dao;
	}

	@Override
	public Long queryCount(String queryStr) {
		return dao.queryCount(queryStr);
	}

	@Override
	public Long queryCount(String queryStr, List<Object> params) {
		return dao.queryCount(queryStr, params);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	@Override
	public List<T> findAllWithoutDeleteParam() {
		return dao.findAllWithoutDeleteParam();
	}

	@Override
	public List<T> findAll(String query, Integer offset, Integer limit) {
		return dao.findAll(query, offset, limit);
	}

	public List<T> findAll(String query, List<Object> params, Integer offset, Integer limit) {
		return dao.findAll(query, params, offset, limit);
	}

	@Override
	public List<T> seek(String query, Integer limit) {
		return dao.seek(query, limit);
	}

	@Override
	public T read(String id) {
		return dao.read(id);
	}

	protected DAO_TYPE getDao() {
		return dao;
	}
}
