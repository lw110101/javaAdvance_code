package contactSys_jsp_exception;

public class RepeatNameException extends Throwable {

	private static final long serialVersionUID = 1L;

	public RepeatNameException(String message) {
		super(message);
		
	}
}
