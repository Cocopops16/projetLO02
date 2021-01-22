package projetLO02;

import java.util.LinkedList;

import java.util.Observable;
import java.util.Queue;

import View.MonInterfacePlateau;
import View.VueTexte;

@SuppressWarnings("deprecation")
public class Jeu extends Observable {
	private int nbrJoueurs, nbrIA, nbrVictoryCardChoosen, nbrRounds;
	private Mode mode;
	private Card hiddenCard;
	private Plateau plateau;
	private Deck deck;
	private Queue<Object> playersQueue = new LinkedList<Object>();
	private Joueur joueurEnCours;
	private MonInterfacePlateau monInterface;
	private VueTexte vueTexte;
	private boolean hasStarted, nextRound;
	private String premier;
	
	public Jeu(MonInterfacePlateau monInterface) {
		
		this.mode = null;
		this.nbrJoueurs = 0;
		this.nbrVictoryCardChoosen = 0;
		this.nbrIA = 0;
		this.nbrJoueurs = 0;
		this.plateau = new Plateau(5, 3, monInterface);
		this.deck = new Deck();
		this.hasStarted = false;
		this.nextRound = false;
		this.nbrRounds = 0;
		
		addObserver(monInterface);
		this.monInterface = monInterface;
	}
	
	public void addJoueur(String name) throws InvalidNbrOfPlayersException {
		if((this.nbrIA+this.nbrJoueurs)<3) {
			Joueur joueur = new Joueur(name, this, this.monInterface, this.vueTexte);
			joueur.setIA(false);
			this.playersQueue.add(joueur);
			this.nbrJoueurs++;
			this.setChanged();
			this.notifyObservers(this.nbrIA+this.nbrJoueurs);
		}
		else {
			int nbrPlayers = this.nbrIA+this.nbrJoueurs;
			throw new InvalidNbrOfPlayersException("Nbr joueurs actuels : "+nbrPlayers+" ; nombre maximum : 3");
		}
	}
	
	public void addIA() throws InvalidNbrOfPlayersException {
		if((this.nbrIA+this.nbrJoueurs)<3) {
			String name = "IA";
			switch(this.nbrIA) {
				case 0:
					name = "[IA]Billy";
					break;
				case 1:
					name = "[IA]Cratos";
					break;
				case 2:
					name = "[IA]Price";
					break;
			}
			Joueur joueurIA = new IA(name, this, this.monInterface, this.vueTexte);
			joueurIA.setIA(true);
			this.playersQueue.add(joueurIA);
			this.nbrIA++;
			this.setChanged();
			this.notifyObservers(this.nbrIA+this.nbrJoueurs);
		}
		else {
			int nbrPlayers = this.nbrIA+this.nbrJoueurs;
			throw new InvalidNbrOfPlayersException("Nbr joueurs actuels : "+nbrPlayers+" ; nombre maximum : 3");
		}
	}
	
