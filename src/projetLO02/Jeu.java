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
	// PARTIE FABIEN 
	public void setMode() {
		System.out.println("Choix du mode de jeu : " + this.mode);
	}
	
	public void setNbrJoueurs() {
		if (this.nbrJoueurs >= 1 && this.nbrJoueurs <= 2) {
			System.out.println("Choix du nombre de joueurs reels participant : " + this.nbrJoueurs);
		} else {System.out.println("Veuillez entrer un ou deux joueurs reel");}
	}
	
	public void setNbrIA() {
		if (this.nbrIA >= 1 && this.nbrIA <= 2) {
			System.out.println("Choix du nombre de joueurs virtuels participant : " + this.nbrIA);
		} else {System.out.println("Veuillez choisir un ou deux joueurs virtuel");}
	}
	
	public int getMode() {
		return this.mode;
	}
	
	public int getNbrJoueurs() {		
		return this.nbrJoueurs;
	}
	
	public int getNbrIa() {
		return this.nbrIa;
	}
}
