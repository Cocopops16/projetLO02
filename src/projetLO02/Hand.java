package projetLO02;

import java.util.LinkedList;
import java.util.List;

public class Hand {
	private List<Object> hand = new LinkedList<Object>();
	
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	public int checkNombreCartes() {
		return hand.size();
	}
	
	public String toString() {
		String affichageCartes = new String();
		for(int i=1; i<=hand.size(); i++) {
			affichageCartes = affichageCartes + "carte n°"+i+" :"+ ( (Card)(hand.get(i-1)) ).toString()+"\n";
		}
		return affichageCartes;
	}
	
	public Card getCard(int numCard) {
		return (Card)(hand.get(numCard));
	}
}
