package projetLO02;

import java.util.Map;

public class ScoreShapeVisitor implements Visitor {
	
	public Card visit(Joueur joueur) {
		return joueur.getVictory();
	}
	
	public Map<String, Object> visit(Plateau plateau) {
		return plateau.getPositions();    
	}
	
	public int ScoreShape(Map<String, Object> positions, Card victoryCard, char colonne, int ligne)	{
		int score = 0;
		int scoreShape = 0;
		for(int i=1; i<=3; i++) {
			String key = colonne+Integer.toString(i);
			if(Card.getType1((Card)positions.get(key)) == Card.getType1(victoryCard)) {//ce problème m'embete : comment peut on faire pour vérifier que la carte sur le plateau et la victoryCard sont bien du même type(ici tous deux ont la même forme)
				score++;                                                                
			} else {score = 0;}
			
			if(score>1) {
			  scoreShape = scoreShape + score -1; //on met le score de la précédente colonne et on ajoute le score marqué de la colonne vérifiée et on enlève 1 pour correspondre au score.
			  System.out.println("Vous avez marqué : " + score + " sur la colonne " +i);
		    } else {System.out.println("Vous n'avez marqué aucun point sur la colonne " +i);}
		score=0;
		}
		for(int i=0; i<5; i++) {
		    String key = ((char)(65+i))+Integer.toString(ligne);
			if(Card.getType1(positions.get(key)) == Card.getType1(victoryCard)) {
				score++;
			}else {score =0;}
			
			if(score>1) {
				scoreShape = scoreShape + score -1; //on met le score de la précédente ligne et on ajoute le score marqué de la ligne vérifiée et on enlève 1 pour correspondre au score.
				System.out.println("Vous avez marqué : " + score + " sur la ligne " +i);
			} else { System.out.println("Vous n'avez marqué aucun point sur la ligne " +i);}
		score=0;
		}
		
		return scoreShape;
	}
}
