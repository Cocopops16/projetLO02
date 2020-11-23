package projetLO02;

public class ScoreShapeVisitor implements Visitor {
	
	public Card visit(Joueur joueur) {
		getVictory();
	}
	
    public Card visit(Plateau plateau) {
		
	}
	
	

}
