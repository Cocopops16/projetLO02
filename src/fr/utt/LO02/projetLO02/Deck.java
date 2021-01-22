package fr.utt.LO02.projetLO02;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Paquet de cartes, contenant 18 cartes � sa cr�ation, repr�sentant toutes les possibilit�es offertes par {@link Card}
 * 
 * @see Card
 * 
 * @author Corentin R�ault
 * @version 1.0
 */

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
	
	/**
	 * Simple randomizer
	 * @return entier al�atoire compris entre 0 et la longueur de la liste cha�n�e contenant les cartes ordon�es
	 */
	private int randomizer() {
		Random rand = new Random();
		int nbr = rand.nextInt(this.tabCards.size());
		return nbr;
	}
	
	/**
	 * M�lange les cartes du {@link Deck} : <br/>
	 * Tire les cartes al�atoirement � l'aide du {@link Deck#randomizer()} pour les placer dans une Collection de type Queue
	 */
	public void shuffleCards() {
		int tailleTab = this.tabCards.size();
		for(int i=0; i<tailleTab; i++) {
			int index = randomizer();
			this.shuffledCards.add(this.tabCards.get(index));
			this.tabCards.remove(index);
		}
	}
	
	/**
	 * Permet l'�change de carte avec le {@link Deck} non m�lang� (utilis� en mode Personnalis� pour choisir sa victoryCard)
	 * @see Mode
	 * @see Joueur
	 * @see Jeu
	 * @param carteRendue	Carte rendue par un joueur
	 * @return Carte donn�e au joueur
	 */
	public Card modePerso(Card carteRendue) {
		if(carteRendue!=null) {
			this.tabCards.add(carteRendue);
		}
		Card card;
		card = (Card)this.tabCards.get(0);
		this.tabCards.remove(0);
		return card;
	}
	
	/**
	 * Donne une carte du {@link Deck} (et la supprime de sa liste de cartes)
	 * @return une carte du {@link Deck}
	 * @throws NoCardsAvailableException si il n'y a plus de crates dans le {@link Deck}
	 */
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
