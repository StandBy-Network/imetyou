package i.met.you.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class Base64AttachmentBean extends AbstractBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4889399635351428717L;

	private String entityUUID = "";

	private String base64Image = "";

	public String getEntityUUID() {
		return entityUUID;
	}

	public void setEntityUUID(String entityUUID) {
		this.entityUUID = entityUUID;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public static Base64AttachmentBean fromJsonToBase64AttachmentBean(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Base64AttachmentBean entity = null;
		try {
			entity = mapper.readValue(json, Base64AttachmentBean.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return entity;
	}

	public static Collection<Base64AttachmentBean> fromJsonArrayToBase64AttachmentBean(String json) {
		ObjectMapper mapper = new ObjectMapper();
		List<Base64AttachmentBean> list = null;
		try {
			list = mapper.readValue(json,
					TypeFactory.defaultInstance().constructCollectionType(List.class, Base64AttachmentBean.class));
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
