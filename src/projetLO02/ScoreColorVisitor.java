package projetLO02;

import java.util.Map;

public class ScoreColorVisitor implements Visitor {
	
	public Card visit(Joueur joueur) {
		return joueur.getVictory();
	}
	
	public Map<String, Object> visit(Plateau plateau) {
		return plateau.getPositions();    
	}
	
	public int ScoreColor(Map<String, Object> positions, Card victoryCard)	{
		int score = 0;
		return score;
	}
}
