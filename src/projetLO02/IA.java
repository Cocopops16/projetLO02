package projetLO02;

import View.MonInterfacePlateau;
import View.VueTexte;

/**
 * {@link IA} héritant de la classe {@link Joueur}, permettant d'automatiser le tour de jeu (spécialisation) en conservant les méthodes {@link Joueur#placer(Card, char, int)} et {@link Joueur#deplacer(char, int, char, int)}
 * 
 * @see Joueur
 * @see Hand
 * @see Jeu
 * 
 * @author Corentin Réault
 * @version 1.0
 */

public class IA extends Joueur{
	private boolean isIA;
	private String keyOuPlacer, keyOuDeplacer1, keyOuDeplacer2, Strategy;
	Card cardToPlay;
	
	public IA(String name, Jeu jeuEnCours, MonInterfacePlateau monInterface, VueTexte vueTexte) {
		super(name, jeuEnCours, monInterface, vueTexte);
		this.keyOuPlacer = new String();
		this.keyOuDeplacer1 = new String();
		this.keyOuDeplacer2 = new String();
		this.Strategy = new String();
	}
	
	/**
	 * Choix de la carte à jouer (en mode avancé)
	 * Compare les cartes de la main à la victoryCard pour définir 
	 */
	public Card chooseCardToPlay() {
		int numCard = 0;
		int nbrTypeCommunsBest = -1;
		int nbrTypeCommuns = 0;
		boolean type1 = false;
		boolean type2 = false;
		boolean type3 = false;
		boolean type1Best = false;
		boolean type2Best = false;
		boolean type3Best = false;
		
		for(int i=0; i<this.myHand.checkNombreCartes(); i++) {
			if(this.myHand.getCard(i).getType1()==this.victoryCard.getType1()) {
				nbrTypeCommuns = nbrTypeCommuns+1;
				type1 = true;
			}
			if(this.myHand.getCard(i).getType2()==this.victoryCard.getType2()) {
				nbrTypeCommuns = nbrTypeCommuns+1;
				type2 = true;
			}
			if(this.myHand.getCard(i).getType3()==this.victoryCard.getType3()) {
				nbrTypeCommuns = nbrTypeCommuns+1;
				type3 = true;
			}
			
			if(nbrTypeCommuns > nbrTypeCommunsBest) {
				nbrTypeCommunsBest = nbrTypeCommuns;
				numCard = i;
				type1Best = type1;
				type2Best = type2;
				type3Best = type3;
			}
			nbrTypeCommuns = 0;
			type1 = false;
			type2 = false;
			type3 = false;
		}
		
		if(nbrTypeCommunsBest>0) {
			if(type3Best) {
				this.Strategy = "color";
			}
			else if(type2Best) {
				this.Strategy = "body";
			}
			else if(type1Best) {
				this.Strategy = "shape";
			}
		}
		else this.Strategy = "obstruct";
		
		return this.myHand.getCard(numCard);
	}
	
	public Strategy chooseStrategy() {
		this.cardToPlay = chooseCardToPlay();
		
		if(this.Strategy=="color") {
			return new ColorStrategy();
		}
		else if(this.Strategy=="body") {
			return new BodyStrategy();
		}
		else if(this.Strategy=="shape") {
			return new ShapeStrategy();
		}
		else return new ObstructStrategy();
	}
	
	public void chooseVictory() {
		Visitor visitor1 = new ScoreBodyVisitor();
		Visitor visitor2 = new ScoreColorVisitor();
		Visitor visitor3 = new ScoreShapeVisitor();
		int scoreFinal = 0;
		int scorePremier = -1;
		Card bestVictoryCard = null;
		for(int i=0; i<(this.myHand.checkNombreCartes()); i++) {
			this.victoryCard = this.myHand.getCard(i);
			scoreFinal = scoreFinal + (accept(visitor1, this.jeu.getPlateau().accept(visitor1)));
			scoreFinal = scoreFinal + (accept(visitor2, this.jeu.getPlateau().accept(visitor1)));
			scoreFinal = scoreFinal + (accept(visitor3, this.jeu.getPlateau().accept(visitor1)));
			if(scoreFinal>scorePremier) {
				scorePremier = scoreFinal;
				bestVictoryCard = this.victoryCard;
			}
			scoreFinal = 0;
		}
		this.victoryCard = bestVictoryCard;
		this.myHand.removeCardFromHand(this.victoryCard);
	}
	
