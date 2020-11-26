package projetLO02;

public class ObstructStrategy implements Strategy {

	public String searchBestPosition(Plateau plateau, Card victoryCard) {
		String key = new String();
		int carteABloquer = 0;
		
		for(int i=0; i<5; i++) {
			for(int j=1; j<=3; j++) {
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
		
		for(int j=1; j<=3; j++) {
			for(int i=0; i<5; i++) {
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
}
