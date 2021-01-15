package projetLO02;

public class InvalidChosenCardException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidChosenCardException(String msg){
		super(msg);
	}

	public InvalidChosenCardException(String msg, Throwable cause){
		super(msg, cause);
	}
}
