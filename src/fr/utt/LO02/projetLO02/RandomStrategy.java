package fr.utt.LO02.projetLO02;

/**
 * Stratégie IA dont le but est de simplement placer une carte pour remplir la condition de passage de tour <br/>
 *  
 * @author Corentin Réault
 * @version 1.0
 * 
 * @see IA#placer()
 */
public class RandomStrategy implements Strategy {
	
	/**
	 * Parcours le plateau en recherche de position occupée pour placer à côté
	 * 
	 * @param plateau		Plateau du jeu en cours
	 * @param victoryCard 	victoryCard de l'IA
	 * @param cardToIgnore	carte à ignorer lors de la recherche de la meilleure position d'arrivée pour un déplacement (dans le cas d'un placement simple on pourra fixer le paramètre à "0")
	 * 
	 * @return la première position permettant de placer une carte
	 */
	public String searchBestPosition(Plateau plateau, Card victoryCard, String cardToIgnore) {
		String key = new String();
		String key2 = new String();
		char colonne;
		int ligne;
		
		for(int i=0; i<plateau.getXMax(); i++) {
			for(int j=1; j<=plateau.getYMax(); j++) {
				colonne = (char)(65+i);
				ligne = j;
				key = colonne+Integer.toString(j);
				if(plateau.getPositions().containsKey(key)) {
					if(colonne=='A') {
						key2 = ((char)((int)(colonne)+1))+Integer.toString(ligne);
						if(!plateau.getPositions().containsKey(key2)) return key2;
					}
					else if(colonne==((char)(65+plateau.getXMax()-1)) ) {
						key2 = ((char)((int)(colonne)-1))+Integer.toString(ligne);
						if(!plateau.getPositions().containsKey(key2)) return key2;
					}
					else {
						key2 = ((char)((int)(colonne)-1))+Integer.toString(ligne);
						if(!plateau.getPositions().containsKey(key2)) return key2;
						
						key2 = ((char)((int)(colonne)+1))+Integer.toString(ligne);
						if(!plateau.getPositions().containsKey(key2)) return key2;
					}
					
					if(ligne==1) {
						key2 = colonne+Integer.toString(ligne+1);
						if(!plateau.getPositions().containsKey(key2)) return key2;
					}
					else if(ligne==plateau.getYMax()) {
						key2 = colonne+Integer.toString(ligne-1);
						if(!plateau.getPositions().containsKey(key2)) return key2;
					}
					else {
						key2 = colonne+Integer.toString(ligne-1);
						if(!plateau.getPositions().containsKey(key2)) return key2;
						
						key2 = colonne+Integer.toString(ligne+1);
						if(!plateau.getPositions().containsKey(key2)) return key2;
					}
				}
			}
		}
		return null;
	}

	@Override
	public String searchPosDeplacement(Plateau plateau, Card victoryCard, int compteur) {
		// TODO Auto-generated method stub
		return null;
	}

}
