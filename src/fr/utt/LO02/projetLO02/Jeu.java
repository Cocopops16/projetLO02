package fr.utt.LO02.projetLO02;

import java.util.LinkedList;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import View.MonInterfacePlateau;
import View.VueTexte;

/**
 * Classe principale du moteur de jeu, permettant de gérer tout le dérouelement d'une partie : de sa création, au comptage de points, en passer par la gestion des tours de jeu et des rounds.
 * Cette classe est aussi une classe {@link Observable} (patron de conception {@link Observer}/{@link Observable}), permettant aux différentes vues de se mettre à jour.
 * 
 * @see Observable
 * @see View.MonInterfacePlateau
 * @see View.VueTexte
 * @see Joueur
 * @see IA
 * 
 * @author Corentin Réault
 * @version 1.0
 */
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
	
	/**
	 * Ajout d'un joueur réel au jeu
	 * 
	 * @param name	Nom du joueur
	 * @throws InvalidNbrOfPlayersException	si le nombre maximal de 3 joueurs, réels et IA compris, est atteint.
	 */
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
	
	/**
	 * Ajout d'un joueur IA au jeu (noms prédéfinis)
	 * 
	 * @throws InvalidNbrOfPlayersException	si le nombre maximal de 3 joueurs, réels et IA compris, est atteint.
	 */
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
	
	/**
	 * Choix du mode de jeu : <br/>
	 * - 1= Classique <br/>
	 * - 2= Avancé (main de 3 cartes) <br/>
	 * - 3= personnalisé) <br/>
	 * 
	 * @param 	mode entier indiquant le mode de jeu
	 */
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
	
	/**
	 * Permet de mettre fin à l'attente d'un thread dans la méthode synchronized {@link Jeu#start()}
	 */
	public synchronized void setNextRound() {
		if(!this.nextRound) {
			this.nextRound = true;
			this.notifyAll();
		}
	}
	
	/**
	 * Permet de lancer la partie si les conditions initiales (mode et nbr de joueurs) sont réunies. <br/>
	 * (Gère aussi les différents rounds)
	 * 
	 * @throws InvalidModeException	si le mode n'a pas été défini
	 * @throws InvalidNbrOfPlayersException si le nombre de joueurs est inférieur à 2
	 * 
	 * @see Jeu#addIA()
	 * @see Jeu#addJoueur(String)
	 * @see Jeu#setMode(int)
	 */
	public synchronized void start() throws InvalidModeException, InvalidNbrOfPlayersException {
		if( (this.mode!=null)&&((this.nbrIA+this.nbrJoueurs)>=2) ) {
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
		else if(this.mode==null){
			throw new InvalidModeException("Mode non choisi");
		}
		else if((this.nbrIA+this.nbrJoueurs)<2){
			throw new InvalidNbrOfPlayersException("Nbr joueurs actuels <2, deux joueurs minimum sont nécessaires pour lancer la partie");
		}
	}
	
	/**
	 * Permet de mettre fin à l'attente d'un thread dans la méthode synchronized {@link Jeu#setup()}
	 */
	public synchronized void setPlayersVictory() {
		if(this.joueurEnCours.getVictory()!=null) {
			this.nbrVictoryCardChoosen++;
			this.notifyAll();
		}
	}
	
	/**
	 * Début d'un round, mélange des cartes, distribution des victoryCard ou des premières cartes aux joueurs en fonction du mode choisi. <br/>
	 * Gère le lancement des tours de jeu en testant si la fin du jeu est arrivée, et lance le comptage des points en fin de round.
	 * 
	 * @see Joueur
	 * @see IA
	 * @see Mode
	 * @see Jeu#start()
	 * @see Jeu#tourDeJeu()
	 * @see Jeu#checkEndGame()
	 * @see Jeu#comptagePoints()
	 */
	public void setup() {
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
	
	/**
	 * Permet de mettre fin à l'attente d'un thread dans la méthode {@link Jeu#tourDeJeu()}
	 */
	public synchronized void unlockJoueur() throws InvalidEndOfTurnException {
		Joueur joueurEnCours = (Joueur)this.playersQueue.peek();
		if(joueurEnCours.aPioche() && joueurEnCours.aPlace()) {
			this.notifyAll();
		}
		else {
			throw new InvalidEndOfTurnException("Fin de tour impossible, le joueur n'a pas effectué toutes ses actions");
		}
	}
	
	/**
	 * Lancement d'un tour de jeu : <br/>
	 * - si le joueur est un joueur réel, on met le thread en attente jusqu'à ce que le tour de jeu soit complet et que le thread soit réveillé <br/>
	 * - si le joueur est une IA on lui fait jouer son tour avec l'appel à la méthode {@link IA#jouer()}
	 * 
	 * @see Joueur
	 * @see IA#jouer()
	 * @see Jeu#setup()
	 */
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
	
	/**
	 * Test si le round est achevé
	 * @return true si le round est achevé - false sinon
	 * 
	 * @see Jeu#setup()
	 */
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
	
	
	/**
	 * Comptage des points grâce au patron de conception Visitor
	 * 
	 * @see Jeu#setup()
	 * @see Visitor
	 * @see ScoreBodyVisitor
	 * @see ScoreColorVisitor
	 * @see ScoreShapeVisitor
	 * @see Joueur#setScore(int)
	 */
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
	
	/**
	 * Donne le nom d'un joueur de la partie
	 * @param playerNum	Le numéro du joueur de 1 à 3 (suivant l'ordre d'inscription)
	 * @return le nom du joueur voulu
	 * 
	 * @see Joueur
	 */
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
	
	/**
	 * Donne le nom du premier de la partie
	 * @return le nom du premier
	 * 
	 * @see Controleur.ControleurFinPartie
	 */
	public synchronized String getPremier() {
		try {
			this.wait(10); //attente pour éviter la lecture de la ressource avant son écriture
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.premier;
	}
}
