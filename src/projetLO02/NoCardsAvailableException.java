package projetLO02;

public class NoCardsAvailableException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NoCardsAvailableException(String msg){
		super(msg);
	}

	public NoCardsAvailableException(String msg, Throwable cause){
		super(msg, cause);
	}
}
