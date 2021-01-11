package projetLO02;

public class InvalidEndOfTurnException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidEndOfTurnException(String msg){
		super(msg);
	}

	public InvalidEndOfTurnException(String msg, Throwable cause){
		super(msg, cause);
	}
}
