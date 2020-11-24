package projetLO02;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Deck {
	private Queue<Object> shuffledCards = new LinkedList<Object>();
	private List<Object> tabCards;
	private static final Scanner monClavier = new Scanner(System.in);
	
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
		for(int i=0; i<18; i++) {
			int index = randomizer();
			this.shuffledCards.add(this.tabCards.get(index));
			this.tabCards.remove(index);
		}
	}
	
	public Card modePerso() {
		System.out.println("Choix de la victoryCard");
		for(int i=0; i<18; i++) {
			System.out.println( ( (Card)this.tabCards.get(i) ).toString() );
			System.out.println("Prendre cette carte ? y/n");
			char choix = monClavier.next().charAt(0);
			if((choix=='y')||(choix=='Y')) {
				return (Card)this.tabCards.get(i);
			}
		}
		System.out.println("Nous avons choisi une Victory Card pour vous");
		int index = randomizer();
		Card card = (Card)this.tabCards.get(index);
		this.tabCards.remove(index);
		return card;
	}
	
	public Card giveCard() {
		return (Card)this.shuffledCards.poll();
	}
	
	public boolean isDeckEmpty() {
		return this.shuffledCards.isEmpty();
	}
}
