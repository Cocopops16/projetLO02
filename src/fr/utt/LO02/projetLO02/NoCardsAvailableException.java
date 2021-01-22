package fr.utt.LO02.projetLO02;

/**
 * Exception levée si plus aucune carte n'est disponible dans le {@link Deck}
 * 
 * @see Jeu#tourDeJeu()
 * @see Joueur#piocher()
 * @see Controleur.ControleurPiocher
 * @see View.VueTexte
 * 
 * @author Corentin Réault
 * @version 1.0
 */
public class NoCardsAvailableException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NoCardsAvailableException(String msg){
		super(msg);
	}

	public NoCardsAvailableException(String msg, Throwable cause){
		super(msg, cause);
	}
}
