package i.met.you.validation;

public interface Validator {

	@SuppressWarnings("rawtypes")
	public abstract boolean supports(Class clazz);

	public abstract void validate(Object target, Errors errors);

}
