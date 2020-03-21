package i.met.you.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

/**
 * Minden random access model osztaly interface specifikacioja
 * 
 * @author AppGen
 *
 */
public interface IBaseModel {

	String getId();

	void setId(String id);


	Integer getVersion();

	void setVersion(Integer version);

	Date getCreated();

	void setCreated(Date created);

	String getCreator();

	void setCreator(String creator);

	Date getModified();

	void setModified(Date modified);

	String getModifier();

	void setModifier(String modifier);

	Integer getDeleted();

	void setDeleted(Integer deleted);

	PreparedStatement createInsertStatement(Connection conn);

	PreparedStatement createUpdateStatement(Connection conn);

	String getConfirmPassword();
	
	void setConfirmPassword(String confirmPassword); 

}
