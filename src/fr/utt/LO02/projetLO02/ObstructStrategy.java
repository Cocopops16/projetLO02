package fr.utt.LO02.projetLO02;

/**
 * Stratégie IA dont le but est de bloquer ses adversaires <br/>
 * 
 * Parcours le plateau en recherche d'occurences entre les cartes du plateau
 * 
 * @author Corentin Réault
 * @version 1.0
 * 
 * @see IA#chooseStrategy()
 * @see IA#placer()
 */
public class ObstructStrategy implements Strategy {
	
	/**
	 * Recherche d'occurence entre les cartes du plateau qui ne partagent pas de caractéristiques avec la victoryCard, dans le but de bloquer une ligne adverse.
	 * 
	 * @param plateau		Plateau du jeu en cours
	 * @param victoryCard 	victoryCard de l'IA
	 * @param cardToIgnore	carte à ignorer lors de la recherche de la meilleure position d'arrivée pour un déplacement (dans le cas d'un placement simple on pourra fixer le paramètre à "0")
	 * 
	 * @return la première position permettant de bloquer une ligne n'ayant aucune caractéristique commune avec la victoryCard
	 */
	public String searchBestPosition(Plateau plateau, Card victoryCard, String cardToIgnore) {
		String key = new String();
		int carteABloquer = 0;
		
		for(int i=0; i<plateau.getXMax(); i++) {
			for(int j=1; j<=plateau.getYMax(); j++) {
				key = ((char)(65+i))+Integer.toString(j);
				if(plateau.getPositions().containsKey(key)) {
					Card card = (Card)plateau.getPositions().get(key);
					if( (card.getType1()!=victoryCard.getType1())&&(card.getType1()!=victoryCard.getType1())&&(card.getType1()!=victoryCard.getType1()) ) {
						carteABloquer = carteABloquer+1;
					}
					else carteABloquer = 0;
				}
				else if(carteABloquer>0) {
					return key;
				}
			}
			carteABloquer = 0;
		}
		
		for(int j=1; j<=plateau.getYMax(); j++) {
			for(int i=0; i<plateau.getXMax(); i++) {
				key = ((char)(65+i))+Integer.toString(j);
				if(plateau.getPositions().containsKey(key)) {
					Card card = (Card)plateau.getPositions().get(key);
					if( (card.getType1()!=victoryCard.getType1())&&(card.getType1()!=victoryCard.getType1())&&(card.getType1()!=victoryCard.getType1()) ) {
						carteABloquer = carteABloquer+1;
						if(carteABloquer<i) {
							String key2 =((char)(65+i-carteABloquer))+Integer.toString(j);
							if(!plateau.getPositions().containsKey(key2))
								return key2;
						}
					}
					else carteABloquer = 0;
				}
				else if(carteABloquer>0) {
					return key;
				}
			}
			carteABloquer = 0;
		}
		
		return "1";
	}

	public String searchPosDeplacement(Plateau plateau, Card victoryCard, int compteur) {
		// TODO Auto-generated method stub
		return null;
	}
}
