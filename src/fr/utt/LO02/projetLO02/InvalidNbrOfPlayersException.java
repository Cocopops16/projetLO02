package fr.utt.LO02.projetLO02;

/**
 * Exception levée si le nombre de joueurs est insuffisant au lancement de la partie (nbr joueurs<2)
 * 
 * @see Jeu
 * @see Controleur.ControleurMenu
 * @see View.VueTexte
 * 
 * @author Corentin Réault
 * @version 1.0
 */
public class InvalidNbrOfPlayersException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidNbrOfPlayersException(String msg){
		super(msg);
	}

	public InvalidNbrOfPlayersException(String msg, Throwable cause){
		super(msg, cause);
	}
}
