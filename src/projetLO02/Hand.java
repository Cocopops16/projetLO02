package projetLO02;

import java.util.LinkedList;
import java.util.Queue;

public class Hand {
	private Queue<Object> hand = new LinkedList<Object>();
	
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	public int checkNombreCartes() {
		return hand.size();
	}
}
