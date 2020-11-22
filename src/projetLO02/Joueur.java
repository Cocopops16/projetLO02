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
	
	public void piocher(Card card) {
		myHand.addCardToHand(card);
	}
	
	public void placer(Card card) {
		Scanner monClavier = new Scanner(System.in);
		System.out.println("Entrez la colonne où placer la carte");
		char colonne = monClavier.next().charAt(0);
		System.out.println("Entrez la ligne où placer la carte");
		int ligne = monClavier.nextInt();
		monClavier.close();
		
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
	
	public void deplacer(Card card) {
		Scanner monClavier = new Scanner(System.in);
		System.out.println("Entrez la colonne de la carte à déplacer");
		String colonne1 = monClavier.next();
		System.out.println("Entrez la ligne de la carte à déplacer");
		String ligne1 = monClavier.next();
		System.out.println("Entrez la colonne où placer la carte");
		String colonne2 = monClavier.next();
		System.out.println("Entrez la ligne où placer la carte");
		String ligne2 = monClavier.next();
		monClavier.close();
		
		
		

	}
}
