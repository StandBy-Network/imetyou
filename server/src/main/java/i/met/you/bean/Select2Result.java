package i.met.you.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class Select2Result extends AbstractBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5296743259565685024L;

	Long total;
	Object data;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static Select2Result fromJsonToSelect2Result(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Select2Result entity = null;
		try {
			entity = mapper.readValue(json, Select2Result.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return entity;
	}

	public static Collection<Select2Result> fromJsonArrayToSelect2Results(String json) {
		ObjectMapper mapper = new ObjectMapper();
		List<Select2Result> list = null;
		try {
			list = mapper.readValue(json,
					TypeFactory.defaultInstance().constructCollectionType(List.class, Select2Result.class));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

}
