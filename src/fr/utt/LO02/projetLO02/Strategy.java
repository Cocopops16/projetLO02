package fr.utt.LO02.projetLO02;

/**
 * Interface du patron de conception Strategy
 * 
 * @author Corentin Réault
 * @version 1.0
 * 
 * @see BodyStrategy
 * @see ColorStrategy
 * @see ShapeStrategy
 * @see ObstructStrategy
 * @see RandomStrategy
 */
public interface Strategy {
	public String searchBestPosition(Plateau plateau, Card victoryCard, String cardToIgnore);
	public String searchPosDeplacement(Plateau plateau, Card victoryCard, int compteur);
}
