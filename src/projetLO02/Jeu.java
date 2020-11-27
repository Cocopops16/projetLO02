package projetLO02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Jeu {
	private int nbrJoueurs, nbrIA;
	private Mode mode;
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
		this.plateau = new Plateau(xMax, yMax);
		
		this.deck = new Deck();
	}
	
	public void start() {		
		setMode();
		setNbrJoueurs();
		setNbrIA();
		if((this.nbrIA+this.nbrJoueurs) > 3)
			start();
		else {
			for(int i=0; i<this.nbrJoueurs; i++) {
				System.out.println("Entrez le nom du Joueur n°"+(i+1)+" :");
				String name = monClavier.next();
				this.playersQueue.add(new Joueur(name, this));
				System.out.println("Bienvenue "+ name);
			}
			for(int i=0; i<this.nbrJoueurs; i++) {
				((Joueur)this.playersQueue.peek()).setIA(false);
				this.playersQueue.add(this.playersQueue.poll());
			}
			
			for(int i=0; i<this.nbrIA; i++) {
				switch(i) {
					case 0:
						this.playersQueue.add(new IA("Billy", this));
						System.out.println( "Billy est dans la place !");
						break;
					case 1:
						this.playersQueue.add(new IA("Cratos", this));
						System.out.println("Attention, Cratos est arrivé avec un air effrayant !");
						break;
					case 2:
						this.playersQueue.add(new IA("Price", this));
						System.out.println("Price est dans les parrages, vous avez vraiment décidé de ne pas vous salir les mains aujourd'hui !");
						break;
				}
			}
			for(int i=0; i<(this.nbrJoueurs+this.nbrIA); i++) {
				if(i>=this.nbrJoueurs) {
					((Joueur)this.playersQueue.peek()).setIA(true);
					System.out.println( ((Joueur)this.playersQueue.peek()).getName() +" a bien été reconnu comme IA");
					this.playersQueue.add(this.playersQueue.poll());
				}
				else {
					System.out.println( ((Joueur)this.playersQueue.peek()).getName() +" a bien été reconnu comme Joueur");
					this.playersQueue.add(this.playersQueue.poll());
				}
			}
			
			if(this.mode != Mode.Personnalisé) {
				this.deck.shuffleCards();
			}
			
			for(int i=0; i<(this.nbrIA+this.nbrJoueurs); i++) {
				if((this.mode == Mode.Personnalisé)&&(this.nbrJoueurs>0)) {
					if(i==this.nbrJoueurs-1) {
						i=(this.nbrIA+this.nbrJoueurs);
					}
					System.out.println( ((Joueur)this.playersQueue.peek()).getName()+" vous allez choisir votre Victory Card, mettez vous à l'abri des regards");
					( (Joueur)this.playersQueue.peek() ).setVictory(this.deck.modePerso());
				}
				else if(this.mode == Mode.Avancé) {
					( (Joueur)this.playersQueue.peek() ).piocher(this.deck.giveCard());
					( (Joueur)this.playersQueue.peek() ).piocher(this.deck.giveCard());
					( (Joueur)this.playersQueue.peek() ).piocher(this.deck.giveCard());
				}
				else if(this.mode == Mode.Classique) {
					( (Joueur)this.playersQueue.peek() ).setVictory(this.deck.giveCard());
					
				}
				this.playersQueue.add(this.playersQueue.poll());
			}
			
			if(this.mode == Mode.Personnalisé) {
				this.deck.shuffleCards();
				if(this.nbrIA>0) {
					for(int i=0; i<this.nbrIA; i++) {
						( (Joueur)this.playersQueue.peek() ).setVictory(this.deck.giveCard());
						this.playersQueue.add(this.playersQueue.poll());
					}
				}
			}
			this.hiddenCard = this.deck.giveCard();
		}
	}
	
	public void tourDeJeu() {
		Joueur joueurEnCours = (Joueur)this.playersQueue.peek();
		System.out.println("C'est au tour de : " + joueurEnCours.getName());
		this.playersQueue.add(this.playersQueue.poll());
		if(this.mode != Mode.Avancé) {
			joueurEnCours.piocher(this.deck.giveCard());
		}
		
		if(!joueurEnCours.getIA()) {
			joueurEnCours.placer(joueurEnCours.chooseCardToPlay());
			if(joueurEnCours.choixSiDeplacer()) {
				joueurEnCours.deplacer();
			}
		}
		else if(joueurEnCours.getIA()) {
			IA IAEnCours = (IA)joueurEnCours;
			IAEnCours.jouer();
		}
		
		if( (this.mode == Mode.Avancé) && (!this.deck.isDeckEmpty()) ) {
			joueurEnCours.piocher(this.deck.giveCard());
		}
		else System.out.println("problème sur le joueur : "+joueurEnCours.getName()+", est-il une IA ?");
	}
	
	public boolean checkEndGame() {
		if(this.deck.isDeckEmpty()) {
			if( ((this.nbrIA+this.nbrJoueurs) == 3) && (this.plateau.getPositions().size()==14) )
				return true;
			else if( ((this.nbrIA+this.nbrJoueurs) == 2) && (this.plateau.getPositions().size()==15) )
				return true;
			else 
				return false;
		}
		else return false;
	}
	
	public void comptagePoints() {
		Visitor visitor1 = new ScoreBodyVisitor();
		Visitor visitor2 = new ScoreColorVisitor();
		Visitor visitor3 = new ScoreShapeVisitor();
		int scoreFinal = 0;
		int scorePremier = -1;
		String premier = new String();
		for(int i=0; i<(this.nbrIA+this.nbrJoueurs); i++) {
			scoreFinal = scoreFinal + ( (Joueur)this.playersQueue.peek() ).accept(visitor1, this.plateau.accept(visitor1));
			scoreFinal = scoreFinal + ( (Joueur)this.playersQueue.peek() ).accept(visitor2, this.plateau.accept(visitor1));
			scoreFinal = scoreFinal + ( (Joueur)this.playersQueue.peek() ).accept(visitor3, this.plateau.accept(visitor1));
			System.out.println("Le joueur : "+( (Joueur)this.playersQueue.peek() ).getName()+" a accumulé : "+scoreFinal+" points");
			if(scoreFinal>scorePremier) {
				scorePremier = scoreFinal;
				premier = ( (Joueur)this.playersQueue.poll() ).getName();
			}
			else {
				this.playersQueue.poll();
			}
			scoreFinal = 0;
		}
		System.out.println("Félicitations "+premier+" ton plan s'est déroulé sans accros ;) , tu as gagné avec : "+scorePremier+" points");
		monClavier.close();
	}

 	public void setMode() {
		System.out.println("Entrez le mode de jeu : \n- 1= Classique\n-2= Avancé (main de 3 cartes)\n- 3= personnalisé (choix de la VictoryCard)");
		int choix = monClavier.nextInt();
		
		switch(choix) {
			case 1: 
				this.mode = Mode.Classique;
				break;
			case 2:
				this.mode = Mode.Avancé;
				break;
			case 3:
				this.mode = Mode.Personnalisé;
				break;
			default:
				this.mode = Mode.Classique;
		}
	}
	
	public void setNbrJoueurs() {
		System.out.println("Choix du nombre de joueurs reels participant : ");
		this.nbrJoueurs = monClavier.nextInt();
	}
	
	public void setNbrIA() {
		System.out.println("Choix du nombre de joueurs virtuels participant : ");
		this.nbrIA = monClavier.nextInt();
	}
	
	public Mode getMode() {
		return this.mode;
	}
	
	public int getNbrJoueurs() {		
		return this.nbrJoueurs;
	}
	
	public int getNbrIA() {
		return this.nbrIA;
 	}
	
	public Plateau getPlateau() {
		return this.plateau;
	}
	
	public Deck getDeck() {
		return this.deck;
	}
	
	public Card getHinddenCard() {
		return this.hiddenCard;
	}
}
