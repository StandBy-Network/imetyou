package i.met.you.model;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class AbstractBaseModel implements IBaseModel {

	private String id;

	

	private Integer version;

	private Date created;

	private String creator;

	private Date modified;

	private String modifier;

	private Integer deleted = 0;
	
	
	// transport fields
	private String confirmPassword = "";
   	
 
	

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	
	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String getCreator() {
		return creator;
	}

	@Override
	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public Date getModified() {
		return modified;
	}

	@Override
	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Override
	public String getModifier() {
		return modifier;
	}

	@Override
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Override
	public Integer getDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	
	// transport 
	@Override
 	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	@Override
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@JsonIgnore
	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

	@JsonIgnore
	public static <T extends IBaseModel> T fromJsonToModel(String json,
			Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		T entity = null;
		try {
			entity = mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return entity;
	}

	@JsonIgnore
	public static <T extends IBaseModel> String toJsonArray(
			Collection<T> collection) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray ="";
		try {
			jsonArray = mapper.writeValueAsString(collection);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonArray;
	}

	@JsonIgnore
	public static <T extends IBaseModel> Collection<T> fromJsonArrayToModel(
			String json, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		List<T> list=null;
		try {
			list = mapper.readValue(json,TypeFactory.defaultInstance().constructCollectionType(List.class,clazz));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@JsonIgnore
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}