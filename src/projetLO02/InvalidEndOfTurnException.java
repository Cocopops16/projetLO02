package projetLO02;

/**
 * Exception levée si la fin du tour est demandée par l'utilisateur alors que toutes les actions obligatoires (pioche et placement) ne sont pas remplies
 * 
 * @see Jeu
 * @see Controleur.ControleurFinTour
 * @see View.VueTexte
 * 
 * @author Corentin Réault
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
