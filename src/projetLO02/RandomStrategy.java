package projetLO02;

import java.util.Map;

public class RandomStrategy implements Strategy {

	public String searchBestPosition(Map<String, Object> positions, Card victoryCard) {
		String key = new String();
		int carteABloquer = 0;
		
		for(int i=0; i<5; i++) {
			for(int j=1; j<=3; j++) {
				key = ((char)(65+i))+Integer.toString(j);
				if(positions.containsKey(key)) {
					Card card = (Card)positions.get(key);
					if( (card.getType1()!=victoryCard.getType1())&&(card.getType1()!=victoryCard.getType1())&&(card.getType1()!=victoryCard.getType1()) ) {
						carteABloquer = carteABloquer++;
					}
					else if(carteABloquer>0) {
							return key;
					}
				}
			}
			carteABloquer = 0;
		}
		
		for(int j=1; j<=3; j++) {
			for(int i=0; i<5; i++) {
				key = ((char)(65+i))+Integer.toString(j);
				if(positions.containsKey(key)) {
					Card card = (Card)positions.get(key);
					if( (card.getType1()!=victoryCard.getType1())&&(card.getType1()!=victoryCard.getType1())&&(card.getType1()!=victoryCard.getType1()) ) {
						carteABloquer = carteABloquer++;
						if(carteABloquer<i) {
							String key2 =((char)(65+i-carteABloquer))+Integer.toString(j);
							return key2;
						}
					}
					else if(carteABloquer>0) {
						return key;
					}
				}
			}
			carteABloquer = 0;
		}
		
		return null;
	}

}
