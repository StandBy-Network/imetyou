package i.met.you.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

import i.met.you.exception.LockingException;
import i.met.you.model.IBaseModel;
import i.met.you.util.ApplicationLogger;




/**
 * Irhato-olvashato DAO ososztaly
 * @author AppGen
 *
 * @param <T> Entitas tipus
 */
public abstract class AbstractRandomAccessDao<T extends IBaseModel> extends AbstractBaseDao<T> implements IRandomAccessDao<T> {
	
	static Logger  LOGGER = ApplicationLogger.getLogger(AbstractRandomAccessDao.class);
	
	
	protected AbstractRandomAccessDao() {
	}

	@Override
	public T create(T entity) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getDataSource().getConnection();
			conn.setAutoCommit(false);
			ps = entity.createInsertStatement(conn);
			if (ps != null && ps.executeUpdate() > 0) {
				conn.commit();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (null != generatedKeys && generatedKeys.next()) {
					String id = generatedKeys.getString(1);
					entity = this.read(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
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
		return entity;
	}

	@Override
	public T update(T entity) throws LockingException {
		int entityVersion = entity.getVersion();
		if(this.read(entity.getId()).getVersion() > entityVersion) {
			throw new LockingException();
		} else {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				entity.setVersion(++entityVersion);
				
				conn = getDataSource().getConnection();
				conn.setAutoCommit(false);
				ps = entity.createUpdateStatement(conn);
				ps.executeUpdate();
				conn.commit();
				entity = this.read(entity.getId());
				
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
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
		}
		return entity;
	}

	@Override
	public void delete(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getDataSource().getConnection();
			conn.setAutoCommit(false);
			String updateString = "UPDATE " + this.getTableName() + " SET  DELETED_10=true  WHERE ID=?";
			ps = conn.prepareStatement(updateString);
			ps.setString(1, id);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
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
	}

	@Override
	public void delete(T entity) {
		if (entity != null && entity.getId() != null && !entity.getId().equals("")) {
			this.delete(entity.getId());
		}
	}

	@Override
	public void undelete(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getDataSource().getConnection();
			conn.setAutoCommit(false);
			String updateString = "UPDATE " + this.getTableName() + " SET  DELETED_10=false  WHERE ID=?";
			ps = conn.prepareStatement(updateString);
			ps.setString(1, id);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
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

	}

	@Override
	public void undelete(T entity) {
		if (entity != null && entity.getId() != null && !entity.getId().equals("")) {
			this.undelete(entity.getId());
		}
	}

	@Override
	public void remove(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getDataSource().getConnection();
			conn.setAutoCommit(false);
			String deleteString = "DELETE FROM " + this.getTableName() + "   WHERE ID=?";
			ps = conn.prepareStatement(deleteString);
			ps.setString(1, id);
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
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

	}

	@Override
	public void remove(T entity) {
		if (entity != null && entity.getId() != null && !entity.getId().equals("")) {
			this.remove(entity.getId());
		}

	}

	@Override
	public T save(T entity) {
		if (entity != null && entity.getId() != null && !entity.getId().equals("")) {
			entity = this.update(entity);
		} else {
			entity = this.create(entity);
		}
		return entity;

	}
}
