package projetLO02;

/**
 * Exception lev�e si aucun mode n'a �t� choisi lors du d�but de partie
 * 
 * @see Jeu
 * @see Mode
 * @see Controleur.ControleurMenu
 * @see View.VueTexte
 * 
 * @author Corentin R�ault
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
