package projetLO02;

/**
 * Exception lev�e si une action du joueur est impossible (contraire aux r�gles)
 * 
 * @see Joueur
 * @see IA
 * @see Jeu
 * @see Controleur.ControleurPiocher
 * @see Controleur.ControleurPlacer
 * @see Controleur.ControleurDeplacer
 * @see View.VueTexte
 * 
 * @author Corentin R�ault
 * @version 1.0
 */
public class InvalidPlayerActionException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidPlayerActionException(String msg){
		super(msg);
	}

	public InvalidPlayerActionException(String msg, Throwable cause){
		super(msg, cause);
	}
}
