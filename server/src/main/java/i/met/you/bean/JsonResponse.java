package i.met.you.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import i.met.you.validation.Errors;

public class JsonResponse extends AbstractBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9119618917827035561L;

	private Boolean success;

	private Boolean failure;

	private String message;

	private String messageI18n;

	private Object body;

	private Errors errors = new Errors();

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Boolean getFailure() {
		return failure;
	}

	public void setFailure(Boolean failure) {
		this.failure = failure;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageI18n() {
		return messageI18n;
	}

	public void setMessageI18n(String messageI18n) {
		this.messageI18n = messageI18n;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
		if (errors.hasErrors()) {
			this.success = false;
			this.failure = true;
		} else {
			this.success = true;
			this.failure = false;
		}
	}

	public Errors getErrors() {
		return this.errors;
	}

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

	public static JsonResponse fromJsonToCustomError(String json) {
		ObjectMapper mapper = new ObjectMapper();
		JsonResponse entity = null;
		try {
			entity = mapper.readValue(json, JsonResponse.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return entity;
	}

	public static Collection<JsonResponse> fromJsonArrayToCustomErrors(String json) {
		ObjectMapper mapper = new ObjectMapper();
		List<JsonResponse> list = null;
		try {
			list = mapper.readValue(json,
					TypeFactory.defaultInstance().constructCollectionType(List.class, JsonResponse.class));
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
