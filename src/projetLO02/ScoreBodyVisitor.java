package projetLO02;

public class ScoreBodyVisitor implements Visitor {
	
	public Card visit(Joueur joueur) {
		getVictory();
	}
	
	public Card visit(Plateau plateau) {
		for(int i=0; i<5; i++) { // on parcourt les lignes
			int sameBody = 0; // on initialise notre variable qui va compter le nombre de cartes alignés ayant le même body
			  for(int j=0; j<3; j++) { // on parcourt les colonnes
				if(Card Victory == CardType1 type1) { //on regarde si le body de la carte victory est le même que le body de la carte posée à la ligne i et la colonne j
					samebody++; //si ils ont le même body alors on ajoute un 
				} else {sameBody == 0}// sinon, on reprend à 0 notre compteur.
				 
		        switch(sameBody) {  // à la fin de la ligne, en fonction du nombre de cartes qui se suivent et qui ont le même body, on attribut les points
	                 case 2 : 
	    	             pointSameBodyLigne = pointSameBodyLigne + 1;
	    	             System.out.println("Vous avez marqué 1 point sur la ligne " +i);
	                 case 3 : 
	    	             pointSameBodyLigne = pointSameBodyLigne + 2;
	    	             System.out.println("Vous avez marqué 2 points sur la ligne " +i);
	                 default : 
	    	             pointSameBodyLigne = pointSameBodyLigne + 0;
	    	             System.out.println("Vous n'avez marqué aucun point sur cette ligne.");
	    	    }
			 }
	     }
		for(int i=0; i<3; i++) { // on parcourt les colonnes
			int sameBody = 0; 
			  for(int j=0; j<5; j++) { // on parcourt les lignes
				if(Card Victory == CardType1 type1) { 
					samebody++; 
				} else {sameBody == 0}
				 
		        switch(sameBody) {  
	                 case 2 : 
	    	             pointSameBodyColonne = pointSameBodyColonne + 1;
	    	             System.out.println("Vous avez marqué 1 point sur la colonne " +i);
	                 case 3 : 
	    	             pointSameBodyColonne = pointSameBodyColonne + 2;
	    	             System.out.println("Vous avez marqué 2 points sur la colonne " +i);
	                 case 4 :
	                	 pointSameBodyColonne = pointSameBodyColonne + 3;
	                	 System.out.println("Vous avez marqué 3 points sur la colonne " +i);
	                 case 5 : 
	                	 pointSameBodyColonne = pointSameBodyColonne + 4;
	                	 System.out.println("Vous avez marqué 4 points sur la colonne " +i);
	                 default : 
	    	             pointSameBodyColonne = pointSameBodyColonne + 0;
	    	             System.out.println("Vous n'avez marqué aucun point sur cette ligne.");
	    	    }
			 }
	     }
	    
	}
}
