package projetLO02;

public interface Strategy {
	public String searchBestPosition(Plateau plateau, Card victoryCard, String cardToIgnore);
	public String searchPosDeplacement(Plateau plateau, Card victoryCard, int compteur);
}
