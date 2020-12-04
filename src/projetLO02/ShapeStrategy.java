package projetLO02;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ShapeStrategy implements Strategy {
	
	public String searchBestPosition(Plateau plateau, Card victoryCard, String cardToIgnore) {
		Map<String, Integer> Occurence = new TreeMap<String, Integer>();
		int sameCards = 0;
		int cardsAligned;
		String key = new String();
		cardToIgnore = cardToIgnore.intern();
		
		for(int i=0; i<plateau.getXMax(); i++) {
			for(int j=1; j<=plateau.getYMax(); j++) {
				key = ((char)(65+i))+Integer.toString(j);
				key = key.intern();
				if((plateau.getPositions().containsKey(key))&&(key!=cardToIgnore)) {
					Card card = (Card)plateau.getPositions().get(key);
					if(card.getType1()==victoryCard.getType1()) {
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
		
		for(int j=1; j<=plateau.getYMax(); j++) {
			for(int i=0; i<plateau.getXMax(); i++) {
				key = ((char)(65+i))+Integer.toString(j);
				key = key.intern();
				if((plateau.getPositions().containsKey(key))&&(key!=cardToIgnore)) {
					Card card = (Card)plateau.getPositions().get(key);
					if(card.getType1()==victoryCard.getType1()) {
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
	
	public String searchPosDeplacement(Plateau plateau, Card victoryCard, int compteur) {
		Map<String, Integer> Occurence = new TreeMap<String, Integer>();
		int sameCards = 0;
		int nbrOccurencePos;
		
		String key = new String();
		String key2 = new String();
		
		for(int i=0; i<plateau.getXMax(); i++) {
			for(int j=1; j<=plateau.getYMax(); j++) {
				key = ((char)(65+i))+Integer.toString(j);
				key2 =((char)(65+i))+Integer.toString(j-1);
				if(plateau.getPositions().containsKey(key)) {
					Card card = (Card)plateau.getPositions().get(key);
					if(card.getType1()==victoryCard.getType1()) {
						sameCards++;
					}
					else if(sameCards>=1) {
						if(plateau.getPositions().containsKey(key2)) {
							for(int x=1; x<=sameCards; x++) {
								key2 =((char)(65+i))+Integer.toString(j-x);
								if(Occurence.containsKey(key2)) {
									nbrOccurencePos = Occurence.get(key2);
									if(sameCards>nbrOccurencePos) {
										Occurence.put(key2, sameCards);
									}
								}
								else {
									Occurence.put(key2, sameCards);
								}
							}
						}
						sameCards = 0;
					}
				}
				else if(sameCards>=1) {
					if(plateau.getPositions().containsKey(key2)) {
						for(int x=1; x<=sameCards; x++) {
							key2 =((char)(65+i))+Integer.toString(j-x);
							if(Occurence.containsKey(key2)) {
								nbrOccurencePos = Occurence.get(key2);
								if(sameCards>nbrOccurencePos) {
									Occurence.put(key2, sameCards);
								}
							}
							else {
								Occurence.put(key2, sameCards);
							}
						}
					}
					sameCards = 0;
				}
			}
			if(sameCards>=1) {
				if(plateau.getPositions().containsKey(key)) {
					for(int x=0; x<sameCards; x++) {
						key2 =((char)(65+i))+Integer.toString(plateau.getYMax()-x);
						if(Occurence.containsKey(key2)) {
							nbrOccurencePos = Occurence.get(key2);
							if(sameCards>nbrOccurencePos) {
								Occurence.put(key2, sameCards);
							}
						}
						else {
							Occurence.put(key2, sameCards);
						}
					}
				}
				sameCards = 0;
			}
		}
		
		for(int j=1; j<=plateau.getYMax(); j++) {
			for(int i=0; i<plateau.getXMax(); i++) {
				key = ((char)(65+i))+Integer.toString(j);
				key2 =((char)(65+i-1))+Integer.toString(j);
				if(plateau.getPositions().containsKey(key)) {
					Card card = (Card)plateau.getPositions().get(key);
					if(card.getType1()==victoryCard.getType1()) {
						sameCards++;
					}
					else if(sameCards>=1) {
						if(plateau.getPositions().containsKey(key2)) {
							for(int x=1; x<=sameCards; x++) {
								key2 =((char)(65+i-x))+Integer.toString(j);
								if(Occurence.containsKey(key2)) {
									nbrOccurencePos = Occurence.get(key2);
									if(sameCards>nbrOccurencePos) {
										Occurence.put(key2, sameCards);
									}
								}
								else {
									Occurence.put(key2, sameCards);
								}
							}
						}
						sameCards = 0;
					}
				}
				else if(sameCards>=1) {
					if(plateau.getPositions().containsKey(key2)) {
						for(int x=1; x<=sameCards; x++) {
							key2 =((char)(65+i-x))+Integer.toString(j);
							if(Occurence.containsKey(key2)) {
								nbrOccurencePos = Occurence.get(key2);
								if(sameCards>nbrOccurencePos) {
									Occurence.put(key2, sameCards);
								}
							}
							else {
								Occurence.put(key2, sameCards);
							}
						}
					}
					sameCards = 0;
				}
			}
			if(sameCards>=1) {
				if(plateau.getPositions().containsKey(key)) {
					for(int x=0; x<sameCards; x++) {
						key2 =((char)(65+plateau.getXMax()-1-x))+Integer.toString(j);
						if(Occurence.containsKey(key2)) {
							nbrOccurencePos = Occurence.get(key2);
							if(sameCards>nbrOccurencePos) {
								Occurence.put(key2, sameCards);
							}
						}
						else {
							Occurence.put(key2, sameCards);
						}
					}
				}
				sameCards = 0;
			}
		}
		
		for(Iterator<String> it=Occurence.keySet().iterator(); it.hasNext();) {
			 key = it.next();
			 if((Occurence.get(key) == 1)&&(compteur==0)) {
				 return key;
			 }
			 else if(compteur>0) {
				 compteur--;
			 }
		}
		return "0";		
	}
}
