package projetLO02;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Deck {
	private Queue<Object> shuffledCards = new LinkedList<Object>();
	private List<Object> tabCards;
	
	public Deck() {
		List<Object> tabCards = new LinkedList<Object>();
		tabCards.add(new Card(CardType1.CIRCLE, CardType2.SOLID, CardType3.RED));
		tabCards.add(new Card(CardType1.TRIANGLE, CardType2.SOLID, CardType3.RED));
		tabCards.add(new Card(CardType1.SQUARE, CardType2.SOLID, CardType3.RED));
		tabCards.add(new Card(CardType1.CIRCLE, CardType2.HOLLOW, CardType3.RED));
		tabCards.add(new Card(CardType1.TRIANGLE, CardType2.HOLLOW, CardType3.RED));
		tabCards.add(new Card(CardType1.SQUARE, CardType2.HOLLOW, CardType3.RED));
		tabCards.add(new Card(CardType1.CIRCLE, CardType2.SOLID, CardType3.GREEN));
		tabCards.add(new Card(CardType1.TRIANGLE, CardType2.SOLID, CardType3.GREEN));
		tabCards.add(new Card(CardType1.SQUARE, CardType2.SOLID, CardType3.GREEN));
		tabCards.add(new Card(CardType1.CIRCLE, CardType2.HOLLOW, CardType3.GREEN));
		tabCards.add(new Card(CardType1.TRIANGLE, CardType2.HOLLOW, CardType3.GREEN));
		tabCards.add(new Card(CardType1.SQUARE, CardType2.HOLLOW, CardType3.GREEN));
		tabCards.add(new Card(CardType1.CIRCLE, CardType2.SOLID, CardType3.BLUE));
		tabCards.add(new Card(CardType1.TRIANGLE, CardType2.SOLID, CardType3.BLUE));
		tabCards.add(new Card(CardType1.SQUARE, CardType2.SOLID, CardType3.BLUE));
		tabCards.add(new Card(CardType1.CIRCLE, CardType2.HOLLOW, CardType3.BLUE));
		tabCards.add(new Card(CardType1.TRIANGLE, CardType2.HOLLOW, CardType3.BLUE));
		tabCards.add(new Card(CardType1.SQUARE, CardType2.HOLLOW, CardType3.BLUE));
	}
	
	private int randomizer() {
		Random rand = new Random();
		int nbr = rand.nextInt(tabCards.size()) + 1;
		return nbr;
	}
	
	public void shuffleCards() {
		for(int i=0; i<18; i++) {
			int index = randomizer();
			shuffledCards.add(tabCards.get(index));
		}
	}
	
	public Card giveCard() {
		return (Card)shuffledCards.poll();
	}
	
	public boolean isDeckEmpty() {
		return shuffledCards.isEmpty();
	}
}
