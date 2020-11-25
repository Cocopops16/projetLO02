package projetLO02;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jeu jeu = new Jeu();
		jeu.start();
		while(!jeu.checkEndGame()) {
			jeu.tourDeJeu();
		}
		if(jeu.checkEndGame()) {
			jeu.comptagePoints();
			System.out.println("End of the game");
		}
		else System.out.println("Problème critique dans le déroulement de la partie");
	}

}
