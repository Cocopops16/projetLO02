package projetLO02;

import java.util.Map;
import java.util.Observable;

import View.MonInterfacePlateau;
import View.VueTexte;

/**
 * Joueur appartenant � une classe jeu instanci�e (composition) <br/>
 * Cette classe est aussi une classe {@link Observable} (patron de conception {@link Observer}/{@link Observable}), permettant aux diff�rentes vues de se mettre � jour.
 * 
 * @see Observable
 * @see View.MonInterfacePlateau
 * @see View.VueTexte
 * @see Hand
 * @see Jeu
 * 
 * @author Corentin R�ault
 * @version 1.0
 */
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
	
	/**
	 * Pioche une carte
	 * @throws InvalidPlayerActionException si le joueur a d�j� pioch� durant son tour de jeu
	 * @throws NoCardsAvailableException si il n'y a plus de cartes dans le {@link Deck}
	 * 
	 * @see Deck
	 * @see Hand
	 */
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
	
	
	/**
	 * Fixe une carte � l'attribu {@link Joueur#victoryCard} selon le mode de jeu
	 * 
	 * @see Mode
	 * @see Jeu#setup()
	 */
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
	
	/**
	 * Placement d'une carte provenant de la main du {@link Joueur} vers une position du plateau donn�e. <br/>
	 * Pour que le placement aboutisse, il faudra obligatoirement que le joueur n'ait pas d�j� pos� durant son tour de jeu, que la position choisie ne soit pas d�j� occup�e, que le plateau n'ait pas atteint sa longueur ou largeur maximale en cas de d�placement de celui-ci, et qu'il y ait des cartes autour de la destination. <br/>
	 * (Si le {@link Plateau} est vide, on peut exceptionnelement placer o� l'on veut)
	 * 
	 * @param card 		La carte � jouer
	 * @param colonne	La colonne o� placer
	 * @param ligne		La ligne o� placer
	 * @throws InvalidPlayerActionException si le joueur effectue une op�ration interdite
	 * @throws InvalidChosenCardException si la carte choisie est d�finie comme {@code null}
	 * 
	 * @see Plateau
	 * @see Jeu#tourDeJeu()
	 */
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
	
	/**
	 * Deplacement d'une carte provenant du {@link Plateau} vers une autre position du {@link Plateau}. <br/>
	 * Pour d�placer il faut obligatoirement que le joueur n'ait pas d�j� d�plac� une carte durant son tour de jeu, que la premi�re position indiqu�e soit occup�e par une carte, et que la seconde position soit : <br/>
	 * - occup�e, dans ce cas on intervertit juste les deux cartes <br/>
	 * - non occup�e mais ayant au moins une position voisine occup�e (utilisation de l'algorithme de placement) <br/>
	 * 
	 * @param colonne1	colonne de la carte � d�placer
	 * @param ligne1	ligne de la carte � deplacer
	 * @param colonne2	colonne de destination
	 * @param ligne2	ligne de destination
	 * @throws InvalidPlayerActionException si le joueur effectue une op�ration interdite
	 * 
	 * @see Plateau
	 * @see Jeu#tourDeJeu()
	 * @see Joueur#placer(Card, char, int)
	 */
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
	
	/**
	 * Remise � zero des actions du tour
	 * 
	 * @see Joueur#placer(Card, char, int)
	 * @see Joueur#deplacer(char, int, char, int)
	 * @see Joueur#piocher()
	 */
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