package projetLO02;

import java.util.LinkedList;

import java.util.Observable;
import java.util.Queue;

import Controleur.MsgBox;
import View.MonInterfacePlateau;

@SuppressWarnings("deprecation")
public class Jeu extends Observable implements Runnable {
	private int nbrJoueurs, nbrIA, nbrVictoryCardChoosen;
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
		this.nbrVictoryCardChoosen = 1;
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
					sendMsg("Attention, Cratos est arrivé avec un air effrayant !");
					break;
				case 2:
					name = "[IA]Price";
					sendMsg("Price est dans les parrages, vous avez vraiment décidé de ne pas vous salir les mains aujourd'hui !");
					break;
			}
			Joueur joueurIA = new IA(name, this, this.monInterface);
			joueurIA.setIA(true);
			this.playersQueue.add(joueurIA);
			this.nbrIA++;
		}
	}
	
	public void setMode(int mode) {	
 		//sendMsg("Entrez le mode de jeu : \n- 1= Classique\n-2= Avancé (main de 3 cartes)\n- 3= personnalisé (choix de la VictoryCard)");
		switch(mode) {
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
	
	public synchronized void setPlayersVictory() {
		if(this.joueurEnCours.getVictory()!=null) {
			this.nbrVictoryCardChoosen++;
			this.notifyAll();
		}
	}
	
	public synchronized void start() throws InvalidModeException,InvalidNbrOfPlayersException {
		if( (this.mode!=null)&&((this.nbrIA+this.nbrJoueurs)>=2) ) {
			
			if(this.mode != Mode.Personnalisé) {
				this.deck.shuffleCards();
			}
			
			for(int i=0; i<(this.nbrJoueurs+this.nbrIA); i++) {
				this.joueurEnCours = (Joueur)this.playersQueue.peek();
				
				if(this.joueurEnCours.getIA()) {
					System.out.println(this.joueurEnCours.getName() +" a bien été reconnu comme IA");
				}
				else {
					System.out.println(this.joueurEnCours.getName() +" a bien été reconnu comme Joueur");
					if(this.mode == Mode.Personnalisé) {
						sendMsg(this.joueurEnCours.getName()+" vous allez choisir votre Victory Card, mettez vous à l'abri des regards");
						while(this.joueurEnCours.getVictory()==null) {
							this.joueurEnCours.setVictory();
							try {
								this.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						//( (Joueur)this.playersQueue.peek() ).setVictory(this.deck.modePerso());
					}
				}
				
				if(this.mode == Mode.Classique) {
					this.joueurEnCours.setVictory();
				}
				else if(this.mode == Mode.Avancé) {
					for(int j=0; j<3; j++) {
						this.joueurEnCours.piocher();
						this.joueurEnCours.resetTurn();
					}
				}
				
				this.playersQueue.add(this.playersQueue.poll());
			}
			
			if(this.mode == Mode.Personnalisé) {
				this.deck.shuffleCards();
				if(this.nbrIA>0) {
					for(int i=0; i<(this.nbrJoueurs+this.nbrIA); i++) {
						this.joueurEnCours = (Joueur)this.playersQueue.peek();
						
						if(this.joueurEnCours.getIA()) {
							System.out.println(this.joueurEnCours.getName() +" a bien été reconnu comme IA");
							this.joueurEnCours.setVictory();
						}
						this.playersQueue.add(this.playersQueue.poll());
					}
				}
			}
			
			this.hiddenCard = this.deck.giveCard();
			
			//début de la partie :
			while(!checkEndGame()) {
				tourDeJeu();
			}
			if(checkEndGame()) {
				comptagePoints();
				sendMsg("End of the game");
			}
			else sendMsg("Problème critique dans le déroulement de la partie");
		}
		else if(this.mode==null){
			throw new InvalidModeException("Mode non choisi");
		}
		else if((this.nbrIA+this.nbrJoueurs)<2){
			throw new InvalidNbrOfPlayersException("Nbr joueurs actuels <2, deux joueurs minimum sont nécessaires pour lancer la partie");
		}
	}
	
	public void tourDeJeu() {
		sendMsg(this.plateau.toString());
		this.joueurEnCours = (Joueur)this.playersQueue.peek();
		this.joueurEnCours.resetTurn();
		this.setChanged();
		this.notifyObservers(this.joueurEnCours);
		sendMsg("C'est au tour de : " + joueurEnCours.getName());
		
		if(!this.joueurEnCours.getIA()) {
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
			if(this.mode != Mode.Avancé) {
				this.joueurEnCours.piocher();
			}
			IA IAEnCours = (IA)joueurEnCours;
			IAEnCours.jouer();
			if( (this.mode == Mode.Avancé) && (!this.deck.isDeckEmpty()) ) {
				joueurEnCours.piocher();
			}
		}
		else sendMsg("problème sur le joueur : "+joueurEnCours.getName()+", est-il une IA ?");
		
		this.playersQueue.add(this.playersQueue.poll());
	}
	
	public synchronized void unlockJoueur() throws InvalidEndOfTurnException {
		Joueur joueurEnCours = (Joueur)this.playersQueue.peek();
		if(joueurEnCours.aPioche() && joueurEnCours.aPlace()) {
			this.notifyAll();
		}
		else {
			throw new InvalidEndOfTurnException("Fin de tour impossible, le joueur n'a pas effectué toutes ses actions");
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
	
	public String comptagePoints() {
		Visitor visitor1 = new ScoreBodyVisitor();
		Visitor visitor2 = new ScoreColorVisitor();
		Visitor visitor3 = new ScoreShapeVisitor();
		int scoreFinal = 0;
		int scorePremier = -1;
		String premier = new String();
		for(int i=0; i<(this.nbrIA+this.nbrJoueurs); i++) {
			Joueur joueur = (Joueur)this.playersQueue.peek();
			if(this.mode == Mode.Avancé) {
				joueur.setVictory();
			}
			scoreFinal = scoreFinal + joueur.accept(visitor1, this.plateau.accept(visitor1));
			scoreFinal = scoreFinal + joueur.accept(visitor2, this.plateau.accept(visitor1));
			scoreFinal = scoreFinal + joueur.accept(visitor3, this.plateau.accept(visitor1));
			sendMsg("Le joueur : "+joueur.getName()+" a accumulé : "+scoreFinal+" points");
			if(scoreFinal>scorePremier) {
				scorePremier = scoreFinal;
				premier = joueur.getName();
			}
			this.playersQueue.poll();
			scoreFinal = 0;
		}
		sendMsg("Félicitations "+premier+" ton plan s'est déroulé sans accros ;) , tu as gagné avec : "+scorePremier+" points");
		return premier;
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
	
	public int getNbrVictoryCardChoosen() {
		return this.nbrVictoryCardChoosen;
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
