package projetLO02;

import java.util.Scanner;

public class Joueur {
	private boolean isIA;
	private String name;
	private Card victoryCard;
	private Hand myHand;
	private Jeu jeu;
	
	public Joueur(String name, Jeu jeuEnCours) {
		this.name = name;
		this.isIA = false;
		jeu = jeuEnCours;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getIA() {
		return isIA;
	}
	
	public void piocher(Card card) {
		myHand.addCardToHand(card);
	}
	
	public void setVictory(Card card) {
		victoryCard = card;
	}
	
	public Card getVictory() {
		return victoryCard;
	}
	
	public boolean choixSiDeplacer() {
		Scanner monClavier = new Scanner(System.in);
		System.out.println("Voulez-vous deplacer une carte ? : y/n");
		char bool = monClavier.next().charAt(0);
		monClavier.close();
		if((bool=='y') || (bool=='Y')) {
			return true;
		}
		else if((bool=='n') || (bool=='N')) {
			return false;
		}
		else return choixSiDeplacer();
	}
	
	public Card chooseCardToPlay() {
		Scanner monClavier = new Scanner(System.in);
		System.out.println("Veuillez choisir le numéro d'une carte :");
		System.out.println(myHand.toString());
		int numCard = monClavier.nextInt();
		monClavier.close();
		return myHand.getCard(numCard);
	}
	
	public void placer(Card card, char colonne, int ligne) {
		if(jeu.getPlateau().getFirstCard()) {
			if(jeu.getPlateau().isPosAlreadyTaken(colonne, ligne)) {
				if( (!jeu.getPlateau().checkMaxXReached(ligne)) && (!jeu.getPlateau().checkMaxYReached(colonne)) ) {
					if(jeu.getPlateau().checkSiCartesAutour(colonne, ligne)) {
						jeu.getPlateau().setCard(card, colonne, ligne);
						System.out.println("Carte placée en ("+colonne+";"+ligne+") par "+this.name);
					}
					else {
						System.out.println("Pas de cartes autour");
						placer(card);
					}
				}
				else {
					System.out.println("maximum du plateau atteint pour cette position");
					placer(card);
				}
			}
			else {
				System.out.println("Position déjà occupé");
				placer(card);
			}
		}
		else {
			jeu.getPlateau().setFirstCard();
			jeu.getPlateau().setCard(card, colonne, ligne);
			System.out.println("Carte placée en ("+colonne+";"+ligne+") par "+this.name);
		}
	}
	
	public void placer(Card card) {
		Scanner monClavier = new Scanner(System.in);
		System.out.println("Entrez la colonne où placer la carte");
		char colonne = monClavier.next().charAt(0);
		System.out.println("Entrez la ligne où placer la carte");
		int ligne = monClavier.nextInt();
		monClavier.close();
		
		placer(card, colonne, ligne);
	}
	
	public void deplacer(Card card) {
		Scanner monClavier = new Scanner(System.in);
		System.out.println("Entrez la colonne de la carte à déplacer");
		char colonne1 = monClavier.next().charAt(0);
		System.out.println("Entrez la ligne de la carte à déplacer");
		int ligne1 = monClavier.nextInt();
		
		if(jeu.getPlateau().isPosAlreadyTaken(colonne1, ligne1)) {
			System.out.println("Entrez la colonne où placer la carte");
			char colonne2 = monClavier.next().charAt(0);
			System.out.println("Entrez la ligne où placer la carte");
			int ligne2 = monClavier.nextInt();
			monClavier.close();
			
			if(jeu.getPlateau().isPosAlreadyTaken(colonne2, ligne2)) {
				Card card1 = jeu.getPlateau().getCard(colonne1, ligne1);
				Card card2 = jeu.getPlateau().getCard(colonne2, ligne2);
				jeu.getPlateau().setCard(card2, colonne1, ligne1);
				jeu.getPlateau().setCard(card1, colonne2, ligne2);
			}
			else {
				placer(jeu.getPlateau().getCard(colonne1, ligne1), colonne2, ligne2);
				jeu.getPlateau().removeCard(colonne1, ligne1);
			}
		}
		else {
			System.out.println("Position non occupée");
			deplacer(card);
		}
	}
	
	public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
