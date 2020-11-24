package projetLO02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Jeu {
	private int nbrJoueurs, nbrIA;
	private boolean modeSpecial;
	private Card hiddenCard;
	private Plateau plateau;
	private Deck deck;
	private Queue<Object> playersQueue = new LinkedList<Object>();
	private static final Scanner monClavier = new Scanner(System.in);
	
	
	public Jeu() {
		System.out.println("Entrez la largeur du plateau :");
		int xMax = monClavier.nextInt();
		System.out.println("Entrez la hauteur du plateau :");
		int yMax = monClavier.nextInt();
		plateau = new Plateau(xMax, yMax);
		
		deck = new Deck();
	}
	
	public void start() {		
		setModeSpecial();
		setNbrJoueurs();
		setNbrIA();
		if((this.nbrIA+this.nbrJoueurs) >= 3)
			start();
		else {
			deck.shuffleCards();
			hiddenCard = deck.giveCard();
			for(int i=0; i<this.nbrJoueurs; i++) {
				System.out.println("Entrez le nom du Joueur n°"+i+" :");
				String name = monClavier.nextLine();
				playersQueue.add(new Joueur(name, this));
				System.out.println("Bienvenue "+ ((Joueur)playersQueue.peek()).getName() );
			}
			
			for(int i=0; i<this.nbrIA; i++) {
				switch(i) {
					case 0:
						playersQueue.add(new IA("Billy", this));
						System.out.println( ((Joueur)playersQueue.peek()).getName() +" est dans la place !");
					case 1:
						playersQueue.add(new IA("Cratos", this));
						System.out.println("Attention, "+ ((Joueur)playersQueue.peek()).getName() +" est arrivé avec un air effrayant !");
					case 2:
						playersQueue.add(new IA("Price", this));
						System.out.println(((Joueur)playersQueue.peek()).getName() +" est dans les parrages, vous avez vraiment décidé de ne pas vous salir les mains aujourd'hui !");
				}
			}
			
			for(int i=0; i<(this.nbrIA+this.nbrJoueurs); i++) {
				playersQueue.add(playersQueue.peek());
				( (Joueur)playersQueue.poll() ).setVictory(deck.giveCard());
				if(modeSpecial) {
					( (Joueur)playersQueue.poll() ).setVictory(deck.giveCard());
					( (Joueur)playersQueue.poll() ).setVictory(deck.giveCard());
				}
			}
		}
	}
	
	public void tourDeJeu() {
		Joueur joueurEnCours = (Joueur)playersQueue.peek();
		playersQueue.add(playersQueue.poll());
		joueurEnCours.piocher(deck.giveCard());
		
		if(!joueurEnCours.getIA()) {
			joueurEnCours.placer(joueurEnCours.chooseCardToPlay());
			if(joueurEnCours.choixSiDeplacer()) {
				joueurEnCours.deplacer();
			}
		}
		else if(joueurEnCours.getIA()) {
			IA IAEnCours = (IA)joueurEnCours;
			IAEnCours.chooseStrategy();
		}
		else System.out.println("problème sur le joueur : "+joueurEnCours.getName()+", est-il une IA ?");
	}
	
	public void comptagePoints() {
		Visitor visitor1 = new ScoreBodyVisitor();
		Visitor visitor2 = new ScoreColorVisitor();
		Visitor visitor3 = new ScoreShapeVisitor();
		int scoreFinal = 0;
		int scorePremier = -1;
		String premier = new String();
		for(int i=0; i<(this.nbrIA+this.nbrJoueurs); i++) {
			scoreFinal = ( ( (Joueur)playersQueue.peek() ).accept(visitor1, plateau.accept(visitor1)) + ( (Joueur)playersQueue.peek() ).accept(visitor2, plateau.accept(visitor1)) + ( (Joueur)playersQueue.peek() ).accept(visitor3, plateau.accept(visitor1)));
			System.out.println("Le joueur : "+( (Joueur)playersQueue.peek() ).getName()+" a accumulé : "+scoreFinal+" points");
			if(scoreFinal>scorePremier) {
				scorePremier = scoreFinal;
				premier = ( (Joueur)playersQueue.poll() ).getName();
			}
			else {
				playersQueue.poll();
			}
		}
		System.out.println("Félicitations "+premier+" ton plan s'est déroulé sans accros ;)");
		monClavier.close();
	}

 	public void setModeSpecial() {
		System.out.println("Entrez le mode de jeu : \n- 1= Classique\n- 2= personnalisé (choix de la VictoryCard)");
		int mode = monClavier.nextInt();
		
		if(mode==1) {
			this.modeSpecial = false;
		}
		else if(mode==2) {
			this.modeSpecial = true;
		}
		else setModeSpecial();
	}
	
	public void setNbrJoueurs() {
		System.out.println("Choix du nombre de joueurs reels participant : ");
		this.nbrJoueurs = monClavier.nextInt();
	}
	
	public void setNbrIA() {
		System.out.println("Choix du nombre de joueurs virtuels participant : ");
		this.nbrIA = monClavier.nextInt();
	}
	
	public boolean getMode() {
		return this.modeSpecial;
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
	
	public Deck getDeck() {
		return deck;
	}
	
	public Card getHinddenCard() {
		return hiddenCard;
	}
}
