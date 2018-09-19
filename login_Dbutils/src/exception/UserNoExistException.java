package exception;

public class UserNoExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNoExistException() {
		super();
		
	}

	public UserNoExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UserNoExistException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserNoExistException(String message) {
		super(message);
		
	}

	public UserNoExistException(Throwable cause) {
		super(cause);
		
	}
	
}
