package utils;

public class InvalidInputFormat extends Exception {
	private static final long serialVersionUID = 8607214290525710898L;

	public InvalidInputFormat() {
		super();
	}

	public InvalidInputFormat(String message) {
		super(message);
	}

	public InvalidInputFormat(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidInputFormat(Throwable cause) {
		super(cause);
	}
}
