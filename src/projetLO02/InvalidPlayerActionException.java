package projetLO02;

public class InvalidPlayerActionException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidPlayerActionException(String msg){
		super(msg);
	}

	public InvalidPlayerActionException(String msg, Throwable cause){
		super(msg, cause);
	}
}
