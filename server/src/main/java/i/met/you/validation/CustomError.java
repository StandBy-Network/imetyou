package i.met.you.validation;

import java.io.Serializable;

public class CustomError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2503667622110276636L;

	private String fieldName;
	private String errorCode;
	private String message;
	private String messageI18n;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
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

	public CustomError(String fieldName, String errorCode, String message) {
		this.fieldName = fieldName;
		this.errorCode = errorCode;
		this.message = message;

	}

	public CustomError(String fieldName, String errorCode, String message, String messageI18n) {
		this.fieldName = fieldName;
		this.errorCode = errorCode;
		this.message = message;
		this.messageI18n = messageI18n;

	}

}
