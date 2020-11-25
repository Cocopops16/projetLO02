package projetLO02;

public class IA extends Joueur{
	private boolean isIA;
	private String keyOuPlacer, keyOuDeplacer1, keyOuDeplacer2, Strategy;
	Card cardToPlay;
	
	public IA(String name, Jeu jeuEnCours) {
		super(name, jeuEnCours);
		this.keyOuPlacer = new String();
		this.keyOuDeplacer1 = new String();
		this.keyOuDeplacer2 = new String();
		this.Strategy = new String();
	}
	
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
		
		for(int i=0; i<3; i++) {
			if(this.myHand.getCard(i).getType1()==this.victoryCard.getType1()) {
				nbrTypeCommuns = nbrTypeCommuns++;
				type1 = true;
			}
			else if(this.myHand.getCard(i).getType2()==this.victoryCard.getType2()) {
				nbrTypeCommuns = nbrTypeCommuns++;
				type2 = true;
			}
			else if(this.myHand.getCard(i).getType3()==this.victoryCard.getType3()) {
				nbrTypeCommuns = nbrTypeCommuns++;
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
		else this.Strategy = "random";
		
		return this.myHand.getCard(numCard);
	}
	
	public Strategy chooseStrategy() {
		if(jeu.getMode()==Mode.Avancé) {
			this.cardToPlay = chooseCardToPlay();
		}
		else {
			this.cardToPlay = this.myHand.getCard(0);
		}
		
		if(this.Strategy=="color") {
			return new ColorStrategy();
		}
		else if(this.Strategy=="body") {
			return new BodyStrategy();
		}
		else if(this.Strategy=="shape") {
			return new ShapeStrategy();
		}
		else return new RandomStrategy();
	}
	
	public void jouer() {
		Strategy strategy = chooseStrategy();
		if(this.jeu.getPlateau().getFirstCard()) {
			this.keyOuPlacer = strategy.searchBestPosition(this.jeu.getPlateau().getPositions(), this.victoryCard);
			if(this.keyOuPlacer == "0") {
				Strategy randomStrategy = new RandomStrategy();
				this.keyOuPlacer = randomStrategy.searchBestPosition(this.jeu.getPlateau().getPositions(), this.victoryCard);
			}
			placer(this.cardToPlay, this.keyOuPlacer.charAt(0), Character.getNumericValue(this.keyOuPlacer.charAt(1)));
		}
		else placer(this.cardToPlay, 'C', 2);
	}
	
}
