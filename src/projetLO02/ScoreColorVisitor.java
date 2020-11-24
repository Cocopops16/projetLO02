package projetLO02;

import java.util.Map;

public class ScoreColorVisitor implements Visitor {
	
	public Card visit(Joueur joueur) {
		return joueur.getVictory();
	}
	
	public Map<String, Object> visit(Plateau plateau) {
		return plateau.getPositions();    
	}
	
	public int ScoreColor(Map<String, Object> positions, Card victoryCard, char colonne, int ligne)	{
		int score = 0;
		int scoreColor = 0;
		for(int i=1; i<=3; i++) {
			String key = colonne+Integer.toString(i);
			if(Card.getType3((Card)positions.get(key)) == Card.getType3(victoryCard)) { //ce problème m'embete : comment peut on faire pour vérifier que la carte sur le plateau et la victoryCard sont bien du même type(ici la même couleur)
				score++;
			} else {score = 0;}
					
			if(score>2) {
			  scoreColor = scoreColor + score+1; //on met le score de la précédente colonne et on ajoute le score marqué de la colonne vérifiée et on ajoute 1 pour correspondre aux points du jeu.
			  System.out.println("Vous avez marqué : " + score + " sur la colonne " +i);
		    } else {System.out.println("Vous n'avez marqué aucun point sur la colonne " +i);}
		score=0;
		}
		for(int i=0; i<5; i++) {
		     String key = ((char)(65+i))+Integer.toString(ligne);
			if(Card.getType3((Card)positions.get(key)) == Card.getType3(victoryCard)) {
				score++;
			}else {score =0;}
			
			if(score>2) {
				scoreColor = scoreColor + score+1; //on met le score de la précédente ligne et on ajoute le score marqué de la ligne vérifiée et on ajoute 1 pour correspondre aux points du jeu.
				System.out.println("Vous avez marqué : " + score + " sur la ligne " +i);
			} else { System.out.println("Vous n'avez marqué aucun point sur la ligne " +i);}
		score=0;
		}
		
		return scoreColor;
	}
		
	}

