package projetLO02;

/**
 * Exception levée si aucun mode n'a été choisi lors du début de partie
 * 
 * @see Jeu
 * @see Mode
 * @see Controleur.ControleurMenu
 * @see View.VueTexte
 * 
 * @author Corentin Réault
 * @version 1.0
 */
public class InvalidModeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidModeException(String msg){
		super(msg);
	}

	public InvalidModeException(String msg, Throwable cause){
		super(msg, cause);
	}
}
