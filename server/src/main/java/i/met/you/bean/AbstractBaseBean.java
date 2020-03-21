package i.met.you.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class AbstractBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2292640627468270490L;

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
	public static <T> T fromJsonToModel(String json, Class<T> clazz) {
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
	public static <T> String toJsonArray(Collection<T> collection) {

		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = "";
		try {
			jsonArray = mapper.writeValueAsString(collection);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonArray;
	}

	@JsonIgnore
	public static <T> Collection<T> fromJsonArrayToModel(String json, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		List<T> list = null;
		try {
			list = mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
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
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
