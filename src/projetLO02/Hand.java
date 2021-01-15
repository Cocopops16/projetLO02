package projetLO02;

import java.util.LinkedList;

import java.util.List;


public class Hand{
	private List<Object> hand;
	
	public Hand() {
		this.hand = new LinkedList<Object>();
	}
	
	public void addCardToHand(Card card) {
		this.hand.add(card);
	}
	
	public int checkNombreCartes() {
		return this.hand.size();
	}
	
	public String toString() {
		String affichageCartes = new String();
		for(int i=1; i<=this.hand.size(); i++) {
			affichageCartes = affichageCartes + "carte n°"+i+" :"+ ( (Card)(this.hand.get(i-1)) ).toString()+"\n";
		}
		return affichageCartes;
	}
	
	public Card getCard(int numCard) {
		if(numCard<=(this.hand.size()-1)) {
			return (Card)(this.hand.get(numCard));
		}
		else {
			return null;
		}
	}
	
	public void removeCardFromHand(Card card) {
		this.hand.remove(card);
	}
}
