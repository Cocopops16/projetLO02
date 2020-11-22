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
		setMode();
		setNbrJoueurs();
		setNbrIA();
		if((this.nbrIA+this.nbrJoueurs) >= 3)
			start();
		else {
			deck.shuffleCards();
			hiddenCard = deck.giveCard();
			
		}
	}

 	public void setMode() {
 		Scanner monClavier = new Scanner(System.in);
		System.out.println("Entrez le mode de jeu : \n- 1= Classique\n- 2= Avancé (main de 3 cartes)\n -3 personnalisé (choix de la VictoryCard)");
		this.mode = monClavier.nextInt();
		monClavier.close();
	}
	
	public void setNbrJoueurs() {
		Scanner monClavier = new Scanner(System.in);
		System.out.println("Choix du nombre de joueurs reels participant : ");
		this.nbrJoueurs = monClavier.nextInt();
		monClavier.close();
	}
	
	public void setNbrIA() {
		Scanner monClavier = new Scanner(System.in);
		System.out.println("Choix du nombre de joueurs virtuels participant : ");
		this.nbrIA = monClavier.nextInt();
		monClavier.close();
	}
	
	public int getMode() {
		return this.mode;
	}
	
	public int getNbrJoueurs() {		
		return this.nbrJoueurs;
	}
	
	public int getNbrIa() {
		return this.nbrIA;
 	}
	
	public Plateau getPlateau() {
		return plateau;
	}
}
