package projetLO02;

import java.util.Map;
import java.util.TreeMap;

public class Plateau {
	private Map<String, Object> positions;
	private int xMax, yMax;
	private boolean isFull, firstCard;
	
	public Plateau(int xMax, int yMax) {
		this.xMax = xMax;
		this.yMax = yMax;
		this.isFull = false;
		this.firstCard = false;
		this.positions = new TreeMap<String, Object>();
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
			if(!this.positions.containsKey(key)) return false;
		}
		return true;
	}
	
	public boolean checkMaxYReached(char colonne) {
		for(int i=1; i<=3; i++) {
			String key = colonne+Integer.toString(i);
			if(!this.positions.containsKey(key)) return false;
		}
		return true;
	}
	
	public boolean checkSiCartesAutour(char colonne, int ligne) {
		String key;
		
		if(colonne=='A') {			
			key = ((char)((int)(colonne)+1))+Integer.toString(ligne);
			if(this.positions.containsKey(key)) return true;
		}
		else if(colonne==((char)(65+this.xMax-1)) ) {
			key = ((char)((int)(colonne)-1))+Integer.toString(ligne);
			if(this.positions.containsKey(key)) return true;
		}
		else {
			key = ((char)((int)(colonne)-1))+Integer.toString(ligne);
			if(this.positions.containsKey(key)) return true;
			
			key = ((char)((int)(colonne)+1))+Integer.toString(ligne);
			if(this.positions.containsKey(key)) return true;
		}
		
		if(ligne==1) {
			key = colonne+Integer.toString(ligne+1);
			if(this.positions.containsKey(key)) return true;
		}
		else if(ligne==this.yMax) {
			key = colonne+Integer.toString(ligne-1);
			if(this.positions.containsKey(key)) return true;
		}
		else {
			key = colonne+Integer.toString(ligne-1);
			if(this.positions.containsKey(key)) return true;
			
			key = colonne+Integer.toString(ligne+1);
			if(this.positions.containsKey(key)) return true;
		}
		return false;
	}
	
	public void setCard(Card card, char colonne, int ligne) {
		if(('a'<=colonne)&&('e'>=colonne)) {
			colonne = (char)((int)colonne-32); //mise en majuscule
		}
		String key = colonne+Integer.toString(ligne);
		
		if(isPosAlreadyTaken(colonne, ligne)) {
			this.positions.remove(key);
		}
		this.positions.put(key, card);
	}
	
	public Card getCard(char colonne, int ligne) {
		String key = colonne+Integer.toString(ligne);
		return (Card)this.positions.get(key);
	}
	
	public boolean isPosAlreadyTaken(char colonne, int ligne) {
		String key = colonne+Integer.toString(ligne);
		return this.positions.containsKey(key);
	}
	
	public void removeCard(char colonne, int ligne) {
		String key = colonne+Integer.toString(ligne);
		this.positions.remove(key);
	}
	
	public Map<String, Object> getPositions() {
		return this.positions;
	}
	
	public Map<String, Object> accept(Visitor visitor) {
        return visitor.visit(this.positions);
    }
	
	public String toString() {
		String affichageCartes = new String();
		for(int j=1; j<=3; j++) {
			for(int i=0; i<5; i++) {
				String key = ((char)(65+i))+Integer.toString(j);
				if(positions.containsKey(key)) {
					affichageCartes = affichageCartes + " " + key + ":" + ( (Card)(this.positions.get(key)) ).toString();
				}
				else {
					affichageCartes = affichageCartes + " " + key + ":" + "noCard";
				}
				if(i==4) affichageCartes = affichageCartes + "\n";
			}
		}
		return affichageCartes;
	}
}
