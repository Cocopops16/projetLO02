package projetLO02;

import java.util.LinkedList;

import java.util.List;

/**
 * Main d'un {@link Joueur}, composée au maximum de 3 cartes
 * 
 * @see Card
 * @see Joueur
 * 
 * @author Corentin Réault
 * @version 1.0
 */

public class Hand{
	private List<Object> hand;
	
	public Hand() {
		this.hand = new LinkedList<Object>();
	}
	
	/**
	 * Ajout d'une carte à la main du {@link Joueur}
	 * @see Card
	 * @see Hand
	 * @param card	La carte à ajouter à la main du joueur
	 */
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
	
	
	/**
	 * Recherche d'une carte en particulier dans la main du {@link Joueur}
	 * @param numCard	numéro de la carte à atteindre
	 * @return retourne la carte correspondant au numéro indiqué (si le numéro est hors de portée de la liste de carte, retorune {@link null})
	 * @see Card
	 * @see Hand
	 */
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
