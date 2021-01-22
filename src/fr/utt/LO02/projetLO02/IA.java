package fr.utt.LO02.projetLO02;

import View.MonInterfacePlateau;
import View.VueTexte;

/**
 * {@link IA} h�ritant de la classe {@link Joueur}, permettant d'automatiser le tour de jeu (sp�cialisation) en conservant les m�thodes {@link Joueur#placer(Card, char, int)} et {@link Joueur#deplacer(char, int, char, int)}
 * 
 * @see Joueur
 * @see Hand
 * @see Jeu
 * 
 * @author Corentin R�ault
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
	 * Choix de la carte � jouer (en mode avanc�) : <br/>
	 * Compare les cartes de la main � la victoryCard pour d�finir la carte � jouer, et fixer la variable {@link IA#Strategy} en fonction des types communs entre la carte choisie et la victoryCard
	 * @return la carte qui sera plac�e
	 * 
	 * @see IA#chooseStrategy()
	 * @see Hand
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
	
	/**
	 * Choix de la strat�gie � employer en fonction de l'�tat de la variable {@link IA#Strategy}
	 * @return un objet de type {@link Strategy}
	 * 
	 * @see Strategy
	 * @see BodyStrategy
	 * @see ColorStrategy
	 * @see ShapeStrategy
	 * @see IA#chooseCardToPlay()
	 * @see IA#placer()
	 */
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
	
	/**
	 * Permet de choisir une victoryCard en mode avanc� pour le tour de jeu (et ainsi d�finir une strat�gie gobale pour le placement/d�placement) : <br/>
	 * Choix en fonction de la carte de la main qui permettra d'obtenir un meilleur score lors du comptage des points <br/>
	 * (Usage du patron de conception Visitor)
	 * 
	 * @see Visitor
	 * @see ScoreBodyVisitor
	 * @see ScoreColorVisitor
	 * @see ScoreShapeVisitor
	 * @see Hand
	 * @see IA#jouer()
	 */
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
	
	
	/**
	 * Essai de diff�rentes strat�gies pour placer la carte la plus rentable de notre main en fonction des possibilit�es du plateau (gr�ce � {@link IA#chooseStrategy()} et {@link IA#chooseCardToPlay()}). <br/>
	 * Premi�re strat�gie utilis�e issue du choix de la carte � jouer (corespondance avec la victoryCard), deuxi�me strat�gie, dite de blocage, pour emp�cher le joueur adverse de marquer des points, et enfin une strat�gie random pour placer tout de m�me une carte quand aucune solution ne fonctionne. <br/>
	 * (Usage du patron de conception Strategy)
	 * 
	 * @see Plateau
	 * @see IA#jouer()
	 * @see IA#chooseStrategy()
	 */
	public void placer() {
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
			System.out.println(this.name+" a utilis� la strategie : "+this.Strategy);
			try {
				placer(this.cardToPlay, this.keyOuPlacer.charAt(0), Character.getNumericValue(this.keyOuPlacer.charAt(1)));
			} catch (InvalidPlayerActionException | InvalidChosenCardException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else {
			try {
				placer(this.cardToPlay, 'C', 2);
			} catch (InvalidPlayerActionException | InvalidChosenCardException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Deplacement uniquement si cela est b�n�fique � l'{@link IA} : <br/>
	 * Essai de plusieures strat�gies successivement en fonction de la couleur, puis du remplissage et enfin de la forme de la carte.
	 * (Usage du patron de conception Strategy)
	 * 
	 * @see Strategy
	 * @see BodyStrategy
	 * @see ColorStrategy
	 * @see ShapeStrategy
	 * @see Plateau
	 */
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
	
	/**
	 * M�thode principale de l'IA permettant de la r�veiller et de lui faire jouer son tour
	 * (en mode avanc�, l'IA choisira une victoryCard temporaire durant son tour pour d�finir une strat�gie de jeu, et rendra la victoryCard � sa main en fin de tour)
	 * 
	 * @see Mode
	 * @see Jeu#tourDeJeu()
	 * @see IA#chooseVictory()
	 * @see IA#placer()
	 * @see IA#deplacer()
	 */
	public void jouer() {
		if(this.jeu.getMode()==Mode.Avanc�) {
			chooseVictory();
		}
		
		placer();
		deplacer();
		
		if(this.jeu.getMode()==Mode.Avanc�) {
			this.myHand.addCardToHand(this.victoryCard);
			this.victoryCard = null;
		}
	}
}
