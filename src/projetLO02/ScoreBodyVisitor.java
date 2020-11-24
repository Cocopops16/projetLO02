package projetLO02;

import java.util.Map;

public class ScoreBodyVisitor implements Visitor {
	
	public Card visit(Joueur joueur) {
		return joueur.getVictory();
	}

	public Map<String, Object> visit(Plateau plateau) {
		return plateau.getPositions();    
	}
	
	public int ScoreBody(Map<String, Object> positions, Card victoryCard, char colonne, int ligne)	{
		int score = 0;
		int scoreBody = 0;
		for(int i=1; i<=3; i++) {
			String key = colonne+Integer.toString(i);
			if(Card.getType2((Card)positions.get(key)) == Card.getType2(victoryCard)) {//ce problème m'embete : comment peut on faire pour vérifier que la carte sur le plateau et la victoryCard sont bien du même type(ici tous deux remplis ou vides)
				score++;                                                               //car ici on veut récupérer le type2 de la carte pour le comparer avec celui de victory
			} else {score = 0;}
			
			if(score>2) {
			  scoreBody = scoreBody + score; //on met le score de la précédente colonne et on ajoute le score marqué de la colonne vérifiée.
			  System.out.println("Vous avez marqué : " + score + " sur la colonne " +i);
		    } else {System.out.println("Vous n'avez marqué aucun point sur la colonne " +i);}
		score=0;
		}
		for(int i=0; i<5; i++) {
		     String key = ((char)(65+i))+Integer.toString(ligne);
			if(Card.getType2(positions.get(key)) == Card.getType2(victoryCard)) {
				score++;
			}else {score =0;}
			
			if(score>2) {
				scoreBody = scoreBody + score; //on met le score de la précédente ligne et on ajoute le score marqué de la ligne vérifiée.
				System.out.println("Vous avez marqué : " + score + " sur la ligne " +i);
			} else { System.out.println("Vous n'avez marqué aucun point sur la ligne " +i);}
		score=0;
		}
		
		return scoreBody;
	}
}

