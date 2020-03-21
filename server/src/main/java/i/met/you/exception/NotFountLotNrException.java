package i.met.you.exception;

public class NotFountLotNrException extends RuntimeException{

	private static final long serialVersionUID = -5506861503515408697L;

	public NotFountLotNrException(String errors) {
		super(errors);
	}

}
