package projetLO02;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Deck {
	private Queue<Object> shuffledCards = new LinkedList<Object>();
	private List<Object> tabCards;
	
	public Deck() {
		this.tabCards = new LinkedList<Object>();
		this.tabCards.add(new Card(CardType1.CIRCLE, CardType2.SOLID, CardType3.RED));
		this.tabCards.add(new Card(CardType1.TRIANGLE, CardType2.SOLID, CardType3.RED));
		this.tabCards.add(new Card(CardType1.SQUARE, CardType2.SOLID, CardType3.RED));
		this.tabCards.add(new Card(CardType1.CIRCLE, CardType2.HOLLOW, CardType3.RED));
		this.tabCards.add(new Card(CardType1.TRIANGLE, CardType2.HOLLOW, CardType3.RED));
		this.tabCards.add(new Card(CardType1.SQUARE, CardType2.HOLLOW, CardType3.RED));
		this.tabCards.add(new Card(CardType1.CIRCLE, CardType2.SOLID, CardType3.GREEN));
		this.tabCards.add(new Card(CardType1.TRIANGLE, CardType2.SOLID, CardType3.GREEN));
		this.tabCards.add(new Card(CardType1.SQUARE, CardType2.SOLID, CardType3.GREEN));
		this.tabCards.add(new Card(CardType1.CIRCLE, CardType2.HOLLOW, CardType3.GREEN));
		this.tabCards.add(new Card(CardType1.TRIANGLE, CardType2.HOLLOW, CardType3.GREEN));
		this.tabCards.add(new Card(CardType1.SQUARE, CardType2.HOLLOW, CardType3.GREEN));
		this.tabCards.add(new Card(CardType1.CIRCLE, CardType2.SOLID, CardType3.BLUE));
		this.tabCards.add(new Card(CardType1.TRIANGLE, CardType2.SOLID, CardType3.BLUE));
		this.tabCards.add(new Card(CardType1.SQUARE, CardType2.SOLID, CardType3.BLUE));
		this.tabCards.add(new Card(CardType1.CIRCLE, CardType2.HOLLOW, CardType3.BLUE));
		this.tabCards.add(new Card(CardType1.TRIANGLE, CardType2.HOLLOW, CardType3.BLUE));
		this.tabCards.add(new Card(CardType1.SQUARE, CardType2.HOLLOW, CardType3.BLUE));
	}
	
	private int randomizer() {
		Random rand = new Random();
		int nbr = rand.nextInt(this.tabCards.size());
		return nbr;
	}
	
	public void shuffleCards() {
		int tailleTab = this.tabCards.size();
		for(int i=0; i<tailleTab; i++) {
			int index = randomizer();
			this.shuffledCards.add(this.tabCards.get(index));
			this.tabCards.remove(index);
		}
	}
	
	public Card modePerso(Card carteRendue) {
		if(carteRendue!=null) {
			this.tabCards.add(carteRendue);
		}
		Card card;
		card = (Card)this.tabCards.get(0);
		this.tabCards.remove(0);
		return card;
	}
	
	public Card giveCard() throws NoCardsAvailableException {
		Card card = (Card)this.shuffledCards.poll();
		if(card==null) {
			throw new NoCardsAvailableException("plus de cartes dans le deck");
		}
		else return card;
	}
	
	public boolean isDeckEmpty() {
		return this.shuffledCards.isEmpty();
	}
}
