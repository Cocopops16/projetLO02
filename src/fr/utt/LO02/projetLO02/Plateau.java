package fr.utt.LO02.projetLO02;

import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

import View.MonInterfacePlateau;

/**
 * Plateau de jeu permettant de poser des cartes sur une grille virtuelle, avec un traitement de type �chiquier (colonnes en lettres et lignes en chiffres). <br/>
 * Ce plateau est dynamique et permet le d�calage des cartes vers le haut / le bas / la gauche / la droite. <br/>
 * Un plateau de jeu est li� par une relation de compostion avec le {@link Jeu}. <br/>
 * Cette classe est aussi une classe {@link Observable} (patron de conception {@link Observer}/{@link Observable}), permettant aux diff�rentes vues de se mettre � jour.
 * 
 * @see Observable
 * @see View.MonInterfacePlateau
 * @see View.VueTexte
 * @see Jeu
 * 
 * @author Corentin R�ault
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class Plateau extends Observable {
	private Map<String, Object> positions;
	private int xMax, yMax;
	private boolean firstCard;
	
	public Plateau(int xMax, int yMax, MonInterfacePlateau monInterface) {
		this.xMax = xMax;
		this.yMax = yMax;
		this.firstCard = false;
		this.positions = new TreeMap<String, Object>();
		addObserver(monInterface);
	}
	
	public void setFirstCard() {
		this.firstCard = true;
	}
	public boolean getFirstCard() {
		return this.firstCard;
	}
	
	public int getXMax() {
		return this.xMax;
	}
	
	public int getYMax() {
		return this.yMax;
	}
	
	/**
	 * Test si toutes les colonnes sont occup�es pour savoir si un d�placement lat�ral de toutes la cartes est possible
	 * 
	 * @param colonne	direction d�sir�e du placement de la nouvelle carte :<br/>
	 * 					- '<' placement � gauche, d�calage � droite <br/>
	 * 					- '>' l'inverse
	 * @return true si le maximum est atteint - false sinon
	 */
	public boolean checkMaxXReached(char colonne) {
		if(colonne=='<') {
			for(int j=1; j<=this.yMax; j++) {
				String key = ((char)(65+this.xMax-1))+Integer.toString(j);
				if(this.positions.containsKey(key)) {
					return true;
				}
			}
		}
		else if(colonne=='>') {
			for(int j=1; j<=this.yMax; j++) {
				String key = 'A'+Integer.toString(j);
				if(this.positions.containsKey(key)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Test si toutes les lignes sont occup�es pour savoir si un d�placement horizontal de toutes la cartes est possible
	 * 
	 * @param colonne	direction d�sir�e du placement de la nouvelle carte :<br/>
	 * 					- 0, placement en bas, d�calage vers le haut <br/>
	 * 					- 4, l'inverse
	 * @return true si le maximum est atteint - false sinon
	 */
	public boolean checkMaxYReached(int ligne) {
		if(ligne==0) {
			for(int i=0; i<this.xMax; i++) {
				String key = ((char)(65+i))+Integer.toString(this.yMax);
				if(this.positions.containsKey(key)) {
					return true;
				}
			}
		}
		else if(ligne==this.yMax+1) {
			for(int i=0; i<this.xMax; i++) {
				String key = ((char)(65+i))+Integer.toString(1);
				if(this.positions.containsKey(key)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Test si il y a des cartes autour de la position cible
	 * 
	 * @param colonne	colonne de la position cible
	 * @param ligne		ligne de la position cible
	 * @return true si il y a au moins une carte autour - false sinon
	 */
	public boolean checkSiCartesAutour(char colonne, int ligne) {
		String key;
		
		if(colonne=='A') {			
			key = ((char)((int)(colonne)+1))+Integer.toString(ligne);
			if(this.positions.containsKey(key)) return true;
		}
		else if(colonne==((char)(65+this.xMax-1)) ) {
			key = ((char)((int)(colonne)-1))+Integer.toString(ligne);
			if(this.positions.containsKey(key)) return true;
		}
		else if(colonne=='<') {
			key = 'A'+Integer.toString(ligne);
			if(this.positions.containsKey(key)) return true;
		}
		else if(colonne=='>') {
			key = ((char)(65+this.xMax-1))+Integer.toString(ligne);
			if(this.positions.containsKey(key)) return true;
		}
		else {
			key = ((char)((int)(colonne)-1))+Integer.toString(ligne);
			if(this.positions.containsKey(key)) return true;
			
			key = ((char)((int)(colonne)+1))+Integer.toString(ligne);
			if(this.positions.containsKey(key)) return true;
		}
		
		if(ligne==1) {
			key = colonne+Integer.toString(ligne+1);
			if(this.positions.containsKey(key)) return true;
		}
		else if(ligne==this.yMax) {
			key = colonne+Integer.toString(ligne-1);
			if(this.positions.containsKey(key)) return true;
		}
		else if(ligne==0) {
			key = colonne+Integer.toString(ligne+1);
			if(this.positions.containsKey(key)) return true;
		}
		else if(ligne==this.yMax+1) {
			key = colonne+Integer.toString(ligne-1);
			if(this.positions.containsKey(key)) return true;
		}
		else {
			key = colonne+Integer.toString(ligne-1);
			if(this.positions.containsKey(key)) return true;
			
			key = colonne+Integer.toString(ligne+1);
			if(this.positions.containsKey(key)) return true;
		}
		return false;
	}
	
	/**
	 * D�calage du plateau d�pendant des param�tres donn�s, puis on place la carte � la position demand�e
	 * @param card 		carte � placer
	 * @param colonne	colonne de destination
	 * @param ligne		ligne de destionation
	 * 
	 * @see Plateau#setCard(Card, char, int)
	 */
	public void movePositions(Card card, char colonne, int ligne) {
		if(colonne=='<') {
			for(int j=1; j<=this.yMax; j++) {
				for(int i=(this.xMax-2); i>=0; i--) {
					String key = ((char)(65+i))+Integer.toString(j);
					String key2 = ((char)(65+i+1))+Integer.toString(j);
					if(this.positions.containsKey(key)) {
						this.positions.put(key2, this.positions.get(key));
						if(i==0) {
							this.positions.remove(key);
						}
					}
					else this.positions.remove(key2);
				}
			}
			setCard(card, 'A', ligne);
		}
		else if(colonne=='>') {
			for(int j=1; j<=this.yMax; j++) {
				for(int i=1; i<this.xMax; i++) {
					String key = ((char)(65+i))+Integer.toString(j);
					String key2 = ((char)(65+i-1))+Integer.toString(j);
					if(this.positions.containsKey(key)) {
						this.positions.put(key2, this.positions.get(key));
						if(i==(this.xMax-1)) {
							this.positions.remove(key);
						}
					}
					else this.positions.remove(key2);
				}
			}
			setCard(card, (char)(65+this.xMax-1), ligne);
		}
		else if(ligne==0) {
			for(int i=0; i<this.xMax; i++) {
				for(int j=this.yMax-1; j>0; j--) {
					String key = ((char)(65+i))+Integer.toString(j);
					String key2 = ((char)(65+i))+Integer.toString(j+1);
					if(this.positions.containsKey(key)) {
						this.positions.put(key2, this.positions.get(key));
						if(j==1) {
							this.positions.remove(key);
						}
					}
					else this.positions.remove(key2);
				}
			}
			setCard(card, colonne, 1);
		}
		else if(ligne==this.yMax+1) {
			for(int i=0; i<this.xMax; i++) {
				for(int j=2; j<=this.yMax; j++) {
					String key = ((char)(65+i))+Integer.toString(j);
					String key2 = ((char)(65+i))+Integer.toString(j-1);
					if(this.positions.containsKey(key)) {
						this.positions.put(key2, this.positions.get(key));
						if(j==this.yMax) {
							this.positions.remove(key);
						}
					}
					else this.positions.remove(key2);
				}
			}
			setCard(card, colonne, 3);
		}
	}
	
	/**
	 * Ajout d'une carte au plateau
	 * 
	 * @param card 		carte � placer
	 * @param colonne	colonne de destination
	 * @param ligne		ligne de destionation
	 * 
	 * @see Plateau#movePositions(Card, char, int)
	 * @see Joueur#placer(Card, char, int)
	 * @see Joueur#deplacer(char, int, char, int)
	 */
	public void setCard(Card card, char colonne, int ligne) {
		if((colonne=='<')||(colonne=='>')||(ligne==this.yMax+1)||(ligne==0)) {
			movePositions(card, colonne, ligne);
		}
		else {
			String key = colonne+Integer.toString(ligne);
			
			if(isPosAlreadyTaken(colonne, ligne)) {
				this.positions.remove(key);
			}
			this.positions.put(key, card);
		}
		this.setChanged();
		this.notifyObservers(this.positions);
	}
	
	/**
	 * Donne la carte correspondant � une position donn�e
	 */
	public Card getCard(char colonne, int ligne) {
		String key = colonne+Integer.toString(ligne);
		return (Card)this.positions.get(key);
	}
	
	/**
	 * Test si une position du {@link Plateau} est occup�e
	 * 
	 * @param colonne	colonne de la position � tester
	 * @param ligne		ligne de la position � tester
	 * @return true si la position est occup�e - false sinon
	 */
	public boolean isPosAlreadyTaken(char colonne, int ligne) {
		if((colonne=='<')||(colonne=='>')||(ligne==this.yMax+1)||(ligne==0)) {
			return false;
		}
		String key = colonne+Integer.toString(ligne);
		return this.positions.containsKey(key);
	}
	
	/**
	 * Supprime une carte du plateau correspondant � une position donn�e
	 */
	public void removeCard(char colonne, int ligne) {
		String key = colonne+Integer.toString(ligne);
		this.positions.remove(key);
		this.setChanged();
		this.notifyObservers(this.positions);
	}
	
	public Map<String, Object> getPositions() {
		return this.positions;
	}
	
	public Map<String, Object> accept(Visitor visitor) {
        return visitor.visit(this.positions);
    }
	
	public String toString() {
		String affichageCartes = new String();
		for(int j=this.yMax; j>0; j--) {
			for(int i=0; i<this.xMax; i++) {
				String key = ((char)(65+i))+Integer.toString(j);
				if(positions.containsKey(key)) {
					affichageCartes = affichageCartes + " " + key + ":" + ( (Card)(this.positions.get(key)) ).toString();
				}
				else {
					affichageCartes = affichageCartes + " " + key + ":" + "noCard";
				}
				if(i==(this.xMax-1)) affichageCartes = affichageCartes + "\n";
			}
		}
		return affichageCartes;
	}
}
