package projetLO02;

import java.util.LinkedList;

import java.util.Observable;
import java.util.Queue;

import Controleur.MsgBox;
import View.MonInterfacePlateau;

@SuppressWarnings("deprecation")
public class Jeu extends Observable implements Runnable {
	private int nbrJoueurs, nbrIA;
	private Mode mode;
	private Card hiddenCard;
	private Plateau plateau;
	private Deck deck;
	private Queue<Object> playersQueue = new LinkedList<Object>();
	protected MsgBox msgBox;
	private String msgToSend;
	private Thread thread;
	private Joueur joueurEnCours;
	private MonInterfacePlateau monInterface;
	
	public Jeu(MonInterfacePlateau monInterface) {
		this.msgBox = new MsgBox();
		
		this.msgToSend = null;
		
		this.mode = null;
		this.nbrJoueurs = 0;
		this.nbrIA = 0;
		this.plateau = new Plateau(5, 3, monInterface);
		this.deck = new Deck();
		
		addObserver(monInterface);
		this.monInterface = monInterface;
	}
	
	public void sendMsg(String msg) {
		this.msgToSend = msg;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public void addJoueur(String name) {
		if((this.nbrIA+this.nbrJoueurs)<4) {
			Joueur joueur = new Joueur(name, this, this.monInterface);
			joueur.setIA(false);
			this.playersQueue.add(joueur);
			sendMsg("Bienvenue "+ name);
			this.nbrJoueurs++;
		}
	}
	
	public void addIA() {
		if((this.nbrIA+this.nbrJoueurs)<4) {
			String name = "IA";
			switch(this.nbrIA) {
				case 0:
					name = "[IA]Billy";
					sendMsg( "Billy est dans la place !");
					break;
				case 1:
					name = "[IA]Cratos";
					sendMsg("Attention, Cratos est arriv� avec un air effrayant !");
					break;
				case 2:
					name = "[IA]Price";
					sendMsg("Price est dans les parrages, vous avez vraiment d�cid� de ne pas vous salir les mains aujourd'hui !");
					break;
			}
			Joueur joueurIA = new IA(name, this, this.monInterface);
			joueurIA.setIA(true);
			this.playersQueue.add(joueurIA);
			this.nbrIA++;
		}
	}
	
	public void setMode(int mode) {	
 		//sendMsg("Entrez le mode de jeu : \n- 1= Classique\n-2= Avanc� (main de 3 cartes)\n- 3= personnalis� (choix de la VictoryCard)");
		switch(mode) {
			case 1: 
				this.mode = Mode.Classique;
				break;
			case 2:
				this.mode = Mode.Avanc�;
				break;
			case 3:
				this.mode = Mode.Personnalis�;
				break;
			default:
				this.mode = Mode.Classique;
		}
	}
	
	
	public void start() throws InvalidModeException,InvalidNbrOfPlayersException {
		if( (this.mode!=null)&&((this.nbrIA+this.nbrJoueurs)>=2) ) {
			for(int i=0; i<(this.nbrJoueurs+this.nbrIA); i++) {
//				if(i>=this.nbrJoueurs) {
//					((Joueur)this.playersQueue.peek()).setIA(true);
//					System.out.println( ((Joueur)this.playersQueue.peek()).getName() +" a bien �t� reconnu comme IA");
//					this.playersQueue.add(this.playersQueue.poll());
//				}
//				else {
//					System.out.println( ((Joueur)this.playersQueue.peek()).getName() +" a bien �t� reconnu comme Joueur");
//					this.playersQueue.add(this.playersQueue.poll());
//				}
				if(((Joueur)this.playersQueue.peek()).getIA()) {
					System.out.println( ((Joueur)this.playersQueue.peek()).getName() +" a bien �t� reconnu comme IA");
				}
				else {
					System.out.println( ((Joueur)this.playersQueue.peek()).getName() +" a bien �t� reconnu comme Joueur");
				}
				this.playersQueue.add(this.playersQueue.poll());
			}
			
			if(this.mode != Mode.Personnalis�) {
				this.deck.shuffleCards();
			}
			
			for(int i=0; i<(this.nbrIA+this.nbrJoueurs); i++) {
				if((this.mode == Mode.Personnalis�)&&(this.nbrJoueurs>0)) {
					if(i==this.nbrJoueurs-1) {
						i=(this.nbrIA+this.nbrJoueurs);
					}
					sendMsg( ((Joueur)this.playersQueue.peek()).getName()+" vous allez choisir votre Victory Card, mettez vous � l'abri des regards");
					( (Joueur)this.playersQueue.peek() ).setVictory(this.deck.modePerso());
				}
				else if(this.mode == Mode.Avanc�) {
					this.joueurEnCours = (Joueur)this.playersQueue.peek();
					this.setChanged();
					this.notifyObservers(this.joueurEnCours);
					for(int j=0; j<3; j++) {
						this.joueurEnCours.piocher();
						this.joueurEnCours.resetTurn();
					}
				}
				else if(this.mode == Mode.Classique) {
					( (Joueur)this.playersQueue.peek() ).setVictory(this.deck.giveCard());
					
				}
				this.playersQueue.add(this.playersQueue.poll());
			}
			
			if(this.mode == Mode.Personnalis�) {
				this.deck.shuffleCards();
				if(this.nbrIA>0) {
					for(int i=0; i<this.nbrIA; i++) {
						( (Joueur)this.playersQueue.peek() ).setVictory(this.deck.giveCard());
						this.playersQueue.add(this.playersQueue.poll());
					}
				}
			}
			this.hiddenCard = this.deck.giveCard();
			
			//d�but de la partie :
			while(!checkEndGame()) {
				tourDeJeu();
			}
			if(checkEndGame()) {
				comptagePoints();
				sendMsg("End of the game");
			}
			else sendMsg("Probl�me critique dans le d�roulement de la partie");
		}
		else if(this.mode==null){
			throw new InvalidModeException("Mode non choisi");
		}
		else if((this.nbrIA+this.nbrJoueurs)<2){
			throw new InvalidNbrOfPlayersException("Nbr joueurs actuels <2, deux joueurs minimum sont n�cessaires pour lancer la partie");
		}
	}
	
	public synchronized void tourDeJeu() {
		sendMsg(this.plateau.toString());
		this.joueurEnCours = (Joueur)this.playersQueue.peek();
		this.joueurEnCours.resetTurn();
		this.setChanged();
		this.notifyObservers(this.joueurEnCours);
		sendMsg("C'est au tour de : " + joueurEnCours.getName());
		
		if(!this.joueurEnCours.getIA()) {
//			joueurEnCours.placer(joueurEnCours.chooseCardToPlay());
//			if(joueurEnCours.choixSiDeplacer()) {
//				joueurEnCours.deplacer();
//			}
			while( (!this.joueurEnCours.aPioche()) || (!this.joueurEnCours.aPlace()) ) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(this.joueurEnCours.getIA()) {
			if(this.mode != Mode.Avanc�) {
				this.joueurEnCours.piocher();
			}
			IA IAEnCours = (IA)joueurEnCours;
			IAEnCours.jouer();
			if( (this.mode == Mode.Avanc�) && (!this.deck.isDeckEmpty()) ) {
				joueurEnCours.piocher();
			}
		}
		else sendMsg("probl�me sur le joueur : "+joueurEnCours.getName()+", est-il une IA ?");
		
		this.playersQueue.add(this.playersQueue.poll());
	}
	
	public synchronized void unlockJoueur() throws InvalidEndOfTurnException {
		Joueur joueurEnCours = (Joueur)this.playersQueue.peek();
		if(joueurEnCours.aPioche() && joueurEnCours.aPlace()) {
			this.notifyAll();
		}
		else {
			throw new InvalidEndOfTurnException("Fin de tour impossible, le joueur n'a pas effectu� toutes ses actions");
		}
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
			Joueur joueur = (Joueur)this.playersQueue.peek();
			if(this.mode == Mode.Avanc�) {
				joueur.setVictory();
			}
			scoreFinal = scoreFinal + joueur.accept(visitor1, this.plateau.accept(visitor1));
			scoreFinal = scoreFinal + joueur.accept(visitor2, this.plateau.accept(visitor1));
			scoreFinal = scoreFinal + joueur.accept(visitor3, this.plateau.accept(visitor1));
			sendMsg("Le joueur : "+joueur.getName()+" a accumul� : "+scoreFinal+" points");
			if(scoreFinal>scorePremier) {
				scorePremier = scoreFinal;
				premier = joueur.getName();
			}
			this.playersQueue.poll();
			scoreFinal = 0;
		}
		sendMsg("F�licitations "+premier+" ton plan s'est d�roul� sans accros ;) , tu as gagn� avec : "+scorePremier+" points");
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
	
	public String getPlayerName(int playerNum) {
		for(int i=1; i<playerNum; i++) {
			this.playersQueue.add(this.playersQueue.poll());
		}
		String name = ( (Joueur)this.playersQueue.peek() ).getName();
		if(playerNum>1) {
			for(int i=(this.nbrIA+this.nbrJoueurs); i>=playerNum; i--) {
				this.playersQueue.add(this.playersQueue.poll());
			}
		}
		return name;
	}
	
	public Joueur getJoueurEnCours() {
		return this.joueurEnCours;
	}
	
	public void run() {
		if(this.msgToSend != null) {
			this.msgBox.addMsg(this.msgToSend);	
			this.msgToSend = null;
		}
	}
}
