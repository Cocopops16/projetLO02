package projetLO02;

public class ScoreColorVisitor implements Visitor {
	
	public Card visit(Joueur joueur) {
		getVictory();
	}
	
    public Card visit(Plateau plateau) {
		
	}
	
	
}
