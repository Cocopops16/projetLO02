package projetLO02;

import java.util.LinkedList;

import java.util.List;

/**
 * Main d'un {@link Joueur}, compos�e au maximum de 3 cartes
 * 
 * @see Card
 * @see Joueur
 * 
 * @author Corentin R�ault
 * @version 1.0
 */

public class Hand{
	private List<Object> hand;
	
	public Hand() {
		this.hand = new LinkedList<Object>();
	}
	
	/**
	 * Ajout d'une carte � la main du {@link Joueur}
	 * @see Card
	 * @see Hand
	 * @param card	La carte � ajouter � la main du joueur
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
			affichageCartes = affichageCartes + "carte n�"+i+" :"+ ( (Card)(this.hand.get(i-1)) ).toString()+"\n";
		}
		return affichageCartes;
	}
	
	
	/**
	 * Recherche d'une carte en particulier dans la main du {@link Joueur}
	 * @param numCard	num�ro de la carte � atteindre
	 * @return retourne la carte correspondant au num�ro indiqu� (si le num�ro est hors de port�e de la liste de carte, retorune {@link null})
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
