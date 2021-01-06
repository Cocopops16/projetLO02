package projetLO02;

public class InvalidModeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidModeException(String msg){
		super(msg);
	}

	public InvalidModeException(String msg, Throwable cause){
		super(msg, cause);
	}
}
