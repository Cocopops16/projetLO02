package projetLO02;

public class IA extends Joueur{
	private boolean isIA;

	public IA(String name, Jeu jeuEnCours) {
		super(name, jeuEnCours);
		this.isIA = true;
	}
	
	public Strategy chooseStrategy(){
		
	}
	
}
