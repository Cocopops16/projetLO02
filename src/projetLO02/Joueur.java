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
	
	public void piocher() throws InvalidPlayerActionException, NoCardsAvailableException {
		if(!this.aDejaPioche) {
			this.aDejaPioche = true;
			Card card = jeu.getDeck().giveCard();
			this.myHand.addCardToHand(card);
			setChanged();
			notifyObservers(this);
		}
		else {
			throw new InvalidPlayerActionException("Vous avez d�j� pioch�");
		}
	}
	
	public void setVictory() {
		if(this.jeu.getMode() == Mode.Classique) {
			try {
				this.victoryCard = this.jeu.getDeck().giveCard();
			} catch (NoCardsAvailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setChanged();
			notifyObservers(this);
		}
		else if(this.jeu.getMode() == Mode.Avanc�) {
			this.victoryCard = this.myHand.getCard(0);
			this.myHand.removeCardFromHand(this.victoryCard);
			setChanged();
			notifyObservers(this);
		}
		else if((this.jeu.getMode() == Mode.Personnalis�)&&(!this.isIA)) {
			this.victoryCard = this.jeu.getDeck().modePerso(this.victoryCard);
			setChanged();
			notifyObservers(this);
		}
		else if((this.jeu.getMode() == Mode.Personnalis�)&&this.isIA) {
			try {
				this.victoryCard = this.jeu.getDeck().giveCard();
			} catch (NoCardsAvailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setChanged();
			notifyObservers(this);
		}
	}
	
	public Card getVictory() {
		return this.victoryCard;
	}
	
	public Card chooseCardToPlay() {
		System.out.println("Veuillez choisir le num�ro d'une carte :");
		System.out.println(myHand.toString());
		int numCard = monClavier.nextInt();
		numCard = numCard-1;
		return this.myHand.getCard(numCard);
	}
	
	public void placer(Card card, char colonne, int ligne) throws InvalidPlayerActionException, InvalidChosenCardException {
		if(!this.aDejaPlace) {
			if(card!=null) {
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
								throw new InvalidPlayerActionException("Pas de cartes autour ("+colonne+";"+ligne+")");
							}
						}
						else {
							this.aDejaPlace = false;
							throw new InvalidPlayerActionException("maximum du plateau atteint pour cette position : ("+colonne+";"+ligne+")");
						}
					}
					else {
						this.aDejaPlace = false;
						throw new InvalidPlayerActionException("Position : ("+colonne+";"+ligne+") d�j� occup�e");
					}
				}
				else {
					this.jeu.getPlateau().setFirstCard();
					this.jeu.getPlateau().setCard(card, colonne, ligne);
					System.out.println("Carte plac�e en ("+colonne+";"+ligne+") par "+this.name);
					this.myHand.removeCardFromHand(card);
					setChanged();
					notifyObservers(this);
				}
			}
			else {
				this.aDejaPlace = false;
				throw new InvalidChosenCardException("Pas de carte choisie");
			}
		}
		else {
			throw new InvalidPlayerActionException("Vous avez d�j� plac� une carte sur le plateau");
		}
	}
	
	public void deplacer(char colonne1, int ligne1, char colonne2, int ligne2) throws InvalidPlayerActionException {
		if(!this.aDejaDeplace) {
			boolean removeCard = true;
			if(this.jeu.getPlateau().isPosAlreadyTaken(colonne1, ligne1)) {
				if(this.jeu.getPlateau().isPosAlreadyTaken(colonne2, ligne2)) {
					Card card1 = this.jeu.getPlateau().getCard(colonne1, ligne1);
					Card card2 = this.jeu.getPlateau().getCard(colonne2, ligne2);
					this.jeu.getPlateau().setCard(card2, colonne1, ligne1);
					this.jeu.getPlateau().setCard(card1, colonne2, ligne2);
					this.aDejaDeplace = true;
				}
				else if(this.jeu.getPlateau().checkSiCartesAutour(colonne2, ligne2)) {
					boolean saveADejaPlace = this.aDejaPlace;
					try {
						placer(this.jeu.getPlateau().getCard(colonne1, ligne1), colonne2, ligne2);
						this.aDejaPlace = false;
					} catch (InvalidPlayerActionException e) {
						if(e.getMessage().equals("Vous avez d�j� plac� une carte sur le plateau")) {
							this.aDejaPlace = false;
							try {
								placer(this.jeu.getPlateau().getCard(colonne1, ligne1), colonne2, ligne2);
							} catch (InvalidPlayerActionException e1) {
								this.aDejaPlace = saveADejaPlace;
								removeCard = false;
								throw new InvalidPlayerActionException(e1.getMessage());
							} catch (InvalidChosenCardException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							this.aDejaPlace = true;
						}
						else {
							this.aDejaPlace = saveADejaPlace;
							removeCard = false;
							throw new InvalidPlayerActionException(e.getMessage());
						}
					} catch (InvalidChosenCardException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(removeCard) {
						if(colonne2=='<') {
							colonne1 = (char) ((int)colonne1+1);
							this.jeu.getPlateau().removeCard(colonne1, ligne1);
						}
						else if(colonne2=='>') {
							colonne1 = (char) ((int)colonne1-1);
							this.jeu.getPlateau().removeCard(colonne1, ligne1);
						}
						else if(ligne2==0) {
							ligne1 = ligne1+1;
							this.jeu.getPlateau().removeCard(colonne1, ligne1);
						}
						else if(ligne2==4) {
							ligne1 = ligne1-1;
							this.jeu.getPlateau().removeCard(colonne1, ligne1);
						}
						else {
							this.jeu.getPlateau().removeCard(colonne1, ligne1);
						}
					}
					
				}
				else {
					throw new InvalidPlayerActionException("Pas de cartes autour ("+colonne2+";"+ligne2+")");
				}
			}
			else {
				throw new InvalidPlayerActionException("Position : ("+colonne1+";"+ligne1+") non occup�e");
			}
		}
		else {
			throw new InvalidPlayerActionException("Vous avez d�j� d�plac� une carte du plateau");
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