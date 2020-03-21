package i.met.you.exception;

public class LockingException extends RuntimeException{

	private static final long serialVersionUID = -306798009471031300L;

	public LockingException() {
		super("LockingException!");
	}
}
