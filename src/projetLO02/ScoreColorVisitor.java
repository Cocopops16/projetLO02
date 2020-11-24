package projetLO02;

import java.util.Map;

public class ScoreColorVisitor implements Visitor {
	
	public int visit(Map<String, Object> positions, Card card) {
		return ScoreColor(positions, card);
	}
	
	public Map<String, Object> visit(Map<String, Object> positions) {
		return positions;    
	}
	
	private int ScoreColor(Map<String, Object> positions, Card victoryCard) {
		int score = 0;
		int scorePartiel = 0;
		
		for(int i=0; i<5; i++) {
			for(int j=1; j<=3; j++) {
				String key = ((char)(65+i))+Integer.toString(j);
				if(positions.containsKey(key)) {
					Card card = (Card)positions.get(key);
					if(card.getType3()==victoryCard.getType3()) {
						scorePartiel = scorePartiel++;
					}
					else{
						if(scorePartiel>2) {
							score = score+scorePartiel+1;
							scorePartiel = 0;
							j=4;
						}
						else {
							scorePartiel = 0;
							j=4;
						}
					}
				}
			}
			scorePartiel = 0;
		}
		
		for(int j=1; j<=3; j++) {
			for(int i=0; i<5; i++) {
				String key = ((char)(65+i))+Integer.toString(j);
				if(positions.containsKey(key)) {
					Card card = (Card)positions.get(key);
					if(card.getType3()==victoryCard.getType3()) {
						scorePartiel = scorePartiel++;
					}
					else{
						if(scorePartiel>2) {
							score = score+scorePartiel+1;
							scorePartiel = 0;
							i=5;
						}
						else if(i<2){
							scorePartiel = 0;
						}
						else {
							scorePartiel = 0;
							i=5;
						}
					}
				}
			}
			scorePartiel = 0;
		}
		
		return score;
	}
}

