package projetLO02;

import java.util.Map;
import java.util.Observable;
import java.util.Scanner;

import View.MonInterfacePlateau;
import View.VueTexte;

@SuppressWarnings("deprecation")
public class Joueur extends Observable {
	private boolean isIA;
	protected String name;
	protected Card victoryCard;
	private boolean aDejaPioche;
	private boolean aDejaPlace;
	private boolean aDejaDeplace;
	protected Hand myHand;
	protected Jeu jeu;
	private int score;
	private static final Scanner monClavier = new Scanner(System.in);
	
	public Joueur(String name, Jeu jeuEnCours, MonInterfacePlateau monInterface, VueTexte vueTexte){
		this.name = name;
		this.jeu = jeuEnCours;
		this.myHand = new Hand();
		this.aDejaPioche = false;
		this.aDejaPlace = false;
		this.aDejaDeplace = false;
		this.victoryCard = null;
		this.score = 0;
		
		addObserver(monInterface);
		addObserver(vueTexte);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setIA(boolean etat) {
		this.isIA = etat;
	}
	
	public boolean getIA() {
		return this.isIA;
	}
	
	public void piocher() throws InvalidPlayerActionException {
		if(!this.aDejaPioche) {
			this.aDejaPioche = true;
			Card card = jeu.getDeck().giveCard();
			System.out.println("Vous piochez : " + card.toString());
			this.myHand.addCardToHand(card);
			setChanged();
			notifyObservers(this);
		}
		else {
			throw new InvalidPlayerActionException("Vous avez déjà pioché");
		}
	}
	
	public void setVictory() {
		if(this.jeu.getMode() == Mode.Classique) {
			this.victoryCard = this.jeu.getDeck().giveCard();
			setChanged();
			notifyObservers(this);
		}
		else if(this.jeu.getMode() == Mode.Avancé) {
			this.victoryCard = this.myHand.getCard(0);
			this.myHand.removeCardFromHand(this.victoryCard);
			setChanged();
			notifyObservers(this);
		}
		else if((this.jeu.getMode() == Mode.Personnalisé)&&(!this.isIA)) {
			this.victoryCard = this.jeu.getDeck().modePerso(this.victoryCard);
			setChanged();
			notifyObservers(this);
		}
		else if((this.jeu.getMode() == Mode.Personnalisé)&&this.isIA) {
			this.victoryCard = this.jeu.getDeck().giveCard();
			setChanged();
			notifyObservers(this);
		}
	}
	
	public Card getVictory() {
		return this.victoryCard;
	}
	
	public Card chooseCardToPlay() {
		System.out.println("Veuillez choisir le numéro d'une carte :");
		System.out.println(myHand.toString());
		int numCard = monClavier.nextInt();
		numCard = numCard-1;
		return this.myHand.getCard(numCard);
	}
	
	public void placer(Card card, char colonne, int ligne) throws InvalidPlayerActionException {
		if(!this.aDejaPlace) {
			this.aDejaPlace = true;
			if(this.jeu.getPlateau().getFirstCard()) {
				if(!this.jeu.getPlateau().isPosAlreadyTaken(colonne, ligne)) {
					if( (!this.jeu.getPlateau().checkMaxXReached(colonne)) && (!this.jeu.getPlateau().checkMaxYReached(ligne)) ) {
						if(this.jeu.getPlateau().checkSiCartesAutour(colonne, ligne)) {
							this.jeu.getPlateau().setCard(card, colonne, ligne);
							this.myHand.removeCardFromHand(card);
							setChanged();
							notifyObservers(this);
						}
						else {
							this.aDejaPlace = false;
							//System.out.println("Pas de cartes autour ("+colonne+";"+ligne+")");
						}
					}
					else {
						this.aDejaPlace = false;
						//System.out.println("maximum du plateau atteint pour cette position : ("+colonne+";"+ligne+")");
					}
				}
				else {
					this.aDejaPlace = false;
					//System.out.println("Position : ("+colonne+";"+ligne+") déjà occupé");
				}
			}
			else {
				this.jeu.getPlateau().setFirstCard();
				this.jeu.getPlateau().setCard(card, colonne, ligne);
				System.out.println("Carte placée en ("+colonne+";"+ligne+") par "+this.name);
				this.myHand.removeCardFromHand(card);
				setChanged();
				notifyObservers(this);
			}
		}
		else {
			throw new InvalidPlayerActionException("Vous avez déjà placé une carte sur le plateau");
		}
	}
	
	public void deplacer(char colonne1, int ligne1, char colonne2, int ligne2) throws InvalidPlayerActionException {
		if(!this.aDejaDeplace) {
			if(this.jeu.getPlateau().isPosAlreadyTaken(colonne1, ligne1)) {
				if(this.jeu.getPlateau().isPosAlreadyTaken(colonne2, ligne2)) {
					Card card1 = this.jeu.getPlateau().getCard(colonne1, ligne1);
					Card card2 = this.jeu.getPlateau().getCard(colonne2, ligne2);
					this.jeu.getPlateau().setCard(card2, colonne1, ligne1);
					this.jeu.getPlateau().setCard(card1, colonne2, ligne2);
				}
				else if(this.jeu.getPlateau().checkSiCartesAutour(colonne2, ligne2)){
					try {
						placer(this.jeu.getPlateau().getCard(colonne1, ligne1), colonne2, ligne2);
						this.aDejaPlace = false;
					} catch (InvalidPlayerActionException e) {
						this.aDejaPlace = false;
						placer(this.jeu.getPlateau().getCard(colonne1, ligne1), colonne2, ligne2);
						this.aDejaPlace = true;
					}
					this.jeu.getPlateau().removeCard(colonne1, ligne1);
				}
				this.aDejaDeplace = true;
			}
		}
		else {
			throw new InvalidPlayerActionException("Vous avez déjà déplacé une carte du plateau");
		}
	}
	
	public boolean aPioche() {
		return aDejaPioche;
	}
	
	public boolean aDeplace() {
		return aDejaDeplace;
	}
	
	public boolean aPlace() {
		return aDejaPlace;
	}
	
	public Hand getHand() {
		return this.myHand;
	}
	
	public void resetTurn() {
		this.aDejaDeplace = false;
		this.aDejaPioche = false;
		this.aDejaPlace = false;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void resetVictory() {
		this.victoryCard = null;
	}
	
	public int accept(Visitor visitor, Map<String, Object> positions) {
        return visitor.visit(positions, this.victoryCard);
    }
}