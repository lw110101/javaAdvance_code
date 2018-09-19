package contactSys_mysql_exception;

public class RepeatNameException extends Throwable {

	private static final long serialVersionUID = 1L;

	public RepeatNameException(String message) {
		super(message);
		
	}
}