	public void setMode(int mode) {	
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
	
	public synchronized void setNextRound() {
		if(!this.nextRound) {
			this.nextRound = true;
			this.notifyAll();
		}
	}
	
	public synchronized void start() throws InvalidModeException, InvalidNbrOfPlayersException {
		while(this.nbrRounds<4) {
			setup();
			if(this.nbrRounds<3) {
				while(!this.nextRound) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for(int i=0; i<(this.nbrJoueurs+this.nbrIA); i++) {
					this.joueurEnCours = (Joueur)this.playersQueue.peek();
					this.joueurEnCours.resetVictory();
					this.playersQueue.add(this.playersQueue.poll());
				}
				this.nextRound = false;
				this.plateau = new Plateau(5, 3, monInterface);
				this.plateau.addObserver(vueTexte);
				this.deck = new Deck();
				this.nbrVictoryCardChoosen = 0;
			}
			this.nbrRounds++;
		}
	}
	
	public void setup() throws InvalidModeException,InvalidNbrOfPlayersException {
		if( (this.mode!=null)&&((this.nbrIA+this.nbrJoueurs)>=2) ) {
			this.hasStarted = true;
			this.setChanged();
			this.notifyObservers(this.hasStarted);
			
			if(this.mode != Mode.Personnalisé) {
				this.deck.shuffleCards();
			}
			
			for(int i=0; i<(this.nbrJoueurs+this.nbrIA); i++) {
				this.joueurEnCours = (Joueur)this.playersQueue.peek();
				
				if((this.mode == Mode.Personnalisé)&&(!this.joueurEnCours.getIA())) {
					while(this.joueurEnCours.getVictory()==null) {
						this.joueurEnCours.setVictory();
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else if(this.mode == Mode.Classique) {
					this.joueurEnCours.setVictory();
				}
				else if(this.mode == Mode.Avancé) {
					for(int j=0; j<3; j++) {
						try {
							this.joueurEnCours.piocher();
						} catch (InvalidPlayerActionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoCardsAvailableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						this.joueurEnCours.resetTurn();
					}
				}
				
				this.playersQueue.add(this.playersQueue.poll());
			}
			
			if(this.mode == Mode.Personnalisé) {
				this.hasStarted = false;
				this.setChanged();
				this.notifyObservers(this.hasStarted);
				this.deck.shuffleCards();
				if(this.nbrIA>0) {
					for(int i=0; i<(this.nbrJoueurs+this.nbrIA); i++) {
						this.joueurEnCours = (Joueur)this.playersQueue.peek();
						
						if(this.joueurEnCours.getIA()) {
							this.joueurEnCours.setVictory();
						}
						this.playersQueue.add(this.playersQueue.poll());
					}
				}
			}
			
			try {
				this.hiddenCard = this.deck.giveCard();
			} catch (NoCardsAvailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//début de la partie :
			while(!checkEndGame()) {
				tourDeJeu();
			}
			if(checkEndGame()) {
				comptagePoints();
			}
		}
		else if(this.mode==null){
			throw new InvalidModeException("Mode non choisi");
		}
		else if((this.nbrIA+this.nbrJoueurs)<2){
			throw new InvalidNbrOfPlayersException("Nbr joueurs actuels <2, deux joueurs minimum sont nécessaires pour lancer la partie");
		}
	}
	
	public void tourDeJeu() {
		this.joueurEnCours = (Joueur)this.playersQueue.peek();
		this.setChanged();
		this.notifyObservers(this.joueurEnCours);
		
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
			if(this.mode != Mode.Avancé && (!this.deck.isDeckEmpty())) {
				try {
					this.joueurEnCours.piocher();
				} catch (InvalidPlayerActionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoCardsAvailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			IA IAEnCours = (IA)joueurEnCours;
			IAEnCours.jouer();
			if( (this.mode == Mode.Avancé) && (!this.deck.isDeckEmpty()) ) {
				try {
					joueurEnCours.piocher();
				} catch (InvalidPlayerActionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoCardsAvailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		this.playersQueue.add(this.playersQueue.poll());
		this.joueurEnCours.resetTurn();
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
	
	public void comptagePoints() {
		Visitor visitor1 = new ScoreBodyVisitor();
		Visitor visitor2 = new ScoreColorVisitor();
		Visitor visitor3 = new ScoreShapeVisitor();
		int scoreFinal = 0;
		int scorePremier = -1;
		for(int i=0; i<(this.nbrIA+this.nbrJoueurs); i++) {
			Joueur joueur = (Joueur)this.playersQueue.peek();
			if(this.mode == Mode.Avancé) {
				joueur.setVictory();
			}
			scoreFinal = scoreFinal + joueur.accept(visitor1, this.plateau.accept(visitor1));
			scoreFinal = scoreFinal + joueur.accept(visitor2, this.plateau.accept(visitor1));
			scoreFinal = scoreFinal + joueur.accept(visitor3, this.plateau.accept(visitor1));
			scoreFinal = scoreFinal + joueur.getScore();
			joueur.setScore(scoreFinal);
			if(scoreFinal>scorePremier) {
				scorePremier = scoreFinal;
				this.premier = joueur.getName();
			}
			this.playersQueue.add(this.playersQueue.poll());
			scoreFinal = 0;
		}
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
	
	public boolean getHasStarted() {
		return this.hasStarted;
	}
	
	public void addVueTexteObserver(VueTexte vueTexte) {
		this.vueTexte = vueTexte;
		this.addObserver(vueTexte);
		this.plateau.addObserver(vueTexte);
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
	
	public int getNbrRounds() {
		return this.nbrRounds;
	}
	
	public synchronized String getPremier() {
		try {
			this.wait(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.premier;
	}
}
