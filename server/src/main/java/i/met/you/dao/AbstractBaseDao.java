package i.met.you.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.logging.log4j.Logger;

import i.met.you.ds.ApplicationDataSource;
import i.met.you.util.ApplicationLogger;

/**
 * Alapertelmezett csak olvasasi uzemmodot tamogato DAO
 * 
 * @author AppGen
 *
 * @param <T> Entitas tipus
 */
public abstract class AbstractBaseDao<T> implements IReadOnlyDao<T> {

	private static final Logger LOGGER = ApplicationLogger.getLogger(AbstractBaseDao.class);

	private BasicDataSource dataSource = null;
	
	private ApplicationDataSource ds =null;

	// private String tableName = "";

	private BasicRowProcessor rowProcessor;

	private Class<T> type;

	@Override
	public BasicDataSource getDataSource() {
		return this.dataSource;
	}
	
	@Override
	public ApplicationDataSource getApplicationDataSource() {
		return this.ds;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected AbstractBaseDao() {
		this.ds = ApplicationDataSource.getInstance();
		this.dataSource = ds.getBasicDataSource();
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public T read(String id) {		
		T entity = null;
		String query = "SELECT * FROM " + getTableName() + "  WHERE id=? ";
		QueryRunner run = new QueryRunner(dataSource);
		ResultSetHandler<T> h;
		if (this.rowProcessor != null) {
			h = new BeanHandler<T>(type, rowProcessor);
		} else {
			h = new BeanHandler<T>(type);
		}
		try {
			LOGGER.trace("query: {} with param: {}", query, id);
			entity = run.query(query, h, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
		
		
		/*
		T entity = null;
		Connection conn = null;
		ResultSet rs =null;
		PreparedStatement ps = null;
		try {
			String query = "SELECT * FROM " + getTableName() + "  WHERE id=? ";
			
			conn = this.getApplicationDataSource().getConnection();
			System.out.println(ds.toString());
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();		
			rs.next();
			entity =  rowProcessor.toBean(rs, type);			
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		
		return entity;*/
	}

	@Override
	public BasicRowProcessor getRowProcessor() {
		return this.rowProcessor;
	}

	public void setRowProcessor(BasicRowProcessor rowProcessor) {
		this.rowProcessor = rowProcessor;

	}

	@Override
	public abstract String getTableName();

	@Override
	public Long queryCount(String queryStr) {
		if (queryStr == null) {
			throw new IllegalArgumentException("queryStr parameter nem lehet null");
		}
		if (queryStr.indexOf("FROM") < 0) {
			throw new IllegalArgumentException("a query parameter nem tartalmaz \"from\" kulcsszot");
		}
		queryStr = "SELECT COUNT(*) ".concat(queryStr.substring(queryStr.indexOf("FROM")));
		queryStr = queryStr.toUpperCase().substring(0, queryStr.indexOf("ORDER BY"));
		LOGGER.trace(queryStr);
		try (Connection conn = getDataSource().getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(queryStr)) {
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return rs.getLong(1);
					
				}
				rs.close();
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public Long queryCount(String queryStr, List<Object> params) {
		if (queryStr == null) {
			throw new IllegalArgumentException("queryStr parameter nem lehet null");
		}
		if (queryStr.indexOf("FROM") < 0) {
			throw new IllegalArgumentException("a query parameter nem tartalmaz \"from\" kulcsszot");
		}
		String statement = "SELECT COUNT(*) ";
		if (queryStr.toUpperCase().indexOf("ORDER BY") > -1) {
			statement = statement
					.concat(queryStr.substring(queryStr.indexOf("FROM"), queryStr.toUpperCase().indexOf("ORDER BY")));
		} else {
			statement = statement.concat(queryStr.substring(queryStr.indexOf("FROM")));
		}
		try (Connection conn = getDataSource().getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(statement)) {
				if (params != null) {
					for (int i = 0; i < params.size(); ++i) {
						if (params.get(i) instanceof Date) {
							ps.setDate(i + 1, new java.sql.Date(((Date) params.get(i)).getTime()));
							if (LOGGER.isTraceEnabled()) {
								LOGGER.trace("with date param ({}) : {}", i + 1, ((Date) params.get(i)).toString());
							}
						} else if (params.get(i) instanceof Number) {
							ps.setBigDecimal(i + 1, new BigDecimal(params.get(i).toString()));
							LOGGER.trace("with param ({}): {}", i + 1, params.get(i));
						} else {
							ps.setObject(i + 1, params.get(i));
							LOGGER.trace("with param ({}): {}", i + 1, params.get(i));
						}
					}
				}
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return rs.getLong(1);
				}
				rs.close();
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<T> findAll(String query, List<Object> params, Integer offset, Integer limit) {
		if (query == null) {
			throw new IllegalArgumentException("queryStr parameter nem lehet null");
		}
		if (query.toUpperCase().indexOf("FROM") < 0) {
			throw new IllegalArgumentException("a query parameter nem tartalmaz \"from\" kulcsszot");
		}
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ");
		queryBuilder.append(query.substring(query.toUpperCase().indexOf("FROM") + "FROM".length()));
		if (offset != null && offset > 0) {
			queryBuilder.append(" OFFSET ").append(offset);
		}
		if (limit != null && limit > 0) {
			queryBuilder.append(" LIMIT ").append(limit);
		}

		LOGGER.trace(queryBuilder.toString());
		try (Connection conn = getDataSource().getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(queryBuilder.toString())) {
				int j = 1;
				for (int i = 0; i < params.size(); ++i) {
					j = i + 1;
					if (params.get(i) instanceof Date) {
						ps.setDate(j, new java.sql.Date(((Date) params.get(i)).getTime()));
						if (LOGGER.isTraceEnabled()) {
							LOGGER.trace("with date param ({}) : {}", j, ((Date) params.get(i)).toString());
						}
					} else {
						ps.setObject(j, params.get(i));
						LOGGER.trace("with param ({}): {}", j, params.get(i));
					}
				}
				ResultSet rs = ps.executeQuery();
				List<T> returnList =  rowProcessor.toBeanList(rs, type);
				rs.close();
				ps.close();
				conn.close();				
				return returnList;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<T> findAll() {
		String queryString = "SELECT * FROM "+getTableName() + " WHERE deleted_10 = 0";
		LOGGER.trace(queryString);
		try (Connection conn = getDataSource().getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(queryString)) {
				ResultSet rs = ps.executeQuery();				
				List<T> returnList =  rowProcessor.toBeanList(rs, type);
				rs.close();
				ps.close();
				conn.close();				
				return returnList;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<T> findAllWithoutDeleteParam() {
		String queryString = "SELECT * FROM ".concat(getTableName());
		LOGGER.trace(queryString);
		try (Connection conn = getDataSource().getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(queryString)) {
				ResultSet rs = ps.executeQuery();
				List<T> returnList =  rowProcessor.toBeanList(rs, type);
				rs.close();
				ps.close();
				conn.close();				
				return returnList;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<T> findAll(Integer offset, Integer limit) {
		return findAll("FROM".concat(getTableName()), offset, limit);
	}

	@Override
	public List<T> findAll(String query, Integer offset, Integer limit) {
		if (query == null) {
			throw new IllegalArgumentException("queryStr parameter nem lehet null");
		}
		if (query.toUpperCase().indexOf("FROM") < 0) {
			throw new IllegalArgumentException("a query parameter nem tartalmaz \"from\" kulcsszot");
		}
		StringBuilder queryString = new StringBuilder("SELECT * FROM ");
		queryString.append(query.substring(query.toUpperCase().indexOf("FROM") + "FROM".length()));
		if (offset != null && offset > 0) {
			queryString.append(" OFFSET ").append(offset);
		}
		if (limit != null && limit > 0) {
			queryString.append(" LIMIT ").append(limit);
		}

		try (Connection conn = getDataSource().getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(queryString.toString())) {
				ResultSet rs = ps.executeQuery();				
				List<T> returnList =  rowProcessor.toBeanList(rs, type);
				rs.close();
				ps.close();
				conn.close();				
				return returnList;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<T> seek(Integer limit) {
		StringBuilder queryString = new StringBuilder("SELECT * FROM ");
		if (limit != null && limit >= 0) {
			queryString.append(" LIMIT ").append(limit);
		}
		try (Connection conn = getDataSource().getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(queryString.toString())) {
				ResultSet rs = ps.executeQuery();
				List<T> returnList =  rowProcessor.toBeanList(rs, type);
				rs.close();
				ps.close();
				conn.close();				
				return returnList;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<T> seek(String query, Integer limit) {
		return findAll(query, null, limit);
	}
}
