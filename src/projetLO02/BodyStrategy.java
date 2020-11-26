package projetLO02;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class BodyStrategy  implements Strategy {
	
	public String searchBestPosition(Plateau plateau, Card victoryCard) {
		Map<String, Integer> Occurence = new TreeMap<String, Integer>();
		int sameCards = 0;
		int cardsAligned;
		String key = new String();
		
		for(int i=0; i<5; i++) {
			for(int j=1; j<=3; j++) {
				key = ((char)(65+i))+Integer.toString(j);
				if(plateau.getPositions().containsKey(key)) {
					Card card = (Card)plateau.getPositions().get(key);
					if(card.getType2()==victoryCard.getType2()) {
						sameCards = sameCards+1;
						if(sameCards<j) {
							String key2 =((char)(65+i))+Integer.toString(j-sameCards);
							if(!plateau.getPositions().containsKey(key2)) {
								if(Occurence.containsKey(key2)) {
									cardsAligned = Occurence.get(key2);
									Occurence.put(key2, cardsAligned+1);
								}
								else Occurence.put(key2, 1);
								sameCards = 0;
							}
						}
					}
					else sameCards = 0;
				}
				else if(sameCards>0) {
					Occurence.put(key, sameCards);
					sameCards = 0;
				}
			}
			sameCards = 0;
		}
		
		for(int j=1; j<=3; j++) {
			for(int i=0; i<5; i++) {
				key = ((char)(65+i))+Integer.toString(j);
				if(plateau.getPositions().containsKey(key)) {
					Card card = (Card)plateau.getPositions().get(key);
					if(card.getType2()==victoryCard.getType2()) {
						sameCards = sameCards+1;
						if(sameCards<i) {
							String key2 =((char)(65+i-sameCards))+Integer.toString(j);
							if(!plateau.getPositions().containsKey(key2)) {
								if(Occurence.containsKey(key2)) {
									cardsAligned = Occurence.get(key2);
									Occurence.put(key2, cardsAligned+1);
								}
								else Occurence.put(key2, 1);
								sameCards = 0;
							}
						}
					}
					else sameCards = 0;
				}
				else if(sameCards>0) {
					if(Occurence.containsKey(key)) {
						cardsAligned = Occurence.get(key);
						Occurence.put(key, cardsAligned+sameCards);
					}
					else Occurence.put(key, sameCards);
					sameCards = 0;
				}
			}
			sameCards = 0;
		}
		
		int bestLine = -1;
		String bestPosition = new String();
		for(Iterator<String> it=Occurence.keySet().iterator(); it.hasNext();) {
			 key = it.next();
			 if(Occurence.get(key) > bestLine) {
				 bestPosition = key;
				 bestLine = Occurence.get(key);
			 }
		}
				
		if(bestLine>0) {
			return bestPosition;
		}
		else return "0";
	}
}
