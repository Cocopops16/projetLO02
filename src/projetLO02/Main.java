package projetLO02;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jeu jeu = new Jeu();
		jeu.start();
		while(!jeu.getDeck().isDeckEmpty()) {
			jeu.tourDeJeu();
		}
		if(jeu.getDeck().isDeckEmpty()) {
			Visitor visitor1 = new ScoreBodyVisitor();
			Visitor visitor2 = new ScoreColorVisitor();
			Visitor visitor3 = new ScoreShapeVisitor();
			
			
		}
	}

}
