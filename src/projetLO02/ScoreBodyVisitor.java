package projetLO02;
import java.util.Map;

public class ScoreBodyVisitor implements Visitor {
	
	public Card visit(Joueur joueur) {
		getVictory();
	}
	
	public Card visit(Plateau plateau) {
		
		for(int i=1; i<=3; i++) {
			String key = colonne+Integer.toString(i);
			if((Card)positions.get(key) == (Card)victoryCard) { //on regarde si le body de la carte victory est le m�me que le body de la carte pos�e � la cl� 
					int sameBody = sameBody + 1;//si ils ont le m�me body alors on ajoute un 
				} else {sameBody == 0}// sinon, on reprend � 0 notre compteur.
				 
		        switch(sameBody) {  // � la fin de la ligne, en fonction du nombre de cartes qui se suivent et qui ont le m�me body, on attribut les points
	                 case 2 : 
	    	             pointSameBodyLigne = pointSameBodyLigne + 1;
	    	             System.out.println("Vous avez marqu� 1 point sur la ligne " +i);
	                 case 3 : 
	    	             pointSameBodyLigne = pointSameBodyLigne + 2;
	    	             System.out.println("Vous avez marqu� 2 points sur la ligne " +i);
	                 default : 
	    	             pointSameBodyLigne = pointSameBodyLigne + 0;
	    	             System.out.println("Vous n'avez marqu� aucun point sur cette ligne.");
	    	    }
			 }
		
	     sameBody = 0; // on r�initialise note compteur � 0
	     
		for(int i=0; i<5; i++) {
			String key = ((char)(65+i))+Integer.toString(ligne);
			if((Card)positions.get(key) == (Card)victoryCard) {
				    int sameBody = sameBody + 1;
				} else {sameBody == 0}
				 
		        switch(sameBody) {  
	                 case 2 : 
	    	             pointSameBodyColonne = pointSameBodyColonne + 1;
	    	             System.out.println("Vous avez marqu� 1 point sur la colonne " +i);
	                 case 3 : 
	    	             pointSameBodyColonne = pointSameBodyColonne + 2;
	    	             System.out.println("Vous avez marqu� 2 points sur la colonne " +i);
	                 case 4 :
	                	 pointSameBodyColonne = pointSameBodyColonne + 3;
	                	 System.out.println("Vous avez marqu� 3 points sur la colonne " +i);
	                 case 5 : 
	                	 pointSameBodyColonne = pointSameBodyColonne + 4;
	                	 System.out.println("Vous avez marqu� 4 points sur la colonne " +i);
	                 default : 
	    	             pointSameBodyColonne = pointSameBodyColonne + 0;
	    	             System.out.println("Vous n'avez marqu� aucun point sur cette colonne.");
	    	    }
			 }
	   
	    
	}
}
