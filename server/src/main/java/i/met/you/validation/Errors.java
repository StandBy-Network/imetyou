package i.met.you.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Errors implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2930323855494257580L;

	private List<CustomError> errors = new ArrayList<CustomError>();
	private String message;

	public void rejectValue(String fieldName, String errorCode, String message, String messageI18n) {
		CustomError error = new CustomError(fieldName, errorCode, message, messageI18n);
		this.errors.add(error);
	}

	public void rejectValue(String fieldName, String errorCode, String message) {

		CustomError error = new CustomError(fieldName, errorCode, message);
		this.errors.add(error);
	}

	public boolean hasErrors() {
		System.out.println(this.errors.size());
		return this.errors.size() == 0 ? false : true;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<CustomError> getErrors() {
		return this.errors;
	}

	public String getAllMessage() {
		String result = "";
		Iterator<CustomError> it = this.errors.iterator();
		CustomError error;
		while (it.hasNext()) {
			error = it.next();
			result += error.getMessage() + "\n";
		}

		return result;
	}

}