	public void deplacer() {
		Strategy strategy = new ColorStrategy();
		this.keyOuDeplacer1 = "1";
		this.keyOuDeplacer2 = "1";
		int i=0;
		char colonne1, colonne2;
		int ligne1, ligne2;
		boolean deplacementEffectue = false;
		
		while((this.keyOuDeplacer1 != "0")&&(this.keyOuDeplacer2 != "0")&&(!deplacementEffectue)) {
			this.keyOuDeplacer1 = strategy.searchPosDeplacement(jeu.getPlateau(), this.victoryCard, i);
			this.keyOuDeplacer2 = strategy.searchBestPosition(jeu.getPlateau(), this.victoryCard, this.keyOuDeplacer1);
			i++;
			if((this.keyOuDeplacer1 != "0")&&(this.keyOuDeplacer2 != "0")) {
				colonne1 = this.keyOuDeplacer1.charAt(0);
				ligne1 = Character.getNumericValue(this.keyOuDeplacer1.charAt(1));
				colonne2 = this.keyOuDeplacer2.charAt(0);
				ligne2 = Character.getNumericValue(this.keyOuDeplacer2.charAt(1));
				try {
					deplacer(colonne1, ligne1, colonne2, ligne2);
				} catch (InvalidPlayerActionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				deplacementEffectue = true;
			}
		}
		if(!deplacementEffectue) {
			strategy = new BodyStrategy();
			this.keyOuDeplacer1 = "1";
			this.keyOuDeplacer2 = "1";
			i=0;
			while((this.keyOuDeplacer1 != "0")&&(this.keyOuDeplacer2 != "0")&&(!deplacementEffectue)) {
				this.keyOuDeplacer1 = strategy.searchPosDeplacement(jeu.getPlateau(), this.victoryCard, i);
				this.keyOuDeplacer2 = strategy.searchBestPosition(jeu.getPlateau(), this.victoryCard, this.keyOuDeplacer1);
				i++;
				if((this.keyOuDeplacer1 != "0")&&(this.keyOuDeplacer2 != "0")) {
					colonne1 = this.keyOuDeplacer1.charAt(0);
					ligne1 = Character.getNumericValue(this.keyOuDeplacer1.charAt(1));
					colonne2 = this.keyOuDeplacer2.charAt(0);
					ligne2 = Character.getNumericValue(this.keyOuDeplacer2.charAt(1));
					try {
						deplacer(colonne1, ligne1, colonne2, ligne2);
					} catch (InvalidPlayerActionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					deplacementEffectue = true;
				}
			}
			if(!deplacementEffectue) {
				strategy = new ShapeStrategy();
				this.keyOuDeplacer1 = "1";
				this.keyOuDeplacer2 = "1";
				i=0;
				while((this.keyOuDeplacer1 != "0")&&(this.keyOuDeplacer2 != "0")&&(!deplacementEffectue)) {
					this.keyOuDeplacer1 = strategy.searchPosDeplacement(jeu.getPlateau(), this.victoryCard, i);
					this.keyOuDeplacer2 = strategy.searchBestPosition(jeu.getPlateau(), this.victoryCard, this.keyOuDeplacer1);
					i++;
					if((this.keyOuDeplacer1 != "0")&&(this.keyOuDeplacer2 != "0")) {
						colonne1 = this.keyOuDeplacer1.charAt(0);
						ligne1 = Character.getNumericValue(this.keyOuDeplacer1.charAt(1));
						colonne2 = this.keyOuDeplacer2.charAt(0);
						ligne2 = Character.getNumericValue(this.keyOuDeplacer2.charAt(1));
						try {
							deplacer(colonne1, ligne1, colonne2, ligne2);
						} catch (InvalidPlayerActionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						deplacementEffectue = true;
					}
				}
			}
		}
	}
	
	public void jouer() {
		if(this.jeu.getMode()==Mode.Avancé) {
			chooseVictory();
		}
		Strategy strategy = chooseStrategy();
		if(this.jeu.getPlateau().getFirstCard()) {
			this.keyOuPlacer = strategy.searchBestPosition(this.jeu.getPlateau(), this.victoryCard, "0");
			if(this.keyOuPlacer == "0") {
				Strategy obstructStrategy = new ObstructStrategy();
				this.keyOuPlacer = obstructStrategy.searchBestPosition(this.jeu.getPlateau(), this.victoryCard, "0");
			}
			if(this.keyOuPlacer == "1") {
				Strategy randomStrategy = new RandomStrategy();
				this.keyOuPlacer = randomStrategy.searchBestPosition(this.jeu.getPlateau(), this.victoryCard, "0");
			}
			System.out.println(this.name+" a utilisé la strategie : "+this.Strategy);
			try {
				placer(this.cardToPlay, this.keyOuPlacer.charAt(0), Character.getNumericValue(this.keyOuPlacer.charAt(1)));
			} catch (InvalidPlayerActionException | InvalidChosenCardException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			try {
				placer(this.cardToPlay, 'C', 2);
			} catch (InvalidPlayerActionException | InvalidChosenCardException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		deplacer();
		
		if(this.jeu.getMode()==Mode.Avancé) {
			this.myHand.addCardToHand(this.victoryCard);
			this.victoryCard = null;
		}
	}
}
