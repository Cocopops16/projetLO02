package projetLO02;

public class InvalidNbrOfPlayersException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidNbrOfPlayersException(String msg){
		super(msg);
	}

	public InvalidNbrOfPlayersException(String msg, Throwable cause){
		super(msg, cause);
	}
}
