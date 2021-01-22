package fr.utt.LO02.projetLO02;

/**
 * Exception lev�e en cas de choix de carte d�finie comme {@code null}
 * 
 * @see Joueur
 * @see IA
 * 
 * @author Corentin R�ault
 * @version 1.0
 */
public class InvalidChosenCardException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidChosenCardException(String msg){
		super(msg);
	}

	public InvalidChosenCardException(String msg, Throwable cause){
		super(msg, cause);
	}
}
