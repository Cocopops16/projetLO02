package projetLO02;

import java.util.Scanner;

public class Jeu {
	private int mode, nbrJoueurs, nbrIA;
	private Card hiddenCard;
	private Plateau plateau;
	private Deck deck;
	
	
	public Jeu() {
		Scanner monClavier = new Scanner(System.in);
		System.out.println("Entrez la largeur du plateau :");
		int xMax = monClavier.nextInt();
		System.out.println("Entrez la hauteur du plateau :");
		int yMax = monClavier.nextInt();
		monClavier.close();
		plateau = new Plateau(xMax, yMax);
		
		deck = new Deck();
	}
	
	public void start() {
		Scanner monClavier = new Scanner(System.in);
		System.out.println("Entrez le mode de jeu : \n- 1= Classique\n- 2= Avancé (main de 3 cartes)\n -3 personnalisé (choix de la VictoryCard)");
		this.mode = monClavier.nextInt();
		System.out.println("Entrez le nombre de joueurs réels :");
		this.nbrJoueurs = monClavier.nextInt();
		System.out.println("Entrez le nombre d'IA :");
		this.nbrIA = monClavier.nextInt();
		monClavier.close();
		if((this.nbrIA+this.nbrJoueurs) >= 3)
			start();
		else {
			Deck.shuffleCards();
			hiddenCard = Deck.giveCard();
		}
	}
	
	public void setMode() {
				
	}
}
