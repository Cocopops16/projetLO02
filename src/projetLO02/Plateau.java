package projetLO02;

import java.util.Map;
import java.util.TreeMap;

public class Plateau {
	private Map<String, Object> positions;
	private int xMax;
	private int yMax;
	private boolean isFull;
	private boolean firstCard;
	
	public Plateau(int xMax, int yMax) {
		this.xMax = xMax;
		this.yMax = yMax;
		this.isFull = false;
		this.firstCard = false;
		positions = new TreeMap<String, Object>();
	}
	
	public void setFirstCard() {
		this.firstCard = true;
	}
	
	public void setIsFull() {
		this.isFull = true;
	}
	
	public boolean getFirstCard() {
		return this.firstCard;
	}
	
	public boolean getIsFull() {
		return this.isFull;
	}
	
	public int getXMax() {
		return this.xMax;
	}
	
	public int getYMax() {
		return this.yMax;
	}
	
	public boolean checkMaxXReached(int ligne) {
		for(int i=0; i<5; i++) {
			String key = ((char)(65+i))+Integer.toString(ligne);
			if(!positions.containsKey(key)) return false;
		}
		return true;
	}
	
	public boolean checkMaxYReached(char colonne) {
		for(int i=1; i<=3; i++) {
			String key = colonne+Integer.toString(i);
			if(!positions.containsKey(key)) return false;
		}
		return true;
	}
	
	public boolean checkSiCartesAutour(char colonne, int ligne) {
		String key;
		
		if(colonne=='A') {
			key = ((char)(65+this.xMax))+Integer.toString(ligne);
			if(!positions.containsKey(key)) return false;
			
			key = ((char)((int)(colonne)+1))+Integer.toString(ligne);
			if(!positions.containsKey(key)) return false;
		}
		else if(colonne==((char)(65+this.xMax)) ) {
			key = ((char)((int)(colonne)-1))+Integer.toString(ligne);
			if(!positions.containsKey(key)) return false;
			
			key = "A"+Integer.toString(ligne);
			if(!positions.containsKey(key)) return false;
		}
		else {
			key = ((char)((int)(colonne)-1))+Integer.toString(ligne);
			if(!positions.containsKey(key)) return false;
			
			key = ((char)((int)(colonne)+1))+Integer.toString(ligne);
			if(!positions.containsKey(key)) return false;
		}
		
		if(ligne==1) {
			key = colonne+Integer.toString(this.yMax);
			if(!positions.containsKey(key)) return false;
			
			key = colonne+Integer.toString(ligne+1);
			if(!positions.containsKey(key)) return false;
		}
		else if(ligne==this.yMax) {
			key = colonne+Integer.toString(ligne-1);
			if(!positions.containsKey(key)) return false;
			
			key = colonne+Integer.toString(1);
			if(!positions.containsKey(key)) return false;
		}
		else {
			key = colonne+Integer.toString(ligne-1);
			if(!positions.containsKey(key)) return false;
			
			key = colonne+Integer.toString(ligne+1);
			if(!positions.containsKey(key)) return false;
		}
		return true;
	}
	
	public void setCard(Card card, char colonne, int ligne) {
		if(('a'<=colonne)&&('e'>=colonne)) {
			colonne = (char)((int)colonne-32); //mise en majuscule
		}
		String key = colonne+Integer.toString(ligne);
		
		positions.put(key, card);
	}
	
	public Card getCard(char colonne, int ligne) {
		
	}
	
	public boolean isPosAlreadyTaken(char colonne, int ligne) {
		String key = colonne+Integer.toString(ligne);
		return positions.containsKey(key);
	}
}
