package projetLO02;

/**
 * Exception lev�e si la fin du tour est demand�e par l'utilisateur alors que toutes les actions obligatoires (pioche et placement) ne sont pas remplies
 * 
 * @see Jeu
 * @see Controleur.ControleurFinTour
 * @see View.VueTexte
 * 
 * @author Corentin R�ault
 * @version 1.0
 */
public class InvalidEndOfTurnException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidEndOfTurnException(String msg){
		super(msg);
	}

	public InvalidEndOfTurnException(String msg, Throwable cause){
		super(msg, cause);
	}
}
